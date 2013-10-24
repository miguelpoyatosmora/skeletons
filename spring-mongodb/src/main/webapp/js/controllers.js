'use strict';

var phonecatApp = angular.module('angularApp', []);

phonecatApp.controller('EventCtrl', function PhoneListCtrl($scope,$http) {


  $http.get('rest/events/').success(function(data) {
       $scope.events = data;
  });

  $scope.create = function(event){
        $http.post('rest/event/',event).success(function(id) {
            alert(id);
        });
      };

  $scope.open = function(id){
    $http.get('rest/event/'+id).success(function(data) {
        $scope.event = data;
    });
  };
});
