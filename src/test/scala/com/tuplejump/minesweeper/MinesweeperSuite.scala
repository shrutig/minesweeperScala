package com.tuplejump.minesweeper

import org.scalatest.{FlatSpec}
import com.tuplejump.minesweeeper._
import org.scalatest.{BeforeAndAfter, FunSuite}

class MinesweeperSuite extends FlatSpec with BeforeAndAfter {

  var board: GameBoard = null
  before {
    board = new GameBoard(10, 10)
    board.setCoverAll
  }

  "Mine " should "be located properly" in {
    board.setMine(2, 2)
    assert(board.isMine(2, 2) === true)
  }

  "Game" should "be won " in {
    board.setMine(2, 2)
    assert(board.playCell(1, 9, 9) === 2)//1 is the choice and (9,9) is the cell choice
  }

  "Game " should "be over when required" in {
    board.setMine(2, 2)
    assert(board.playCell(1, 2, 2) === 1)//1 is the choice and (2,2) is the cell choice
  }

  "Cells " should "be uncovered properly" in {
    board.setMine(2, 2)
    val status = board.playCell(1, 7, 2)//1 is the choice and (7,2) is the cell choice
    assert(board.isUncovered(7,3) === true)
  }

  "Flag " should " be placed" in {
    board.setFlag(3, 3)
    assert(board.isFlag(3, 3) === true)
  }

}