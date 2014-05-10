package narrationmanager.controller;

import java.util.TreeSet;

import javax.swing.JOptionPane;

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
  {//TODO: Select character and edit it
  }
  
  public void editEvent()
  {
    
    EventModel[] choices=getAllEventsInArray();
    EventModel toEdit=(EventModel) JOptionPane.showInputDialog(null,"Please select which event to edit.","Edit event...",JOptionPane.QUESTION_MESSAGE,null,choices,choices[0]);
    
    if(toEdit!=null)
    {
      EventEditionWindow eventEditor=new EventEditionWindow(this,toEdit);
    
      if(eventEditor.getExitOption()==EditionWindow.OK_EXIT_OPTION)
        saveEventModifications(eventEditor.getTarget());
    }
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
  
  public TreeSet<EventModel> getAllEvents()
  {
    /*
    Retourne un TreeSet trié contenant tous les EventModel connus par ordre alphabétique
    */
    
    
    //TODO ATTENTION POUR TOUT CE QUI SUIT: Code de test, il n'y a rien de bon là-dedans! :p
    TreeSet<EventModel> rslt=new TreeSet<>((EventModel a,EventModel b)->a.getName().compareTo(b.getName()));
    EventModel model1=EventModel.defaultInstance();
    EventModel model2=EventModel.defaultInstance();
    
    model1.setName("event 1");
    model2.setName("event 2");
    
    model1.updateDB();
    model2.updateDB();
    
    rslt.add(model1);
    rslt.add(model2);
    
    return rslt;
  }
  
  private EventModel[] getAllEventsInArray()
  {
    TreeSet<EventModel> allEvents=getAllEvents();
    EventModel[] rslt=new EventModel[allEvents.size()];
    allEvents.toArray(rslt);
    return rslt;
  }
  
  public void saveNewEvent(EventModel newEvent)
  {
    //TODO: méthode appelée à la création d'un nouvel Event pour le sauvegarder
    System.out.println("Saving new event: "+newEvent.getName());
  }
  
  public void saveEventModifications(EventModel modifiedEvent)
  {
    //TODO: méthode appelée après modification d'un event pour le sauvegarder en BDD
    System.out.println("Saving modified event: "+modifiedEvent.getName()+"  "+modifiedEvent.updateDB());
  }
}
