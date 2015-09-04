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
      score = score + 1
      gameIndicator = board.playCell(choice, c(0), c(1))
      display.displayGameBoard(score, board, gameIndicator)
    }
  }

}