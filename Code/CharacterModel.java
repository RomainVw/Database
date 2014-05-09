package narrationmanager.model;

import narrationmanager.model.util.DBModel;

public class CharacterModel extends DBModel
{  
  private String birthPlace = null;
  
  public CharacterModel(String name, String birthPlace,boolean alreadyInDB)
  {
    super(name,alreadyInDB);
    this.birthPlace = birthPlace;
  }
  
  public String getBirthPlace()
  {
    return birthPlace;
  }
  
  public void setBirthPlace(String birthPlace)
  {
    this.birthPlace = birthPlace;
  }
}
