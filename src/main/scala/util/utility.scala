import javax.swing.JTextField

import scala.annotation.tailrec

/**
 * Fatto da Lorenzo Pacini
 **/
package object utility {

  val dimSudoku = 9
  val matList: Array[Array[List[Int]]] = Array.ofDim[List[Int]](dimSudoku, dimSudoku)
  var puzzle: Array[Array[Int]] = Array.ofDim[Int](dimSudoku, dimSudoku)
  var elemEmpty: Int = dimSudoku * dimSudoku

  var tfCells: Array[Array[JTextField]] = Array.ofDim[JTextField](dimSudoku, dimSudoku)

  def puzzleSolved(): Boolean = puzzle.flatten.forall(_.!=(0))

  def calculateEmpty(): Int = {
    puzzle.flatten.foreach {
      case 0 =>
      case _ => elemEmpty = elemEmpty - 1
    }
    elemEmpty
  }

  def contains[A](list: List[A], item: A): Boolean =
    list.foldLeft(false)(_ || _==item)

  def confrontPuzzle[T](puzzle1: Array[Array[T]], puzzle2: Array[Array[T]]): Boolean = {
      if (puzzle1.length == puzzle2.length) {
        for (i <- 0 until dimSudoku; j <- 0 until dimSudoku)
          if (puzzle1(i)(j) != puzzle2(i)(j)) return false
        return true
      }
    false
  }

  /*
  si restituisce un nuovo oggetto, mai il riferimento
   */
  def getPuzzle: Array[Array[Int]] = {
    val puzzleTemp: Array[Array[Int]] = Array.ofDim[Int](dimSudoku, dimSudoku)
    for {i <- 0 until dimSudoku; j <- 0 until dimSudoku} yield {
      puzzleTemp(i)(j) = puzzle(i)(j)
    }
    puzzleTemp
  }

  @tailrec
  final def computeOnList[T](f: T => Unit, list: List[T]): Unit = list match {
    case h :: t => f(h); computeOnList(f, t)
    case _ =>
  }

  def printMatrix(): Unit = {
    for (i <- 1 until dimSudoku; j <- 1 until dimSudoku) yield computeOnList(print, matList(i)(j))
  }

  def display(puzzleGame: Array[Array[Int]]): Unit = {
    display("", puzzleGame)
  }

  def display(title: String, puzzleGame: Array[Array[Int]]): Unit = {
    def closureSudokuLine(l:Array[Int]): String = {
      l.map {
        case 0 => "_"
        case y => `y`.toString
      }.mkString(" ")
    }

    println(title)
    (0 until dimSudoku).foreach(i => {
      computeOnList(print, closureSudokuLine(puzzleGame(i)).toList)
      println()
    })
    println()
  }

  def displayList(row: Int, col: Int): Unit = {
    print("[" + row + " " + col + "]  ")
    computeOnList(print, matList(row)(col))
  }
}
