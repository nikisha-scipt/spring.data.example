angular.module('product-gui', []).controller('appController', function ($scope, $http) {

    const context = 'http://localhost:22333/app/product/';
    let currentPage = 1;

    $scope.loadProducts = function (pageIndex = currentPage) {
        $http({
            url: context + 'allPage',
            method: 'GET',
            params: {
                p: pageIndex
            }
        }).then(function (response) {
            console.log(response)
            currentPage = pageIndex;
            $scope.products = response.data;
            $scope.paginationArray = $scope.generatePagesIndexes(1, 3);
        });
    }

    $scope.deletePage = function (id) {
        $http({
            url: context + 'del/' + id,
            method: 'GET'
        }).then(function (resp) {
            console.log(resp.data);
            $scope.loadProducts(currentPage);
        })
    }

    $scope.increment = function () {
        currentPage++;
        if (currentPage > $scope.products) {
            currentPage = $scope.products;
        }
        $scope.loadProducts(currentPage);
    }

    $scope.decrement = function () {
        currentPage--;
        if (currentPage < 2) {
            currentPage = 1;
        }
        $scope.loadProducts(currentPage);
    }

    $scope.generatePagesIndexes = function (startPage, endPage) {
        let arr = [];
        for (let i = startPage; i <= endPage; i++) {
            arr.push(i);
        }
        return arr;
    }

    $scope.createNewProduct = function () {
        $http.post(context + 'save', $scope.new_product)
            .then(function successCallback(response) {
                    $scope.loadProducts(currentPage);
                    $scope.new_product = null;
                }, function failCallback(response) {
                    alert(response.data.message);
                }
            );
    }

    $scope.loadProducts(currentPage);

});