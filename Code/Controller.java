package narrationmanager.controller;

import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import narrationmanager.gui.MainMenu;

import narrationmanager.gui.CharacterEditionWindow;
import narrationmanager.gui.EventEditionWindow;
import narrationmanager.gui.PlaceEditionWindow;
import narrationmanager.gui.MapChoiceWindow;

import narrationmanager.gui.util.EditionWindow;

import narrationmanager.model.PlaceModel;
import narrationmanager.model.EventModel;
import narrationmanager.model.CharacterModel;

import narrationmanager.db.DatabaseCoordinator;

public class Controller
{
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
    CharacterEditionWindow characterEditor=new CharacterEditionWindow(this);
    
    if(characterEditor.getExitOption()==EditionWindow.OK_EXIT_OPTION)
      saveNewCharacter(characterEditor.getTarget());
  }
  
  public void createPlace()
  {
    PlaceEditionWindow placeEditor = new PlaceEditionWindow(this);
    
    if (placeEditor.getExitOption() == EditionWindow.OK_EXIT_OPTION)
      saveNewPlace(placeEditor.getTarget());
    
  }
  
  public void editCharacter()
  {
    CharacterModel[] choices=getAllCharactersInArray();
    
    if(choices.length>0)
    {
      CharacterModel toEdit=(CharacterModel) JOptionPane.showInputDialog(null,"Please select which character to edit.","Edit character...",JOptionPane.QUESTION_MESSAGE,null,choices,choices[0]);
      
      if (toEdit != null)
      {
        CharacterEditionWindow characterEditor=new CharacterEditionWindow(this,toEdit);
      
        if(characterEditor.getExitOption()==EditionWindow.OK_EXIT_OPTION)
          saveCharacterModifications(characterEditor.getTarget());
      }
    }
    else 
      JOptionPane.showMessageDialog(null,"Error: no character can be edited, as there is currently no character available in database","Error",JOptionPane.ERROR_MESSAGE);
  }
  
  public void editEvent()
  {
    
    EventModel[] choices=getAllEventsInArray();
    
    if(choices.length>0)
    {
      EventModel toEdit=(EventModel) JOptionPane.showInputDialog(null,"Please select which event to edit.","Edit event...",JOptionPane.QUESTION_MESSAGE,null,choices,choices[0]);
      
      if(toEdit!=null)
      {
        EventEditionWindow eventEditor=new EventEditionWindow(this,toEdit);
    
        if(eventEditor.getExitOption()==EditionWindow.OK_EXIT_OPTION)
          saveEventModifications(eventEditor.getTarget());
      }
    }
    else 
      JOptionPane.showMessageDialog(null,"Error: no event can be edited, as there is currently no event available in database","Error",JOptionPane.ERROR_MESSAGE);
  }
  
  public void editPlace()
  {
    PlaceModel[] choices = getAllPlacesInArray();
    if(choices.length>0)
    {
      PlaceModel toEdit = (PlaceModel) JOptionPane.showInputDialog(null, "Please select which place to edit.", "Edit place...", JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
      
      if (toEdit != null)
      {
        PlaceEditionWindow placeEditor = new PlaceEditionWindow(this, toEdit);
        
        if (placeEditor.getExitOption() == EditionWindow.OK_EXIT_OPTION)
          dbCoordinator.savePlace(placeEditor.getTarget(), false);
      }
    }
    
  }
  
  public int mapChooser(PlaceModel parent)
  {
    if (parent.getMap() != null)
    {
      MapChoiceWindow map = new MapChoiceWindow(this, parent.getMap());
      return map.getExitOption();
    }
    return -1;
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
  
  private PlaceModel[] getAllPlacesInArray()
  {
    TreeSet<PlaceModel> allPlaces = getAllPlaces();
    PlaceModel[] rslt = new PlaceModel[allPlaces.size()];
    allPlaces.toArray(rslt);
    return rslt;
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

  }
  
  public EventModel[] getAllEventsInArray()
  {
    TreeSet<EventModel> allEvents=getAllEvents();
    EventModel[] rslt=new EventModel[allEvents.size()];
    allEvents.toArray(rslt);
    return rslt;
  }
  
  public TreeSet<CharacterModel> getAllCharacters()
  {
    TreeSet<CharacterModel> rslt = new TreeSet<CharacterModel>((CharacterModel a, CharacterModel b)->a.getName().compareTo(b.getName()));
    
    ArrayList<String> characterNames = dbCoordinator.getAllCharacterID();
    for (String character: characterNames)
    {
      rslt.add(dbCoordinator.getCharacter(character));
    }
    return rslt;
  }
  
  public CharacterModel[] getAllCharactersInArray()
  {
    TreeSet<CharacterModel> allCharacters=getAllCharacters();
    CharacterModel[] rslt=new CharacterModel[allCharacters.size()];
    allCharacters.toArray(rslt);
    
    return rslt;
  }
  
  public void saveNewCharacter(CharacterModel newCharacter)
  {
      dbCoordinator.saveNewCharacter(newCharacter, true);
      System.out.println("Saving new character: "+newCharacter.getName());
  }
  
  public void saveCharacterModifications(CharacterModel toSave)
  {
      dbCoordinator.saveNewCharacter(toSave, false);
      System.out.println("Saving modified character: "+toSave.getName());
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
  
  public PlaceModel loadPlace(String ID)
  {
    return dbCoordinator.makePlace(ID);  	  
  }
  
  public Collection<EventModel> getEventModelsFromID(Collection<String> IDList)
  {
    TreeSet<EventModel> events = new TreeSet<EventModel>((EventModel a,EventModel b)->a.getName().compareTo(b.getName()));
    for (String event : IDList)
    {
      events.add(dbCoordinator.getEvent(event));
    }
    return events;
  }
}
