package narrationManager.controller;

import narrationManager.gui.MainMenu;

import narrationManager.gui.ViewCharacterWindow;


public class Controller
{
  //TODO: coordination modèle-interface
  
  public void start()
  {
    new MainMenu(this);
  }
  
  public void createCharacter()
  {
      new ViewCharacterWindow(this);
  }
  
  public void editCharacter()
  {
    //Select character and edit it
  }
}
