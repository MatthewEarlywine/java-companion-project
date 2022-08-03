package com.organization.mvcproject.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.organization.mvcproject.model.GameImpl;

@Repository
public class MockGameDAO {
	
	private static Long gameId = new Long(0);
	private static Long companyId = new Long(0);
	private static List<GameImpl> games = new ArrayList<GameImpl>();

	static {
		games = populateGames();
	}

	private static List<GameImpl> populateGames() {

		GameImpl game1 = new GameImpl();
		game1.setId(++gameId);
		game1.setGenre("Sport");
		game1.setName("Rocket League");

		GameImpl game2 = new GameImpl();
		game2.setId(++gameId);
		game2.setGenre("Shooter");
		game2.setName("Halo 3");

		GameImpl game3 = new GameImpl();
		game3.setId(++gameId);
		game3.setGenre("MMORPG");
		game3.setName("Runescape");

		games.add(game1);
		games.add(game2);
		games.add(game3);

		return games;
	}

	public List<GameImpl> retrieveAllGames() {
		return games;
	}

	public GameImpl saveGame(GameImpl game) {
		
		//perform update if game has a valid id
		if(game.getId() != null) {
			GameImpl foundGame = findGameById(game.getId());
			if(foundGame != null) {
				for(int i = 0; i < games.size(); i++) {
					if(game.getId().equals(games.get(i).getId())) {
						games.set(i, game);
						return findGameById(game.getId());
					}
				}
			}
		}
		
		//create a new game
		game.setId(++gameId);
		games.add(game);
		
		return game;
	}

	public boolean deleteGameById(Long idOfGameToDelete) {
		for(int i = 0; i < games.size(); i++) {
			if(games.get(i).getId().equals(idOfGameToDelete)) {
				return games.remove(games.get(i));	
			}
		}
		return false;
	}
	
	public GameImpl findGameById(Long gameIdToFind) {
		for(GameImpl g : games) {
			if(gameIdToFind.equals(g.getId())) {
				return g;
			}
		}
		return null;
	}

}
