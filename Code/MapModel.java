package narrationmanager.model;

import java.util.Map;
import java.util.HashMap;

public class MapModel
{
  private String mapID = null;
  private int numWidth;
  private int numLength;
  private float width;
  private float length;
  private Map<String,Integer> subplaceID = null;
  
  public MapModel(String mapID, int numWidth, int numLength, float width, float length)
  {
    this.mapID = mapID;
    this.numWidth = numWidth;
    this.numLength = numLength;
    this.width = width;
    this.length = length;
  }
  
  public String getMapID()
  {
    return mapID;
  }
  
  public int getNumWidth()
  {
    return numWidth;
  }
  
  public int getNumLength()
  {
    return numLength;
  }
    
  public float getWidth()
  {
    return width;
  }

  public float getLength()
  {
    return length;
  }
    
  public Map<String,Integer> getSubplaceID()
  {
    return subplaceID;
  }
    
  public void setMapID(String mapID)
  {
    this.mapID = mapID;
  }

  public void setNumWidth(int numWidth)
  {
    this.numWidth = numWidth;
  }
    
  public void setNumLength(int numLength)
  {
    this.numLength = numLength;
  }
    
  public void setWidth(float width)
  {
    this.width = width;
  }

  public void setLength(float length)
  {
    this.length = length;
  }
    
  public void setSubplaceID(Map<String,Integer> subplaceID)
  {
    this.subplaceID = subplaceID;
  }
  
  public void addSubplace(String place, int id)
  {
    if (subplaceID == null)
      subplaceID = new HashMap<String, Integer>();
    subplaceID.put(place, id);
  }
 
  public void removeSubplace(String id)
  {
   subplaceID.remove(id); 
  }
  
  public static MapModel defaultInstance()
  {
    return new MapModel(null, 0, 0, 0,0);
  }
  
}
