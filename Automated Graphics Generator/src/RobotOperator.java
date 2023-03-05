import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class RobotOperator {
  public RobotOperator(int numBands) throws AWTException {
    final int sleepTimeLong = 1000; // generally used for "mouse" operations
    final int sleepTimeShort = 100; // generally used for "key" operations
    final int sleepTimeShorter = 25; // generally used for rapid, looping, repeated keystrokes
    Robot robot = new Robot();
    
    ClickCoordinate createNewMessageButton = new ClickCoordinate(120, 175);
    ClickCoordinate insertButton = new ClickCoordinate(120, 30);
    ClickCoordinate importButton = new ClickCoordinate(200, 150);
    ClickCoordinate browseButton = new ClickCoordinate(900, 100);
    ClickCoordinate firstImageNormal = new ClickCoordinate(650, 250);
    ClickCoordinate firstImageAbnormal = new ClickCoordinate(650, 380);

    // 635, 390
    
    createNewMessageButton.moveToClick(robot);
    sleep(sleepTimeLong);
    //Sequence of events to have it click all the buttons and what not
    for(int i = 0; i < numBands; i++) {
      insertButton.moveToClick(robot);
      sleep(sleepTimeLong);
      importButton.moveToClick(robot);
      sleep(sleepTimeShort);
      
      // Checks to see if it says "Failed to load image"
      Color sampleColor = robot.getPixelColor(635, 390);
      if(sampleColor.getRed() == 13 && sampleColor.getGreen() == 137 && sampleColor.getBlue() == 218) {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        sleep(sleepTimeShort);
        
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        sleep(sleepTimeShort);
        
        firstImageAbnormal.moveToClick(robot);
        sleep(sleepTimeShort);
      } else {
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
        sleep(sleepTimeShort);
        
        firstImageNormal.moveToClick(robot);
        sleep(sleepTimeShort);
      }
      for(int j = 0; j < i; j++) {
        robot.keyPress(KeyEvent.VK_RIGHT);
        sleep(sleepTimeShorter);
      }
      
      robot.keyPress(KeyEvent.VK_TAB);
      robot.keyRelease(KeyEvent.VK_TAB);
      
      robot.keyPress(KeyEvent.VK_RIGHT);
      robot.keyRelease(KeyEvent.VK_RIGHT);
      sleep(sleepTimeShort);
      
      for(int j = 0; j < 4; j++) {
        robot.keyPress(KeyEvent.VK_LEFT);
        robot.keyRelease(KeyEvent.VK_LEFT);
        sleep(sleepTimeShorter);
      }
      
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_SHIFT);
      for(int j = 0; j < 10; j++) {
        robot.keyPress(KeyEvent.VK_LEFT);
        sleep(sleepTimeShorter);
      }
      
      robot.keyPress(KeyEvent.VK_C);
      robot.keyRelease(KeyEvent.VK_C);
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
      sleep(sleepTimeShort);

      robot.keyPress(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_V);
      sleep(sleepTimeShort);

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
    
    System.out.println("Robot Operator complete");
    
  }
  
  /**
   * Method simply pauses the program momentarily
   * 
   * @param ms - how long you would like to delay/wait/sleep for
   */
  public static void sleep(long ms) {
    try {Thread.sleep(ms);} catch (Exception ignored) {}
  }
}
