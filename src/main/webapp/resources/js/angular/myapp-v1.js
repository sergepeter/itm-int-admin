var intApp = angular.module('intApp', []);


/*****
 * Data flow controller declaration (simplest way)
 * @param $scope
 * @param $http
 */
intApp.controller('dataflowController', function ($scope, $http) {

	/**
	 * variables initialisation 
	 */
	$scope.edit = true;
	$scope.error = false;
	$scope.new = false;
	
	$scope.incomplete = false;
	$scope.name = '';
	$scope.itServiceName = '';
	$scope.status = '';
	$scope.techno = '';
	$scope.processId = '';
	$scope.onErrorsMessagesToday = '';
	$scope.treatedMessagesToday = '';
	$scope.dataflows = [];

	/**
	 * function : prepare data for view (findAll)
	 */
	$scope.loadData = function() {
		$http.get("rest/dataflows")
				.success(function(response) {
					$scope.dataflows = response;
					console.log(">> test data set loaded");
				});
	};
	
	
	$scope.initFormVar = function() {
		$scope.name = '';
		$scope.itServiceName = '';
		$scope.status = '';
		$scope.techno = '';
		$scope.processId = '';
		$scope.onErrorsMessagesToday = '';
		$scope.treatedMessagesToday = '';
	}

	// run it
	$scope.loadData();

	/**
	 * function : edit and create new entry (create and update)
	 */
	$scope.editDataFlow = function(id) {

		if (id == 'new') {

			$scope.edit = true;
			$scope.incomplete = false;
			$scope.initFormVar();
			
			$scope.dataflow.id = 'new';
			
			console.log(">> create new record, form loaded");

		} else {

			$scope.edit = false;

			$http.get("rest/dataflows/" + id)
				.success(
						function(response) {
							$scope.dataflow = response;

							$scope.name = $scope.dataflow.name;
							$scope.itServiceName = $scope.dataflow.itService.name;
							$scope.status = $scope.dataflow.status;
							$scope.techno = $scope.dataflow.techno;
							$scope.processId = $scope.dataflow.processId;
							$scope.onErrorsMessagesToday = $scope.dataflow.onErrorsMessagesToday;
							$scope.treatedMessagesToday = $scope.dataflow.treatedMessagesToday;
							
							console.log(">> edit record : " + $scope.name + " , form loaded");

						});
		}
	};

	/**
	 * function that re-init application data (for demonstration purpose)
	 */
	$scope.initData = function() {
		$http.get("rest/dataflows/init")
				.success(function(response) {
					$scope.response = response;
					$scope.loadData();
					
					console.log(">> reinit data for test purpose");
				});
	};

	/**
	 * function : delete data flow (Delete)
	 */
	$scope.deleteDataFlow = function(id) {
		$http.delete("rest/dataflows/" + id)
		.success(function(response){
			$scope.response = response;
			$scope.loadData();
			console.log(">> dataflow deleted (id = " + id + ")");
		});
	};
	
	
	/**
	 * function : delete data flow (Delete)
	 */
	$scope.updateDataFlow = function(id) {
		
		if (id == "new") {
			
			$scope.dataflow.id = null;
			$scope.dataflow.name = $scope.name;
			$scope.dataflow.status = $scope.status;
			$scope.dataflow.techno = $scope.techno;
			$scope.dataflow.processId = $scope.processId; 
			$scope.dataflow.onErrorsMessagesToday = $scope.onErrorsMessagesToday;
			$scope.dataflow.treatedMessagesToday = $scope.treatedMessagesToday;
			
			$http.put("rest/dataflows/", $scope.dataflow ).success(
					function(response) {
						// reinit data
						$scope.initFormVar();
						$scope.loadData();
						console.log(">> dataflow created");
					});
			
			
			
		} else {
		
			$http.get("rest/dataflows/" + id)
			.success(
					function(response) {
						
						$scope.dataflow = response;
	
						$scope.dataflow.name = $scope.name;
						$scope.dataflow.status = $scope.status;
						$scope.dataflow.techno = $scope.techno;
						$scope.dataflow.processId = $scope.processId; 
						$scope.dataflow.onErrorsMessagesToday = $scope.onErrorsMessagesToday;
						$scope.dataflow.treatedMessagesToday = $scope.treatedMessagesToday;
						
						$http.put("rest/dataflows/", $scope.dataflow ).success(
								function(response) {
										// reinit data
										$scope.initFormVar();
										$scope.loadData();
										console.log(">> dataflow updated (id = " + id + ")");
									});
					});
			}
	};

});
