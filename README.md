# GM Trade Simulator

**What is it going to be?**

My personal project will be an NHL trade simulator where a "team" (user) will 
submit requirements of a player that meets their needs and wants. Then,  list of players that 
will that meet the user's wants will be shown and also the requirements or assets needed 
for the player to be successfully traded to the user's team. 

**Who will use it?** 

My project will be for any type of person. This can range from a hockey fanatic to 
a person who is just getting familiar with hockey. 

**Why is this project of interest to you?** 

Ever since I was young, I have always been intrigued by NHL general managers 
evaluating players and trading players with other teams. This fascination has 
led me to invest my time into playing fantasy sports, constantly trading players
with the motivation to put out the best lineup for upcoming games. However, the amount
of time spending on looking through each and every player's stats that fit my needs 
takes a long time to do. As a result, I want to use this opportunity to build 
a system that automatically outputs a list of players that meet my requirements and 
estimates the costs required to obtain them. 

# User Stories 

**NHL Trade Simulator Application**

As a user, I want to be able to view all the players on the current roster and trading list

As a user, I want to be able to view players' specific attributes such as their availability status from either 
the current roster or trading block list

As a user, I want to be able to add players between current roster and the trading block depending on their
availability status

As a user, I want to be able to make new players and add them into the current roster

As a user, I want to be able to quit the game

As a user, I want to be able to edit any players in the game

As a user, I want to be able to save the current roster and the trading block 

As a user, I want to be to load my current team and trading list

As a user, I want to be able to make players and add the player to the current roster by clicking on the "Make Player"
           button

As a user, I want to be able to edit players by clicking on the "Edit Player" button

As a user, I want to be able to display attributes of the players in the application

As a user, I want to be able to load and save the state of my application by clicking the "Save" and "Load" options

# Phase 4: Task 2

Example: 

Thu Mar 31 22:52:39 PDT 2022
Assigned Brock Boeser to current team
Thu Mar 31 22:52:39 PDT 2022
Assigned Elias Pettersson to current team
Thu Mar 31 22:52:39 PDT 2022
Assigned Quinn Hughes to current team
Thu Mar 31 22:53:52 PDT 2022
Setting Daniel Kim shooting rating to: 1
Thu Mar 31 22:53:52 PDT 2022
Setting Daniel Kim skating rating to: 1
Thu Mar 31 22:53:52 PDT 2022
Setting Daniel Kim puck skill rating to: 1
Thu Mar 31 22:53:52 PDT 2022
Setting Daniel Kim hockey IQ rating to: 1
Thu Mar 31 22:53:52 PDT 2022
Setting Daniel Kim compete level rating to: 1
Thu Mar 31 22:53:52 PDT 2022
Assigned Daniel Kim to current team
Thu Mar 31 22:54:01 PDT 2022
Player moved to trading block from current team: Brock Boeser
Thu Mar 31 22:54:01 PDT 2022
Player moved to trading block from current team: Quinn Hughes
Thu Mar 31 22:54:01 PDT 2022
Player moved to trading block from current team: Daniel Kim
Thu Mar 31 22:54:02 PDT 2022
Assigned Jack Hughes to current team
Thu Mar 31 22:54:02 PDT 2022
Player moved to current team from trading block: Jack Hughes
Thu Mar 31 22:54:02 PDT 2022
Assigned Auston Matthews to current team
Thu Mar 31 22:54:02 PDT 2022
Player moved to current team from trading block: Auston Matthews
Thu Mar 31 22:54:24 PDT 2022
Setting Daniel Kim position to: Forward
Thu Mar 31 22:54:24 PDT 2022
Setting Daniel Kim skating rating to: 5
Thu Mar 31 22:54:24 PDT 2022
Setting Daniel Kim shooting rating to: 5
Thu Mar 31 22:54:24 PDT 2022
Setting Daniel Kim puck skill rating to: 5
Thu Mar 31 22:54:24 PDT 2022
Setting Daniel Kim compete level rating to: 5
Thu Mar 31 22:54:24 PDT 2022
Setting Daniel Kim hockey IQ rating to: 5
Thu Mar 31 22:54:46 PDT 2022
Setting Tyler Motte shooting rating to: 3
Thu Mar 31 22:54:46 PDT 2022
Setting Tyler Motte skating rating to: 3
Thu Mar 31 22:54:46 PDT 2022
Setting Tyler Motte puck skill rating to: 3
Thu Mar 31 22:54:46 PDT 2022
Setting Tyler Motte hockey IQ rating to: 3
Thu Mar 31 22:54:46 PDT 2022
Setting Tyler Motte compete level rating to: 3
Thu Mar 31 22:54:46 PDT 2022
Assigned Tyler Motte to current team
Thu Mar 31 22:54:51 PDT 2022
Assigned Daniel Kim to current team
Thu Mar 31 22:54:51 PDT 2022
Player moved to current team from trading block: Daniel Kim

# Phase 4: Task 3

1) Creating an abstract class for CurrentTeamDialog, TradingBlockDialog, and AllPlayerDialog. After examining my code,
   there are a fair amount of code duplication that could have been diminished with a higher-level abstract class.
   For example, putting all the player's names into a string array occurs in each of the three methods above that
   could have been refactored. 
2) Creating an abstract class for MakePlayerDialog and EditPlayerDialog. After examining my mode, there were a fair
   amount of code duplication that could have been diminshed with a higher-level abstract class. For example, in the 
   setAttributes method, the way each player has their attributes set is the same, so refactoring an abstract class
   that encompasses this method for MakePlayerDialog and EditPlayerDialog could use would improve my code. 










