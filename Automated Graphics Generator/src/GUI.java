import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener{
  
  private JButton buttonSelectFile;
  private JButton buttonConfirmGeneration;
  private JButton buttonBeginIgniteProgram;
  private File selectedFile;
  private GraphicGenerator test;
  private JFrame frame;
  private ArrayList<JButton> errorCheckingAlgorithmButtons;
  private JPanel panel;
  
  /**
   * constructor the the GUI
   */
  public GUI() {
    
    frame = new JFrame();
    
    buttonSelectFile = new JButton("Select File");
    buttonSelectFile.addActionListener(this);
    
    buttonConfirmGeneration = new JButton("Click to begin the generation of graphics");
    buttonConfirmGeneration.addActionListener(this);
    
    buttonBeginIgniteProgram = new JButton("Click this button to begin creating Ignite messages");
    buttonBeginIgniteProgram.addActionListener(this);
    
    JLabel label = new JLabel("Please select a \".txt file\" to create graphics from");
    
    panel = new JPanel();
    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
    panel.setLayout(new GridLayout(0, 1));
    panel.add(buttonSelectFile);
    panel.add(buttonConfirmGeneration);
    panel.add(buttonBeginIgniteProgram);
    buttonConfirmGeneration.setVisible(false);
    buttonBeginIgniteProgram.setVisible(false);
    panel.add(label);
    
    frame.add(panel, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Automated Graphics Generator");
    frame.pack();
    frame.setVisible(true);
  }
  
  /**
   * The method that is executed whenever an ActionEvent occurs
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    
    // case when the button to select a file is clicked
    if(e.getSource() == buttonSelectFile) {
      System.out.println("button select clicked");
      JFileChooser fileChooser = new JFileChooser();
      fileChooser.setCurrentDirectory(new File("."));
      int response = fileChooser.showOpenDialog(null); // select file to open
      if(response == JFileChooser.APPROVE_OPTION) {
        selectedFile = new File(fileChooser.getSelectedFile().getAbsolutePath());
      }
      buttonSelectFile.setVisible(false);
      buttonConfirmGeneration.setVisible(true);
    }
    
    // case when the button to generate graphics is clicked
    if(e.getSource() == buttonConfirmGeneration) {
      System.out.println("button confirmation clicked");
      try {
        ArrayList<Band> bands = Driver.createArrayListofBands(selectedFile);
        test = new GraphicGenerator(bands, 1080, 720);
        test.createAndExportAllGraphics();
        System.out.println("graphics have been created, now on to error");
        errorCheckingAlgorithmButtons = new ArrayList<JButton>(test.numBands());
        for(int i = 0; i < test.numBands(); i++) {
          String buttonText = test.getBand(i).nameIntoSections()[0];
          if(test.getBand(i).nameIntoSections()[1] != null) {
            buttonText = buttonText + "||" + test.getBand(i).nameIntoSections()[1];
          }
          errorCheckingAlgorithmButtons.add(new JButton(buttonText));
          panel.add(errorCheckingAlgorithmButtons.get(i));
          frame.pack();
        }
      } catch (FileNotFoundException e1) {
        System.out.println("file was not found");
        e1.printStackTrace();
      }
      buttonConfirmGeneration.setVisible(false);
      buttonBeginIgniteProgram.setVisible(true);
    }
    
    // case when the button to begin the Ignite program is clicked
    if(e.getSource() == buttonBeginIgniteProgram) {
      System.out.println("button begin ignite program clicked");
      try {
        new RobotOperator(test.numBands());
      } catch (AWTException e1) {
        System.out.println("Failed to create robot object");
        e1.printStackTrace();
      }
      
      buttonBeginIgniteProgram.setVisible(false);
    }
  }
  
  /**
   * Accessor method for the selected file
   * 
   * @return selectedFile - the file the user selects from the GUI
   */
  public File getSelectedFile() {
    return this.selectedFile;
  }
}
