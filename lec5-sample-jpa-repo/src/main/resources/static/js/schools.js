var app = angular.module('schools', []);

app.controller("SchoolsController", function ($scope, $http) {

    $scope.getSchools = function () {
        $http.get('/public/rest/schools').success(function (data, status, headers, config) {
            $scope.schoolsList = data;
            for (var i = 0; i < $scope.schoolsList.length; i++) {
                $scope.schoolsList[i].edit = 0;
            }
        }).error(function (data, status, headers, config) {
            if (data.message === 'Time is out') {
                $scope.finishByTimeout();
            }
        });
    };

    $scope.delete = function (id) {
        $http.delete('/public/rest/schools/delete/' + id).success(function (data, status, headers, config) {
            for (var i = 0; i < $scope.schoolsList.length; i++) {
                var j = $scope.schoolsList[i];
                if (j.id === id) {
                    $scope.schoolsList.splice(i, 1);
                    break;
                }
            }
        }).error(function (data, status, headers, config) {
            console.error(status, data, headers);
        });
    };

    $scope.addSchool = function () {
        $http.post('/public/rest/schools/add/' + $scope.number + "/" + $scope.name).success(function (data, status, headers, config) {
            $scope.schoolsList.splice(0, 0, data);
        }
        ).error(function (data, status, headers, config) {
            console.error(status, data, headers);
        });

    };
});
