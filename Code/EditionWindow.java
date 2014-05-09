package narrationmanager.gui.util;

import javax.swing.JDialog;

import narrationmanager.controller.Controller;

public abstract class EditionWindow<T> extends JDialog
{
  public static final int OK_EXIT_OPTION=0;
  public static final int CANCEL_EXIT_OPTION=1;
  public static final int UNDEFINED_EXIT_OPTION=2;
  
  private int exitOption=UNDEFINED_EXIT_OPTION;
	
  protected Controller controller;
  protected T editionTarget;
  
  public EditionWindow(Controller controller)
  {
    super((JDialog)null,"",true);
    
    this.controller=controller;
    setTitle(getWindowTitle());
    editionTarget=getDefaultTargetInstance();
    buildUI();  	  
  }
  
  public EditionWindow(Controller controller,T toEdit)
  {
    super((JDialog)null,"",true);
    
    this.controller=controller;
    setTitle(getWindowTitle());
    editionTarget=toEdit;
    buildUI();
  }
  
  protected void setExitOption(int exitOption)
  {
    this.exitOption=exitOption;
  }
  
  public int getExitOption()
  {
    return exitOption;	  
  }
  
  public T getTarget()
  {
    return editionTarget;	  
  }
  
  protected abstract T getDefaultTargetInstance();
  protected abstract void buildUI();
  protected abstract String getWindowTitle();
}
