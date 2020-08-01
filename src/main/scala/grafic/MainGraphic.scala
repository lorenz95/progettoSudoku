package grafic
import grafic.Sudoku.Sudoku
import resolutionAlgorithm.FullExploration
import scalafx.Includes._
import scalafx.application
import scalafx.application.JFXApp
import scalafx.event.ActionEvent
import scalafx.scene.Scene
import scalafx.scene.control._
import scalafx.scene.input.{KeyCode, KeyCodeCombination, KeyCombination}
import scalafx.stage.FileChooser
import scalafx.stage.FileChooser.ExtensionFilter
import sudoku.MatListOperation.initList
import sudoku.SudokuLoad.loadPuzzle
import utility.getPuzzle

import scala.io.Source


object MainGraphic extends JFXApp {

  stage=new application.JFXApp.PrimaryStage {
    title="Menu"
    scene=new Scene(600,300) {
      val menuBar = new MenuBar
      val fileMenu = new Menu("Open Sudoku")

      val startItem=new MenuItem("Start Game")
      startItem.accelerator=new KeyCodeCombination(KeyCode.Y,KeyCombination.ControlDown)

      val openItem = new MenuItem("Open File")
      openItem.accelerator = new KeyCodeCombination(KeyCode.Digit0, KeyCombination.ControlDown)

      val saveItem = new MenuItem("Rename File")
      saveItem.accelerator = new KeyCodeCombination(KeyCode.Digit5, KeyCombination.ControlDown)



      val exitItem = new MenuItem("Exit")
      exitItem.accelerator = new KeyCodeCombination(KeyCode.X, KeyCombination.ControlDown)


      fileMenu.items = List( startItem, openItem, saveItem, new SeparatorMenuItem, exitItem)
      val initGame=new Button("Init Game:")


      menuBar.menus  = List(fileMenu)
      menuBar.prefWidth = 800



      val label = new Label("File Opened:")
      label.layoutX = 20
      label.layoutY = 150


      content=List(menuBar,label)

      //defining handles
      exitItem.onAction=(event:ActionEvent) => sys.exit(0)

      //apre il file txt e lo carica nella matrice del sudoku, inizia il gioco
      startItem.onAction= (event:ActionEvent)=>{

        val fileChooser=new FileChooser{
          title = "Open Resource File"

          //you can open only file txt
          extensionFilters ++= Seq(
            new ExtensionFilter("Text Files", "*.txt"),

          )

        }
        //you can open multiple sudoku
        val selectedFile= fileChooser.showOpenMultipleDialog(stage)


        selectedFile.foreach(i=>{
          //i.toString

          label.text="Open "+ selectedFile
          loadPuzzle(i.toString)
          initList()
          val sudokuSolver = FullExploration(getPuzzle)
          sudokuSolver.solve(0, 0)

          setPuzzleResolt(sudokuSolver.returnPuzzle())

          val sudoku = Sudoku()
          sudoku.create()
        })
        // loadPuzzle(selectedFile.toString)



        //areaText.
      }

      //apre il file txt e lo stampa su label
      openItem.onAction= (event:ActionEvent)=>{
        val fileChooser=new FileChooser{
          title = "Open Resource File"

          //you can open only file txt
          extensionFilters ++= Seq(
            new ExtensionFilter("Text Files", "*.txt"),

          )

        }
        //you can open multiple sudoku
        val selectedFile= fileChooser.showOpenDialog(stage)
        // val lines = Source.fromFile("selectedFile").getLines.toList
        /* for (line <- Source.fromFile(selectedFile).getLines) {
           println(line)

         }*/
        val fileContents = Source.fromFile(selectedFile).getLines.mkString
        label.text= fileContents
      }

      saveItem.onAction = (event:ActionEvent) => {
        val fileChooser=new FileChooser
        val selectedFile=fileChooser.showSaveDialog(stage)
        label.text="Save "+selectedFile

      }
    }











  }








}
