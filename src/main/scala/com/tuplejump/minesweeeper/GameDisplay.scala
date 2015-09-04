package com.tuplejump.minesweeeper

import Vector._
import scala.io.StdIn.readLine

class GameDisplay {
  def displayGameBoard(score: Int, board: GameBoard, gameIndicator: Int) = {
    if (gameIndicator == 1)
      println("Mine found: Game lost with score :" + score)
    else if (gameIndicator == 2) {
      println("All non-mine cells uncovered . Game won with score :" + score)
    }
    else {
      println("Welcome to Minesweeper : To win uncover all cells without mines.")
      println("The number in each cell denotes number of mines in surrounding 8 cells")
      println("\t Game score: " + score)
      for (row <- 0 until board.getHeight) {
        for (col <- 0 until board.getWidth) {
          if (board.isUncovered(row, col))
            print(board.getValue(row, col) + "\t")
          else if (board.isFlag(row, col))
            print("flag" + "\t")
          else
            print("e" + "\t")
        }
        println("")
      }
    }
  }

  def createGameBoard: GameBoard = {
    var Height = 0
    var Width = 0
    while (Height <= 0 || Width <= 0) {
      println("Enter the board height follwed by board Width in the next line")
      Height = readLine().toInt
      Width = readLine().toInt
      if (Width <= 0 || Height <= 0) {
        System.out.println("invalid input. Enter again")
      }
    }
    val board: GameBoard = new GameBoard(Height, Width)
    board.setCoverAll
    var row = 0
    var col = 0
    while (!(row == -1 && col == -1)) {
      println("Enter the mine: row col format(starting from 0) or -1 -1 to exit")
      row = readLine().toInt
      col = readLine().toInt
      if (board.isValid(row, col)) {
        board.setMine(row, col)
      }
    }
    board
  }

  def getInputOption = {
    var option = 0
    while (option != 1 && option != 2) {
      println("Enter 1 to uncover cell or 2 to flag cell")
      option = readLine().toInt
      if (option != 1 && option != 2) {
        println("invalid input.Enter again.")
      }
    }
    option
  }

  def getInputCell(choice: Int, height: Int, width: Int) = {
    var cell = Vector(-1, -1)
    if (choice == 1)
      println(
        "Enter cell with e to uncover it or with flag to unflag it : row col format(starting from 0)")
    else if (choice == 2)
      println("Enter cell with e flag it : row col format(starting from 0)")
    while (cell(0) > height - 1 || cell(0) < 0 || cell(1) > width - 1 || cell(1) < 0) {
      //runs till valid input from 0 to height/width -1 is received
      val i = readLine().toInt
      val j = readLine().toInt
      cell = cell updated(0, i)
      cell = cell updated(1, j)
      if (cell(0) > height - 1 || cell(0) < 0 || cell(1) > width - 1 || cell(1) < 0) {
        println("invalid input. Enter again")
      }
    }
    cell
  }
}
