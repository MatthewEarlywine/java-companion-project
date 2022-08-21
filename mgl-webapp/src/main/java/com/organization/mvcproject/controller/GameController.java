package com.organization.mvcproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.organization.mvcproject.api.model.Game;
import com.organization.mvcproject.api.service.GameService;
import com.organization.mvcproject.model.GameImpl;



@RestController
@RequestMapping(value = "/game")
public class GameController {

	@Autowired
	private GameService gameService;

	
	@PostMapping(value = "/", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Game>> fetchGames(@RequestBody List<String> filters) {
		return new ResponseEntity<>(gameService.findRequestedGames(filters), HttpStatus.OK);
	}

	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createGame(@RequestBody GameImpl game) {
		gameService.saveGame(game);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	
	@PostMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> deleteGame(@RequestBody GameImpl game){
		gameService.deleteGame(game);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping(value = "/genres")
	public ResponseEntity<List<String>> getGenres() {
		System.out.println("Hey! The Data went out through GameController.java!");
		return new ResponseEntity<>(gameService.getGenres(), HttpStatus.OK);
	}
	
}