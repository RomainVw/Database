package narrationmanager.model.util;

public abstract class DBModel
{
  protected String id;

  /*
  e, name and lastDBName will always be equal, which is paticularly 
  useful for creating new objects and then save them
  */
  public DBModel(String id)
  {
    this.id=id;	  
  }
  
 /* public String updateDB()
  {
    /*
    lastDBName will be null only if the object has not yet been saved in DB.
    In that case, we me set lastDBName to name and return name in order to
    properly make the first save of this DBModel.
    
    String res = (lastDBName==null)? name:lastDBName;
    lastDBName = name;
    return res; 
  }*/
  
  public String getID()
  {
    return id;
  }
  
  public void setID(String id)
  {
    this.id=id;
  }
  
  public boolean equals(Object o)
  {//TODO: tester que ça marche bien
    return o instanceof DBModel && o.getClass().equals(getClass()) && ((DBModel)o).getID().equals(id);	  
  }
  
  public String toString()
  {
    return getID();
  }
}
