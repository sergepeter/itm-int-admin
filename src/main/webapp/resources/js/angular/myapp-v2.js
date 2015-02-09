var intApp = angular.module('intApp', []);


/*******************************************************************************
 * Data flow controller declaration (more advanced way)
 * 
 * @param $scope
 * @param $http
 */
intApp.controller('dataflowController', function ($scope, $http) {

	/**
	 * variables initialisation
	 */
	$scope.edit = true;

	// form scope vars
	$scope.name = '';
	$scope.itServiceName = '';
	$scope.itServiceId = '';
	$scope.status = '';
	$scope.techno = '';
	$scope.processId = '';
	$scope.onErrorsMessagesToday = '';
	$scope.treatedMessagesToday = '';
	$scope.dataflows = [];

	// example with hard coded values
	$scope.technos = {
			'SOA10g' : 'SOA10g',
			'SOA11g' : 'SOA11g',
			'Fuse' : 'Fuse'
	};


	// example with list of value get from backend
	$scope.itservices = [];
	
	// get list of services
	$http.get("rest/itservices")
		.success(function(response) {
			$scope.itservices = response;
			console.log(">> get it services list " + $scope.itservices[1].name);
	});

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
	
	// run it
	$scope.loadData();

	
	/**
	 * function : init form scope vars with empty values
	 */
	$scope.initFormVar = function() {
		
		$scope.id = '';
		$scope.name = '';
		$scope.itServiceName = '';
		$scope.itServiceId = '';
		$scope.itService = null;
		$scope.status = '';
		$scope.techno = '';
		$scope.processId = '';
		
		$scope.onErrorsMessagesToday = 0;
		$scope.treatedMessagesToday = 0;
		
	}

	
	/**
	 * function : edit and create new entry (create and update)
	 */
	$scope.editDataFlow = function(id) {

		if (id == 'new') {

			$scope.edit = true;
			
			$scope.initFormVar();
			
			console.log(">> create new record, form loaded");

		} else {

			$scope.edit = false;

			$http.get("rest/dataflows/" + id)
				.success(
						function(response) {
							$scope.dataflow = response;
							$scope.name = $scope.dataflow.name;
							$scope.itServiceName = $scope.dataflow.itService.name;
							$scope.itServiceId = $scope.dataflow.itService.id;
							
							$scope.status = $scope.dataflow.status;
							$scope.techno = $scope.dataflow.techno;
							$scope.processId = $scope.dataflow.processId;
							$scope.onErrorsMessagesToday = $scope.dataflow.onErrorsMessagesToday;
							$scope.treatedMessagesToday = $scope.dataflow.treatedMessagesToday;
							
							console.log(">> itService.id " + $scope.dataflow.itService.id);
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
		
		$scope.itService=null;
		
		/*
		 * get service object
		 */
		$http.get("rest/itservices/" +  $scope.itServiceId)
			.success(function(response){
				$scope.itService = response;
				console.log(">> itservice  (id = " + $scope.itService.id + ")");
				console.log(">> itserviceId  (id = " + $scope.itServiceId + ")");
		
		
		
		console.log(">> PARAM (id = " + id + ")");
		
		/*
		 * New case
		 */
		if (angular.isUndefined(id)) {
			
			// Do it this way because dataflow object is not available
			$scope.dataflow = {
					id: null,
					name: $scope.name,
					status: $scope.status,
					techno : $scope.techno,
					processId : $scope.processId, 
					onErrorsMessagesToday : $scope.onErrorsMessagesToday,
					treatedMessagesToday : $scope.treatedMessagesToday,
					itService : $scope.itService
			}
			console.log(">> itservice  (id = " + $scope.itService.id + ")");
			
			
			$http.put("rest/dataflows/", $scope.dataflow ).success(
					function(response) {
						// reinit data
						$scope.initFormVar();
						$scope.loadData();
						console.log(">> dataflow created");
					});
		} else {
		
			/*
			 * Update record
			 */
			$http.get("rest/dataflows/" + id)
			.success(
					function(response) {
						
						$scope.dataflow = response;
	
						$scope.dataflow.name = $scope.name;
						$scope.dataflow.status = $scope.status;
						$scope.dataflow.techno = $scope.techno;

						$scope.dataflow.itService = $scope.itService;
						
						$scope.dataflow.processId = $scope.processId; 
						$scope.dataflow.onErrorsMessagesToday = $scope.onErrorsMessagesToday;
						$scope.dataflow.treatedMessagesToday = $scope.treatedMessagesToday;
						
						// save record
						$http.put("rest/dataflows/", $scope.dataflow ).success(
								function(response) {
										// reinit data
										$scope.initFormVar();
										$scope.loadData();
										console.log(">> dataflow updated (id = " + id + ")");
									});
					});
			}
		
			});
	};


	
	/**
	 * Function : showError, presentation of validation errors
	 */
	$scope.showError = function(ngModelController, error) {
		return ngModelController.$error[error];
	};

	/**
	 * Function : canSave trigger the Save Changes button
	 */
	$scope.canSave = function() {
		console.log($scope.dataFlowForm.$valid);
		return  $scope.dataFlowForm.$valid;
	};
  
});
