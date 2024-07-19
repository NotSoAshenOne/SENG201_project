
# Our Project
Welcome to our game for our SENG201 project. This README file includes instructions for cloning, building, and running our game. 
It also provides some information on playing the game.

## Playing the game
In the game, the idea is to buy towers in order to fill the carts on the side of the screen in the upcoming cart lists in order to keep your coins above 0.
If you go below 0 then you will go into debt and if you are still in debt by the end of the last round you will lose.
You can earn coins from a number of sources:
- Filling carts
- Selling Towers
- Random Events

In addition to debt, you can also lose if all of your towers break, as such, you are able to buy new towers to fill more carts and replace broken ones. 
If you don't fill a cart all the way then you will not earn any coins for it, so, you are able to choose the towers active each round to ensure that you can get as many coins as possible.

Good luck and have fun!

## Authors
- Morgan Lee and Jessica Dixon

## Prerequisites
- JDK >= 17 [click here to get the latest stable OpenJDK release (as of writing this README)](https://jdk.java.net/18/)
- *(optional)* Gradle [Download](https://gradle.org/releases/) and [Install](https://gradle.org/install/)


## Cloning the project (into IntelliJ)
1. From the start window of IntelliJ select the `Get from VCS` option
2. Copy the url from the GitLab page and paste into the url box in IntelliJ
3. Choose a directory for the project to be stored
4. Select the `clone` option

## Build and Run Jar
### Building the Jar
To build the Jar:
1. Within a terminal, navigate to the directory where you have cloned the project
2. Run `./gradlew jar` to build the packaged Jar. 

The Jar file is located at build/libs/seng201_team0-1.0-SNAPSHOT.jar

### Running the Jar
1. In a terminal, navigate to ` build/libs/ ` within the directory where the project was cloned
3. Run the command `java -jar seng201_team0-1.0-SNAPSHOT.jar` to open the application.

## Run Tests
1. Open a command line interface inside the project directory and run `./gradlew test` to run the tests.
2. Test results are printed to the command line

