'use strict';

angular.module('GameLibraryApp').controller('LibraryController',
		[ 'LibraryService', function(LibraryService) {  
			
			this.game = {
				gameId : '',
				gameName : '',
				gameGenre : ''
			};
			this.games = [];
			this.genres = ['All'];
			this.filters = new Set(['All']);
			this.sort = '';
			
			this.sortGameData = function(data) {
				if (this.sort === 'A') {
					data.sort((a, b) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
				} else if (this.sort === 'Z') {
					data.sort((b, a) => (a.name > b.name) ? 1 : ((b.name > a.name) ? -1 : 0));
				}

				return data;
			}

			this.fetchRequestedGames = function() {
				LibraryService.fetchRequestedGames(Array.from(this.filters)).then((data) => {
					this.games = this.sortGameData(data);
					this.getGenres();
					this.reset();
				});
			}

			this.getGenres = function() {
				LibraryService.getGenres().then((data) => { this.genres = data; });
			}
			
			this.toggleFilter = function(filter) {
				if (filter === 'All') {
					// if the selected filter is 'All', remove all other filters
					// clicking 'All' should NOT toggle
					this.filters.clear();
					this.filters.add(filter);
				} else {
					// if any other filter is selected, 'All' should be deselected
					this.filters.delete('All');
					if (this.filters.has(filter)) {
						this.filters.delete(filter)
					} else {
						this.filters.add(filter);
					}
				}

				// if we managed to deselect all filters, set it back to 'All'
				if (this.filters.size === 0) this.filters.add('All');

				// update visuals for selected filters
				this.showFilters();

				// update game list
				this.fetchRequestedGames();
			}

			this.showFilters = function() {
				// go through all elements and set selected attribute as needed
				for (const genre of this.genres) {
					document.getElementById('genredropdown-' + genre).classList.remove('selected');
				}
				for (const selFilter of this.filters) {
					document.getElementById('genredropdown-' + selFilter).classList.add('selected');
				}
			}
			
			this.setSort = function(sortType) {
				this.sort = sortType;
				this.showSort();
				this.fetchRequestedGames();
			}
	
			this.showSort = function() {
				for (const child of document.getElementById('nameFilterDropdown').children) {
					child.classList.remove('selected');
				}
				if (this.sort) {
					document.getElementById('namedropdown-' + this.sort).classList.add('selected');
				}
			}

			this.addGame = function() {
				return LibraryService.createGame(this.game).then(() => { this.fetchRequestedGames(); });
			}

			this.deleteGame = function(game) {
				return LibraryService.deleteGame(game).then(() => { this.fetchRequestedGames(); });
			}

			this.selectGame = function(game) {
				this.game = angular.copy(game);
			}

			this.reset = function() {
				this.game = {};
			}

			this.fetchRequestedGames();
		} ]);
