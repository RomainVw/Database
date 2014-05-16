package narrationmanager.model.util;


/**
An abstract class representing an object loaded from database, containing
thus a unique String id to identify it


@author Baugnies Benjamin
@author Colson Olivier
@author Vanwelde Romain 
**/
public abstract class DBModel
{
  protected String id;

  public DBModel(String id)
  {
    this.id=id;	  
  }
  
  public String getID()
  {
    return id;
  }
  
  public void setID(String id)
  {
    this.id=id;
  }
  
  public boolean equals(Object o)
  {
    return o instanceof DBModel && o.getClass().equals(getClass()) && ((DBModel)o).getID().equals(id);	  
  }
  
  public String toString()
  {
    return getID();
  }
}
