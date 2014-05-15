package narrationmanager.model;

import narrationmanager.model.MapModel;
import narrationmanager.model.util.DBModel;

import java.util.ArrayList;


public class PlaceModel extends DBModel implements Comparable<PlaceModel>
{
  // mandatory fields:
  // id inherited from DBModel
  private String name = null;
  
  // optional fields:
  private MapModel map = null;
  private ArrayList<String> eventsID = null;
  private ArrayList<String> charactersID = null;
  
  private String parentID = null;
  private int location;
  

  public PlaceModel(String id, String name)
  {
    super(id);
    this.name = name;
  }
   
  public int compareTo(PlaceModel other)
  {
    return this.getName().compareTo(other.getName());
  }
  
  public String getParentID()
  {
    return parentID;
  }
  
  public int getLocation()
  {
    return location;
  }
  
  public String getName()
  {
    return name;
  }
  
  public MapModel getMap()
  {
    return map;
  }
  
  public ArrayList<String> getEvents()
  {
    return eventsID;
  }
    
  public ArrayList<String> getCharacters()
  {
    return charactersID;
  }
  
  public void setParentID(String id)
  {
    parentID = id;
  }
  
  public void setLocation(int i)
  {
    location = i;
  }

  public void setName(String name)
  {
    this.name = name;
  }
  
  public void setMap(MapModel map)
  {
    this.map = map;
  }
  
  public void setEvents(ArrayList<String> eventsID)
  {
    this.eventsID = eventsID;
  }
    
  public void setCharacters(ArrayList<String> events)
  {
    this.charactersID = charactersID;
  }
  
  public String toString()
  {
    return this.getName();
  }
  
  public static PlaceModel defaultInstance()
  {
    return new PlaceModel(null, "Place Name");
  }
}
