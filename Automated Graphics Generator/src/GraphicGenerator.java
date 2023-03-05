// Imports for image generation
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
 
import javax.imageio.ImageIO;


//Other imports
import java.util.ArrayList;


/**
 * Generates and exports the images that will be used 
 * 
 * @author william
 *
 */
public class GraphicGenerator {
  private ArrayList<Band> bands;
  private int width;
  private int height;
  
  /**
   * Constructor that receives the ArrayList of Band objects
   * 
   * @param bands - the ArrayList of Band objects from which graphics will be generated
   */
  public GraphicGenerator(ArrayList<Band> bands, int width, int height) {
    this.bands = bands;
    this.width = width;
    this.height = height;
  }
  
  public void createAndExportAllGraphics() {
    // TODO: change the max back
    for (int i = 0; i < 3/*this.bands.size()*/; i ++) {
      try {
        this.createExportGraphic(bands.get(i));
      } catch (IOException e) {
        System.out.println("The background image file could not be located");
      }
    }
  }
  
  private void createExportGraphic(Band band) throws IOException {
    
    // the background image (options are in the "Music Backgrounds" folder)
    BufferedImage background = ImageIO.read(new File("Music Backgrounds/MusicBackground1.jpg"));
    
    
    Graphics2D g2d = background.createGraphics();
    
    g2d.setFont(new Font("TimesRoman", Font.PLAIN, 40));
    
    g2d.setColor(Color.WHITE);
    g2d.drawString(band.getName(), 100, 100);
        
    File outputFile = new File("Generated Graphics/" + band.toString() + ".jpg");
    
    try {
      ImageIO.write(background,  "png", outputFile);
    } catch (IOException e) {
      System.out.println("could not load the output file correctly");
      e.printStackTrace();
    }
  }
  
}
