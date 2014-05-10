package narrationmanager.model;

public class RelationData
{
  private String relationName;
  private String targetCharacterName;

  public RelationData(String relationName,String targetCharacterName)
  {
    this.relationName=relationName;
    this.targetCharacterName=targetCharacterName;
  }
  
  public String getRelationName()
  {
    return relationName;	  
  }
  
  public void setRelationName(String relationName)
  {
    this.relationName=relationName;
  }
  
  public String getTargetCharacterName()
  {
    return targetCharacterName;	  
  }
  
  public void setTargetCharacterName(String targetCharacterName)
  {
    this.targetCharacterName=targetCharacterName;	  
  }
}
