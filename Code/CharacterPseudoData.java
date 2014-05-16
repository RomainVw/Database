package narrationmanager.model;


/**
A class encapsulating all the data representiong a pseudo given to a character
by another one.

@author Baugnies Benjamin
@author Colson Olivier
@author Vanwelde Romain
**/
public class CharacterPseudoData implements Comparable<CharacterPseudoData>
{
  private String sourceCharacterName;
  private String targetCharacterName;
  private String pseudo;
    
  
  public CharacterPseudoData(String sourceCharacterName, String targetCharacterName,String pseudo)
  {
    this.sourceCharacterName = sourceCharacterName;
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
  
  public String getTargetCharacterID()
  {
    return targetCharacterName;	  
  }
  
  public void setTargetCharacterID(String targetCharacterName)
  {
    this.targetCharacterName=targetCharacterName;	  
  }
    
  public String getSourceCharacterName()
  {
    return sourceCharacterName;
  }
    
  public void setSourceCharacterName(String targetCharacterName)
  {
    this.sourceCharacterName=sourceCharacterName;
  }
  
  public int compareTo(CharacterPseudoData other)
  {
    int rslt=targetCharacterName.compareTo(other.getTargetCharacterID());
    
    if(rslt==0) rslt=pseudo.compareTo(other.getPseudo());
    
    return rslt;
  }
}
