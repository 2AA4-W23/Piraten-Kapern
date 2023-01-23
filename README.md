# A1 - Piraten Karpen

  * Author: Abdallah Alqashqish
  * Email: alqashqa@mcmaster.ca

## Build and Execution

  * To clean your working directory:
    * `mvn clean`
  * To compile the project:
    * `mvn compile`
  * To run the project in development mode (without tracing mode):
    * `mvn -q exec:java`
  * To run the project in development mode (with tracing mode):
    * `mvn -q exec:java -D TRACE`
  * To package the project as a turn-key artefact:
    * `mvn package`
  * To run the packaged delivery:
    * `java -jar target/piraten-karpen-jar-with-dependencies.jar` 

Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

 * Status: 
   * Pending (P), Started (S), Blocked (B), Done (D)
 * Definition of Done (DoD):
   * Works as a standalone feature. No bugs, exceptions or unexpected behaviour
   * Works alongside the other features that have already been implemented
   * Matches all expected business logic and requirements

### Backlog 

| MVP? | Id  | Feature  | Status  |  Started  | Delivered |
| :-:  |:-:  |---       | :-:     | :-:       | :-:       |
| x   | F01 | Roll a dice |  D | 01/01/23 | 09/01/23 |
| x   | F02 | Build MVP of game  |  D | 16/01/23 | 17/01/23
| x   | F02I | Roll eight dices  |  D  | 16/01/23 | 16/01/23
| x   | F02II | Roll random number of dice  |  D | 16/01/23 | 16/01/23
| x   | F02III | Implement scorecard class  | D | 16/01/23 | 16/01/232
| x   | F02IV | Implement counter class | D | 16/01/23 | 16/01/23
| x   | F02V | Implement player class | D | 16/01/23 | 16/01/23
| x   | F02VI | Implement player strategy | D | 16/01/23 | 16/01/23
| x   | F02VI | Implement game class  | D | 16/01/23 | 16/01/23
| x   | F02VIII | Simulate the game 42 times | D | 16/01/23 | 16/01/23
| -   | F03 | Fix code and implement logging.  |  D  |  16/01/23  | 19/01/23
| -   | F03I | Implement simulation class to handle all simulation tasks.  |  D  | 17/01/23  | 17/01/23
| -   | F03II | Update documentation  |  D  |  17/01/23  | 17/01/23
| -   | F03III | Implement Log4j logger  |  P  | 17/01/23 | 18/01/23
| -   | F03IV | Implement general Scorable so that we can have different scorable types  |  D  | 18/01/23 | 18/01/23
| -   | F03V | Implement generic scorecard to differentiate between game and turn scorecards  |  D  | 18/01/23 | 18/01/23
| -   | F03VI | Implement dice holder to keep track of rolls |  D  | 18/01/2023 | 18/01/23
| -   | F03VII | Fix win checking logic |  D  | 19/01/23 | 19/01/23
| -   | F03VIII | PlayerStrategy split use method into several general methods that each PlayerStrategy has |  D  | 19/01/23 | 19/01/23
| -   | F03IX | Game rules class implementation | D | 19/01/23 | 19/01/23
| -   | F04 | Group scoring | D | 20/01/23 | 20/01/23
| -   | F04I | Implement groups enum to know the score of each group | D | 20/01/23 | 20/01/23
| -   | F04II | Implement turn scorecard to compute combinations  | D | 20/01/23 | 20/01/23
| -   | F05 | Bonus chest | D | 20/01/23 | 21/01/23
| -   | F05I | Implement Bonus Chest Enum | D | 20/01/23 | 20/01/23
| -   | F05II | Implement turn scorecard to detect bonus chest | D | 21/01/23 | 21/01/23
| -   | F06 | Implement combination strategy | D | 21/01/23 | 22/01/23
| -   | F06I | Implement combination strategy class | D | 21/01/23 | 21/01/23
| -   | F06II | Implement giving each player a strategy through CMD arguments | D | 22/01/23 | 22/01/23
| -   | F07 | Implement 2-5 players playing the game | P | 22/01/23 | 22/01/2023
| -   | F08 | Implement cards into the game | P |  |
| -   | F08I | Implement card interface | D | 23/01/23 | 23/01/23
| -   | F08II | Implement abstract card class | P | 23/01/23 |
| -   | F08III | Implement Sea Battle card | P |  |
| -   | F08IV | Implement Card Deck | P |  |
| -   | F08V | Implement player to use a card in their turn | P |  |
| ... | ... | ... |

