package narrationmanager.model;

import narrationmanager.model.MapModel;
import narrationmanager.model.util.DBModel;

import narrationmanager.model.util.ModelInfo;

import java.util.ArrayList;


public class PlaceModel extends DBModel implements Comparable<PlaceModel>
{
  // mandatory fields:
  // id inherited from DBModel
  private String name = null;
  
  // optional fields:
  private MapModel map = null;
  private ArrayList<ModelInfo> events = null;
  private ArrayList<ModelInfo> characters = null;
  
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
  
  public String getName()
  {
    return name;
  }
  
  public MapModel getMap()
  {
    return map;
  }
  
  public ArrayList<ModelInfo> getEvents()
  {
    return events;
  }
    
  public ArrayList<ModelInfo> getCharacters()
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
  
  public void setEvents(ArrayList<ModelInfo> events)
  {
    this.events = events;
  }
    
  public void setCharacters(ArrayList<ModelInfo> events)
  {
    this.characters = characters;
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
