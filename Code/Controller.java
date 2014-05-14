package narrationmanager.controller;

import java.util.TreeSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import narrationmanager.gui.MainMenu;

import narrationmanager.gui.CharacterEditionWindow;
import narrationmanager.gui.EventEditionWindow;
import narrationmanager.gui.PlaceEditionWindow;

import narrationmanager.gui.util.EditionWindow;

import narrationmanager.model.PlaceModel;
import narrationmanager.model.EventModel;

import narrationmanager.db.DatabaseCoordinator;

public class Controller
{
  //TODO: coordination modèle-interface
  private DatabaseCoordinator dbCoordinator = new DatabaseCoordinator();
  
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
  
  public void createPlace()
  {
    PlaceEditionWindow placeEditor = new PlaceEditionWindow(this);
    
    if (placeEditor.getExitOption() == EditionWindow.OK_EXIT_OPTION)
      saveNewPlace(placeEditor.getTarget());
    
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
    TreeSet<PlaceModel> set = new TreeSet<PlaceModel>();
    ArrayList<String> placenames = dbCoordinator.getPlaceIDs();
    
    for (String place : placenames)
    {
      set.add(dbCoordinator.makePlace(place));
    }
      
    return set; 
  }
  
  public TreeSet<EventModel> getAllEvents()
  {
    /*
    Retourne un TreeSet trié contenant tous les EventModel connus par ordre alphabétique
    */
    TreeSet<EventModel> rslt=new TreeSet<>((EventModel a,EventModel b)->a.getName().compareTo(b.getName()));
    
    ArrayList<EventModel> allEventsFromDB = dbCoordinator.getAllEvents();
    for(EventModel singleEvent:allEventsFromDB)
    {
        rslt.add(singleEvent);
    }
      
    return rslt;
    
    //TODO ATTENTION POUR TOUT CE QUI SUIT: Code de test, il n'y a rien de bon là-dedans! :p
    /*TreeSet<EventModel> rslt=new TreeSet<>((EventModel a,EventModel b)->a.getName().compareTo(b.getName()));
    EventModel model1=EventModel.defaultInstance();
    EventModel model2=EventModel.defaultInstance();
    
    model1.setName("event 1");
    model2.setName("event 2");
    
    model1.updateDB();
    model2.updateDB();
    
    rslt.add(model1);
    rslt.add(model2);
    
    return rslt;*/
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
    dbCoordinator.saveNewEvent(newEvent);
    System.out.println("Saving new event: "+newEvent.getName());
  }
  
  public void saveEventModifications(EventModel modifiedEvent)
  {
    dbCoordinator.saveEvent(modifiedEvent);
    System.out.println("Saving modified event: "+modifiedEvent.getName());
  }
  
  public void saveNewPlace(PlaceModel newPlace)
  {
    dbCoordinator.savePlace(newPlace, true);
  }
}
