// Imports for image generation
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.GraphicsEnvironment;
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
  
  /**
   * Creates and exports all of the graphics
   */
  public void createAndExportAllGraphics() {
    // TODO: change the max back
    for (int i = 0; i < this.bands.size(); i ++) {
      try {
        this.createExportGraphic(bands.get(i));
      } catch (IOException e) {
        System.out.println("The background image file could not be located");
      }
    }
  }
  
  /**
   * Creates and exports a single Band graphic
   * 
   * @param band - a Band object that holds all the band's information
   * @throws IOException - if the output file is not found
   */
  private void createExportGraphic(Band band) throws IOException {
    
    // the background image (options are in the "Music Backgrounds" folder)
    // TODO: make this a randomly generated image
    // TODO: make any background image work for any graphic size by automatically resizing it to fit
    BufferedImage image = ImageIO.read(new File("Music Backgrounds/MusicBackground1.jpg"));
    Graphics2D g2d = image.createGraphics();
    
    
    
    String[] nameIntoSections = band.nameIntoSections();
    this.newDrawBandName(g2d, image, nameIntoSections, 60, 200, 250);
    
    File outputFile = new File("Generated Graphics/" + band.toString() + ".jpg");
    
    try {
      ImageIO.write(image,  "png", outputFile);
    } catch (IOException e) {
      System.out.println("could not load the output file correctly");
      e.printStackTrace();
    }
  }
  
  /**
   * Places the Band name centered horizontally on the image
   * 
   * @param g2d - the Graphics2D object where the images should be drawn
   * @param buffImg - the image being used to determine the "canvas" size
   * @param text - the array of lines of text to be displayed
   * @param xPadding - the amount of padding to go on the outside of the text
   * @param yBasePadding - the amount of room to leave on the bottom for the date
   */
  private void newDrawBandName(Graphics2D g2d, BufferedImage buffImg, 
                      String[] text, int xPadding, int yBasePadding, int maxFontSize) {
    Font font;
    
    Font tempFont1 = new Font("Britannic Bold", Font.BOLD, 10);
    Font tempFont2 = new Font("Britannic Bold", Font.BOLD, 10);
    
    FontMetrics metrics;
    int maxSizeLine1 = -1;
    int maxSizeLine2 = -1;
    
    // Finds the max size of line 1
    if(true) {
      metrics = g2d.getFontMetrics(tempFont1);
      while(metrics.stringWidth(text[0]) < (buffImg.getWidth() - xPadding * 2)) {
        int newSize = tempFont1.getSize() + 10;
        
        tempFont1 = new Font(tempFont1.getFontName(), Font.BOLD, newSize);
        
        metrics = g2d.getFontMetrics(tempFont1);
      }
      maxSizeLine1 = tempFont1.getSize();
    }
    
    // Finds the max size of line 2 (if applicable)
    if(text[1] != null) {
      metrics = g2d.getFontMetrics(tempFont2);
      while(metrics.stringWidth(text[1]) < (buffImg.getWidth() - xPadding * 2)) {
        int newSize = tempFont2.getSize() + 10;
        
        tempFont2 = new Font(tempFont2.getFontName(), Font.BOLD, newSize);
        
        metrics = g2d.getFontMetrics(tempFont2);
      }
      maxSizeLine2 = tempFont2.getSize();
    }
    
    // for testing purposes: prints out the max sizes of each line
    System.out.println("maxSizeLine1: " + maxSizeLine1);
    System.out.println("maxSizeLine2: " + maxSizeLine2);
    
    // Decides which size to use (if only 1 line, automatically set size to size of first line
    // and assigns the size to the font accordingly
    if(text[1] == null) {
      font = new Font(tempFont2.getFontName(), Font.BOLD, maxSizeLine1);
    } else {
      if(maxSizeLine1 > maxSizeLine2) {
        font = new Font(tempFont2.getFontName(), Font.BOLD, maxSizeLine2);
      } else {
        font = new Font(tempFont1.getFontName(), Font.BOLD, maxSizeLine1);
      }
    }
    
    // Checks that the text isn't bigger than the max size
    if(font.getSize() > maxFontSize) {
      font = new Font(font.getFontName(), Font.BOLD, maxFontSize);
    }
    
    metrics = g2d.getFontMetrics(font);
    g2d.setFont(font);
    g2d.setColor(Color.WHITE);
    
    if(text[1] != null) {
      for(int i = 0; i < 2; i++) {
        int x = (buffImg.getWidth() - metrics.stringWidth(text[i])) / 2;
        int y = (((buffImg.getHeight() - yBasePadding - (2 * metrics.getHeight())) / 3) + metrics.getHeight()) * (i + 1);
        g2d.drawString(text[i], x, y);
      }
    } else {
      int x = (buffImg.getWidth() - metrics.stringWidth(text[0])) / 2;
      int y = (buffImg.getHeight() - yBasePadding - metrics.getHeight()) / 2 + metrics.getHeight();
      g2d.drawString(text[0], x, y);

    }
  }
  
}
