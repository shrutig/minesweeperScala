# Route

##Prerequisites
This program requires SBT(Scala Build Tool) to compile and run it.This program was written with SBT version 0.13.8 and Scala version 2.11.7. 

The following Java configuration is installed in the system:
java version "1.8.0_51"
Java(TM) SE Runtime Environment (build 1.8.0_51-b16)
Java HotSpot(TM) 64-Bit Server VM (build 25.51-b03, mixed mode)

##Project details
This Minesweeper application in Scala is written to mimic the popular game, Minesweeper.

*This game allows the user to enter board height and board width and creates a game with randomly generated mines. 
*The game then asks for input of row and col from the user. 
*The game keeps playing till the user enters a mine cell or all non-mine cells are uncovered.

##Project run and test details
The MinesweeperSuite.scala file has tests for this program.Run this file by :
```
shell> sbt test
```

Run Minesweeper.scala to enable input from the user and play the game by:
```
shell> sbt run
```


