package com.tuplejump.minesweeeper
//grid[][] is the game board in this game
//In this game, -1 in a grid cell indicates presence of mine, -2 indicates cell not uncovered and >=0 means cell uncovered
class GameBoard(Height: Int, Width: Int) {

  private var boardHeight: Int = Height

  private var boardWidth: Int = Width

  private var grid = Vector[Vector[Int]]()

  def getHeight:Int = boardHeight

  def getWidth:Int = boardWidth

  def getValue(row: Int, col: Int):Int = grid(row)(col)

  def isMine(row: Int, col: Int): Boolean = grid(row)(col) == -1

  def isCovered(row: Int, col: Int): Boolean = grid(row)(col) == -2

  def isUncovered(row: Int, col: Int): Boolean = grid(row)(col) >= 0

  def isFlag(row: Int, col: Int): Boolean = grid(row)(col) == -3

  def isValid(row: Int, col: Int): Boolean = row >= 0 && col >= 0 && row < boardHeight && col < boardWidth

  def setFlag(row: Int, col: Int) = {
    grid = grid updated(row, grid(row).updated(col, -3))
  }

  def setMine(row: Int, col: Int) = {
    grid = grid updated(row, grid(row).updated(col, -1))
  }

  def setCover(row: Int, col: Int) = {
    grid = grid updated(row, grid(row).updated(col, -2))
  }

  def setCoverAll: Unit = {
    for (i <- 0 until boardHeight) {
      val a: Vector[Int] = {
        for (j <- 0 until boardWidth) yield -2
      }.toVector
      grid = grid ++ Vector(a)
    }
  }

  def setUncover(row: Int, col: Int): Unit = {
    var count = 0
    for (i <- -1 to 1) {
      // checks the upper neighbors of the current
      // cell for mines
      if (isValid(row - 1, col + i)) {
        if (isMine(row - 1, col + i))
          count = count + 1
      }
    }
    for (i <- -1 to 1) {
      // checks the lower neighbors of the current
      // cell for mines
      if (isValid(row + 1, col + i)) {
        if (isMine(row + 1, col + i))
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
      for (i <- -1 to 1) {
        // Uncovering upper neighbors
        if (isValid(row - 1, col + i) && isCovered(row - 1, col + i)) {
          setUncover(row - 1, col + i)
        }
      }
    for (i <- -1 to 1) {
      // Uncovering lower neighbors
      if (isValid(row + 1, col + i) && isCovered(row + 1, col + i)) {
        setUncover(row + 1, col + i)
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
