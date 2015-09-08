package com.tuplejump.minesweeeper

//grid[][] is the game board in this game
//In this game, -1 in a grid cell indicates presence of mine,
// -2 indicates cell not uncovered and >=0 means cell uncovered
class GameBoard(height: Int, width: Int) {

  private val MINE = -1

  private val COVER = -2

  private val FLAG = -3

  private val BOARD_HEIGHT: Int = height

  private val BOARD_WIDTH: Int = width

  private var grid = Vector[Vector[Int]]()

  def getHeight: Int = BOARD_HEIGHT

  def getWidth: Int = BOARD_WIDTH

  def getValue(row: Int, col: Int): Int = grid(row)(col)

  def isMine(row: Int, col: Int): Boolean = grid(row)(col) == MINE

  def isCovered(row: Int, col: Int): Boolean = grid(row)(col) == COVER

  def isUncovered(row: Int, col: Int): Boolean = grid(row)(col) >= 0

  def isFlag(row: Int, col: Int): Boolean = grid(row)(col) == FLAG

  def isValid(row: Int, col: Int): Boolean = row >= 0 && col >= 0 && row < BOARD_HEIGHT && col < BOARD_WIDTH

  def setFlag(row: Int, col: Int) = {
    grid = grid updated(row, grid(row).updated(col, FLAG))
  }

  def setMine(row: Int, col: Int) = {
    grid = grid updated(row, grid(row).updated(col, MINE))
  }

  def setCover(row: Int, col: Int) = {
    grid = grid updated(row, grid(row).updated(col, COVER))
  }

  def setCoverAll: Unit = {
    for (row <- 0 until BOARD_HEIGHT) {
      val rows: Vector[Int] = {
        for (col <- 0 until BOARD_WIDTH) yield COVER
      }.toVector
      grid = grid ++ Vector(rows)
    }
  }

  def playCell(choice: Int, row: Int, col: Int): Int = {
    var gameStatus = 0
    // stores 1 if game ended with loss, 2 if game ended
    // with win and 0 if incomplete
    var WinStatus: Boolean = false
    if (choice == 1) {
      // to uncover cell
      if (isMine(row, col)) {
        gameStatus = 1
      } else if (isCovered(row, col)) {
        setUncover(row, col)
        WinStatus = true//stores true if all cells are uncovered
        for (row <- 0 until BOARD_HEIGHT) {
          for (col <- 0 until BOARD_WIDTH) {
            if (isCovered(row, col) || isFlag(row, col))
              WinStatus = false
          }
        }
        if (WinStatus) {
          gameStatus = 2
        }
      } else if (isFlag(row, col)) {
        setCover(row, col)
        gameStatus = 0
      }
    } else {
      // to flag cell
      if (isCovered(row, col) || isMine(row, col)) {
        setFlag(row, col)
        gameStatus = 0
      }
    }
    gameStatus
  }

  def setUncover(row: Int, col: Int): Unit = {
    var count = 0
    for (sideCount <- -1 to 1) {
      // checks the upper neighbors of the current
      // cell for mines
      if (isValid(row - 1, col + sideCount)) {
        if (isMine(row - 1, col + sideCount))
          count = count + 1
      }
    }
    for (sideCount <- -1 to 1) {
      // checks the lower neighbors of the current
      // cell for mines
      if (isValid(row + 1, col + sideCount)) {
        if (isMine(row + 1, col + sideCount))
          count = count + 1
      }
    }
    if (isValid(row, col + 1)) {
      // checks right neighbor
      if (isMine(row, col + 1))
        count = count + 1
    }
    if (isValid(row, col - 1)) {
      // checks left neighbor
      if (isMine(row, col - 1))
        count = count + 1
    }
    grid = grid updated(row, grid(row).updated(col, count))
    if (count == 0) {
      // if no mines in neighboring cells , then neighboring
      // cells are uncovered
      for (sideCount <- -1 to 1) {
        // Uncovering upper neighbors
        if (isValid(row - 1, col + sideCount) && isCovered(row - 1, col + sideCount)) {
          setUncover(row - 1, col + sideCount)
        }
      }
      for (sideCount <- -1 to 1) {
        // Uncovering lower neighbors
        if (isValid(row + 1, col +sideCount) && isCovered(row + 1, col + sideCount)) {
          setUncover(row + 1, col + sideCount)
        }
      }
      if (isValid(row, col + 1) && isCovered(row, col + 1)) {
        setUncover(row, col + 1) // uncovers right neighbor
      }
      if (isValid(row, col - 1) && isCovered(row, col - 1)) {
        setUncover(row, col - 1) // uncovers left neighbor
      }
    }
  }

}
