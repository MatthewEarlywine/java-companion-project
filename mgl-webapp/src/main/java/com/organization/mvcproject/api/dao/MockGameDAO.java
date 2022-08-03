package com.organization.mvcproject.api.dao;

import java.util.List;

import com.organization.mvcproject.api.model.Game;
import com.organization.mvcproject.model.GameImpl;

public interface MockGameDAO {
	
	List<GameImpl> retrieveAllGames();

	GameImpl saveGame(GameImpl game);

	boolean deleteGameById(Long idOfGameToDelete);
	
	Game findGameById(Long gameIdToFind);
	
	List<Game> findGamesByGenre(String genre);

}
