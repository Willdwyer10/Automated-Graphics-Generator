import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.*;

public class RobotOperator {
  public RobotOperator(int numBands) throws AWTException {
    final int sleepTime = 200;
    Robot robot = new Robot();
    
    ClickCoordinate createNewMessageButton = new ClickCoordinate(120, 175);
    ClickCoordinate insertButton = new ClickCoordinate(120, 30);
    ClickCoordinate importButton = new ClickCoordinate(200, 150);
    ClickCoordinate browseButton = new ClickCoordinate(900, 100);
    ClickCoordinate firstImage = new ClickCoordinate(650, 250);
    
    createNewMessageButton.moveToClick(robot);
    //Sequence of events to have it click all the buttons and what not
    for(int i = 0; i < numBands; i++) {
      insertButton.moveToClick(robot);
      sleep(sleepTime*10);
      importButton.moveToClick(robot);
      sleep(sleepTime*10);
      browseButton.moveToClick(robot);
      sleep(sleepTime*10);
      firstImage.moveToClick(robot);
      sleep(sleepTime*10);
      
      for(int j = 0; j < i; j++) {
        robot.keyPress(KeyEvent.VK_RIGHT);
        sleep(sleepTime);
      }
      
      robot.keyPress(KeyEvent.VK_TAB);
      robot.keyRelease(KeyEvent.VK_TAB);
      
      robot.keyPress(KeyEvent.VK_RIGHT);
      robot.keyRelease(KeyEvent.VK_RIGHT);
      sleep(sleepTime);
      
      for(int j = 0; j < 4; j++) {
        robot.keyPress(KeyEvent.VK_LEFT);
        robot.keyRelease(KeyEvent.VK_LEFT);
      }
      
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_SHIFT);
      for(int j = 0; j < 10; j++) {
        robot.keyPress(KeyEvent.VK_LEFT);
        sleep(sleepTime);
      }
      
      robot.keyPress(KeyEvent.VK_C);
      robot.keyRelease(KeyEvent.VK_C);
      sleep(sleepTime);
      
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyRelease(KeyEvent.VK_SHIFT);
      
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
      sleep(sleepTime);

      robot.keyPress(KeyEvent.VK_TAB);
      robot.keyRelease(KeyEvent.VK_ENTER);
      sleep(sleepTime);

      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
      sleep(sleepTime);

      robot.keyPress(KeyEvent.VK_CONTROL);
      
      robot.keyPress(KeyEvent.VK_S);
      robot.keyRelease(KeyEvent.VK_S);
      sleep(sleepTime);

      robot.keyPress(KeyEvent.VK_V);
      robot.keyRelease(KeyEvent.VK_V);
      sleep(sleepTime);

      for(int j = 0; j < 4; j++) {
        robot.keyPress(KeyEvent.VK_TAB);
        sleep(sleepTime);

      }
      
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
      sleep(sleepTime);

      robot.keyPress(KeyEvent.VK_N);
      robot.keyPress(KeyEvent.VK_N);
      sleep(sleepTime);

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
