package narrationmanager.model;


import narrationmanager.model.NarrationDate;

/**
A class defining the data obtained from a relation saved in database.

@author Baugnies Benjamin
@author Colson Olivier
@author Vanwelde Romain
**/
public class RelationData
{
  public static final String INVERSE_NAME_TAG=" [INVERSE]"; 
	
  private boolean isTarget;
  private String relationName;
  private String targetCharacterID;
  private String targetCharacterName;
  private NarrationDate start = null;
  private NarrationDate end = null;
    
  public RelationData(String id, String relationName,String targetCharacterID, boolean isTarget)
  {
    this.isTarget = isTarget;
    this.relationName=relationName;
    this.targetCharacterID=targetCharacterID;
  }
    
  public boolean getIsTarget()
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
  
  public String getTargetCharacterID()
  {
    return targetCharacterID;	  
  }
  
  public void setTargetCharacterID(String targetCharacterID)
  {
    this.targetCharacterID=targetCharacterID;	  
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
    String additionalString=(isTarget)? INVERSE_NAME_TAG:"";
    return getRelationName()+additionalString;	  
  }
}
