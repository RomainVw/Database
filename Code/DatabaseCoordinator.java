package narrationManager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import narrationManager.model.CharacterModel;


public class DatabaseCoordinator
{
  private Connection con = null;
  
  public DatabaseCoordinator()
  {
    String url = "jdbc:postgresql://localhost/lingi2172";
    String user = "postgres";
    String pwd = "ben";
    con = DriverManager.getConnection(url, user, pwd);    
  }
  
  public CharacterModel getCharacter(String name)
  {
    String b_place = null;
    PreparedStatement pst = null;
    
    // get birth place
    pst = con.prepareStatement("select PLACENAME from   ORIGINATES, CHARACTER, PLACE where NAME = ? and CHARACTER.CHARACTERID = ORIGINATES.CHARACTERID and ORIGINATES.PLACEID = PLACE.PLACEID");
    pst.setString(1, name);
    ResultSet res = pst.executeQuerry();
    res.next();
    b_place = res.getString(1);
    
    return new CharacterModel(name, b_place);  
  }
  
  public void setCharacter(CharacterModel c)
  {
    PreparedStatement pst = null;
    
    // update birthplace
    pst = con.prepareStatement("update originates set placeid = (select placeid from place where placename = ?) from character where character.characterid=originates.characterid and character.name=?");
    pst.setString(1, c.getPlace());
    pst.setString(2, c.getName());
    pst.executeUpdate();
    
    // update name
    pst = con.prepareStatement("update CHARACTER set NAME = ? where NAME = ?");
    pst.setString(1, c.getName());
    pst.setString(2, c.update_db());
    pst.executeUpdate();    
    
  }
}
