angular
.module("dataTable", ["ngResource"])
.constant("apiUri", "api")
.controller("dataTableController", function($scope, $resource, apiUri) {
	
	$scope.initializeResource = function(resourceEndPoint) {
		$scope.restResource = $resource(apiUri + "/" + resourceEndPoint + "/:id", {id: "@id"});
		$scope.sortBy = [];
		$scope.columnFilters = [];
	}
	
	$scope.listRecords = function() {
		$scope.restResource.query(function(result) {
			$scope.records = result;
			
			if ($scope.records && angular.isArray($scope.records)
				&& $scope.columnFilters && angular.isArray($scope.columnFilters)) {
				
				for (var col = 0; col < $scope.columnFilters.length; col++) {
					$scope.columnFilters[col].uniqueValues = [];
					for (var row = 0; row < $scope.records.length; row++) {
						var uniqueValue = {};
						uniqueValue.value = $scope.records[row][$scope.columnFilters[col].name];
						
						if ($scope.columnFilters[col].uniqueValues.map(function(e) { return e.value; }).indexOf(uniqueValue.value) < 0) {
							uniqueValue.selected = false;
							$scope.columnFilters[col].uniqueValues.push(uniqueValue);
						}
					}
				}
			}
		},
		function(error) {
			$scope.error = "Error encountered while retrieving the list of records";
		});
	}
	
	$scope.getRecord = function(id) {
		$scope.record = $scope.restResource.get(id);
	}
	
	$scope.createRecord = function(record) {
		delete record.editing;
		delete $scope.error;
		new $scope.restResource(record).$save(function(result) {
			if (result.messageType === "SUCCESS") {
				$scope.records[$scope.records.length - 1] = result;
				delete $scope.records.editing;
			}
			else {
				record.editing = true;
				$scope.error = result.messageText;
			}
		},
		function(error) {
			$scope.error = "Error creating a new record";
		});
	}
	
	$scope.updateRecord = function(record) {
		delete record.editing;
		delete $scope.error;
		record.$save(function(result) {
			if (result.messageType === "SUCCESS") {
				delete $scope.records.editing;	
			}
			else {
				record.editing = true;
				$scope.error = result.messageText;
			}
		},
		function(error) {
			record.editing = true;
			$scope.error = "Error updating the record";
		});
	}
	
	$scope.deleteRecord = function(record) {
		delete $scope.error;
		record.$delete(function(result) {
			if (result.messageType === "SUCCESS") {
				$scope.records.splice($scope.records.indexOf(record), 1);
			}
			else {
				$scope.error = result.messageText;
			}
		},
		function(error) {
			$scope.error = "Error deleting the record";
		});
	}
	
	$scope.$watch("restResource", function() {
		$scope.listRecords();
	});
	
	$scope.editRecord = function(record) {
		$scope.recordBeforeEdit = angular.copy(record);
		record.editing = true;
		$scope.records.editing = true;
	}
	
	$scope.cancelRecord = function(record) {
		if (record.id) {
			$scope.records[$scope.records.indexOf(record)] = $scope.recordBeforeEdit;
			delete $scope.recordBeforeEdit;
		}
		else {
			$scope.records.splice($scope.records.indexOf(record), 1);
		}
		
		delete $scope.records.editing;
		delete $scope.error;
	}
	
	$scope.addRecord = function() {
		var record = {"editing": true};
		$scope.records.push(record);
		$scope.records.editing = true;
	}

	$scope.changeSortSelection = function() {
		if ($scope.sortSelection === "Single") {
			if ($scope.sortBy.length > 1) {
				var primarySort = $scope.sortBy[0];
				$scope.clearSort();
				$scope.sortBy.push(primarySort);
			}
		}
	}
	
	$scope.hasSort = function(column) {
		var columnIndex = $scope.sortBy.indexOf(column);
		if (columnIndex >= 0) {
			return true;
		}
		
		var columnReverseIndex = $scope.sortBy.indexOf("-" + column);
		if (columnReverseIndex >= 0) {
			return true;
		}
		
		return false;
	}

	$scope.showSortIndex = function(column) {
		if ($scope.sortSelection === "Multiple") {
			var columnIndex = $scope.sortBy.indexOf(column);
			if (columnIndex >= 0) {
				return columnIndex + 1;
			}
			
			var columnReverseIndex = $scope.sortBy.indexOf("-" + column);
			if (columnReverseIndex >= 0) {
				return columnReverseIndex + 1;
			}
		}
		
		return -1;
	}
	
	$scope.addSort = function(column) {
		var columnIndex = $scope.sortBy.indexOf(column);
		var columnReverseIndex = $scope.sortBy.indexOf("-" + column);

		if (columnIndex >= 0) {
			$scope.sortBy[columnIndex] = "-" + column;
		}
		else if (columnReverseIndex >= 0) {
			$scope.sortBy[columnReverseIndex] = column;
		}
		else {
			if ($scope.sortSelection === "Single") {
				$scope.clearSort();
			}
			
			$scope.sortBy.push(column);
		}
	}
	
	$scope.clearSort = function() {
		$scope.sortBy = [];
	}
	
	$scope.initializeColumnFilter = function(columnName, columnType) {
		var columnFilter = {};
		columnFilter.name = columnName;
		columnFilter.type = columnType;
		
		$scope.columnFilters.push(columnFilter);
	}
	
	$scope.clearColumnFilter = function(columnIndex) {
		if ($scope.columnFilters[columnIndex].uniqueValues) {
			for (var i = 0; i < $scope.columnFilters[columnIndex].uniqueValues.length; i++) {
				$scope.columnFilters[columnIndex].uniqueValues[i].selected = false;
			}
		}
		
		if ($scope.columnFilters[columnIndex].type === 'Text') {
			delete $scope.columnFilters[columnIndex].contains;
			delete $scope.columnFilters[columnIndex].doesNotContain;
			delete $scope.columnFilters[columnIndex].startsWith;
			delete $scope.columnFilters[columnIndex].endsWith;
		}
		else if ($scope.columnFilters[columnIndex].type === 'Number') {
			delete $scope.columnFilters[columnIndex].fromNumber;
			delete $scope.columnFilters[columnIndex].toNumber;
		} 
	}

	$scope.clearFilters = function() {
		for (var i = 0; i < $scope.columnFilters.length; i++) {
			$scope.clearColumnFilter(i);
		}
	}
	
	$scope.hasFilterFields = function(columnIndex) {
		if ($scope.columnFilters[columnIndex].type === 'Text') {
			if ($scope.columnFilters[columnIndex].contains
				|| $scope.columnFilters[columnIndex].doesNotContain
				|| $scope.columnFilters[columnIndex].startsWith
				|| $scope.columnFilters[columnIndex].endsWith) {
				return true;
			}
		}
		else if ($scope.columnFilters[columnIndex].type === 'Number') {
			if ($scope.columnFilters[columnIndex].fromNumber
				|| $scope.columnFilters[columnIndex].toNumber) {
				return true;
			}
		}

		return false;
	}
	
	$scope.hasFilter = function(columnIndex) {
		var filterPresent = $scope.hasFilterFields(columnIndex);
		
		if (!filterPresent && $scope.columnFilters[columnIndex].uniqueValues) {
			for (var i = 0; !filterPresent && i < $scope.columnFilters[columnIndex].uniqueValues.length; i++) {
				filterPresent = $scope.columnFilters[columnIndex].uniqueValues[i].selected;
			}
		}
		
		return filterPresent;
	}
	
	$scope.getFilterClass = function(columnIndex) {
		return $scope.hasFilter(columnIndex) ? "red" : "";
	}
	
	$scope.columnFilterFunc = function(record) {
		var matchFound = true;
		
		for (var i = 0; matchFound && i < $scope.columnFilters.length; i++) {
			if ($scope.hasFilter(i)) {
				if ($scope.columnFilters[i].uniqueValues) {
					for (var j = 0; matchFound && j < $scope.columnFilters[i].uniqueValues.length; j++) {
						if ($scope.columnFilters[i].uniqueValues[j].selected) {
							matchFound = record[$scope.columnFilters[i].name] === $scope.columnFilters[i].uniqueValues[j].value;
						}
					}
				}
				
				if ($scope.columnFilters[i].type === 'Text') {
					var ignoreCaseStr = ($scope.columnFilters[i].ignoreCase) ? "i" : "";
					var regExp;
					
					if (matchFound && $scope.columnFilters[i].contains) {
						regExp = new RegExp($scope.columnFilters[i].contains, ignoreCaseStr);
						matchFound = record[$scope.columnFilters[i].name].search(regExp) >= 0;
					}
					
					if (matchFound && $scope.columnFilters[i].doesNotContain) {
						regExp = new RegExp("^((?!" + $scope.columnFilters[i].doesNotContain + ").)*$", ignoreCaseStr);
						matchFound = record[$scope.columnFilters[i].name].search(regExp) >= 0;
					}

					if (matchFound && $scope.columnFilters[i].startsWith) {
						regExp = new RegExp("^" + $scope.columnFilters[i].startsWith, ignoreCaseStr);
						matchFound = record[$scope.columnFilters[i].name].search(regExp) >= 0;
					}

					if (matchFound && $scope.columnFilters[i].endsWith) {
						regExp = new RegExp($scope.columnFilters[i].endsWith + "$", ignoreCaseStr);
						matchFound = record[$scope.columnFilters[i].name].search(regExp) >= 0;
					}
				}
				else if ($scope.columnFilters[i].type === 'Number') {
					if (matchFound && $scope.columnFilters[i].fromNumber) {
						matchFound = record[$scope.columnFilters[i].name] >= $scope.columnFilters[i].fromNumber;
					}

					if (matchFound && $scope.columnFilters[i].toNumber) {
						matchFound = record[$scope.columnFilters[i].name] <= $scope.columnFilters[i].toNumber;
					}
				}
			}
		}
		
		return matchFound;
	}
});

// Disable click inside the dropdown menu to close the dropdown except on button or link
$('.dropdown-menu').click(function(event) {
	if (event.target.nodeName === 'A' || event.target.nodeName === 'BUTTON') return;
    event.stopPropagation();
});