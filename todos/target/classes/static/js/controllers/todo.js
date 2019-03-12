angular.module('myApp').controller('ToDoController', ['$scope', '$http', function($scope, $http) {

    /**
     * Format Url
     * @returns formatted api url string with a page param
     */
    function formatUrl() {
        return $scope.api + '?page=' + $scope.page;
    };

    /**
     * toDos - cache all returned elements
     * toDosFiltered - elements rendered in html
     * filterStr - filter type string, only has 3 valid states so far, 'all', 'completed', 'inProgress'
     * api - restful api endpoint string
     * page - paging number as a request param when making api call
     * disableLoadButton - disable 'Load More' button during http call, prevent race condition
     */
    $scope.toDos = [];
    $scope.toDosFiltered = [];
    $scope.filterStr = 'all';
    $scope.api = 'http://localhost:8080/todos';
    $scope.page = 1;
    $scope.disableLoadButton = true;

    /**
     *  Fetch todos from backend api and then apply the current filter
     *  Page number will be increased by 1 if the endpoint returns no err
     *  Load button will be disabled during http call in order to prevent race condition
     *  This method will also be called when the ng-controller initialized
     *  Refer to index.html:
     *  <div ng-controller="ToDoController" ng-init="fetchToDos()">
     */
    $scope.fetchToDos = function () {
        $scope.disableLoadButton = true;
        $http.get(formatUrl()).then(function (res, err) {
            if(!err) {
                $scope.toDos = $scope.toDos.concat(res.data);
                $scope.filter($scope.filterStr);
                $scope.page = $scope.page + 1;
            }
            $scope.disableLoadButton = false;
        });
    };

    /*
     * Apply filters
     * Only accepts 3 valid states, 'all', 'completed', 'inProgress'
     * If arg=null or undefined, it returns directly without any action
     * @param str
     */
    $scope.filter = function(str) {
        if(str) {
            $scope.filterStr = str;
        } else {
            return;
        }
        if(str == 'all') {
            $scope.toDosFiltered = $scope.toDos;
        } else if (str == 'completed') {
            $scope.toDosFiltered = $scope.toDos.filter(function(todo){
                return todo.completed == true;
            });
        } else if (str == 'inProgress') {
            $scope.toDosFiltered = $scope.toDos.filter(function(todo){
                return todo.completed == false;
            });
        } else {
            console.log("filter(str): invalid argument");
        }
        return;
    }

}]);