'use strict';

angular.module('GameLibraryApp').factory('LibraryService', ['$http', function($http) {

		var REST_SERVICE_URI = 'game/';

		var factory = {
			fetchRequestedGames : fetchRequestedGames,
			createGame : createGame,
			deleteGame : deleteGame,
			getGenres : getGenres
		};

		return factory;

		function fetchRequestedGames(filters) {
			return $http.get(REST_SERVICE_URI, filters).then((response) => response.data);
		}

		function createGame(game) {
			return $http.post(REST_SERVICE_URI, game).then(function(response) {
					return response.data;
				}
			);
		}

		function deleteGame(game) {
			return $http.post(REST_SERVICE_URI + 'delete', game).then((response) => response.data);
		}
		
		function getGenres() {
			return $http.get(REST_SERVICE_URI + 'genres').then((response) => response.data);
		}
}]);
