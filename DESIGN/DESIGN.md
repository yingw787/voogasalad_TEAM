
CS308 Design: VOOGASalad
===================

##Introduction

The problem that we are trying to solve by writing this program is to create a game development environment generic enough to design and deploy a tower defense game, that would be simple enough to be used by a non-expert in JavaFX. 

Our primary design goals include the simplicity and ease of use for the user interface, flexibility for the user game developer in adding in attributes to the core elements of the game, extensibility for any third-party developers that want to use or generalize our platform for different purposes, and enough depth in the developed game to make it engaging and fun for the user who plays the game. 

The architecture of our design should support the open/closed principle (open for extension, closed for modification). The user should be allowed to extend different types of units (board pieces in the game) as well as the different parameters for different towers. The user should not be allowed to modify the way the model communicates with the view and as extension any of the back-end logic in general, how the game data format is saved in terms of the XML, the core components of the game development environment, etc. 
 
Our chosen game genre is tower defense games. A subset of real-time strategy games, tower defense games are unique in that they have a mostly or all static level design for each level, as the only variable that changes is what units are available for the user game player to use, as well as the units available for the opponent. Tower defense games are also unique in that most of the units available to the player are static and strategically positioned on the board, while the enemy is mobile but goes along a predictable path that can be intercepted. The only interaction users have with the game is creating a unit and placing it on the board; the unit does all of the fighting autonomously. The win condition for a tower defense game is to prevent enough enemies from moving from the start to the end for a given number of levels, while the lose condition is if enough enemies pass through the defenses. 

The attributes of tower defense games narrow down our game development environment to support a set of traits. For example, since the interaction between the user and the game is limited to placing the units down onto the board, the game development environment should support a toolbar that allows the user to view, choose, purchase, and place any kind of tower that is currently available to them. The game should then support the towers functioning by themselves. The nature of setting a path, or having a specific path, implies the need for the user game developer to create a certain path, and the need to verify a win/lose condition implies that the game needs to check that the path is valid (i.e. connects a given start and end point, and does not cross the path without special path blocks and does not go off the map). The nature of the levels implies that if the game were to save at a particular time, it would save after each wave of opponents is done. 

##Overview

<p align = "center">
	<img src = "/DESIGN/voogasalad_TEAM_HighLevelUML.png" />
</p>


The main modules that we intend to create are the game engine, game player, and authoring environment, as well as a place to hold all the created games called game data. The authoring environment is an interface which allows a user to create a game, which is then exported to the game data once completed. From there, we will have a "store" interface that is populated with user created games, which can then be launched. Games are launched by being sent to the game engine, which serves as the main "back end" of our project. The game engine then interfaces with the game player through a controller, sending the game player objects to be represented in a GUI for a player to interact with. 




##User Interface

<p align = "center">
	<img src = "/DESIGN/launcherDiagram.PNG" />
</p>

When the program is initially run, a GUI will pop up that asks the user whether they would like to create a game or play a game. If they choose to create a game, then the authoring environment will open. If they choose to play a game, a file dropdown will appear that is populated with a list of already completed games from game data. From there, selecting a game will load the xml into a new instance of the game engine, which will launch the game player.

###Player


<p align = "center">
	<img src = "/DESIGN/playerDiagram.PNG" />
</p>

For the game player, the main interface will include a menu labeled "New Game" which will allow the player to select a new game to play. Clicking it will bring down a drop down with a list of completed games populated from Game Data, and will reload an xml into the game editor and refresh the game player if a new game is selected. When a new game is loaded, on the left of the screen the map will be displayed, with the background image and maps. On the right will be a plane of player information such as health, gold, and the current level/wave. On the bottom of the screen will be two tabs of Troops that can be hired and Towers that can be bought. These tabs will only be present if troops can be hired.

Clicking these tabs will change the bottom of the window, which is a scrolling bar populated with all the possible towers that can be purchased, or the troops that can be hired. Anything the player does not have enough gold to purchase will be grayed out and unclickable. To place a tower on the board, the player will select a tower which is then highlighted, and then click an area of the map. If this is a legal place to place the tower, it will then appear on the map. To hire a troop, the player will click on a troop, which is then highlighted, and then click a "buy" button. 

###Authoring Environment

<p align = "center">
	<img src = "/DESIGN/editorDiagram.png" />
</p>

When the authoring environment launches, it consists of a menu bar on the top that has multiple dropdown menus for user configuration options, such as starting over in creating a game and other such options. On the left, it has a blank map, and on the right it has a variety of tabs that denote all the different objects that the user can create for placement on the board. From the menu, the user can choose to load a background image for the map. 

Clicking tabs change the right side of the screen to represent different configuration options. For instance, at first when the user clicks the "Tower" tab, it is an empty scrolling pane that simply has a button that gives the user an option to add a new Tower. Clicking this button brings up a popup, which allows the user to load an image and set the stats of the tower. Saving this tower then populates the right side of the screen with created towers, with options to edit or delete said towers. This is the same for Allies, Enemies, and Bases. Clicking the Player tab brings up options to configure things like player health, lives, and initial gold. Clicking the Config tabs gives the user options to configure things like the goals of the game and what sort of tower defense game the game is. Finally, there will be a paths tab that the user can also select. Paths can be created by clicking on the left map, which will create lines that delineate the paths that the enemies can walk on.

##Design Details


##Example games


One type of tower defense game is the type where enemies are generated to walk around a map on a predetermined map. The player can then place towers alongside the path and defend a main base. Another type of game is that where enemies walk linearly along a map and towers can be placed anywhere, including in their path. Finally, another type of game is that where the player can not only defend a base, but also purchase units to attack an enemy base. 



##Design Considerations



##Team Responsibilities

Ying, Cheng, and Wanning plan to take primary responsibility for the game engine. Dennis, William, and Susan plan to take primary responsibility for the authoring environment. Vanessa, Jaidev, and Abhishek plan to take primary responsibility for the game player. Main leaders from each module will be chosen for ease of communication, and Ying plans to take primary responsibility for high-level logistics of the project. 


