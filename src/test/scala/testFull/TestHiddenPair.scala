package testFull

import org.scalatest.FunSuite
import resolutionAlgorithm.HiddenPair
import sudoku.MatListOperation
import sudoku.SudokuLoad.loadPuzzle
import utility.{display, puzzle}

class TestHiddenPair extends FunSuite {

  test("Hidden Pair") {
    //init
    val nameFile = "input/sudoku01.txt"
    val nameSolved = "outputSolved/sudoku01.txt"
      loadPuzzle(nameFile)

    display(puzzle)
    MatListOperation.initList()
  HiddenPair.solveHiddenPair()

    //verify bisogna vedere che funzioni e che si riesca a risolvere l'algoritmo

    //verify
  }


}