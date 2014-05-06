package narrationManager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import narrationManager.model.CharacterModel;


public class DatabaseCoordinator
{    
  /*public static void main(String[] args)
  {
    DatabaseCoordinator c = new DatabaseCoordinator();
    CharacterModel c1 = c.getCharacter("Pierre");
    System.out.println(c1.getName());
  }*/
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
    String b_place = null;
    PreparedStatement pst = null;
    
    // get birth place
    try {
      pst = con.prepareStatement("select PLACENAME from   ORIGINATES, CHARACTER, PLACE where NAME = ? and CHARACTER.CHARACTERID = ORIGINATES.CHARACTERID and ORIGINATES.PLACEID = PLACE.PLACEID");
      pst.setString(1, name);
      ResultSet res = pst.executeQuery();
      res.next();
      b_place = res.getString(1);
    } catch (SQLException e) {      
        Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
        lgr.log(Level.SEVERE, e.getMessage(), e);
    }
    
    return new CharacterModel(name, b_place);  
  }
  
  public void setCharacter(CharacterModel c)
  {
    PreparedStatement pst = null;
    
    // update birthplace
    try {
      pst = con.prepareStatement("update originates set placeid = (select placeid from place where placename = ?) from character where character.characterid=originates.characterid and character.name=?");
      pst.setString(1, c.getB_place());
      pst.setString(2, c.getName());
      pst.executeUpdate();
      
      // update name
      pst = con.prepareStatement("update CHARACTER set NAME = ? where NAME = ?");
      pst.setString(1, c.getName());
      pst.setString(2, c.update_db());
      pst.executeUpdate(); 
    } catch (SQLException e) {      
        Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
        lgr.log(Level.SEVERE, e.getMessage(), e);
    }     
    

    
  }
}
