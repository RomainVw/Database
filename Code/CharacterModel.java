package narrationManager.model;

public class CharacterModel
{
  private String last_dbName = null;
  private String name = null;
  private String b_place = null;
  
  public CharacterModel(String name, String b_place)
  {
    last_dbName = name;
    this.name = name;
    this.b_place = b_place;
  }
  
  public String getName()
  {
    return name;
  }
  
  public String getB_place()
  {
    return b_place;
  }
  
  public void setName(String name)
  {
    this.name = name;
  }
  
  public void setB_place(String b_place)
  {
    this.b_place = b_place;
  }
  
  public String update_db()
  {
    String res = last_dbName;
    last_dbName = name;
    return res;
  }
}
