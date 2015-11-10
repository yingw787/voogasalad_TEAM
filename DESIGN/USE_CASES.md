CS308 Use Cases: VOOGASalad
===================

> **Use Cases**

> This section describes the problem your team is trying to solve by writing this program, the primary design goals of the project (i.e., where is it most flexible), and the primary architecture of the design (i.e., what is closed and what is open). It should also describe your chosen game genre and what qualities make it unique that your design will need to support. It should be approximately 300-600 words long and discuss the program at a high-level (i.e., without referencing specific classes, data structures, or code).

----------

1. User can create new enemies with different attributes (health, speed, image, money given per kill, range)
2. Application will have a game editor GUI (to create the game) and a game player GUI (to play the game)
3. User can define path(s) that enemies take (default would be no path, for example, pre-defined path, user has to define a path no matter what, including shortest path to target) 
4. User can define path components (bridges, tunnels, etc. with appropriate images, sizes, money cost, etc.)
5. User can define bullet attributes (damage, speed, mapped to which units, etc.)
6. User can define locations where the player is allowed to place towers (user cannot place towers on the user-defined path)
7. User can create new types of towers (health, cost, appearance, damage per attack, attack rate, bullet image, upgrades/upgrade  costs) 
8. Editor should have a HTML formatted help page for user to use the components of the editor.
9. User can save a game he makes (saves the list of towers, enemies, locations of paths, base, images, etc.)
10. User can load the game that they saved
11. User can define appearance, health, and location of main base
12. User can see health of main base and enemies
13. User can place scenery/aesthetic items on the map
14. User can upgrade turrets and troops during game (after each wave a popup screen appears that shows options available in-game
for user)
15. User can use cheat codes for more gold or more health
16. User can define spawn rate and spawn order of enemies
17. User can define the level background image
18. User can define which towers are available for user control 
19. User can drag and drop items onto the board during gameplay
20. User can define to see their money/points
21. User can define a rate for the player to constantly gain money/points
22. User can write a ‘Rules’ dialog that the player can open up
23. User can define the goal of the round (whether to kill all enemies within a time limit, or keep base alive within a time limit, or keep base alive for a certain number of enemies).
24. User defines win/lose conditions, if any (infinite levels (survival mode), defeat enemy base, campaign mode, etc.)
25. User can define win/lose scenes that player sees
26. User can define cheat key (automatically go to win/lose conditions, clear enemies on board, infinite money, etc.)
27. User can define how the levels differ in terms of difficulty
28. User can keep track of games' high scores through successive runs of the program until the user clears it
29. User can replay the game repeatedly without quitting the editor
30. User can open games repeatedly without quitting (application creates a store, user writes games to save it to the store, store launches game upon user command) 
31. User can see which games are available, including at least each game's name, image, and description
32. User can define the result of lose at a certain level (fall to the previous level or fall down to the very beginning level).
33. User defines the animation of bullets/units (default should be 
34. User can define the order in which enemies come out or have it be random based on spawn rate
35. Defaults are defined for each of the user-defined attributes of the game 
36. User can define rounds/levels with different/modified enemies; Player will see pauses between them and be able to choose when to advance to the next round/level
37. Create, duplicate, modify, and delete preset files (dual XML files or file components, one with the static game state (path, turret location, start/end, etc.) and the dynamic game play (money, players, lives, etc. that references the static game state). 
38. User can create troops that the player can buy (define health, speed, cost)
39. Player can buy his own troops to send out; they travel backward on the path and collide with enemy troops
40. Editor should have tabs to define aspects of the games (create enemies, create turrets, load image etc)
