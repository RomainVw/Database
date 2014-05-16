package narrationmanager;

import javax.swing.UIManager;
import javax.swing.UIManager.*;

import narrationmanager.controller.Controller; 


/**
NarrationManager's main class.

@author Baugnies Benjamin
@author Colson Olivier
@author Vanwelde Romain
**/
public class NarrationManager
{
  /**
  Sets the Nimbus Look & Feel to be used.
  **/
  private static void applyNimbus()
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
