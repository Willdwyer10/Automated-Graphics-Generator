import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
//import java.io.IOException;
import java.util.ArrayList;

/**
 * Driver class for the program
 * 
 * @author william
 *
 */
public class Driver {

  public static void main(String[] args) throws FileNotFoundException {
    
    
    // Open the file and initialize the scanner
    /*
     * NOTE: "file" should be formatted as follows (must include newline at end of last band):
     * month/date name
     * month/date name
     * (additional newline)
     * 
     */
    System.out.println("opening Mock Band Information.txt");
    File file = new File("Mock Band Information.txt");
    
    // 
    ArrayList<Band> bands = Driver.createArrayListofBands(file);
    
    System.out.println(bands);
    		
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
    System.out.println("successfully opened the file");
    
    // All the bands from the spreadsheet
    ArrayList<Band> bands = new ArrayList<Band>();
    
    // Getting the data from the file
    System.out.println("Trying to read data from the opened file");
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
        
    System.out.println("Successfully read data from the opened file");
    
    inFS.close();
    
    return bands;
  }

}

