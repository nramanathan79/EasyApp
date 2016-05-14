// Dropdown menu positioning to display fully in the screen
$('.dropdown-toggle').click(function() {
    var dropdownList = $('.dropdown-menu');
    var dropdownOffset = $(this).offset();
    var offsetLeft = dropdownOffset.left;
    var dropdownWidth = dropdownList.width();
    var docWidth = $(window).width();

    var isDropdownVisible = (offsetLeft + dropdownWidth <= docWidth);

    if (!isDropdownVisible) {
        $('.dropdown-menu').addClass('dropdown-menu-right');
    } else {
        $('.dropdown-menu').removeClass('dropdown-menu-right');
    }
});

if (dataTableApp) {
	dataTableApp.controller('columnFilterController', function($scope) {
		$scope.columnFilter = {"filterPresent": false, "uniqueValuesFilterPresent": false};

		$scope.sortUniqueValues = function() {
			if ($scope.columnFilter.uniqueValues && $scope.columnFilter.uniqueValues.length > 0) {
				$scope.columnFilter.uniqueValues.sort(function compare(a, b) {
					return a.value < b.value ? -1 : (a.value > b.value ? 1 : 0);
				});
			}
		};
		
		$scope.populateUniqueValue = function(value) {
			if (!$scope.columnFilter.uniqueValues) {
				$scope.columnFilter.uniqueValues = [];
			}
			
			var uniqueValue = {};
			uniqueValue.value = value;
			
			if (uniqueValue.value
				&& $scope.columnFilter.uniqueValues.map(function(e) { return e.value; }).indexOf(uniqueValue.value) < 0) {
				uniqueValue.selected = false;
				$scope.columnFilter.uniqueValues.push(uniqueValue);
			}
		};

		$scope.$on('addToFilter', function(event, record) {
			$scope.populateUniqueValue(record[$scope.columnFilter.name]);
			$scope.sortUniqueValues();
		});
		
		
		$scope.$on('resetFilters', function(event, records, columnFilters) {
			if (columnFilters && angular.isArray(columnFilters) && columnFilters.length > 0) {
				if (columnFilters.map(function(e) { return e.name; }).indexOf($scope.columnFilter.name) >= 0) {
					return;
				}
			}
            else {
                $scope.columnFilter.filterPresent = false;
                $scope.columnFilter.uniqueValuesFilterPresent = false;
            }
			
			if (records && angular.isArray(records)) {
				$scope.columnFilter.uniqueValues = [];

				for (var row = 0; row < records.length; row++) {
					$scope.populateUniqueValue(records[row][$scope.columnFilter.name]);
				}
			}

			$scope.sortUniqueValues();
		});
		
		$scope.initializeColumnFilter = function(columnName, columnType) {
			$scope.columnFilter.name = columnName;
			$scope.columnFilter.type = columnType;
			$scope.$emit('addColumn', $scope.columnFilter);
		};
		
		$scope.closeDropdown = function() {
			delete $scope.copyColumnFilter;
			$('.dropdown.open').removeClass('open');
		};

		$scope.backupColumnFilter = function() {
			$scope.copyColumnFilter = angular.copy($scope.columnFilter);
		};
		
		$scope.restoreColumnFilter = function() {
			$scope.columnFilter = angular.copy($scope.copyColumnFilter);
			$scope.closeDropdown();
		};
		
		$scope.clearColumnFilter = function() {
			if ($scope.columnFilter.uniqueValues) {
				for (var i = 0; i < $scope.columnFilter.uniqueValues.length; i++) {
					$scope.columnFilter.uniqueValues[i].selected = false;
				}
			}
			
			delete $scope.columnFilter.blank;
			delete $scope.columnFilter.notBlank;
			
			if ($scope.columnFilter.type === 'Text') {
				delete $scope.columnFilter.contains;
				delete $scope.columnFilter.doesNotContain;
				delete $scope.columnFilter.startsWith;
				delete $scope.columnFilter.endsWith;
			}
			else if ($scope.columnFilter.type === 'Integer' || $scope.columnFilter.type === 'Decimal') {
				delete $scope.columnFilter.fromNumber;
				delete $scope.columnFilter.toNumber;
			}

			$scope.columnFilter.filterPresent = false;
			$scope.columnFilter.uniqueValuesFilterPresent = false;
			
			$scope.$emit('filterColumn', $scope.columnFilter);
			$scope.closeDropdown();
		};
		
		$scope.hasFilter = function() {
			if ($scope.columnFilter.blank || $scope.columnFilter.notBlank) {
				return true;
			}
			
			if ($scope.columnFilter.type === 'Text') {
				if ($scope.columnFilter.contains
					|| $scope.columnFilter.doesNotContain
					|| $scope.columnFilter.startsWith
					|| $scope.columnFilter.endsWith) {
					return true;
				}
			}
			else if ($scope.columnFilter.type === 'Integer' || $scope.columnFilter.type === 'Decimal') {
				if ($scope.columnFilter.fromNumber
					|| $scope.columnFilter.toNumber) {
					return true;
				}
			}
			
			if ($scope.columnFilter.uniqueValues) {
				for (var i = 0; i < $scope.columnFilter.uniqueValues.length; i++) {
					if ($scope.columnFilter.uniqueValues[i].selected) {
						$scope.columnFilter.uniqueValuesFilterPresent = true;
						return true;
					}
				}
			}
			
			return false;
		};
		
		$scope.getFilterClass = function() {
			return $scope.columnFilter.filterPresent ? 'red' : '';
		};
		
		$scope.applyColumnFilter = function() {
			$scope.columnFilter.filterPresent = $scope.hasFilter();
			$scope.$emit('filterColumn', $scope.columnFilter);
			$scope.closeDropdown();
		};
		
		$scope.columnFilter.match = function(record) {
			if (!record) {
				return false;
			}
			
			if ($scope.columnFilter.filterPresent) {
				if ($scope.columnFilter.blank && record[$scope.columnFilter.name]) {
					return false;
				}
				
				if ($scope.columnFilter.notBlank && !record[$scope.columnFilter.name]) {
					return false;
				}

				if ($scope.columnFilter.uniqueValuesFilterPresent) {
					var selectedMatchFound = false;
					
					for (var i = 0; i < $scope.columnFilter.uniqueValues.length; i++) {
						if ($scope.columnFilter.uniqueValues[i].selected
							&& record[$scope.columnFilter.name] === $scope.columnFilter.uniqueValues[i].value) {
							selectedMatchFound = true;
						}
					}
					
					if (!selectedMatchFound) {
						return false;
					}
				}
				
				if ($scope.columnFilter.type === 'Text') {
					var ignoreCaseStr = ($scope.columnFilter.ignoreCase) ? 'i' : '';
					var regExp;
					
					if ($scope.columnFilter.contains) {
						regExp = new RegExp($scope.columnFilter.contains, ignoreCaseStr);
						if (record[$scope.columnFilter.name].search(regExp) < 0) {
							return false;
						}
					}
					
					if ($scope.columnFilter.doesNotContain) {
						regExp = new RegExp('^((?!' + $scope.columnFilter.doesNotContain + ').)*$', ignoreCaseStr);
						if (record[$scope.columnFilter.name].search(regExp) < 0) {
							return false;
						}
					}

					if ($scope.columnFilter.startsWith) {
						regExp = new RegExp('^' + $scope.columnFilter.startsWith, ignoreCaseStr);
						if (record[$scope.columnFilter.name].search(regExp) < 0) {
							return false;
						}
					}

					if ($scope.columnFilter.endsWith) {
						regExp = new RegExp($scope.columnFilter.endsWith + '$', ignoreCaseStr);
						if (record[$scope.columnFilter.name].search(regExp) < 0) {
							return false;
						}
					}
				}
				else if (($scope.columnFilter.type === 'Integer' || $scope.columnFilter.type === 'Decimal')) {
					if ($scope.columnFilter.fromNumber && record[$scope.columnFilter.name] < $scope.columnFilter.fromNumber) {
						return false;
					}

					if ($scope.columnFilter.toNumber && record[$scope.columnFilter.name] > $scope.columnFilter.toNumber) {
						return false;
					}
				}
			}

			return true;
		}
	});
}