var dataTableApp = angular.module('dataTable', ['ngResource', 'angularSpinner', 'ui.bootstrap']).constant('apiUri', 'api');

dataTableApp.controller('dataTableController', function($scope, $resource, apiUri) {
	$scope.startSpinner = function() {
		$scope.showSpinner = true;
	};
	
	$scope.stopSpinner = function() {
		$scope.showSpinner = false;
	};
	
	$scope.initializeResource = function(resourceEndPoint) {
		$scope.restResource = $resource(apiUri + '/' + resourceEndPoint + '/:id', {id: '@id'});
	};
	
	$scope.$on('filterColumn', function(event, columnFilter) {
		if (columnFilter) {
			if (!$scope.columnFilters) {
				$scope.columnFilters = [];
			}

			if ($scope.columnFilters.length > 0) {
				var columnIndex = $scope.columnFilters.map(function(e) { return e.name; }).indexOf(columnFilter.name);
				
				if (columnIndex >= 0) {
					$scope.columnFilters.splice(columnIndex, 1);
				}
			}

			if (columnFilter.filterPresent) {
				$scope.columnFilters.push(columnFilter);
			}

			$scope.filteredRecords = [];
			
			angular.forEach($scope.records, function(record, key) {
				var matchFound = true;
				for (var col = 0; matchFound && col < $scope.columnFilters.length; col++) {
					matchFound = $scope.columnFilters[col].match(record, $scope.searchText);
				}
				
				if (matchFound) {
					$scope.filteredRecords.push(record);
				}
			});
			
			$scope.$broadcast('resetFilters', $scope.filteredRecords, $scope.columnFilters);
		}
	});

	$scope.listRecords = function() {
		$scope.startSpinner();

		delete $scope.error;
		$scope.restResource.query(function(result) {
			if (result && result.length > 0) {
				$scope.records = result;
				$scope.filteredRecords = $scope.records;
				$scope.$broadcast('resetFilters', $scope.filteredRecords, $scope.columnFilters);
			}
			else {
				$scope.stopSpinner();
			}
		},
		function(error) {
			$scope.error = 'Error encountered while retrieving the list of records';
		});
	};
	
	$scope.getRecord = function(id) {
		$scope.startSpinner();
		
		$scope.record = $scope.restResource.get(id);
		
		$scope.stopSpinner();
	};
	
	$scope.createRecord = function(record, fromImport) {
		$scope.startSpinner();

		delete $scope.error;
		new $scope.restResource(record).$save(function(result) {
			if (fromImport && fromImport === true) {
				$scope.records.push(result);
			}
			else {
				$scope.records[$scope.records.length - 1] = result;
			}

			if (result.messageType === 'SUCCESS') {
				delete $scope.filteredRecords.editing;
				$scope.$broadcast('addToFilter', result);
			}
			else {
				record.editing = true;
				$scope.error = result.messageText;
			}
		},
		function(error) {
			$scope.error = 'Error creating a new record';
		});

		$scope.stopSpinner();
	};
	
	$scope.updateRecord = function(record) {
		$scope.startSpinner();

		delete record.editing;
		delete $scope.error;
		record.$save(function(result) {
			if (result.messageType === 'SUCCESS') {
				delete $scope.filteredRecords.editing;
				$scope.$broadcast('resetFilters', $scope.records);
			}
			else {
				record.editing = true;
				$scope.error = result.messageText;
			}
		},
		function(error) {
			record.editing = true;
			$scope.error = 'Error updating the record';
		});

		$scope.stopSpinner();
	};
	
	$scope.deleteRecord = function(record) {
		$scope.startSpinner();

		delete $scope.error;
		record.$delete(function(result) {
			if (result.messageType === 'SUCCESS') {
				$scope.records.splice($scope.records.indexOf(record), 1);
				$scope.$broadcast('resetFilters', $scope.records);
			}
			else {
				$scope.error = result.messageText;
			}
		},
		function(error) {
			$scope.error = 'Error deleting the record';
		});

		$scope.stopSpinner();
	};
	
	$scope.$watch('restResource', function() {
		$scope.listRecords();
	});
	
	$scope.editRecord = function(record) {
		$scope.recordBeforeEdit = angular.copy(record);
		record.editing = true;
		$scope.filteredRecords.editing = true;
	};
	
	$scope.cancelRecord = function(record) {
		if (record.id) {
			$scope.filteredRecords[$scope.filteredRecords.indexOf(record)] = angular.copy($scope.recordBeforeEdit);
			delete $scope.recordBeforeEdit;
		}
		else {
			$scope.filteredRecords.splice($scope.records.indexOf(record), 1);
		}
		
		delete $scope.filteredRecords.editing;
		delete $scope.error;
	};
	
	$scope.addRecord = function() {
		var record = {'editing': true};
		$scope.records.push(record);
		$scope.filteredRecords.editing = true;
	};

	$scope.changeSortSelection = function(selection) {
        if (!$scope.sortBy) {
            $scope.sortBy = [];
        }

        $scope.sortSelection = selection;
		if ($scope.sortSelection === 'Single') {
			if ($scope.sortBy.length > 1) {
				var primarySort = $scope.sortBy[0];
				$scope.clearSort();
				$scope.sortBy.push(primarySort);
			}
		}
	};
	
	$scope.hasSort = function(column) {
        if (!$scope.sortBy) {
            $scope.sortBy = [];
        }

		if ($scope.sortBy.indexOf(column) >= 0) {
			return true;
		}
		
		return $scope.sortBy.indexOf('-' + column) >= 0;
	};

	$scope.showSortIndex = function(column) {
        if (!$scope.sortBy) {
            $scope.sortBy = [];
        }

        if ($scope.sortSelection === 'Multiple') {
			var columnIndex = $scope.sortBy.indexOf(column);
			if (columnIndex >= 0) {
				return columnIndex + 1;
			}
			
			var columnReverseIndex = $scope.sortBy.indexOf('-' + column);
			if (columnReverseIndex >= 0) {
				return columnReverseIndex + 1;
			}
		}
		
		return -1;
	};
	
	$scope.addSort = function(column) {
		if (!$scope.sortBy) {
			$scope.sortBy = [];
		}

		var columnIndex = $scope.sortBy.indexOf(column);
		var columnReverseIndex = $scope.sortBy.indexOf('-' + column);

		if (columnIndex >= 0) {
			$scope.sortBy[columnIndex] = '-' + column;
		}
		else if (columnReverseIndex >= 0) {
			$scope.sortBy[columnReverseIndex] = column;
		}
		else {
			if ($scope.sortSelection === 'Single') {
				$scope.clearSort();
			}
			
			$scope.sortBy.push(column);
		}
	};
	
	$scope.clearSort = function() {
		$scope.sortBy = [];
	};

    $scope.sortPresent = function() {
        return $scope.sortBy && angular.isArray($scope.sortBy) && $scope.sortBy.length > 0;
    };
	
	$scope.clearSearch = function() {
		$scope.searchText = '';
	};
	
	$scope.clearFilters = function() {
		$scope.columnFilters = [];
		$scope.filteredRecords = $scope.records;
		$scope.$broadcast('resetFilters', $scope.filteredRecords, $scope.columnFilters);
	};

	$scope.hasFilters = function() {
		return $scope.columnFilters && angular.isArray($scope.columnFilters) && $scope.columnFilters.length > 0;
	};
	
	$scope.hasImportCsvFile = function() {
		return $scope.csvFile && $scope.csvFile.name;
	};
	
	$scope.importCsvFile = function() {
		if ($scope.hasImportCsvFile()) {
			Papa.parse($scope.csvFile, {
				header: true,
				comments: '#',
				step: function(results, parser) {
					$scope.createRecord(results.data[0], true);
				},
				complete: function(results) {
					$('#importCsv').modal('hide');
				},
				error: function(error, file) {
					console.log('Error: ' + error + ' encountered in file: ' + file);
				},
				skipEmptyLines: true
			});
		}
	}
});

dataTableApp.directive('repeatComplete', function($timeout, $parse) {
    return function(scope, element, attrs) {
    	if (scope.$last) {
    		if (attrs.repeatComplete) {
        		scope.$eval(attrs.repeatComplete);
    		}
       	}
    };
});

dataTableApp.directive('importCsv', function() {
	return function(scope, element, attrs) {
		$(element).on('change', function(changeEvent) {
			var files = changeEvent.target.files;
			if (files && files.length) {
				scope.$apply(function() {
					scope.csvFile = files[0];
				});
			}
		});
	};
});