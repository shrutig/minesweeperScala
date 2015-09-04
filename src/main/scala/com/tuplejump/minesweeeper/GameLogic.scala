package com.tuplejump.minesweeeper

object GameLogic {

  private var board: GameBoard = null
  private var display: GameDisplay = null
  private var score: Int = 0

  def main(args: Array[String]) {
    display = new GameDisplay
    board = display.createGameBoard
    playGame
  }

  def playGame = {
    var gameIndicator = 0
    // stores 1 if game ended with loss, 2 if game ended
    // with win and 0 if incomplete
    var choice = 0
    while (gameIndicator == 0) {
      display.displayGameBoard(score, board, gameIndicator)
      choice = display.getInputOption // stores 1 if user wants to uncover cell
      // or 2 if user wants to place a flag
      val c = display.getInputCell(choice, board.getHeight, board.getWidth)
      gameIndicator = playCell(choice, c(0), c(1))
      display.displayGameBoard(score, board, gameIndicator)
    }
  }

  def playCell(choice: Int, row: Int, col: Int) = {
    var gameStatus = 0
    score = score + 1
    var WinStatus: Boolean = false
    if (choice == 1) {
      // to uncover cell
      if (board.isMine(row, col)) {
        gameStatus = 1
      } else if (board.isCovered(row, col)) {
        board.setUncover(row, col)
        WinStatus = true
        for (i <- 0 until board.getHeight) {
          for (j <- 0 until board.getWidth) {
            if (board.isCovered(row, col) || board.isFlag(row, col))
              WinStatus = false
          }
        }
        if (WinStatus) {
          gameStatus = 2
        }
      } else if (board.isFlag(row, col)) {
        board.setCover(row, col)
        gameStatus = 0
      }
    } else {
      // to flag cell
      if (board.isCovered(row, col) || board.isMine(row, col)) {
        board.setFlag(row, col)
        gameStatus = 0
      }
    }
    gameStatus
  }

}