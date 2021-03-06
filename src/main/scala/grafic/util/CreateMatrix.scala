package grafic.util

/**
 * Made by Pacini
 */
object CreateMatrix {
  import javax.swing.{JPanel,BorderFactory}
  import java.awt.{Dimension, GridLayout, Color}
  import utility.{dimSudoku, puzzle}
  import grafic.{cp, tfCells}
  import grafic.panels.TextOpNumber.TextOpNumber

  /**
   * creazione del pannello della matrice di gioco
   */
  private def getMatrixGame: JPanel = {
    val matrixGame = new JPanel()

    matrixGame.setPreferredSize(new Dimension(MATRIX_WIDTH, MATRIX_HEIGHT))
    matrixGame.setLayout(new GridLayout(dimSudoku, dimSudoku))

    matrixGame
  }

  /**
   * matrice di gioco dove in base al numero del puzzle caricato:
   * se 0 vengono associati i 2 listener nella corrispondente cella
   * se > 0 viene scritto il numero sulla matrice di gioco
   */
  def createMatrix(): Unit = {
    val matrixGame = getMatrixGame
    cp.add(matrixGame)

    for (row <- 0 until dimSudoku; col <- 0 until dimSudoku) {
      tfCells(row)(col) = TextOpNumber()
      tfCells(row)(col).setBorder(BorderFactory.createLineBorder(Color.black))
      matrixGame.add(tfCells(row)(col)) // ContentPane adds JTextArea

      puzzle(row)(col) match {
        case 0 =>
          AssociateListener.writeTextEmpty(tfCells(row)(col), row, col,
            editFlag = true,
            FONT_NUMBERS, OPEN_CELL_BGCOLOR, OPEN_CELL_TEXT_YES)

        case _ =>
          AssociateListener.writeText(tfCells(row)(col), row, col,
            editFlag = false,
            FONT_NUMBERS, CLOSED_CELL_BGCOLOR, CLOSED_CELL_TEXT)(puzzle(row)(col) + "")
      }

      // Beautify all the cells
      tfCells(row)(col).setAlignmentX(10)
    }

    createBorder()
  }

  /**
   * imposta il bordo di ciascuna cella come nero, le celle di separazione,
   * cioè quelle che stanno nelle righe e colonne di indice pari a partire
   * da 2 sono colorate di rosso
   */
  private def createBorder(): Unit = {
    for (i <- 0 until dimSudoku; j <- 2 until dimSudoku by 3) {
      tfCells(i)(j).setBorder(BorderFactory.createMatteBorder(1,1,1,3,Color.red))
    }

    for (i <- 2 until dimSudoku by 3; j <- 0 until dimSudoku) {
      tfCells(i)(j).setBorder(BorderFactory.createMatteBorder(1,1,3,1,Color.red))
    }
  }
}
