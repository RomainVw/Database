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

import narrationmanager.model.MapModel;
import narrationmanager.model.util.ModelInfo;
import narrationmanager.model.EventModel;
import narrationmanager.model.NarrationDate;

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
  
  public ArrayList<String> getPlaceIDs()
  {
    ArrayList<String> names = new ArrayList<String>();
    PreparedStatement pst = null;
    ResultSet res = null;
    
    try {
      pst = con.prepareStatement("select PLACEID from PLACE");
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
  
  public PlaceModel makePlace(String id)
  { // TODO
    PreparedStatement pst = null;
    ResultSet res = null;
    String name = null;
    PlaceModel place = null;    
    ArrayList<ModelInfo> list = new ArrayList<ModelInfo>();
    
    /*
     * get the places name (only mandatory info)
     */
    try {
      pst = con.prepareStatement("select PLACENAME from PLACE where PLACEID=?");
      pst.setString(1, id);
      res = pst.executeQuery();
      res.next();
      name = res.getString(1);
    } catch (SQLException e) {      
        Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
        lgr.log(Level.SEVERE, e.getMessage(), e);
    }    
    
    place = new PlaceModel(id, true, name);
    
    /*
     * get all optional info and add it to placemodel
     */
    try {
      pst = con.prepareStatement("select MAP.MAPID, NUMWIDTH, NUMLENGTH, WIDTH, LENGTH from MAP, MAPPEDPLACE, PLACE where MAP.MAPID=MAPPEDPLACE.MAPID and PLACE.PLACEID=MAPPEDPLACE.PLACEID and PLACE.PLACEID=?");
      pst.setString(1, id);
      res = pst.executeQuery();
      if (res.next()) place.setMap(new MapModel(res.getString(1), res.getInt(2), res.getInt(3), res.getFloat(4), res.getFloat(5)));
      
      pst = con.prepareStatement("select EVENTNAME.EVENTID, EVENTNAME.NAME from EVENTNAME, EVENT where PLACEID=? and EVENT.EVENTID=EVENTNAME.EVENTID");
      pst.setString(1, id);
      res = pst.executeQuery();
      while (res.next())
      {
        list.add(new ModelInfo(res.getString(1), res.getString(2)));
      }
      place.setEvents(list);
      
      list = new ArrayList<ModelInfo>();
      pst = con.prepareStatement("with allCharLinks as (select * from originates union (select a.characterid, e.placeid from event e join attends a on e.eventid = a.eventid) ) select CH.characterid, CH.name from allCharLinks ACL join character CH on ACL.characterid = CH.characterid where ACL.placeid=?");
      pst.setString(1, id);
      res=pst.executeQuery();
      while (res.next())
      {
        list.add(new ModelInfo(res.getString(1), res.getString(2)));
      }
      place.setCharacters(list);
      
    } catch (SQLException e) {      
        Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
        lgr.log(Level.SEVERE, e.getMessage(), e);
    }    
      
    return place;
  }
    
    public String getEventDescription(String eventId)
    {
        String result = null;
        PreparedStatement pst = null;
        ResultSet res = null;
        
        try {
            pst = con.prepareStatement("select description from eventdescription where eventid = ?");
            pst.setString(1, eventId);
            res = pst.executeQuery();
            if(res.next()){
              result = res.getString(1);
            }
            
        } catch (SQLException e) {
            Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
        }
        return result;
    }


  public ArrayList<EventModel> getAllEvents()
  {
    ArrayList<EventModel> events = new ArrayList<EventModel>();
    PreparedStatement pst = null;
    ResultSet res = null;
    
    try {
      pst = con.prepareStatement("select EN.eventid, EN.name, E.placeid, (E.beginning).year, (E.beginning).month, (E.beginning).day, (E.enddate).year, (E.enddate).month, (E.enddate).day from EVENT E JOIN EVENTNAME EN on E.eventid = EN.eventid");
      res = pst.executeQuery();
      while(res.next())
      {
        String eventId =res.getString(1);
        EventModel event = new EventModel(eventId,res.getString(2),this.makePlace(res.getString(3)),new NarrationDate(res.getInt(4),res.getInt(5),res.getInt(6)),new NarrationDate(res.getInt(7),res.getInt(8),res.getInt(9)),true);
        event.setEventDescription(getEventDescription(eventId));
        events.add(event);
      }
    } catch (SQLException e) {
      Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
      lgr.log(Level.SEVERE, e.getMessage(), e);
    }
    return events;
  }
    
    
}
