
/*******************************************************************************
 * service rest dataQueuesFactory
 */
var services = angular.module('intApp.services', ['ngResource']);

/**
 * data queues factory service
 */
services.factory('dataQueuesFactory', function ($resource) {
    return $resource('rest/dataqueues', {}, {
        query: { method: 'GET', isArray: true },
        create: { method: 'POST'}
    })
});

/**
 * data queue factory service single rows.
 */
services.factory('dataQueueFactory', function ($resource) {
    return $resource('rest/dataqueues/:id', {}, {
        show: { method: 'GET' },
        update: { method: 'PUT' },
        delete: { method: 'DELETE', params: {id: '@id'}}
    })
});

/**
 * data flow factory service single rows.
 */
services.factory('dataFlowsFactory', function ($resource) {
    return $resource('rest/dataflows', {}, {
        query: { method: 'GET', isArray: true }
    })
});

/**
 * data flow factory service multiple rows.
 */
services.factory('dataFlowFactory', function ($resource) {
    return $resource('rest/dataflows/:id', {}, {
    	show: { method: 'GET' }
    })
});


var intApp = angular.module('intApp', ['intApp.services']);


/*******************************************************************************
 * Data queue controller declaration with rest service
 * 
 * @param $scope
 * @param $http
 */
intApp.controller('dataqueueController', ['$scope', '$http', 'dataQueuesFactory', 'dataQueueFactory', 'dataFlowsFactory','dataFlowFactory',
function ($scope, $http, dataQueuesFactory, dataQueueFactory, dataFlowsFactory,dataFlowFactory) {

	// init scope vars
	$scope.edit = false;
	$scope.dataqueues = dataQueuesFactory.query();
	
	$scope.dataflows = dataFlowsFactory.query();
	
	/**
	 * Function : edit dataqueue
	 */
	$scope.editDataQueue = function(id) {
		$scope.dataqueue = dataQueueFactory.show({ id : id}, function() {
			$scope.edit = true;
			$scope.dataqueues = dataQueuesFactory.query();
		});
	};
	
	/**
	 * Function : delete data queue
	 */
	$scope.deleteDataQueue = function(id) {
		dataQueueFactory.delete({ id : id} , function() {
			$scope.dataqueues = dataQueuesFactory.query();	
		}) ;
		
	};
	
	$scope.createNewDataQueue = function() {
		$scope.edit = false;
		$scope.dataqueue=null;
		
	};
	
	/**
	 * Function udpate dataq queue
	 */
	$scope.updateDataQueue = function(id) {
		
		if ($scope.edit == true) {
			
			$scope.dataqueue.intDataFLow = dataFlowFactory.show({ id : $scope.dataqueue.intDataFLow.id} , function () {
				
				console.log(">>> " + $scope.dataqueue.intDataFLow.name);
				console.log(">>> " + $scope.dataqueue.name);
				
				dataQueueFactory.update($scope.dataqueue, function() {
					console.log(">>> " + $scope.dataqueue.name);
					
					$scope.dataqueues = dataQueuesFactory.query();
					$scope.edit = false;
					$scope.dataqueue = null;
					
				});
			});
		} else {
			$scope.dataqueue.intDataFLow = dataFlowFactory.show({ id : $scope.dataqueue.intDataFLow.id}, function () {
				
				console.log(">>> " + $scope.dataqueue.intDataFLow.name);
				console.log(">>> " + $scope.dataqueue.name);
				
				dataQueuesFactory.create($scope.dataqueue, function() {
					
					console.log(">>> " + $scope.dataqueue.name);
					
					$scope.dataqueues = dataQueuesFactory.query();
					$scope.edit = false;
					$scope.dataqueue = null;
				});
			});
		}
	};

	/**
	 * function that re-init application data (for demonstration purpose)
	 */
	$scope.initData = function() {
		$http.get("rest/dataflows/init")
				.success(function(response) {
					$scope.dataqueues = dataQueuesFactory.query();
					console.log(">> reinit data for test purpose");
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
		return  $scope.dataQueueForm.$valid;
	};
	
}]);



