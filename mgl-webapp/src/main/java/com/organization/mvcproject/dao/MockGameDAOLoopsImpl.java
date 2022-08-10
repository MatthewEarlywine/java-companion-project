package com.organization.mvcproject.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.organization.mvcproject.api.dao.MockGameDAO;
import com.organization.mvcproject.api.model.Game;
import com.organization.mvcproject.model.GameImpl;

@Repository
public class MockGameDAOLoopsImpl implements MockGameDAO{
	
	private static Long gameId = new Long(0);
	private static Long companyId = new Long(0);
	private static List<Game> games = new ArrayList<>();

	static {
		games = populateGames();
	}

	private static List<Game> populateGames() {

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

	public List<Game> retrieveAllGames() {
		return games;
	}

	public Game saveGame(Game game) {
		
		//perform update if game has a valid id
		if(game.getId() != null) {
			Game foundGame = findGameById(game.getId());
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

//	public boolean deleteGameById(Long idOfGameToDelete) {
//		for(int i = 0; i < games.size(); i++) {
//			if(games.get(i).getId().equals(idOfGameToDelete)) {
//				return games.remove(games.get(i));	
//			}
//		}
//		return false;
//	}
	
	@Override
	public List<Game> findRequestedGames(List<String> filters) {
		if (filters.size() == 1 && filters.get(0).equals("All")) return this.retrieveAllGames();		
		return games.stream().filter(g -> filters.contains(g.getGenre())).collect(Collectors.toList());
	}
	
	public List<Game> findGamesByGenre(String genre) {
		//for loop
		List<Game> gamesOfGenre = new ArrayList<>();
			for(int i = 0; i < games.size(); i++) {
				if( genre == games.get(i).getGenre()) {
					gamesOfGenre.add(games.get(i));
				}
			}
			
			return (gamesOfGenre.isEmpty()) ? null : gamesOfGenre;
	}

	@Override
	public Game findGameById(Long gameIdToFind) {
		return games.stream().filter(g -> g.getId().equals(gameIdToFind)).findFirst().orElse(null);
	}

	public List<String> getGenres() {
		List<String> genreList = games.stream().map(Game::getGenre).distinct().collect(Collectors.toList());
		genreList.add(0, "All");
		return genreList;
	}

	@Override
	public boolean deleteGame(Game game) {		
		return games.removeIf(g -> g.getId().equals(game.getId()));
	}

}
