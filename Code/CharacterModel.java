package narrationmanager.model;

import java.util.TreeSet;
import java.util.LinkedList;
import java.util.Collection;

import narrationmanager.model.util.DBModel;

/**
A DBModel defining the data obtained from a character saved in database.

@author Baugnies Benjamin
@author Colson Olivier
@author Vanwelde Romain
**/
public class CharacterModel extends DBModel implements Comparable<CharacterModel>
{  
  // mandatory fields:
  // id inherited from DBModel
  private String name;
  private String birthPlace;
  
  // optional fields:
  private Collection<RelationData> relations=new LinkedList<>();
  private LinkedList<String> relatedEventsID=new LinkedList<>(); 
  private TreeSet<CharacterPseudoData> charactersPseudo=new TreeSet<>(); 
  private LinkedList<String> associationsID=new LinkedList<>();
  
  public CharacterModel(String id, String name)
  {
    super(id);
    this.name = name;
  }
  
  public String getName()
  {
    return name;
  }
  
  public void setName(String name)
  {
    this.name=name;
  }
  
  public String getBirthPlace()
  {
    return birthPlace;
  }
  
  public void setBirthPlace(String birthPlace)
  {
    this.birthPlace = birthPlace;
  }
  
  public void setRelations(Collection<RelationData> relations)
  {
    this.relations=relations;    	  
  }
    
  public LinkedList<String> getRelatedEventsID()
  {
    return relatedEventsID;	  
  }
  
  public void setRelatedEventsID(LinkedList<String> relatedEventsID)
  {
    this.relatedEventsID=relatedEventsID;	  
  }
  
  public LinkedList<String> getAssociations()
  {
    return associationsID;	  
  }
  
  public void setAssociations(LinkedList<String> associationsID)
  {
    this.associationsID=associationsID;	  
  }
  
  public TreeSet<CharacterPseudoData> getCharactersPseudo()
  {
    return charactersPseudo;
  }
  
  public void setCharactersPseudoData(TreeSet<CharacterPseudoData> charactersPseudo)
  {
    this.charactersPseudo=charactersPseudo;	  
  }
  
  public TreeSet<RelationData> getRelationsByCharacter()
  {
    TreeSet<RelationData> rslt=new TreeSet<>((RelationData a,RelationData b)-> groupRelationsByCharacter(a,b));	 
    rslt.addAll(relations);
    return rslt;
  }
  
  public TreeSet<RelationData> getRelationsByType()
  {
    TreeSet<RelationData> rslt=new TreeSet<>((RelationData a,RelationData b)-> groupRelationsByType(a,b));
    rslt.addAll(relations);
    return rslt;
  }  
  
  private static int groupRelationsByCharacter(RelationData a, RelationData b)
  {
    int rslt=rslt=a.getTargetCharacterName().compareTo(b.getTargetCharacterName());
    
    if(rslt==0)
      rslt=a.getRelationName().compareTo(b.getRelationName());

    if(rslt==0 && (a.getIsTarget()^b.getIsTarget())) rslt=(a.getIsTarget())? -1:1;

    return rslt;
  }
  
  private static int groupRelationsByType(RelationData a,RelationData b)
  {
    int rslt=a.getRelationName().compareTo(b.getRelationName());
    
    if(rslt==0) 
      rslt=a.getTargetCharacterName().compareTo(b.getTargetCharacterName());

    if(rslt==0 && (a.getIsTarget()^b.getIsTarget())) rslt=(a.getIsTarget())? -1:1;
    
    return rslt;
  }
  
  public static CharacterModel getDefaultInstance()
  {
    return new CharacterModel(null,"New Character");
  }
  
  public int compareTo(CharacterModel other)
  {
    int rslt=getName().compareTo(other.getName());
    
    if(rslt==0) rslt=getID().compareTo(other.getID());
    
    
    return rslt;
  }
  
  public String toString()
  {
    return name;
  }
}
