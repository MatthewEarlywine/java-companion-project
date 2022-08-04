package com.organization.mvcproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organization.mvcproject.api.service.GameService;
import com.organization.mvcproject.dao.MockGameDAOLoopsImpl;
import com.organization.mvcproject.api.model.Game;


@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	private MockGameDAOLoopsImpl mockGameDAO;
	
	@Override
	public List<Game> retrieveAllGames() {
		return mockGameDAO.retrieveAllGames();
	}

	@Override
	public Game saveGame(Game game) {
		//logic to save game
		return mockGameDAO.saveGame(game);
	}

	@Override
	public Boolean deleteGame(Long gameId) {
		return mockGameDAO.deleteGameById(gameId);
	}

	@Override
	public Game updateGame(Game game) {
		//logic to update game
		return mockGameDAO.saveGame(game);
	}

}