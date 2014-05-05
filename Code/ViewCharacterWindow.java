package narrationManager.gui;

import javax.swing.JDialog;

import narrationManager.controller.Controller;

import narrationManager.model.CharacterModel;

public class ViewCharacterWindow extends JDialog
{
  private Controller controller;
	
  //Menu principal, qui s'ouvre avec le programme
  public ViewCharaterWindow(Controller controller)
  {
    super(null,"Character editor",true);
  
    //TODO: create UI
    
    this.controller=controller;	  
    
    setVissible(true);
  }
  
  public ViewCharacterWindow(Controller controller,Character toEdit)
  {
    this(controller);
    //TODO: mettre toEdit comme contenu
  }
  
  public CharacterModel getResult()
  {
    //TODO
  }
  
  public boolean hasResult() //Retourne true si on a validé le résultat
  {
    //TODO	  
  }
}
