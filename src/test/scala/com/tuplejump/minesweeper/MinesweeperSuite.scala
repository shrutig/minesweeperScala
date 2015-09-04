package com.tuplejump.minesweeper

import org.scalatest.{FlatSpec}
import com.tuplejump.minesweeeper._
import org.scalatest.{BeforeAndAfter, FunSuite}
class MinesweeperSuite extends FlatSpec with BeforeAndAfter {

  var board:GameBoard = null
  before {
    board = new GameBoard(10, 10)
    board.setCoverAll
  }

  "Mine " should "be located properly" in {
    board.setMine(2,2)
    assert(board.isMine(2,2) === true)
  }

 /* "Invalid input " should "not be considered " in {

  }*/

  "Game " should "be over when required" in {
    board.setMine(2,2)
    assert(board.playCell(1,2,2) === 1)
  }

  "Flag " should " be placed" in {
    board.setFlag(3,3)
    assert(board.isFlag(3,3) === true)
  }


}