package narrationManager.controller;

import java.util.TreeSet;

import narrationManager.gui.MainMenu;

import narrationManager.gui.ViewCharacterWindow;

import narrationManaer.model.PlaceModel;

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
  
  public TreeSet<PlaceModel> getAllPlaces()
  {//TODO: retourne tous les PlaceModel existant, en appelant la DB==> TRIES (par ordre alphabétique de nom?)!!!
    return new TreeSet<PlaceModel>(); //TODO: pour éviter les bugs
  }
}
