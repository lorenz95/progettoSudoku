import scala.io.Source

object Sudoku {
  val dimSudoku = 9
  val puzzle: Array[Array[Int]] = Array.ofDim[Int](dimSudoku, dimSudoku)
  val nameFile = "input/sudoku11.txt"

  def main(args: Array[String]): Unit = {
    parsePuzzle(readFile(nameFile).toList, 0)
    display()
    solve(0, 0)
    display()
  }

  def readFile(fileName: String): Array[String] = {
    val file = Source.fromFile(fileName)
    val it = file.getLines()
    Source.fromFile(fileName).close()
    it.toArray
  }

  def parsePuzzle(puzzleInput: List[String], row: Int): Unit = {
    puzzleInput match {
        case h :: t => ({
          var col = 0
          h.foreach(ch => {
            puzzle(row)(col) = ch.asDigit
            col+=1
          })
        }, parsePuzzle(t, row+1))
        case _ =>
      }
  }

  def validate(row: Int, col: Int, num: Int): Boolean = {
    for (i <- 0 until dimSudoku) {
      if (puzzle(row)(i) == num || puzzle(i)(col) == num) {
        return false
      }
    }

    val r = (row / 3) * 3
    val c = (col / 3) * 3
    for (i <- r until r + 3) {
      for (j <- c until c + 3) {
        if (puzzle(i)(j) == num) {
          return false
        }
      }
    }
    true
  }

  object util {
    def formatSudokuLine(l:Array[Int]): String =
      l.map(y => if (y == 0) "_" else y.toString).mkString(" ")
  }

  def display(): Unit = {
    for (i <- puzzle.indices) {
      print(util.formatSudokuLine(puzzle(i)))
      println()
    }
    println()
  }

  def puzzleSolved(): Boolean = {
    for (i <- 0 until dimSudoku) {
      for (j <- 0 until dimSudoku) {
        if (puzzle(i)(j) == 0) {
          return false
        }
      }
    }

    true
  }

  def next(row: Int, col: Int): Boolean = {
    if (col >= 8) {
      solve(row + 1, 0)
    } else {
      solve(row, col + 1)
    }
  }

  def solve(row: Int, col: Int): Boolean = {
    if (puzzleSolved()) {
      return true
    } else if (puzzle(row)(col) > 0) {
      return next(row, col)
    } else {
      for (i <- 1 to dimSudoku) {
        if (validate(row, col, i)) {
          puzzle(row)(col) = i

          if (next(row, col)) {
            return true
          }

          puzzle(row)(col) = 0
        }
      }
    }
    false
  }
}