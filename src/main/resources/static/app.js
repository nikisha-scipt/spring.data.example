angular.module('product-gui', []).controller('appController', function ($scope, $http) {
    const context = 'http://localhost:22333/app/product/';
    let count = 1;

    $scope.loadProducts = function (pageIndex = count) {
        $http({
            url: context + 'allPage',
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            console.log(response)
            $scope.products = response.data;
        });
    }

    $scope.deletePage = function (id) {
        $http({
            url: context + 'del/' + id,
            method: 'GET'
        }).then(function (resp) {
            console.log(resp.data);
            $scope.loadProducts(count);
        })
    }

    $scope.increment = function () {
        count++;
        $scope.loadProducts(count);
    }

    $scope.decrement = function () {
        count--;
        $scope.loadProducts(count);
    }

    $scope.loadProducts(count);

});