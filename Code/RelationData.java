package narrationmanager.model;


import narrationmanager.model.NarrationDate;

public class RelationData
{
  private boolean isTarget;
  private String relationName;
  private String targetCharacterName;
  private NarrationDate start = null;
  private NarrationDate end = null;
    
  public RelationData(String relationName,String targetCharacterName, boolean isTarget)
  {
    this.isTarget = isTarget;
    this.relationName=relationName;
    this.targetCharacterName=targetCharacterName;
  }
    
  public boolean getIsTarget() //TODO: question de Neo: Ca sert à quoi isTarget? C'est éditable?
  {
    return isTarget;
  }
    
  public void setIsTarget(boolean isTarget)
  {
    this.isTarget=isTarget;
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
    
  public  NarrationDate getStart()
  {
    return start;
  }
      
  public void setStart(NarrationDate start)
  {
    this.start=start;
  }
    
  public  NarrationDate getEnd()
  {
    return end;
  }  
    
  public void setEnd(NarrationDate end)
  {
    this.end=end;
  }  
  
  public String toString()
  {
    String additionalString=(isTarget)? "":" [INVERSE]";
    return getRelationName()+additionalString;	  
  }
}
