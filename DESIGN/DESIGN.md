
CS308 Design: VOOGASalad
===================




> **Introduction**

> This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). It should also describe your chosen game genre and what qualities make it unique that your design will need to support. It should be approximately 300-600 words long and discuss the program at a high-level (i.e., without referencing specific classes, data structures, or code).

----------

The problem that we are trying to solve by writing this program is to create a game development environment generic enough to design and deploy a tower defense game, that would be simple enough to be used by a non-expert in JavaFX. 

Our primary design goals include the simplicity and ease of use for the user interface, flexibility for the user game developer in adding in attributes to the core elements of the game, extensibility for any third-party developers that want to use or generalize our platform for different purposes, and enough depth in the developed game to make it engaging and fun for the user who plays the game. 

The architecture of our design should support the open/closed principle (open for extension, closed for modification). The user should be allowed to extend different types of units (board pieces in the game) as well as the different parameters for different towers. The user should not be allowed to modify the way the model communicates with the view and as extension any of the back-end logic in general, how the game data format is saved in terms of the XML, the core components of the game development environment, etc. 
 
Our chosen game genre is tower defense games. A subset of real-time strategy games, tower defense games are unique in that they have a mostly or all static level design for each level, as the only variable that changes is what units are available for the user game player to use, as well as the units available for the opponent. Tower defense games are also unique in that most of the units available to the player are static and strategically positioned on the board, while the enemy is mobile but goes along a predictable path that can be intercepted. The only interaction users have with the game is creating a unit and placing it on the board; the unit does all of the fighting autonomously. The win condition for a tower defense game is to prevent enough enemies from moving from the start to the end for a given number of levels, while the lose condition is if enough enemies pass through the defenses. 

The attributes of tower defense games narrow down our game development environment to support a set of traits. For example, since the interaction between the user and the game is limited to placing the units down onto the board, the game development environment should support a toolbar that allows the user to view, choose, purchase, and place any kind of tower that is currently available to them. The game should then support the towers functioning by themselves. The nature of setting a path, or having a specific path, implies the need for the user game developer to create a certain path, and the need to verify a win/lose condition implies that the game needs to check that the path is valid (i.e. connects a given start and end point, and does not cross the path without special path blocks and does not go off the map). The nature of the levels implies that if the game were to save at a particular time, it would save after each wave of opponents is done. 

<p align = "center">
	<img src = "/DESIGN/voogasalad_TEAM_HighLevelUML.png" />
</p>

> **Overview**

> This section serves as a map of your design for other programmers to gain a general understanding of how and why the program was divided up, and how the individual parts work together to provide the desired functionality. As such, it should describe specific modules you intend to create, their purpose with regards to the program's functionality, and how they collaborate with each other, focusing specifically on each one's behavior. It should also include a picture of how the modules are related (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a UML design program). This section should be approximately 700-1000 words long and discuss specific classes, methods, and data structures, but not individual lines of code.

> The main modules that we intend to create are the game engine, game player, and authoring environment, as well as a place to hold all the created games called game data. The authoring environment is an interface which allows a user to create a game, which is then exported to the game data once completed. From there, we will have a "store" interface that is populated with user created games, which can then be launched. Games are launched by being sent to the game engine, which serves as the main "back end" of our project. The game engine then interfaces with the game player through a controller, sending the game player objects to be represented in a GUI for a player to interact with. 

----------


> **User Interface**

> This section describes how the user will interact with your program (keep it simple to start). It should describe the overall appearance of program's user interface components and how users interact with these components (especially those specific to your program, i.e., means of input other than menus or toolbars). It should also include one or more pictures of the user interface (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a dummy program that serves as a exemplar). Describe how a game is represented to the designer and what support is provided to make it easy to create a game. Finally, it should describe any erroneous situations that are reported to the user (i.e., bad input data, empty data, etc.). This section may be as long as it needs to be and go into as much detail as necessary to cover all your team wants to say.

----------




> **Design Details**

> This section describes each module introduced in the Overview in detail (as well as any other sub-modules that may be needed but are not significant to include in a high-level description of the program). It should describe how each module handles specific features given in the assignment specification, what resources it might use, how it collaborates with other modules, and how each could be extended to include additional requirements (from the assignment specification or discussed by your team). Note, each sub-team should have its own API for others in the overall team or for new team members to write extensions. Finally, justify the decision to create each module with respect to the design's key goals, principles, and abstractions. This section may be as long as it needs to be and go into as much detail as necessary to cover all your team wants to say.

----------

> **Example games**

> Describe three example games from your genre in detail that differ significantly. Clearly identify how the functional differences in these games is supported by your design and enabled by your authoring environment. Use these examples to help make concrete the abstractions in your design. This section may be as long as it needs to be and go into as much detail as necessary to cover all your team wants to say.

> One type of tower defense game is the type where enemies are generated to walk around a map on a predetermined map. The player can then place towers alongside the path and defend a main base. Another type of game is that where enemies walk linearly along a map and towers can be placed anywhere, including in their path. Finally, another type of game is that where the player can not only defend a base, but also purchase units to attack an enemy base. 

----------



> **Design Considerations**

> This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. It should include any design decisions that each sub-team discussed at length (include pros and cons from all sides of the discussion) as well as any ambiguities, assumptions, or dependencies regarding the program that impact the overall design. This section may be as long as it needs to be and go into as much detail as necessary to cover all your team wants to say.

----------



> **Team Responsibilities**

> This section describes the program modules each team member plans to take primary and secondary responsibility for and a high-level plan of how the team will complete the program.

> Ying, Cheng, and Wanning plan to take primary responsibility for the game engine. Dennis, William, and Susan plan to take primary responsibility for the authoring environment. Vanessa, Jaidev, and Abhishek plan to take primary responsibility for the game player. Main leaders from each module will be chosen for ease of communication, and Ying plans to take primary responsibility for high-level logistics of the project. 

----------

