package com.organization.mvcproject.api.service;

import java.util.List;

import com.organization.mvcproject.api.model.Game;


public interface GameService {

	List<Game> retrieveAllGames();

	Game saveGame(Game game);
	
	Boolean deleteGame(Game game);
	
	Game updateGame(Game game);

	List<Game> findRequestedGames(List<String> filters);

	List<String> getGenres();

}
