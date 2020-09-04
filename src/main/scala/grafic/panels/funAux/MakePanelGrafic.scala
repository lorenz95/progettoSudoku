package grafic.panels.funAux

private[panels] object MakePanelGrafic {
  import java.awt.event.ActionEvent
  import java.awt.{Color, Dimension}

  import grafic.util.FONT_MATLIST
  import grafic.{showNumberList, textTime}
  import grafic.panels._

  def setDimension(): Unit = {
    pb.setPreferredSize(new Dimension(ButtonsWidth, ButtonsHeight)) // dim
    pb.setBackground(WS)

    FL.setVgap(55)
    FL.setHgap(100) //set the flow layout to give  symmetric display
    pb.setLayout(FL)
  }

  def showNumberListMake(): Unit = {
    showNumberList.setForeground(Color.WHITE)
    showNumberList.setBackground(Color.BLUE)
    showNumberList.setFont(FONT_MATLIST)
    showNumberList.setPreferredSize(new Dimension(ButtonsWidth, ButtonsHeight))

    pb.add(showNumberList)
  }

  def startStopButtonMake(): Unit = {
    startStopButton.setBackground(Color.green)
    startStopButton.addActionListener((_: ActionEvent) => AuxFunctSPanel.startStop())

    pb.add(startStopButton)
  }

  def addButtons(): Unit = {
    pb.add(refreshList)
    textTime.setPreferredSize(new Dimension(ButtonsWidth, ButtonsHeight*2)) // dim
    pb.add(textTime)
    saveButton.addActionListener((_: ActionEvent) => SaveLoad.save())
    pb.add(saveButton)

    adviceButton.addActionListener((_: ActionEvent) => Advice.suggerisci())
    pb.add(adviceButton)
  }
}