'use strict';

var phonecatApp = angular.module('angularApp', []);

phonecatApp.controller('EventCtrl', function PhoneListCtrl($scope, $http) {

    $scope.newEvent = {
        "name": null,
        "date": null,
        "time": null
    };
    $http.get('rest/events/').success(function (data) {
        $scope.events = data;
    });

    $scope.create = function () {

        console.log($scope.newEvent.date + " " + $scope.newEvent.time);
        var time = new Date($scope.newEvent.date + " " + $scope.newEvent.time).getTime();

        $http.post('rest/event/', {
            "name": $scope.newEvent.name,
            "time": time
        }).success(function (id) {
            $scope.events.push({
                "id": eval(id),
                "name": $scope.newEvent.name,
                "time": time,
                "likes": 0
            })
        });
    };

    $scope.like = function (id) {
        $http.put('rest/event/' + id).success(function () {
            for (var i = 0; i < $scope.events.length; i++) {
                if ($scope.events[i].id.indexOf(id) != -1) {
                    $scope.events[i].likes++;
                }
            }
        });
    };
});

phonecatApp.directive('datepicker', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, element, attrs, ngModelCtrl) {
            element.datepicker({
                dateFormat: 'MM dd, yy',
                onSelect: function (date) {
                    scope.newEvent.date = date
                    element.value = date;
                }
            });
        }
    };
});
phonecatApp.directive('timepicker', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
        link: function (scope, element, attrs, ngModelCtrl) {
            $(element).on("change", function (data) {
                scope.newEvent.time = data.target.value;
            });
            element.timepicker({
                "timeFormat": "G:i:s"
            });
        }
    };
});