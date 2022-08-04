package com.organization.mvcproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.organization.mvcproject.api.model.Game;
import com.organization.mvcproject.api.model.Review;
import com.organization.mvcproject.api.service.GameService;
import com.organization.mvcproject.model.GameImpl;
import com.organization.mvcproject.model.ReviewImpl;

@Controller
@RestController
public class GameController {

	@Autowired
	private GameService gameService;

	
	@RequestMapping(value = "/game", method = RequestMethod.GET)
	public ResponseEntity<List<Game>> fetchAllGames() {
		return new ResponseEntity<List<Game>>(gameService.retrieveAllGames(), HttpStatus.OK);
	}

	@RequestMapping(value = "/game", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createGame(@RequestBody GameImpl game) {
		gameService.saveGame(game);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteGame(@PathVariable("id") Long id){
		return new ResponseEntity<>(gameService.deleteGame(id), HttpStatus.OK);
	}
	
	
	
	
	
	
	
}