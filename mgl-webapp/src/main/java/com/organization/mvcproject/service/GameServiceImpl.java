package com.organization.mvcproject.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.organization.mvcproject.api.service.GameService;
import com.organization.mvcproject.dao.MockGameDAOImpl;
import com.organization.mvcproject.model.GameImpl;


@Service
public class GameServiceImpl implements GameService {
	
	@Autowired
	private MockGameDAOImpl mockGameDAO;
	
	@Override
	public List<GameImpl> retrieveAllGames() {
		return mockGameDAO.retrieveAllGames();
	}

	@Override
	public GameImpl saveGame(GameImpl game) {
		//logic to save game
		return mockGameDAO.saveGame(game);
	}

	@Override
	public Boolean deleteGame(Long gameId) {
		return mockGameDAO.deleteGameById(gameId);
	}

	@Override
	public GameImpl updateGame(GameImpl game) {
		//logic to update game
		return mockGameDAO.saveGame(game);
	}

}