package narrationmanager.gui;

import javax.swing.JDialog;

import narrationmanager.controller.Controller;

import narrationmanager.model.CharacterModel;

import java.awt.Dialog;

public class CharacterEditionWindow extends JDialog
{
  private Controller controller;
	
  //Menu principal, qui s'ouvre avec le programme
  public CharacterEditionWindow(Controller controller)
  {
    super((Dialog) null,"Character editor",true);
    
    //TODO: create UI
    
    this.controller=controller;	  
    
    setVisible(true);
  }
  
  public CharacterEditionWindow(Controller controller,Character toEdit)
  {
    this(controller);
    //TODO: mettre toEdit comme contenu
  }
  
  public CharacterModel getResult()
  {
      return null;
      //TODO
  }
  
  public boolean hasResult() //Retourne true si on a validé le résultat
  {
      return true;
    //TODO	  
  }
}
