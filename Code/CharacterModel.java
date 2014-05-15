package narrationmanager.model;

import java.util.TreeSet;
import java.util.LinkedList;
import java.util.Collection;

import narrationmanager.model.AssociationModel;

import narrationmanager.model.util.DBModel;

public class CharacterModel extends DBModel implements Comparable<CharacterModel>
{  
  // mandatory fields:
  // id inherited from DBModel
  private String name;
  private String birthPlace;
  
  // optional fields:
  private Collection<RelationData> relations=new LinkedList<>();
  private TreeSet<String> relatedEventsNames=new TreeSet<>();
  private TreeSet<CharacterPseudoData> charactersPseudo=new TreeSet<>(); 
  private TreeSet<AssociationModel> associations=new TreeSet<>();
  
  public CharacterModel(String id, String name)
  {
    super(id);
    this.name = name;
    
    //TODO TEST:
    relations.add(new RelationData("ceci est un test, ligne 25 de CharacterModel","toto",true));
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
  
  public TreeSet<String> getRelatedEventsNames()
  {
    return relatedEventsNames;	  
  }
  
  public void setRelatedEventsNames(TreeSet<String> relatedEventsNames)
  {
    this.relatedEventsNames=relatedEventsNames;	  
  }
  
  public TreeSet<AssociationModel> getAssociations()
  {
    return associations;	  
  }
  
  public void setAssociations(TreeSet<AssociationModel> associations)
  {
    this.associations=associations;	  
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
    TreeSet<RelationData> rslt=new TreeSet<>((RelationData a,RelationData b)-> groupRelationsByType(a,b));	 
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

    return rslt;
  }
  
  private static int groupRelationsByType(RelationData a,RelationData b)
  {
    int rslt=a.getRelationName().compareTo(b.getRelationName());
    
    if(rslt==0) 
      rslt=a.getTargetCharacterName().compareTo(b.getTargetCharacterName());
    
    return rslt;
  }
  
  public static CharacterModel getDefaultInstance()
  {
    return new CharacterModel(null,"New Character");//TODO: "test"??  
  }
  
  public int compareTo(CharacterModel other)
  {
    int rslt=getName().compareTo(other.getName());
    
    if(rslt==0) rslt=getID().compareTo(other.getID());
    
    return rslt;
  }
}
