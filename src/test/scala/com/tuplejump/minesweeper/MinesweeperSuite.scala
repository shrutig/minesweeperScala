package com.tuplejump.minesweeper

import org.scalatest.{FlatSpec}
import com.tuplejump.minesweeeper._
import org.scalatest.{BeforeAndAfter, FunSuite}
class MinesweeperSuite extends FlatSpec with BeforeAndAfter {

  before{
    val display:GameDisplay = new GameDisplay
    val board:GameBoard = display.createGameBoard
  }

  "Mine " should "not be located properly" in {
    //assert(Set.empty.size == 0)
  }

  "Invalid input " should "not be considered " in {

  }

  "Game " should "be over when required" in {

  }

  "Flag " should " be placed" in {

  }


}