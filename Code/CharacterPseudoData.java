package narrationmanager.model;


public class CharacterPseudoData implements Comparable<CharacterPseudoData>
{
  private String targetCharacterName;
  private String pseudo;
  private NarrationDate start; //not mandatory, null if not defined
  private NarrationDate end; //not mandatory, null if not defined
  
  public CharacterPseudoData(String targetCharacterName,String pseudo)
  {
    this.targetCharacterName=targetCharacterName;
    this.pseudo=pseudo;
  }

  public String getPseudo()
  {
    return pseudo;	  
  }
  
  public void setPseudo(String pseudo)
  {
    this.pseudo=pseudo;	  
  }
  
  public String getTargetCharacterName()
  {
    return targetCharacterName;	  
  }
  
  public void setTargetCharacterName(String targetCharacterName)
  {
    this.targetCharacterName=targetCharacterName;	  
  }
  
  public NarrationDate getStart()
  {
    return start;
  }
  
  public void setStart(NarrationDate start)
  {
    this.start=start;	  
  }
  
  public NarrationDate getEnd()
  {
    return end;
  }
  
  public void setEnd(NarrationDate end)
  {
    this.end=end;	  
  }
  
  public int compareTo(CharacterPseudoData other)
  {
    int rslt=targetCharacterName.compareTo(other.getTargetCharacterName());
    
    if(rslt==0) rslt=start.compareTo(other.getStart());
    if(rslt==0) rslt=end.compareTo(other.getEnd());
    if(rslt==0) rslt=pseudo.compareTo(other.getPseudo());
    
    return rslt;
  }
}
