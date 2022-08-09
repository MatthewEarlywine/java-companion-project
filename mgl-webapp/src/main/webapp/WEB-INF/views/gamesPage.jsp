<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="mgl" %>
    <!DOCTYPE html>
    <html>

    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.7.8/angular.min.js"></script>
        <script src="resources/static/js/app.js" /></script>
        <script src="resources/static/js/game.service.js"></script>
        <script src="resources/static/js/game.controller.js"></script>
        <script src="resources/static/js/dropdown.controller.js"></script>

        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
       	 	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" 
        	crossorigin="anonymous">
        	
        <!-- Filter icon stylesheet -->
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

        <!-- Our own stylesheet -->
		<link rel="stylesheet" href="resources/style.css">

		<title>Mist Library : Games</title>
        <style type="text/css">
            body {
            	background-image:
            		url('https://ak6.picdn.net/shutterstock/videos/1024598666/thumb/1.jpg');
            	background-repeat: no-repeat;
            	background-size: cover;
            }
        </style>
        <link rel="apple-touch-icon" sizes="180x180" href="/android-chrome-192x192.png">
    	<link rel="icon" type="image/png" sizes="32x32" href="resources/static/images/favicon-32x32.png">
    	<link rel="icon" type="image/png" sizes="16x16" href="resources/static/images/favicon-16x16.png">
    </head>

    <body ng-app="GameLibraryApp" class="ng-cloak">
        <mgl:myNav/>
        <br>
        <div class="container" ng-controller="LibraryController as ctrl">
            <div class="panel panel-default">
                <div class="panel-heading text-light"><span class="lead">Game Registration Form </span></div>
                <div class="formcontainer">
                    <form ng-submit="ctrl.addGame()" name="gameForm" class="form-horizontal">
                        <input type="hidden" ng-model="ctrl.game.id" />
                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-lable text-light" for="game_name">Name*</label>
                                <div class="col-md-7">
                                    <input type="text" ng-model="ctrl.game.name" id="game_name" class="game_name form-control input-sm" placeholder="Enter the name of the new game [required]" required ng-minlength="3" />
                                    <div class="has-error" ng-show="gameForm.$dirty">
                                        <span ng-show="gameForm.game_name.$error.required">This is a required field</span>
                                        <span ng-show="gameForm.game_name.$error.minlength">Minimum length required is 3</span>
                                        <span ng-show="gameForm.game_name.$invalid">This field is invalid </span>
                                    </div>
                                </div>
                            </div>
                        </div>


                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-2 control-lable text-light" for="game_genre">Game Genre</label>
                                <div class="col-md-7">
                                    <input type="text" ng-model="ctrl.game.genre" id="game_genre" class="form-control input-sm" placeholder="Enter the genre of the new game" />
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-actions floatRight">
								<!-- If a game has an id, we are updating. Otherwise we are adding a new game -->
								<input ng-if="ctrl.game.id" type="submit" value="Update" class="btn btn-primary btn-sm">
								<input ng-if="!ctrl.game.id" type="submit" value="Add" class="btn btn-primary btn-sm">
								<input ng-click="ctrl.reset()" type="reset" value="Clear" class="btn btn-secondary btn-sm">
                            </div>
                        </div>
                    </form>
                </div>
            </div>
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading text-light"><span class="lead">List of all current games</span></div>
                <div class="tablecontainer">
                    <table class="table table-dark table-striped text-light">
                        <thead>
                            <tr>
							<th width="10%"></th>
							<th width="10%"></th>
							<th width="40%">Game Name
								<div class="dropdown">
									<button onclick="showNameFilter(event)" class="btn dropbtn" style="background-color:transparent">
										<span class="fa fa-filter" style="color:gray"></span>
									</button>
									<div id="nameFilterDropdown" class="dropdown-content">
										<a ng-click="ctrl.setSort('A')" id="namedropdown-A" href="#">Sort A to Z</a>
									    <a ng-click="ctrl.setSort('Z')" id="namedropdown-Z" href="#">Sort Z to A</a>
									    <a ng-click="ctrl.setSort('')" href="#">Clear</a>
									</div>
								</div>
							</th>
							<th width="40%">Game Genre 
								<div class="dropdown">
									<button onclick="showGenreFilter(event)" ng-click="ctrl.showFilters()" class="btn dropbtn" style="background-color:transparent">
										<span class="fa fa-filter" style="color:gray"></span>
									</button>
									<div id="genreFilterDropdown" class="dropdown-content">
										<a ng-repeat="genre in ctrl.genres" ng-click="ctrl.toggleFilter(genre)" ng-attr-id="{{'genredropdown-' + genre}}" href="#"><span ng-bind="genre"></span></a>
									</div>
								</div>
							</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr ng-repeat="currentGame in ctrl.games">
								<td><button ng-click="ctrl.deleteGame(currentGame)" class="btn btn-danger btn-sm">Delete</button></td>
								<td><button ng-click="ctrl.selectGame(currentGame)" class="btn btn-secondary btn-sm">Update</button></td>
								<td><span ng-bind="currentGame.name"></span></td>
								<td><span ng-bind="currentGame.genre"></span></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

    </body>
    <script type="text/javascript"></script>

    </html>