package narrationmanager.model;

import narrationmanager.model.MapModel;
import narrationmanager.model.util.DBModel;

import java.util.ArrayList;

public class PlaceModel extends DBModel implements Comparable<PlaceModel>
{
  private String name = null;
  private MapModel map = null;
  private ArrayList<String> events = null;
  private ArrayList<String> characters = null;
  

  
  public PlaceModel(String name, boolean alreadyInDB)
  {
    super(name, alreadyInDB);
  }
   
  public int compareTo(PlaceModel other)
  {
    return this.getName().compareTo(other.getName());
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
    return events;
  }
    
  public ArrayList<String> getCharacters()
  {
    return characters;
  }

  public void setName(String name)
  {
    this.name = name;
  }
  
  public void setMap(MapModel map)
  {
    this.map = map;
  }
  
  public void setEvents(ArrayList<String> events)
  {
    this.events = events;
  }
    
  public void setCharacters(ArrayList<String> events)
  {
    this.characters = characters;
  }
}
