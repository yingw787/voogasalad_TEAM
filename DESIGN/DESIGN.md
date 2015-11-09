
CS308 Design: VOOGASalad
===================




> **Introduction**

> This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). It should also describe your chosen game genre and what qualities make it unique that your design will need to support. It should be approximately 300-600 words long and discuss the program at a high-level (i.e., without referencing specific classes, data structures, or code).

>Our chosen genre is tower defense games, which are a subset of real-time strategy games. The qualities that make this genre unique is a set level design for each level, but variability in where the user can place towers or turrets to defend a base. Towers can vary in their strength, cost, and abilities. Our design will need to support the user's ability to interact with the game by placing towers in permissible locations, as well as ability for these towers to interact with enemies that walk along a path that is predetermined by the game designer. It will also need to support user defined images for all of these objects (towers, enemies, etc) and keep track of scores, waves, and other elements of tower defense games. 

----------

<p align = "center">
	<img src = "/DESIGN/voogasalad_TEAM_HighLevelUML.png" />
</p>

> **Overview**

> This section serves as a map of your design for other programmers to gain a general understanding of how and why the program was divided up, and how the individual parts work together to provide the desired functionality. As such, it should describe specific modules you intend to create, their purpose with regards to the program's functionality, and how they collaborate with each other, focusing specifically on each one's behavior. It should also include a picture of how the modules are related (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a UML design program). This section should be approximately 700-1000 words long and discuss specific classes, methods, and data structures, but not individual lines of code.

>The main modules that we intend to create are the game engine, game player, and authoring environment, as well as a place to hold all the created games called game data. The authoring environment is an interface which allows a user to create a game, which is then exported to the game data once completed. From there, we will have a "store" interface that is populated with user created games, which can then be launched. Games are launched by being sent to the game engine, which serves as the main "back end" of our project. The game engine then interfaces with the game player through a controller, sending the game player objects to be represented in a GUI for a player to interact with. 
----------


> **User Interface**

> This section describes how the user will interact with your program (keep it simple to start). It should describe the overall appearance of program's user interface components and how users interact with these components (especially those specific to your program, i.e., means of input other than menus or toolbars). It should also include one or more pictures of the user interface (these pictures can be hand drawn and scanned in, created with a standard drawing program, or screen shots from a dummy program that serves as a exemplar). Describe how a game is represented to the designer and what support is provided to make it easy to create a game. Finally, it should describe any erroneous situations that are reported to the user (i.e., bad input data, empty data, etc.). This section may be as long as it needs to be and go into as much detail as necessary to cover all your team wants to say.

----------




> **Design Details**

> This section describes each module introduced in the Overview in detail (as well as any other sub-modules that may be needed but are not significant to include in a high-level description of the program). It should describe how each module handles specific features given in the assignment specification, what resources it might use, how it collaborates with other modules, and how each could be extended to include additional requirements (from the assignment specification or discussed by your team). Note, each sub-team should have its own API for others in the overall team or for new team members to write extensions. Finally, justify the decision to create each module with respect to the design's key goals, principles, and abstractions. This section may be as long as it needs to be and go into as much detail as necessary to cover all your team wants to say.

----------

> **Example games**

> Describe three example games from your genre in detail that differ significantly. Clearly identify how the functional differences in these games is supported by your design and enabled by your authoring environment. Use these examples to help make concrete the abstractions in your design. This section may be as long as it needs to be and go into as much detail as necessary to cover all your team wants to say.

----------



> **Design Considerations**

> This section describes any issues which need to be addressed or resolved before attempting to devise a complete design solution. It should include any design decisions that each sub-team discussed at length (include pros and cons from all sides of the discussion) as well as any ambiguities, assumptions, or dependencies regarding the program that impact the overall design. This section may be as long as it needs to be and go into as much detail as necessary to cover all your team wants to say.

----------



> **Team Responsibilities**

> This section describes the program modules each team member plans to take primary and secondary responsibility for and a high-level plan of how the team will complete the program.

----------

