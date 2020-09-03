package grafic.panels.funAux

import java.awt.Color

import grafic.textTime
import grafic.panels.AuxFunctSPanel.{stopVar, timeInit}
import grafic.panels.startStopButton
import grafic.util.{factSecond, score}

/**
 * Made by Pacini
 */
sealed trait ThreadTime extends Thread {

  private def appendScoreTime(): Unit = {
    val text: String = textTime.getText()
    if (!text.isEmpty) {
      try {
        val time = text.substring(11, text.length).toInt

        synchronized {
          textTime.setText("")
          textTime.append("Your score = " + score)
          textTime.append("\nYour time = " + time)
        }
        startStopButton.setBackground(Color.green)
      } catch {
        case _: NumberFormatException =>
      }
    }
  }

  override def run() {
    while (true) {
      if (!stopVar) {
        println("timeInit = " + timeInit)
        synchronized {
          textTime.setText("Your Time: " + ((System.currentTimeMillis() / factSecond) - timeInit))
        }
      } else {
        appendScoreTime()
      }
    }
  }
}
object ThreadTime {
  def apply(): ThreadTime = ThreadTimeImpl()
  private case class ThreadTimeImpl() extends ThreadTime
}
