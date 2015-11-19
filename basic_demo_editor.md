#Tower Defense Authoring Environment

##Overall Design

The game editor is split into 5 major parts: the toolbar, the scene display, the game object tabs, the rules display, and the attributes display. After careful discussion, we decided that by extending these sections, we will be able to create any kind of tower defense game we want, so the general sections of the editor are closed to modification. However, the contents of each section are open to extension. If a developer wants to add a new kind of object to the game, he can simply add a new tab that allows users to create new objects of that type.

The game objects tabs, rules display, and attributes display are views that display from our data classes, which are models of all the game objects data and update in real time with modifications the user makes in the authoring environment.

In order to create a game, the user goes through the tabs, creating all the different objects he wants to appear in the game and defining rules and constraints for them. 

##Rules and Attributes

When the user edits an object that contains rules or attributes, the rules and attributes display automatically show the corresponding properties for the user to edit. 

Attributes are exactly what they sound like; they are properties of the game object. They have default values which can be changed by the user. Currently, the set of attributes is immutable for each type of object, but we plan to implement a function for users to add or remove existing attributes and rename them.

Rules are bundles of logic that define how game objects interact. Each rule consists of a condition to be checked, and an action to be performed when the condition is true. Initially, we had considered predefining all the behaviors each object can have and just allowing the users to modify how those occur. That system would have been easy to implement and have cleaner, clearer code, but the current rules system allows users more flexibility with defining behaviors for their objects, as they can match any condition to any action, and it gives developers more flexibility to implement new kinds of conditions and actions, as they are contained separately from each other. Before the final demo, we plan to implement more conditions and actions that give the user more flexibility to create different kinds of game objects.
