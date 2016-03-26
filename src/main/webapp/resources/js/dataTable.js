angular
.module("dataTable", ["ngResource"])
.constant("apiUri", "api")
.controller("dataTableController", function($scope, $resource, apiUri) {
	
	$scope.initializeResource = function(resourceEndPoint) {
		$scope.restResource = $resource(apiUri + "/" + resourceEndPoint + "/:id", {id: "@id"});
		$scope.sortBy = [];
	}
	
	$scope.listRecords = function() {
		$scope.restResource.query(function(result) {
			$scope.records = result;
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
	
	$scope.clearFilters = function() {
		
	}
	
	$scope.$watch("restResource", function() {
		$scope.listRecords();
	});
});