package narrationmanager.controller;

import java.util.TreeSet;

import narrationmanager.gui.MainMenu;

import narrationmanager.gui.CharacterEditionWindow;
import narrationmanager.gui.EventEditionWindow;

import narrationmanager.gui.util.EditionWindow;

import narrationmanager.model.PlaceModel;
import narrationmanager.model.EventModel;

public class Controller
{
  //TODO: coordination modèle-interface
  
  public void start()
  {
    new MainMenu(this);
  }
  
  public void createEvent()
  {
    EventEditionWindow eventEditor=new EventEditionWindow(this);
    
    if(eventEditor.getExitOption()==EditionWindow.OK_EXIT_OPTION)
      saveNewEvent(eventEditor.getTarget());	    
  }
  
  public void createCharacter()
  {
    new CharacterEditionWindow(this);
  }
  
  public void editCharacter()
  {
    //Select character and edit it
  }
  
  public TreeSet<PlaceModel> getAllPlaces()
  {
    /*TODO: 
    retourne tous les PlaceModel existant, en appelant la DB
    ==> ces éléments sont triés (par ordre alphabétique de nom?)!!! 
    (avec un Comparator dans le constructeur du TreeSet et une Lambda 
    pour le remplacer ça va tout seul)
    */
    return new TreeSet<PlaceModel>(); //TODO: pour éviter les bugs
  }
  
  public void saveNewEvent(EventModel newEvent)
  {
    //TODO
    System.out.println("Saving new event: "+newEvent.getName());
  }
}
