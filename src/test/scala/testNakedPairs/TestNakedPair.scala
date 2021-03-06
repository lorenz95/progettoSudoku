package testNakedPairs

import org.scalatest.FunSuite
import resolutionAlgorithm.{FullExploration}
import sudoku.SudokuLoad.loadPuzzle
import sudoku.MatListOperation.initList
import utility.getPuzzle
import resolutionAlgorithm.NakedPairs._
import utility._



class TestNakedPair extends FunSuite {
  test("Sudoku test with change MatList in row") {

    val nameFile = "input/easy/sudoku30.txt"
    loadPuzzle(nameFile)

    displayNoTitle(puzzle)
    initList()
    for (i <- 0 to 8) {
      println("MatList riga " + 0 + " colonna" + i + " è " + matList(0)(i).toString)
    }

    val initMat = utility.matList(0)(4)
    findCouple(0,true)
    for (i <- 0 to 8) {
      println("MatList DOPO riga " + 0 + " colonna" + i + " è " + matList(0)(i).toString)
    }

    val finishMat = utility.matList(0)(4)

    println("matList iniziale " + initMat)
    println("matList finale "+ finishMat)
    assert(initMat != finishMat)
  }

  test("Sudoku test change Matlist in subSquare") {
    //init
    val nameFile = "input/easy/sudoku31.txt"
    loadPuzzle(nameFile)

    displayNoTitle(puzzle)
    initList()
    for (i <- 6 to 8) {
      for(j <- 0 to 3)
        println("MatList riga " + i + " colonna" + j + " è " + matList(i)(j).toString)
    }
    val initMat = utility.matList(7)(2)
    findCoupleSubSquare(6,0)

    for (i <- 6 to 8) {
      for(j <- 0 to 3)
        println("MatList DOPO riga " + i + " colonna" + j + " è " + matList(i)(j).toString)
    }
    val finishMat = utility.matList(7)(2)

    println("matList iniziale " + initMat)
    println("matList finale "+ finishMat)
    assert(initMat != finishMat)
  }
  test("Sudoku 01") {
    val nameFile = "input/easy/sudoku01.txt"

    loadPuzzle(nameFile)
    initList()
    val sudokuInput = getPuzzle
    findCouple(0,true)
    val sudokuSolved = getPuzzle

    assert(sudokuInput(0)(2) == sudokuSolved(0)(2))
    assert(sudokuInput(0)(6) == sudokuSolved(0)(6))

  }

  test("Sudoku Test Row") {
    val nameFile = "input/easy/sudoku30.txt"
    loadPuzzle(nameFile)

    displayNoTitle(puzzle)
    initList()
    for (i <- 0 to 8) {
      println("MatList riga " + 2 + " colonna" + i + " è " + matList(2)(i).toString)
    }

    val initMat = utility.matList(2)(4)
    findCouple(2,true)
    for (i <- 0 to 8) {
      println("MatList DOPO riga " + 2 + " colonna" + i + " è " + matList(2)(i).toString)
    }

    val finishMat = utility.matList(2)(4)

    println("matList iniziale " + initMat)
    println("matList finale "+ finishMat)
    assert(initMat != finishMat)
  }
  test("Sudoku23 - check if not destroy matList") {
    val nameFile = "input/easy/sudoku23.txt"

    loadPuzzle(nameFile)
    initList()
    val solverWithout = FullExploration(getPuzzle)
    solverWithout.solve(0, 0)

    loadPuzzle(nameFile)
    initList()
    findCouple(7,true)
    findCoupleSubSquare(0,0)
    findCouple(7,false)
    val solverWith = FullExploration(getPuzzle)
    solverWith.solve(0, 0)
    assert(solverWithout.returnPuzzle()(2)(2) == solverWith.returnPuzzle()(2)(2))
    assert(solverWithout.returnPuzzle()(5)(2) == solverWith.returnPuzzle()(5)(2))

  }

  /*test("Sudoku05") {
    val nameFile = "input/sudoku06.txt"

    loadPuzzle(nameFile)
    initList()
    findCouple(0,false)
    val sudokuInput = getPuzzle

    val solver = FullExploration(getPuzzle)
    solver.solve(0, 0)


    assert(sudokuInput(2)(2) == solver.returnPuzzle()(2)(2))
    assert(sudokuInput(5)(2) == solver.returnPuzzle()(5)(2))

  }*/

}
