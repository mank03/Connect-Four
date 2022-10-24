# Connect Four

This Java program emulates the simple functionalities of a Connect Four game. 

## Description

Connect Four is a two-player connection board game in which players alternately place pieces on a vertical board 7 columns across and 6 rows high. The first to form a horizontal, vertical, or diagonal line of four of one's own tokens wins the game. The purpose is to demonstrate Object-Oriented principles including cohesion, coupling and implement the concepts learned into my code. 

## Getting Started

### Dependencies

* Latest version of JUnit testing framework: 'junit:junit:4.13'
* Latest version of gradle is required
* Functional on both MacOS and Windows


### Executing program

* To build the program using Gradle, in your terminal head to the directory that contains the project files. Ensure that the build.gradle file is included in this directory. In the command line type:
```
gradle build
```
* The program should build successfully. Now type:
```
gradle run
```
* The terminal shoud ouptut the run task of the program which looks like this:
```
To run the program:
java -cp build/classes/java/main connectfour.ConnectFour
```
* Now copy and paste that command line into your terminal and you are ready to play!


## Limitations

The user interface in the program is not the most attractive.

## Author Information

* Manu Konnur
* mkonnur@uoguelph.ca

## Development History

* 0.7: Sunday, October 22, 2022
    * Performed few bug fixed
    * Completed readMe and program is ready for submission
* 0.6: Friday, October 21, 2022
    * Javadoc commenting of the program was completed
    * Improved readability
* 0.5: Thursday, October 20, 2022
    * Completed Load and Read from file methods in the board class
    * Completed error checking and fixed exception erorrs
    * Various bug fixed and optimized methods to adhere to OOP principles
* 0.4: Wednesday, October 19, 2022
    * Completed methods to check winning and tie scenarios
    * Formed the methods for game functionality in ConnectFour class
    * Optimized Board class to follow coding conventions and proper formatting
* 0.3: Tuesday October 18, 2022
    * Completed print statements in the TextUI class
    * Created accessors and mutators of instance variables  
    * Fixed various bugs detected in other methods
* 0.2: Sunday October 16, 2022
    * Began with constructing the board class
    * Used an 2D array data structure to manage the positions of the board
    * Completed main method
    * Various bug fixes and optimizations
* 0.1: Friday October 14, 2022
    * Git clone repository into local system
    * Ran a hello world demo to ensure program is running

## Acknowledgments

Inspiration, code snippets, etc.
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [simple-readme] (https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)






