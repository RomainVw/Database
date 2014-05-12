package narrationmanager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.ArrayList;

import narrationmanager.model.CharacterModel;
import narrationmanager.model.PlaceModel;


public class DatabaseCoordinator
{    
  public static void main(String[] args)//TODO retirer quand on en aura plus besoin
  {
    DatabaseCoordinator c = new DatabaseCoordinator();
    CharacterModel c1 = c.getCharacter("Pierre");
    System.out.println(c1.getName());
  }
  private Connection con = null;
  
  public DatabaseCoordinator()
  {
    String url = "jdbc:postgresql://localhost/lingi2172";
    String user = "postgres";
    String pwd = "ben";
    try {
      con = DriverManager.getConnection(url, user, pwd); 
    } catch (SQLException e) {      
        Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
        lgr.log(Level.SEVERE, e.getMessage(), e);
    }
  }
  
  public CharacterModel getCharacter(String name)
  {
    String birthPlace = null;
    PreparedStatement pst = null;
    
    // get birth place
    try {
      pst = con.prepareStatement("select PLACENAME from   ORIGINATES, CHARACTER, PLACE where NAME = ? and CHARACTER.CHARACTERID = ORIGINATES.CHARACTERID and ORIGINATES.PLACEID = PLACE.PLACEID");
      pst.setString(1, name);
      ResultSet res = pst.executeQuery();
      res.next();
      birthPlace = res.getString(1);
    } catch (SQLException e) {      
        Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
        lgr.log(Level.SEVERE, e.getMessage(), e);
    }
    
    return new CharacterModel(name, birthPlace,true);  
  }
  
  public void setCharacter(CharacterModel c)
  {
    PreparedStatement pst = null;
    
    // update birthplace
    try {
      pst = con.prepareStatement("update originates set placeid = (select placeid from place where placename = ?) from character where character.characterid=originates.characterid and character.name=?");
      pst.setString(1, c.getBirthPlace());
      pst.setString(2, c.getName());
      pst.executeUpdate();
      
      // update name
      pst = con.prepareStatement("update CHARACTER set NAME = ? where NAME = ?");
      pst.setString(1, c.getName());
      pst.setString(2, c.updateDB());
      pst.executeUpdate(); 
    } catch (SQLException e) {      
        Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
        lgr.log(Level.SEVERE, e.getMessage(), e);
    }     
  }
  
  public ArrayList<String> getPlaceNames()
  {
    ArrayList<String> names = new ArrayList<String>();
    PreparedStatement pst = null;
    ResultSet res = null;
    
    try {
      pst = con.prepareStatement("select PLACENAME from PLACE");
      res = pst.executeQuery();
      while(res.next())
      {
        names.add(res.getString(1));
      }
    } catch (SQLException e) {      
        Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
        lgr.log(Level.SEVERE, e.getMessage(), e);
    }    
    return names;
  }
  
  public PlaceModel makePlace(String name)
  { // TODO
    return new PlaceModel(name, true);
  }
}
