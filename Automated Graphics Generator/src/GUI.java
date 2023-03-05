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
  private File selectedFile;
  
  public GUI() {
    
    JFrame frame = new JFrame();
    
    buttonSelectFile = new JButton("Select File");
    buttonSelectFile.addActionListener(this);
    
    buttonConfirmGeneration = new JButton("Click to commence generation");
    buttonConfirmGeneration.addActionListener(this);
    
    JLabel label = new JLabel("Pleas select a \".txt file\" to create graphics from");
    
    JPanel panel = new JPanel();
    panel.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
    panel.setLayout(new GridLayout(0, 1));
    panel.add(buttonSelectFile);
    panel.add(buttonConfirmGeneration);
    panel.add(label);
    
    frame.add(panel, BorderLayout.CENTER);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Automated Graphics Generator");
    frame.pack();
    frame.setVisible(true);
    buttonConfirmGeneration.setVisible(false);

    
    
    
    frame.pack();
  }
  
  /**
   * The method that is executed whenever an ActionEvent occurs
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    
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
    
    if(e.getSource() == buttonConfirmGeneration) {
      System.out.println("button confirmation clicked");
      try {
        ArrayList<Band> bands = Driver.createArrayListofBands(selectedFile);
        GraphicGenerator test = new GraphicGenerator(bands, 1080, 720);
        test.createAndExportAllGraphics();
        System.out.println("graphics have been created");
      } catch (FileNotFoundException e1) {
        System.out.println("file was not found");
        e1.printStackTrace();
      }

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
