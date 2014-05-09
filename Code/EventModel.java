package narrationmanager.model;

import java.util.LinkedList;

import narrationmanager.model.NarrationDate;
import narrationmanager.model.CharacterModel;

import narrationmanager.model.util.DBModel;

public class EventModel extends DBModel
{
  //Mandatory fields (+name, inherited from DBModel) 
  private PlaceModel eventPlace;
  private NarrationDate start;
  private NarrationDate end;
  
  //Optional fields
  private LinkedList<CharacterModel> linkedCharacters;
  private String eventDescription;

  /**
  Returns a new EventModel. Only mandatory fields are specified into its parameters,
  setters should be used for the other ones.
  **/
  public EventModel(String eventName,PlaceModel eventPlace,NarrationDate start,NarrationDate end,boolean alreadyInDB)
  {
    super(eventName,alreadyInDB);
    setEventPlace(eventPlace);
    setStartDate(start);
    setEndDate(end);
  }
  
  public void setLinkedCharacters(LinkedList<CharacterModel> linkedCharacters)
  {
    this.linkedCharacters=linkedCharacters;
  }
  
  public LinkedList<CharacterModel> getLinkedCharacters()
  {
    return linkedCharacters;	  
  }
  
  public void setEventDescription(String eventDescription)
  {
    this.eventDescription=eventDescription;
  }
  
  public String getEventDescription()
  {
    return eventDescription;	  
  }
  
  public void setEventPlace(PlaceModel eventPlace)
  {
    this.eventPlace=eventPlace;
  }
  
  public PlaceModel getEventPlace()
  {
    return eventPlace;	  
  }
  
  public void setStartDate(NarrationDate start)
  {
    this.start=start;
  }
  
  public NarrationDate getStartDate()
  {
    return start;
  }
  
  public void setEndDate(NarrationDate end)
  {
    this.end=end;	  
  }
  
  public NarrationDate getEndDate()
  {
    return end;	  
  }
  
  public static EventModel defaultInstance()
  {
    //TODO: retourner une instance par défaut un peu plus correcte!!
    return new EventModel("New event",null,new NarrationDate("1992-12-22"),new NarrationDate("1992-12-22"),false);
  }
}
