package narrationmanager.model;

import java.util.LinkedList;

import narrationmanager.model.NarrationDate;

import narrationmanager.model.util.DBModel;


/**
A DBModel defining the data obtained from a event saved in database.

@author Baugnies Benjamin
@author Colson Olivier
@author Vanwelde Romain
**/
public class EventModel extends DBModel
{
  //Mandatory fields (+id, inherited from DBModel) 
  private PlaceModel eventPlace;
  private NarrationDate start;
  private NarrationDate end;
  private String eventName;
  
  //Optional fields
  private LinkedList<String> linkedCharactersID;
  private String eventDescription;

  /**
  Returns a new EventModel. Only mandatory fields are specified into its parameters,
  setters should be used for the other ones.
  **/
  public EventModel(String eventId, String eventName,PlaceModel eventPlace,NarrationDate start,NarrationDate end)
  {
    super(eventId);
    this.eventName = eventName;
    setEventPlace(eventPlace);
    setStartDate(start);
    setEndDate(end);
  }
  
  public void setName(String name)
  {
    eventName=name;
  }
  
  public String getName()
  {
    return eventName;
  }
  
  public void setLinkedCharactersID(LinkedList<String> linkedCharactersID)
  {
    this.linkedCharactersID=linkedCharactersID;
  }
  
  public LinkedList<String> getLinkedCharactersID()
  {
    return linkedCharactersID;	  
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
    return new EventModel(null,"New event",null,new NarrationDate("1992-12-22"),new NarrationDate("1992-12-22"));
  }
  
  public String toString()
  {
    return this.getName();
  }
}
