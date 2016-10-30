var app = angular.module('retailStoreApp', ['ngMaterial'])
.config(function($mdThemingProvider) {
  $mdThemingProvider.theme('Raised')
    .primaryPalette('light-blue')
    .accentPalette('orange');
});

app.controller('retailStoreCtrl', function($scope, $http, $q){

    var that = this;
    var chainStores = [];

    $scope.values = ['Store1', 'Store2', 'Store3'];
    $scope.storeTypes = ['CHAIN', 'FRANCHISE'];
    $scope.chainStores = [];
    $scope.status = false;

    function getAndUpdateChainStores() {
        $http.get('/store/chain/all').then(function(resp) {
            var data = resp.data;

            var chainStoreIds = chainStores.map(function(c){
                return c.id;
            });

            data.forEach(function(d) {
                if(chainStoreIds.indexOf(d.id) == -1) {
                    chainStores.push({"id": d.id, "name": d.name});
                }
            });
            updateParentDropDown(chainStores);
         },
         function(error, resp) {
            console.log(error);
        });
    }

    function alphaSort(a, b) {
        if(a < b) {
            return -1;
        }
        if(a > b) {
            return 1;
        }
        return 0;
    }

    function updateParentDropDown(allChains) {
        console.log("updateParentDropDown called");
        $scope.newStore.parentStores = [];

        var chainStoreIds  = $scope.chainStores.map(function(c){
            return c.id;
        });

        allChains.filter(function(d){
            return chainStoreIds.indexOf(d.id) == -1;
        }).sort(alphaSort).forEach(function(d) {
            $scope.chainStores.push(d);
        });
    }

    var successFn = function(response) {
      console.log("Status = " + response.status +  " " + response.statusText);
      $scope.status = {success: true};


      that.createFieldForm.$setPristine(); //clears the fields
      $scope.newStoreName = response.data.name;

      if($scope.allStores) {
        $scope.allStores.push(response.data);
      }

    }

    var errorFn = function(error, status) {
        console.log("Error encountered = " + JSON.stringify(error));
        $scope.status = {error:true, message: error};
    }

    var getPayload = function(data) {
        var payload = {};
        payload.id = data.id;
        payload.name = data.name;
        payload.localName = data.localName;
        payload.type = data.storeType;

        payload.address = {};
        payload.address.address1 = data.address1;
        payload.address.address2 = data.address2;
        payload.address.zipCode = data.zipCode;
        if(data.parentStore) {
            payload.parentStoreName = data.parentStore.name;
        }
        payload.length = data.length;
        payload.width = data.width;
        payload.height = data.height;

        console.log("data.parentStoreId = " + data.parentStore);
        return payload;
    }

    $scope.storeTypeUpdated = function() {
        $scope.newStore.id = "";
        $scope.newStore.name = "";
        $scope.newStore.address1 = "";
        $scope.newStore.address2 = "";
        $scope.newStore.zipCode = "";
        $scope.status = null;

        if($scope.newStore.storeType == 'FRANCHISE') {

            $scope.newStore.localName = "";
            $scope.newStore.length = "";
            $scope.newStore.width = "";
            $scope.newStore.height = "";

            getAndUpdateChainStores();
        }
    }

    $scope.generateId = function() {
        var newStore = $scope.newStore;
        if(newStore && newStore.storeType) {
            var data = $scope.newStore.storeType;
            $http.get('/idgenerator/' + data).then(function(response) {
                var id = response.data;
                $scope.newStore.id = id;
            }, function(error, status) {
                    console.log("ERROR : " + JSON.stringify(error));
            });
        } else {
            console.log("No newStore or type defined");
        }

    }

    $scope.createStore = function() {
        console.log($scope.newStore);

        var data = $scope.newStore;
        console.log("parentStore = " + $scope.newStore.parentStore);
        var payload = getPayload(data);
        $http.post('/store', payload).then(successFn, errorFn);
    }

    $scope.listAll = function() {
        console.log("list all called");
        $http.get('/store').then(function(response) {
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
         $scope.allStores = [];
    }

});