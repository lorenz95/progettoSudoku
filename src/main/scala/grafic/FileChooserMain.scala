package grafic

import java.awt.Dimension
import java.awt.event.{ActionEvent, KeyEvent}
import java.io.File

import grafic.Sudoku.Sudoku
import javax.swing._
import javax.swing.filechooser.FileNameExtensionFilter
import resolutionAlgorithm.FullExploration
import sudoku.MatListOperation.initList
import sudoku.SudokuLoad.loadPuzzle
import utility.getPuzzle

object FileChooserMain extends App {
  // by zandoli
  val mainFrame = new JFrame("Sudoku")

  mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)

  mainFrame.setPreferredSize(new Dimension(400, 300))
  val menuBar = new JMenuBar()


  val menu = new JMenu("File")

  menu.setMnemonic(KeyEvent.VK_F)

  // menu.getAccessibleContext.setAccessibleDescription("The only menu in this program that has menu items")
  menuBar.add(menu)


  val openMenu = new JMenuItem("Open",
    KeyEvent.VK_O)
  openMenu.setAccelerator(KeyStroke.getKeyStroke(
    KeyEvent.VK_O, ActionEvent.ALT_MASK))

  val aboutMenu = new JMenuItem("About", KeyEvent.VK_U)
  aboutMenu.setAccelerator(KeyStroke.getKeyStroke(
    KeyEvent.VK_U, ActionEvent.ALT_MASK))

  val exitMenu = new JMenuItem("Exit", KeyEvent.VK_X)
  exitMenu.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.ALT_MASK))


  menu.add(openMenu)
  menu.add(aboutMenu)
  menu.addSeparator()
  menu.add(exitMenu)
  mainFrame.setJMenuBar(menuBar)
  mainFrame.pack()
  mainFrame.setVisible(true)

  openMenu.addActionListener((e: ActionEvent) => {
    initAndReUpload(mainFrame)
  })
  exitMenu.addActionListener((e: ActionEvent) => {
    sys.exit(0)
  })

  aboutMenu.addActionListener((e: ActionEvent) => {
    val n = JOptionPane.showMessageDialog(null, "Project done by:\n" + " Lorenzo Pacini\n " +
      "Silvia Zandoli\n" + " Alberto Antonelli", "About", JOptionPane.DEFAULT_OPTION)
  })


  def initAndReUpload(frame: JFrame): Unit = {
    import grafic.Helpers._
    //val jfc = new JFileChooser("input")
    //se si vuole riportare senza interfaccia togli come argomento JFrame, anche da Open
    //e metti showOpenDialog(null)
    //implicit
    val jfc = "input".filechooser()


    //val retValue = jfc.showOpenDialog(null)
    val retValue = jfc.open(frame)
    if (retValue == JFileChooser.APPROVE_OPTION) {
      //val file = jfc.multipleFiles()
      val file = jfc.selectedFile()
      //open one sudoku
      processFile(file)
      //file.foreach(processFile)

    }
  }

  def processFile(f: File) = {
    println(f)
    loadPuzzle(f.toString)
    initList()

    val sudokuSolver = FullExploration(getPuzzle)
    sudokuSolver.solve(0, 0)

    setPuzzleResolt(sudokuSolver.returnPuzzle())

    val sudoku = Sudoku()
    sudoku.create()

  }

  //initAndReUpload()
}

//using of the pattern implicit
object Helpers {

  implicit class MyStringHelper(str: String) {
    def filechooser() = {
      val jfc = new JFileChooser(str)
      jfc.setMultiSelectionEnabled(false)

      jfc.setAcceptAllFileFilterUsed(false)
      val filter = new FileNameExtensionFilter("Files txt", "txt")
      jfc.addChoosableFileFilter(filter)
      jfc

    }
  }

  implicit class MyFileChooserHelper(jfc: JFileChooser) {
    def open(frame: JFrame) = jfc.showOpenDialog(frame)

    //def multipleFiles() = jfc.getSelectedFiles()
    def selectedFile() = jfc.getSelectedFile()
  }

}