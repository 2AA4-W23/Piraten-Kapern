# A1 - Piraten Karpen

  * Author: Abdallah Alqashqish
  * Email: alqashqa@mcmaster.ca

## Build and Execution

  * To clean your working directory:
    * `mvn clean`
  * To compile the project:
    * `mvn compile`
  * To run the project in development mode:
    * `mvn -q exec:java` (here, `-q` tells maven to be _quiet_)
  * To package the project as a turn-key artefact:
    * `mvn package`
  * To run the packaged delivery:
    * `java -jar target/piraten-karpen-jar-with-dependencies.jar` 

Remark: **We are assuming here you are using a _real_ shell (e.g., anything but PowerShell on Windows)**

## Feature Backlog

 * Status: 
   * Pending (P), Started (S), Blocked (B), Done (D)
 * Definition of Done (DoD):
   * < Your DoD goes here >

### Backlog 

| MVP? | Id  | Feature  | Status  |  Started  | Delivered |
| :-:  |:-:  |---       | :-:     | :-:       | :-:       |
| x   | F01 | Roll a dice |  D | 01/01/23 | 09/01/23 |
| x   | F02 | Build MVP of game  |  B (F01) | 16/01/23 |
| x   | F02I | Roll eight dices  |  D  | 16/01/23 | 16/01/23
| x   | F02II | Roll random number of dice  |  D | 16/01/23 | 16/01/23
| x   | F02III | Implement scorecard class  | D | 16/01/23 | 16/01/232
| x   | F02IV | Implement counter class | D | 16/01/23 | 16/01/23
| x   | F02V | Implement player class | D | 16/01/23 | 16/01/23
| x   | F02VI | Implement player strategy | D | 16/01/23 | 16/01/23
| x   | F02VI | Implement game class  | P | 16/01/23 |
| x   | F02VIII | Simulate the game 42 times | P |   |
| x   | F03 | Select how many games as command-line arg.  |  P  |   |
| x   | F04 | end of game with three cranes | P | |
| x   | F05 | Player keeping random dice at their turn | B (F02) | | 
| x   | F06 | Score points: 3-of-a-kind | B (F04) | | 
| ... | ... | ... |

