package narrationManager.model;

import java.util.LinkedList;

import java.time.LocalDate;

import narrationManager.model.CharacterModel;

public class EventModel
{
  //Mandatory fields
  private String eventName;
  private PlaceModel eventPlace;
  private LocalDate start;
  private LocalDate end;
  
  //Optional fields
  private LinkedList<CharacterModel> linkedCharacters;
  private String eventDescription;

  /**
  Returns a new EventModel. Only mandatory fields are specified into its parameters,
  setters should be used for the other ones.
  **/
  public EventModel(String eventName,PlaceModel eventPlace,LocalDate start,LocalDate end)
  {
    setEventName(eventName);
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
  
  public void setEventName(String eventName)
  {
    this.eventName=eventName;	  
  }
  
  public String getEventName()
  {
    return eventName;	  
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
  
  public void setStartDate(LocalDate start)
  {
    this.start=start;
  }
  
  public LocalDate getStartDate()
  {
    return start;
  }
  
  public void setEndDate(LocalDate end)
  {
    this.end=end;	  
  }
  
  public LocalDate getEndDate()
  {
    return end;	  
  }
  
  public static EventModel defaultInstance() //TODO: passer ça dans une interface dont tous les modèles héritent
  {
    //TODO: retourner une instance par défaut un peu plus correcte!!
    return new EventModel("New event",null,LocalDate.parse("1992-12-22"),LocalDate.parse("1992-12-22"));
  }
}
