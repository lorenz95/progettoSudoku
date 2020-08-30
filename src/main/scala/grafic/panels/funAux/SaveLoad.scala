package grafic.panels.funAux

import grafic.util.FileWork

object SaveLoad {

  import java.io.{BufferedWriter, File, FileWriter}

  import grafic.panels.AuxFunctSPanel.timeInit
  import grafic.util.score
  import grafic.{masks, textTime, tfCells}
  import utility.dimSudoku
  def save(): Unit = {
    FileWork.deleteFile()
    FileWork.createFile()
    val bw = new BufferedWriter(new FileWriter(new File("temp/tmp.txt")))

    //you save the file
    for {
      i <- 0 until dimSudoku
      j <- 0 until dimSudoku
    } {
      if (masks(i)(j)) {
        val text = tfCells(i)(j).getText
        val number = text.charAt(0)
        bw.append(number)
      } else {
        bw.append('0')
      }

      if (j == dimSudoku - 1) {
        bw.append('\n')
      }
    }

    bw.close()

    //you save the score and the time

    FileWork.createFile()
    val scoreFile = new BufferedWriter(new FileWriter(new File("score/timer.txt")))
    val text = textTime.getText()
    scoreFile.write(text)
    scoreFile.close()
  }

  def read(): Unit= {
    val bufferedSource = scala.io.Source.fromFile("score/timer.txt")
    if(bufferedSource.isEmpty){
      timeInit=0
      score=0
    }else {
      for (lines <- bufferedSource.getLines()) {
        println(lines)
      }
    }
    bufferedSource.close()
  }
}
