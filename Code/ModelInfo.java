package narrationmanager.model.util;

public class ModelInfo
{
  private String name = null;
  private String id = null;
  
  public ModelInfo(String id, String name)
  {
    this.name = name;
    this.id = id;
  }
  
  public String getName()
  { return name; }
  public String getId()
  { return id; }
  public void setName(String n)
  { name = n; }
  public void setId(String i)
  { id = i; }
}
