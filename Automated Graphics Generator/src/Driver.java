import java.util.Scanner;
import javax.imageio.ImageIO;
import java.awt.GraphicsEnvironment;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.AWTException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.io.IOException;
import java.util.ArrayList;

//Stuff for GUI
import javax.swing.*;

/**
 * Driver class for the program
 * 
 * @author william
 *
 */
public class Driver {

  public static void main(String[] args){
    
    /*
     * NOTE: "file" should be formatted as follows (must include newline at end of last band):
     * month/date name
     * month/date name
     * (additional newline)
     */
    // GUI STUFF
    /*while(true) {
      
      try {
      Point p = MouseInfo.getPointerInfo().getLocation();
      System.out.println("(" + p.x + ", " + p.y + ")");
      } catch (Exception e) {
        System.out.println("skipped");
      }
    }*/
    
    
    
    new GUI();
    
    
  }

  /**
   * Method to generate an ArrayList of bands based on a text file passed to the method
   * 
   * @param file - the file that contains the list of bands
   * @return an ArrayList of Band objects that was read from a text file
   * @throws FileNotFoundException if the file passed to the method is not found in a relative path
   */
  public static ArrayList<Band> createArrayListofBands(File file) throws FileNotFoundException{
    
    Scanner inFS = new Scanner(file);
    
    // All the bands from the spreadsheet
    ArrayList<Band> bands = new ArrayList<Band>();
    
    // Getting the data from the file
    while(inFS.hasNext()) {
      int month;
      int date;
      String name;
      // gets month
      inFS.useDelimiter("/");
      month = Integer.parseInt(inFS.next());
      
      // uses one character (the "/" in this case)
      inFS.useDelimiter("");
      inFS.next();
      
      // gets date
      inFS.useDelimiter(" ");
      date = Integer.parseInt(inFS.next());
      
      // uses one character (the " " in this case)
      inFS.useDelimiter("");
      inFS.next();
      
      // gets name
      inFS.useDelimiter("\n");
      name = inFS.nextLine();
      
      bands.add(new Band(month, date, name));
    }
            
    inFS.close();
    
    return bands;
  }

}

