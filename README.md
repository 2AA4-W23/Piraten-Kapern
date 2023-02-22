# Piraten Karpen

  * Author: Abdallah Alqashqish
  * Email: alqashqa@mcmaster.ca

## Description
A command line simulator of the Piraten Kapern board game.
The simulator simulates the game where 2-5 players play against each other to score 6000 points, the first to do so 
is deemed as the winner. There are 2 strategies that can be given to each player, the random or combo strategy. The
players will play the given number of games (42 by default) the simulator will display the win percentage of 
each player at the end of the simulation.

The random strategy is what is known as the "DUMMY" strategy. This strategy randomly chooses the number of dice to roll
and when to end their turn.

The combo strategy is a smarter strategy where the player utilizes the combination scoring mechanism to maximize their
points. This strategy also detects when the best time to end the turn to avoid loosing collected points.

The simulator has most of the features of the real game implemented excluding:
1. Island of skulls
2. The sorceress card

## Java
__Recomended:__ Java 17

## Build and Execution

```shell
# Clean & compile the project
mvn clean
mvn compile

# Package the project into a jar file
mvn package

# Using maven
mvn -q exec:java -D exec.args="-h" # Help command

# Strategies 3 to 5 are optional
# Normal mode
mvn -q exec:java -D exec.args="-g NUMBER_OF_GAMES -s strategy1 strategy2 strategy3 strategy4 strategy5" 
# Debug mode
mvn -q exec:java -D exec.args="-g NUMBER_OF_GAMES -T -s strategy1 strategy2 strategy3 strategy4 strategy5" 

# Using jar
# Normal mode
java -jar PIRATEN_KAPERN_JAR_PATH -g NUMBER_OF_GAMES -s strategy1 strategy2 strategy3 strategy4 strategy5 
# Debug mode
java -jar PIRATEN_KAPERN_JAR_PATH -g NUMBER_OF_GAMES -T -s strategy1 strategy2 strategy3 strategy4 strategy5 
```