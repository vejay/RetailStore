var app = angular.module('retailStoreApp', []);

app.controller('retailStoreCtrl', function($scope, $http){
    $scope.values = ['Store1', 'Store2', 'Store3'];


    var successFn = function(response) {
      console.log("Status = " + response.status +  " " + response.statusText);
      alert('Successfully created Store!');
      $scope.newStore = {};
    }

    var errorFn = function(error) {
        console.log("Error encountered = " + JSON.stringify(error));
    }

    var getPayload = function(data) {
        var payload = {};
        payload.storeName = data.name;
        payload.address = {};
        payload.address.address1 = data.address1;
        payload.address.address2 = data.address2;
        payload.address.zipCode = data.zipCode;
        payload.parentStoreId = data.parentStore;

        return payload;
    }

    $scope.createStore = function() {
        console.log($scope.newStore);

        var data = $scope.newStore;
        var payload = getPayload(data);

        $http.post('/chainStore', payload).then(successFn, errorFn);
    }

    $scope.listAll = function() {
        console.log("list all called");
        $http.get('/chainStore/all').then(function(response) {

            console.log("response = " + JSON.stringify(response));
            var data = response.data;

            $scope.allStores = [];
            data.forEach(function(d){
                $scope.allStores.push(d);
            });

        }, function(error){
            console.log("error is " + JSON.stringify(error));
        });
    }

    $scope.clearAllStoresView = function() {
         $scope.allStores = false;
    }

});