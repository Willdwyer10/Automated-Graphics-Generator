import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class RobotOperator {
  public RobotOperator(int numBands) throws AWTException {
    final int sleepTimeLong = 1000; // generally used for "mouse" operations
    final int sleepTimeShort = 500; // generally used for "key" operations
    final int sleepTimeShorter = 200; // generally used for rapid, looping, repeated keystrokes
    
    Robot robot = new Robot();
    
    ClickCoordinate createNewMessageButton = new ClickCoordinate(120, 175);
    ClickCoordinate insertButton = new ClickCoordinate(120, 30);
    ClickCoordinate importButton = new ClickCoordinate(200, 150);
    ClickCoordinate firstImageNormal = new ClickCoordinate(650, 250);
    ClickCoordinate firstImageAbnormal = new ClickCoordinate(650, 400);
    
    // clicks the "new message" button (or the empty space thats in that same location)
    createNewMessageButton.moveToClick(robot);
    sleep(sleepTimeLong);
    
    // Everything from here on out will be repeated
    for(int i = 0; i < 5; i++) {  
      // clicks the "insert" button
      insertButton.moveToClick(robot);
      sleep(sleepTimeLong);
      
      // clicks the "import" button
      importButton.moveToClick(robot);
      sleep(sleepTimeLong);
      
      // checks if there is either the "Failed to load image" message, or not
      Color sampleColor = robot.getPixelColor(635, 390);
      if(sampleColor.getRed() == 13 && sampleColor.getGreen() == 137 && sampleColor.getBlue() == 218) {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        sleep(sleepTimeLong);
        
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        sleep(sleepTimeLong);
        
        firstImageAbnormal.moveToClick(robot);
        sleep(sleepTimeLong);
      } else {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        sleep(sleepTimeLong);
        
        firstImageNormal.moveToClick(robot);
        sleep(sleepTimeLong);
      }
      
      // Now that the first image has been selected, arrow over the necessary number of times
      for(int j = 0; j < i; j++) {
        robot.keyPress(KeyEvent.VK_RIGHT);
        robot.keyRelease(KeyEvent.VK_RIGHT);
        sleep(sleepTimeShort);
      }
      
      // Navigate to the "File name:" box
      robot.keyPress(KeyEvent.VK_TAB);
      robot.keyRelease(KeyEvent.VK_TAB);
      sleep(sleepTimeShort);
      
      // Right arrow in order to get the very right side of the file name
      robot.keyPress(KeyEvent.VK_RIGHT);
      robot.keyPress(KeyEvent.VK_RIGHT);
      sleep(sleepTimeShort);
      
      // Move left past the file extension ".jpg"
      for(int j = 0; j < 4; j++) {
        robot.keyPress(KeyEvent.VK_LEFT);
        robot.keyRelease(KeyEvent.VK_LEFT);
        sleep(sleepTimeShort);
      }
      
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_SHIFT);
      for(int j = 0; j < 10; j++) {
        robot.keyPress(KeyEvent.VK_LEFT);
        robot.keyRelease(KeyEvent.VK_LEFT);
        sleep(sleepTimeShorter);
      }
      robot.keyPress(KeyEvent.VK_C);
      robot.keyRelease(KeyEvent.VK_C);
      
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyRelease(KeyEvent.VK_SHIFT);
      sleep(sleepTimeShort);
      
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyRelease(KeyEvent.VK_SHIFT);
      
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
      sleep(sleepTimeShort);

      robot.keyPress(KeyEvent.VK_TAB);
      robot.keyRelease(KeyEvent.VK_ENTER);
      sleep(sleepTimeShort);

      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
      sleep(sleepTimeShort);

      robot.keyPress(KeyEvent.VK_CONTROL);
      
      robot.keyPress(KeyEvent.VK_S);
      robot.keyRelease(KeyEvent.VK_S);
      sleep(sleepTimeLong);

      robot.keyPress(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_V);
      sleep(sleepTimeLong);

      for(int j = 0; j < 4; j++) {
        robot.keyPress(KeyEvent.VK_TAB);
        sleep(sleepTimeShorter);

      }
      
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
      sleep(sleepTimeShort);

      robot.keyPress(KeyEvent.VK_N);
      robot.keyPress(KeyEvent.VK_N);
      sleep(sleepTimeShort);

      robot.keyRelease(KeyEvent.VK_CONTROL);
    }
    System.out.println("Robot completed");

  }
    
    /*
      
      
    }
    
    System.out.println("Robot Operator complete");
    
  }*/
  
  /**
   * Method simply pauses the program momentarily
   * 
   * @param ms - how long you would like to delay/wait/sleep for
   */
  public static void sleep(long ms) {
    try {Thread.sleep(ms);} catch (Exception ignored) {}
  }
}