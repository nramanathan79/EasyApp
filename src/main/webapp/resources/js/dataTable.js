angular
.module("dataTable", ["ngResource", 'angularSpinner'])
.constant("apiUri", "api")
.controller("dataTableController", function($scope, $resource, apiUri) {

	$scope.initializeResource = function(resourceEndPoint) {
		$scope.restResource = $resource(apiUri + "/" + resourceEndPoint + "/:id", {id: "@id"});
		$scope.sortBy = [];
		$scope.columnFilters = [];
	}
	
	$scope.populateUniqueValues = function(record) {
		if ($scope.columnFilters && angular.isArray($scope.columnFilters)) {
			for (var col = 0; col < $scope.columnFilters.length; col++) {
				if (!$scope.columnFilters[col].uniqueValues) {
					$scope.columnFilters[col].uniqueValues = [];
				}
				
				var uniqueValue = {};
				uniqueValue.value = record[$scope.columnFilters[col].name];
				
				if ($scope.columnFilters[col].uniqueValues.map(function(e) { return e.value; }).indexOf(uniqueValue.value) < 0) {
					uniqueValue.selected = false;
					$scope.columnFilters[col].uniqueValues.push(uniqueValue);
				}
			}
		}
	}
	
	$scope.populateAllUniqueValues = function() {
		if ($scope.columnFilters && angular.isArray($scope.columnFilters)) {
			for (var col = 0; col < $scope.columnFilters.length; col++) {
				$scope.columnFilters[col].uniqueValues = [];
			}
		}
		
		if ($scope.records && angular.isArray($scope.records)) {
			for (var row = 0; row < $scope.records.length; row++) {
				$scope.populateUniqueValues($scope.records[row]);
			}
		}
	}
	
	$scope.listRecords = function() {
		$scope.showSpinner = true;
		delete $scope.error;
		$scope.restResource.query(function(result) {
			$scope.records = result;
			
			if (result.length > 0) {
				$scope.populateAllUniqueValues();
			}
			else {
				$scope.showSpinner = false;
			}
		},
		function(error) {
			$scope.error = "Error encountered while retrieving the list of records";
		});
	}
	
	$scope.getRecord = function(id) {
		$scope.showSpinner = true;
		$scope.record = $scope.restResource.get(id);
		$scope.showSpinner = false;
	}
	
	$scope.createRecord = function(record, fromImport) {
		$scope.showSpinner = true;
		delete $scope.error;
		new $scope.restResource(record).$save(function(result) {
			if (fromImport && fromImport === true) {
				$scope.records.push(result);
			}
			else {
				$scope.records[$scope.records.length - 1] = result;
			}

			if (result.messageType === "SUCCESS") {
				delete $scope.records.editing;
				$scope.populateUniqueValues(result);
			}
			else {
				record.editing = true;
				$scope.error = result.messageText;
			}
		},
		function(error) {
			$scope.error = "Error creating a new record";
		});
		$scope.showSpinner = false;
	}
	
	$scope.updateRecord = function(record) {
		$scope.showSpinner = true;
		delete record.editing;
		delete $scope.error;
		record.$save(function(result) {
			if (result.messageType === "SUCCESS") {
				delete $scope.records.editing;
				$scope.populateAllUniqueValues();
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
		$scope.showSpinner = false;
	}
	
	$scope.deleteRecord = function(record) {
		$scope.showSpinner = true;
		delete $scope.error;
		record.$delete(function(result) {
			if (result.messageType === "SUCCESS") {
				$scope.records.splice($scope.records.indexOf(record), 1);
				$scope.populateAllUniqueValues();
			}
			else {
				$scope.error = result.messageText;
			}
		},
		function(error) {
			$scope.error = "Error deleting the record";
		});
		$scope.showSpinner = false;
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

	$scope.changeSortSelection = function(selection) {
		$scope.sortSelection = selection;
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
	
	$scope.clearSearch = function() {
		$scope.searchText = "";
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
		
		delete $scope.columnFilters[columnIndex].blank;
		delete $scope.columnFilters[columnIndex].notBlank;
		
		if ($scope.columnFilters[columnIndex].type === 'Text') {
			delete $scope.columnFilters[columnIndex].contains;
			delete $scope.columnFilters[columnIndex].doesNotContain;
			delete $scope.columnFilters[columnIndex].startsWith;
			delete $scope.columnFilters[columnIndex].endsWith;
		}
		else if ($scope.columnFilters[columnIndex].type === 'Integer' || $scope.columnFilters[columnIndex].type === 'Decimal') {
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
		else if ($scope.columnFilters[columnIndex].type === 'Integer' || $scope.columnFilters[columnIndex].type === 'Decimal') {
			if ($scope.columnFilters[columnIndex].fromNumber
				|| $scope.columnFilters[columnIndex].toNumber) {
				return true;
			}
		}

		return false;
	}
	
	$scope.hasFilter = function(columnIndex) {
		var filterPresent = $scope.hasFilterFields(columnIndex);
		
		if (!filterPresent && ($scope.columnFilters[columnIndex].blank || $scope.columnFilters[columnIndex].notBlank)) {
			filterPresent = true;
		}
		
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
				if ($scope.columnFilters[i].blank) {
					if (record[$scope.columnFilters[i].name]) {
						matchFound = false;
					}
				}
				
				if (matchFound && $scope.columnFilters[i].notBlank) {
					if (!record[$scope.columnFilters[i].name]) {
						matchFound = false;
					}
				}

				if (matchFound && $scope.columnFilters[i].uniqueValues) {
					for (var j = 0; matchFound && j < $scope.columnFilters[i].uniqueValues.length; j++) {
						if ($scope.columnFilters[i].uniqueValues[j].selected) {
							matchFound = record[$scope.columnFilters[i].name] === $scope.columnFilters[i].uniqueValues[j].value;
						}
					}
				}
				
				if (matchFound && $scope.columnFilters[i].type === 'Text') {
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
				else if (matchFound && ($scope.columnFilters[i].type === 'Integer' || $scope.columnFilters[i].type === 'Decimal')) {
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
	
	$scope.hasImportCsvFile = function() {
		if ($scope.csvFile && $scope.csvFile.name) {
			return true;
		}
		
		return false;
	}
	
	$scope.stopSpinner = function() {
		$scope.showSpinner = false;
	}
	
	$scope.importCsvFile = function() {
		if ($scope.hasImportCsvFile()) {
			Papa.parse($scope.csvFile, {
				header: true,
				comments: "#",
				step: function(results, parser) {
					$scope.createRecord(results.data[0], true);
				},
				complete: function(results) {
					$('#importCsv').modal('hide');
				},
				error: function(error, file) {
					console.log("Error: " + error + " encountered in file: " + file);
				},
				skipEmptyLines: true
			});
		}
	}
})
.directive('repeatComplete', function($timeout, $parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attr) {
        	console.log(scope);
            if (scope.$last === true) {
            	$timeout(function () {
                    if (!!attr.repeatComplete) {
                    	$parse(attr.repeatComplete)(scope);
                    }
                });
            }
        }
    };
})
.directive("importCsv", function() {
	return {
		link: function(scope, element, attr) {
			$(element).on('change', function(changeEvent) {
				var files = changeEvent.target.files;
				if (files && files.length) {
					scope.$apply(function() {
						scope.csvFile = files[0];
					});
				}
			});
		}
	};
});

// Disable click inside the dropdown menu to close the dropdown except on button or link
$('.dropdown-menu').click(function(event) {
	if (event.target.nodeName === 'A' || event.target.nodeName === 'BUTTON' || event.target.nodeName === 'LI') return;
    event.stopPropagation();
});