
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

###Game player
Game Player module loads the game data and uses game engine to run a particular game. It consists of a view where the game that is created by the user is loaded into and played from. The game player communicates with Game Engine to get the information from the XML to display as well as sends information to the Game Engine with the updates from the player. Game player also has options for player to make changes and add components into the game during runtime. These changes will then be passed onto the game engine to update the XML files for the game. Similarly, if the player wishes to save the game in its current state, then they could do show and open it at the that same state again. To do so, the game player would create a new XML file in the game data for the game, so when the player opens it using game engine, the exact states will be displayed in the view. The communication flow is shown in the high level UML diagram.

It will have a main Player class in the module, as well as a subclass called View that holds the primary UI elements. The View will also have subclasses that manage different aspects of the GUI such as the map, the player information, the scrolling pane of towers/troops, and the menu at the top of the screen. It will use resource files in order to add text to the GUI and will be populated with a list of potential towers/troops from the back end, which is read in from the Game Data XML files. The main Player class takes care of the communication between the Game Player and Game Engine through its API.
 
The main Player class will have the methods to communicate about the state of the game. populate(List<Unit> store) would be used to get the list of all the objects that can be purchased. Similarly, updateMap(List<Unit> units) method would be used to pass the player all of the objects that exist in the back end to update the front end. This allows the front end to display all the objects that exist in the back end. updateUserInfo(UserInfoStatus uis) passes the player the user's current information like gold, health, etc. To tell the player if the win condition has been met showWin() would be called. Finally, to call if the lose condition has been met, showLose() would be called.

List is used to get the data from the back-end (Game Engine) and display the objects as their state in the view. The data structures in the main Player class depends on the data structure of objects which are passed from the Game Engine.


###Game Engine

Game Engine is the model where the game state is saved at every point during play. Game Engine is intended to be able to play finished games from a game store (which is a collection of finished games from Game Data), or be able to play prototyped games from the Authoring Environment. Game Engine will send a representation of the game state over to the Game Player such that Game Player will be able to represent the game at any given point in time to the user game player. This includes the pieces that are currently on the board as well as any parameters they have, the status of the user game player including how much money they gave, what level they are currently on, and other information crucial for the logical following of the game. In return, Game Player updates the Game Engine on what the user game player does and what needs to be added to the representation of the game, for example when the user game player adds a tower to the game board. 

Game Engine is intended to be modularized into components that represent corresponding elements on the Game Player. Therefore, besides a top-level GameEngine() class, there will likely also be a GameEngineBoard() class for the back-end representation of the game board, GameEnginePlayerInformationStatus() class for the back-end representation of the user game player, and a GameEngineAvailableUnitToolbar() class for the back-end representation of the toolbar of available units for the user game player to choose from. Finally, helper classes like GameEngineXMLParser() may also be needed in order to process data from Game Data or communicate with other elements of the project. 

The methods that correspond to Game Engine would correspond to what functionality the classes that correspond to Game Engine need. For example, it is likely that there will be a play() class that will instruct the engine to begin playing the game that the game user player has decided to play or test. There will also be a retrieveAndParseGameData() method in order to retrieve and parse data from Game Data in order to create the game state. There may be a saveGameState() where the current game state will be saved into Game State. Most importantly, there will be update() method where the Game Engine will pass the updated game state to the Game Player, as well as a handleRequest() method to handle request methods from the Game Player in terms of player actions. 

There will likely be a hashed data structure of the different types of tower and unit objects available to the player.



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

###Game Player

The player will have a main Player class in the module, as well as a subclass called View that holds the primary UI elements. The View will also have subclasses that manage different aspects of the GUI such as the map, the player information, the scrolling pane of towers/troops, and the menu at the top of the screen. It will use resource files in order to add text to the GUI and will be populated with a list of potential towers/troops from the back end, which is read in from the Game Data XML files. 

Game Player module only communicates with Game Engine module. This is done using observers and observables. Most of the components in the view can be extended to include additional features. The tabs and viewboxes would have an abstract class that could be used if any extension needs to be made. 

The Game Player will support the ability for the player to see game high scores through interfaces in the GUI. It will also have a HUD in the right side of the screen displaying the player stats as the game goes on, which is updated from the back end. The user will also have the option from the menu bar to replay and switch games, and naturally from the menu will be able to see which games are available. We will also try to support save states for the player from the menu bar, which will be done by indicating to the back end that an xml needs to be saved.

We decided to create modules depending on the way it was described in the project description. Having a Game Player module makes sense because this distinguishes between different roles in the project. It also makes each module do one specific work, hence the modules can have extendibility functions individually. It also makes sense to encapsulate the front end of the game in a specific class. Furthermore, the Game Player module’s GUI largely consists of subclasses that are placed into the one main View subclass. so it is easily extensible by adding more subclasses that are in charge of new GUI element.


##Example games

One type of tower defense game is the type where enemies are generated to walk around a map on a predetermined map. The player can then place towers alongside the path and defend a main base. Another type of game is that where enemies walk linearly along a map and towers can be placed anywhere, including in their path. Finally, another type of game is that where the player can not only defend a base, but also purchase units to attack an enemy base.

The main difference between these games is the paths: meandering vs. linear across the screen. This is supported by our design because it is up to the user how paths can be created and how many points it takes to define a path- so they can choose to make multiple paths across the screen, or they can choose to use many points to create one or more paths that wander around the screen. There are also a multitude of winning conditions in these games, such as simply defending a base for a certain number of enemies or perhaps by taking down an enemy base. Our authoring environment will be able to support the ability to define how a win condition is met and how the user plays, such as whether they can purchase supporting troops or not in addition to towers. 

##Design Considerations

While class inheritance will definitely be closely observed and critiqued by all developers (if it is implemented at all), there is debate about whether inherited interfaces should be used. Since classes are not tied to an interface the same way they are tied to a superclass, inheritance hierarchies are less rigid than class hierarchies. However, given that any interface with an implementation anywhere cannot be easily revised without changing all classes that implement that interface, there is still a degree of inflexibility with regards to interfaces. One solution to this dilemma is the idea of composition. With composition, specific classes create specific implementations of a particular interface, and other classes include those classes as their specific implementation of the interface. For example, Solid would implement KeepShape while Fluid would implement NoShape, where KeepShape and NoShape are implementations of IShapeUnderPressure. Composition should prove useful as modules can be swapped out and yet remain standardized according to the interface declaration. 

While we added several functions to our API, we will need to ensure that the API remains flexible enough in order to fit in functions as the needs are discovered during our basic implementation and extensions. We were careful to not add in too much functionality into the API, especially if we did not know with certainty that the function is needed. Functionality can always be built in later, but public APIs are effectively eternal and must be supported forever. We plan on wrapping most of the functionality in Controller classes in the MVC ideal, so that additional functionality wouldn’t need to change too much either in the Game Player or the Game Engine. 

Observables will be heavily used in the Game Engine as well as the Game Player, as they provide an intuitive way in order to communicate between the two modules. By setting an Observable, the view would automatically be able to look for the object instead of having to generate an event or explicitly send it. Observables would likely be a primary way of communicating if not objects, then that objects are ready for transfer. 

Reflections, while immensely useful in generating objects, should be used carefully since they do not obey standard class scopes. If the class of the object is not known at compile time, reflections are a handy way in order to generate those objects at runtime. This is definitely useful for generating user-defined objects like specific towers created in game, or searching for a particular tower to create in a series of packages. Reflections should be limited in their scope of use since they give unfettered access to an object, of course; they may cause security issues with code and thus may not be good practice.

Event handling logic will be prevalent particularly in the front-end with the use of I/O interrupts. Additional cases beyond that should be handled by regular functions without the need to pass methods in as parameters. We do not foresee a situation where other design patterns will not suffice in taking care of all other communications methods. 

Exception handling logic will be simplified without the consistent use of event handling logic, and we aim to create our own exceptions extending from both Exception and RuntimeException. We will strive to ensure that no control flows will operate through the use of exceptions, in that any case generating exceptions will require user input to handle. 

The team also heavily discussed the purpose and the scope of the Game Data object; will that be a relatively simple collection of game XML files, or whether it will be that collection in addition to a controller that will help return requests and store data. In the end, the team decided to go with a simple collection of game XML files and have all parsing and handling done within the modules. If it turns out a controller is needed, we will add it later; by then we should have most of the pieces in play and it should be a case of refactoring. 



##Team Responsibilities

Ying, Cheng, and Wanning plan to take primary responsibility for the game engine. Dennis, William, and Susan plan to take primary responsibility for the authoring environment. Vanessa, Jaidev, and Abhishek plan to take primary responsibility for the game player. Main leaders from each module will be chosen for ease of communication, and Ying plans to take primary responsibility for high-level logistics of the project. 


