package narrationManager;

import javax.swing.UIManager;
import javax.swing.UIManager.*;

import narrationManager.controller.Controller; 

public class NarrationManager
{
  private static void applyNimbus()//Loads the Nimbus Look & Feel
  {
    try 
    {
      for(LookAndFeelInfo info: UIManager.getInstalledLookAndFeels())
      {
        if(info.getName().equals("Nimbus"))
        {
          UIManager.setLookAndFeel(info.getClassName());
          break;
        }
      }
    }
    catch(Exception e)
    {
      System.err.println("ERROR LOADING NIMBUS");
    }
  }
	
  public static void main(String[] args)
  {
    applyNimbus();
    
    Controller controller=new Controller();
    
    controller.start();
  }
}
