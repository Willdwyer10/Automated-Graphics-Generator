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
  // TODO: incorporate different "The Bar" locations
  // private Restaurant restaurant;
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
    for (int i = 0; i < this.bands.size(); i ++) {
      try {
        this.createAndExportGraphic(bands.get(i));
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
  private void createAndExportGraphic(Band band) throws IOException {
    
    // the background image (options are in the "Music Backgrounds" folder)
    // TODO: make this a randomly generated image
    // TODO: make any background image work for any graphic size by automatically resizing it to fit
    BufferedImage image = ImageIO.read(new File("Music Backgrounds/MusicBackground1.jpg"));
    Graphics2D g2d = image.createGraphics();
    
    // Variables to use for now before they are made into instance variables 
    int xPadding = 60;
    int maxNameFontSize = 250;
    int maxDateFontSize = 160;
    
    this.drawText(g2d, image, band, xPadding, maxNameFontSize, maxDateFontSize);
    File outputFile = new File("Generated Graphics/" + band.toString() + ".jpg");
    
    try {
      ImageIO.write(image,  "png", outputFile);
    } catch (IOException e) {
      System.out.println("could not load the output file correctly");
      e.printStackTrace();
    }
  }
  
  /**
   * Draws the name and date of the Band onto the Image
   * 
   * @param g2d - the Graphics2D object that the text is drawn onto
   * @param buffImg - the image that is being drawn onto
   * @param band - the Band that is currently having a graphic created
   * @param xPadding - the amount of spacing on either side of the text
   * @param maxNameFontSize - the maximum font size for the name (to prevent overcrowding)
   * @param maxDateFontSize - the maximum font size for the date (to prevent overcrowding)
   */
  private void drawText(Graphics2D g2d, BufferedImage buffImg, Band band, int xPadding, int maxNameFontSize, int maxDateFontSize) {
    Font bandNameFont;
    Font bandNameTempFont1 = new Font("Britannic Bold", Font.BOLD, 10);
    Font bandNameTempFont2 = new Font("Britannic Bold", Font.BOLD, 10);
    
    FontMetrics bandNameMetrics;
    int bandNameMaxLineSize1 = -1;
    int bandNameMaxLineSize2 = -1;

    String[] bandNameSections = band.nameIntoSections();
    
    // Finds the max size of line 1 of the band name
    {
      bandNameMetrics = g2d.getFontMetrics(bandNameTempFont1);
      while(bandNameMetrics.stringWidth(bandNameSections[0]) < (buffImg.getWidth() - xPadding * 2)) {
        int newSize = bandNameTempFont1.getSize() + 10;
        bandNameTempFont1 = new Font(bandNameTempFont1.getFontName(), Font.BOLD, newSize);
        bandNameMetrics = g2d.getFontMetrics(bandNameTempFont1);
      }
      bandNameMaxLineSize1 = bandNameTempFont1.getSize();
    }
    
    // finds the max size of line 2 (if applicable)
    if(bandNameSections[1] != null) {
      bandNameMetrics = g2d.getFontMetrics(bandNameTempFont2);
      while(bandNameMetrics.stringWidth(bandNameSections[1]) < (buffImg.getWidth() - xPadding *2)) {
        int newSize = bandNameTempFont2.getSize() + 10;
        bandNameTempFont2 = new Font(bandNameTempFont2.getFontName(), Font.BOLD, newSize);
        bandNameMetrics = g2d.getFontMetrics(bandNameTempFont2);
      }
      bandNameMaxLineSize2 = bandNameTempFont2.getSize();
    }
    
    // Decides which size to use (if only 1 line, uses the first size automatically)
    if(bandNameSections[1] == null) {
      bandNameFont = new Font(bandNameTempFont1.getFontName(), Font.BOLD, bandNameMaxLineSize1);
    } else {
      if(bandNameMaxLineSize1 > bandNameMaxLineSize2) {
        bandNameFont = new Font(bandNameTempFont2.getFontName(), Font.BOLD, bandNameMaxLineSize2);
      } else {
        bandNameFont = new Font(bandNameTempFont1.getFontName(), Font.BOLD, bandNameMaxLineSize1);
      }
    }
    
    // Checks that the font is not larger than the largest allowable font size
    if(bandNameFont.getSize() > maxNameFontSize) {
      bandNameFont = new Font(bandNameFont.getFontName(), Font.BOLD, maxNameFontSize);
    }
    
    bandNameMetrics = g2d.getFontMetrics(bandNameFont);
    
    
    
    // Now moving on to the stuff for the month and date
    Font bandDateFont = new Font("Britannic Bold", Font.BOLD, 10);; //= new Font("Britannic Bold", Font.BOLD, 10);
    FontMetrics bandDateMetrics = g2d.getFontMetrics(bandDateFont); //= g2d.getFontMetrics(bandDateFont);
    
    String dateText = "";
    
    switch(band.getMonth()) {
      case 1: dateText = dateText + "Jan. "; break;
      case 2: dateText = dateText + "Feb. "; break;
      case 3: dateText = dateText + "March "; break;
      case 4: dateText = dateText + "April "; break;
      case 5: dateText = dateText + "May "; break;
      case 6: dateText = dateText + "June "; break;
      case 7: dateText = dateText + "July "; break;
      case 8: dateText = dateText + "August "; break;
      case 9: dateText = dateText + "Sept. "; break;
      case 10: dateText = dateText + "Oct. "; break;
      case 11: dateText = dateText + "Nov. "; break;
      case 12: dateText = dateText + "Dec. "; break;
    }
    
    dateText = dateText + Integer.toString(band.getDate());
    
    switch(band.getDate()) {
      case 1, 21, 31: dateText = dateText + "st"; break;
      case 2, 22: dateText = dateText + "nd"; break;
      case 3, 23: dateText = dateText + "rd"; break;
      default: dateText = dateText + "th"; break;
    }
    
    while(bandDateMetrics.stringWidth(dateText) < (buffImg.getWidth() - xPadding *2)) {
      int newSize = bandDateFont.getSize() + 10;
      bandDateFont = new Font(bandDateFont.getFontName(), Font.BOLD, newSize);
      bandDateMetrics = g2d.getFontMetrics(bandDateFont);
    }
    
    if(bandDateFont.getSize() > maxDateFontSize) {
      bandDateFont = new Font(bandDateFont.getFontName(), Font.BOLD, maxDateFontSize);
      bandDateMetrics = g2d.getFontMetrics(bandDateFont);
    }
    
    
    
    // Now we can actually draw the images, but first we gotta calculate vertical spacing
    int whitespaceDouble = (buffImg.getHeight() - 
        (bandNameMetrics.getHeight()*2 + bandDateMetrics.getHeight())) / 4;
    int[] yNameLineDouble = new int[2];
    yNameLineDouble[0] = whitespaceDouble + (int) (bandNameMetrics.getHeight()*0.85);
    yNameLineDouble[1] = yNameLineDouble[0] + whitespaceDouble + bandNameMetrics.getHeight();
    int yDateLineDouble = yNameLineDouble[1] + whitespaceDouble + bandDateMetrics.getHeight();
    
    int whitespaceSingle = (buffImg.getHeight() - 
        (bandNameMetrics.getHeight() + bandDateMetrics.getHeight())) / 3;
    int yNameLineSingle = whitespaceSingle + bandNameMetrics.getHeight();
    int yDateLineSingle = yNameLineSingle + whitespaceSingle + bandDateMetrics.getHeight();
    
    // Now let's draw the names
    g2d.setFont(bandNameFont);
    g2d.setColor(Color.WHITE);
    
    if(bandNameSections[1] != null) {
      for(int i = 0; i < bandNameSections.length; i++) {
        int x = (buffImg.getWidth() - bandNameMetrics.stringWidth(bandNameSections[i])) / 2;
        int y = yNameLineDouble[i];
        g2d.drawString(bandNameSections[i], x, y);
      }
    } else {
      int x = (buffImg.getWidth() - bandNameMetrics.stringWidth(bandNameSections[0])) / 2;
      int y = yNameLineSingle;
      g2d.drawString(bandNameSections[0], x, y);
    }
    
    // Now let's draw the dates
    g2d.setFont(bandDateFont);
    g2d.setColor(Color.LIGHT_GRAY);
    int dateX = (buffImg.getWidth() - bandDateMetrics.stringWidth(dateText)) / 2;

    if(bandNameSections[1] != null) {
      int y = yDateLineDouble;
      g2d.drawString(dateText, dateX, y);
    } else {
      int y = yDateLineSingle;
      g2d.drawString(dateText, dateX, y);
    }
  }
  
}
