package narrationmanager.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.util.logging.Level;
import java.util.logging.Logger;

import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;
import java.util.TreeSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.HashMap;

import narrationmanager.model.CharacterModel;
import narrationmanager.model.PlaceModel;
import narrationmanager.model.MapModel;
import narrationmanager.model.EventModel;
import narrationmanager.model.NarrationDate;
import narrationmanager.model.RelationData;
import narrationmanager.model.CharacterPseudoData;



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
  
  public CharacterModel getCharacter(String characterid)
  { // TO REDO
    String name = null;
    String birthPlace = null;
    Collection<RelationData> relations = new ArrayList<>();
    LinkedList<String> relatedEventsNames = new LinkedList<>();
    TreeSet<CharacterPseudoData> charactersPseudo = new TreeSet<>();
    PreparedStatement pst = null;
    
    

      /*
       *   Get Name
       */
    try {
      pst = con.prepareStatement("select NAME from  CHARACTER where CHARACTERID = ?");
      pst.setString(1, characterid);
      ResultSet res = pst.executeQuery();
      if(res.next()){
          name = res.getString(1);
      }
        
      
    } catch (SQLException e) {      
        Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
        lgr.log(Level.SEVERE, e.getMessage(), e);
    }
      
      /*
       * Birth Place
       */
      
    try {
        pst = con.prepareStatement("select PLACENAME from  ORIGINATES, CHARACTER, PLACE where CHARACTER.CHARACTERID = ? and CHARACTER.CHARACTERID = ORIGINATES.CHARACTERID and ORIGINATES.PLACEID = PLACE.PLACEID");
        pst.setString(1, characterid);
        ResultSet res = pst.executeQuery();
        if(res.next()){
            birthPlace = res.getString(1);
        }
        
    } catch (SQLException e) {
        Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
        lgr.log(Level.SEVERE, e.getMessage(), e);
    }
      
      
      /*
       * All relationsf
       */
      
      // get timeless relations where target
      try {
          pst = con.prepareStatement(" select RL.relationid, source, relationtype, name from TIMELESSRELATION TR JOIN RELATIONLIST RL on TR.relationid = RL.relationid, CHARACTER where target = ? and CHARACTERID=SOURCE");
          pst.setString(1, characterid);
          ResultSet res = pst.executeQuery();
          while(res.next()){
             RelationData toAdd = new RelationData(res.getString(1), res.getString(3),res.getString(2), true);
             toAdd.setTargetCharacterName(res.getString(4));
             relations.add(toAdd);
          }
          
      } catch (SQLException e) {
          Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
          lgr.log(Level.SEVERE, e.getMessage(), e);
      }
      
      
      
      // get timeless relations where source
      try {
          pst = con.prepareStatement(" select RL.relationid, target, relationtype, name from TIMELESSRELATION TR JOIN RELATIONLIST RL on TR.relationid = RL.relationid, character where source = ? and characterid=target");
          pst.setString(1, characterid);
          ResultSet res = pst.executeQuery();
          while(res.next()){
              RelationData toAdd = new RelationData(res.getString(1), res.getString(3),res.getString(2), false);
              toAdd.setTargetCharacterName(res.getString(4));
              relations.add(toAdd);
          }
          
      } catch (SQLException e) {
          Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
          lgr.log(Level.SEVERE, e.getMessage(), e);
      }
      
      
      // get relations with one date where target
      try {
          pst = con.prepareStatement(" select RL.relationid, source, relationtype, (date).year, (date).month, (date).day, name from DATERELATION DR JOIN RELATIONLIST RL on DR.relationid = RL.relationid, character where target = ? and characterid=source");
          pst.setString(1, characterid);
          ResultSet res = pst.executeQuery();
          while(res.next()){
              RelationData toAdd = new RelationData(res.getString(1), res.getString(3),res.getString(2), true);
              toAdd.setStart(new NarrationDate(res.getInt(4),res.getInt(5),res.getInt(6)));
              toAdd.setTargetCharacterName(res.getString(7));
              relations.add(toAdd);
          }
          
      } catch (SQLException e) {
          Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
          lgr.log(Level.SEVERE, e.getMessage(), e);
      }
      
      
      // get relations with one date where source
      try {
          pst = con.prepareStatement(" select RL.relationid , target, relationtype, (date).year, (date).month, (date).day , name from DATERELATION DR JOIN RELATIONLIST RL on DR.relationid = RL.relationid, character where source = ? and characterid = target");
          pst.setString(1, characterid);
          ResultSet res = pst.executeQuery();
          while(res.next()){
            RelationData toAdd = new RelationData(res.getString(1),res.getString(3),res.getString(2), false);
            toAdd.setStart(new NarrationDate(res.getInt(4),res.getInt(5),res.getInt(6)));
            toAdd.setTargetCharacterName(res.getString(7));
            relations.add(toAdd);
          }
          
      } catch (SQLException e) {
          Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
          lgr.log(Level.SEVERE, e.getMessage(), e);
      }
      
      
      // get relations with daterange where target
      try {
          pst = con.prepareStatement("  select RL.relationid, source, relationtype, (start).year, (start).month, (start).day, (enddate).year, (enddate).month, (enddate).day, name from RANGERELATION RR JOIN RELATIONLIST RL on RR.relationid = RL.relationid, character where target = ? and characterid= source");
          pst.setString(1, characterid);
          ResultSet res = pst.executeQuery();
          while(res.next()){
              RelationData toAdd = new RelationData(res.getString(1),res.getString(3),res.getString(2), true);
              toAdd.setStart(new NarrationDate(res.getInt(4),res.getInt(5),res.getInt(6)));
              toAdd.setEnd(new NarrationDate(res.getInt(7),res.getInt(8),res.getInt(9)));
              toAdd.setTargetCharacterName(res.getString(10));
              relations.add(toAdd);
          }
          
      } catch (SQLException e) {
          Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
          lgr.log(Level.SEVERE, e.getMessage(), e);
      }
      
      
      // get relations with daterange where source
      try {
          pst = con.prepareStatement("  select RL.relationid, source, relationtype, (start).year, (start).month, (start).day, (enddate).year, (enddate).month, (enddate).day, name from RANGERELATION RR JOIN RELATIONLIST RL on RR.relationid = RL.relationid, character where source = ? and characterid=target");
          pst.setString(1, characterid);
          ResultSet res = pst.executeQuery();
          while(res.next()){
              RelationData toAdd = new RelationData(res.getString(1),res.getString(3),res.getString(2), false);
              toAdd.setStart(new NarrationDate(res.getInt(4),res.getInt(5),res.getInt(6)));
              toAdd.setEnd(new NarrationDate(res.getInt(7),res.getInt(8),res.getInt(9)));
              toAdd.setTargetCharacterName(res.getString(10));
              relations.add(toAdd);
          }
          
      } catch (SQLException e) {
          Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
          lgr.log(Level.SEVERE, e.getMessage(), e);
      }
      
      /*
       * Get related events
       */
      try {
          pst = con.prepareStatement("select eventid from ATTENDS where characterid = ?");
          pst.setString(1, characterid);
          ResultSet res = pst.executeQuery();
          while(res.next()){
              String toAdd = res.getString(1);
              relatedEventsNames.add(toAdd);
          }
          
      } catch (SQLException e) {
          Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
          lgr.log(Level.SEVERE, e.getMessage(), e);
      }
      
      /*
       * Get pseudonymes
       */
      try {
          pst = con.prepareStatement("select name, pseudonyme from pseudo P JOIN character C on P.callerid = C.characterid where calledid = ?");
          pst.setString(1, characterid);
          ResultSet res = pst.executeQuery();
          while(res.next()){
              CharacterPseudoData toAdd = new CharacterPseudoData(res.getString(1),name,res.getString(2));
              charactersPseudo.add(toAdd);
          }
          
      } catch (SQLException e) {
          Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
          lgr.log(Level.SEVERE, e.getMessage(), e);
      }
      

    CharacterModel characterResult = new CharacterModel(characterid, name);
    characterResult.setBirthPlace(birthPlace);
    characterResult.setRelations(relations);
    characterResult.setRelatedEventsID(relatedEventsNames);
    characterResult.setCharactersPseudoData(charactersPseudo);
      
    return characterResult;
  }
  
  public ArrayList<String> getAllCharacterID()
  {
    PreparedStatement pst;
    ResultSet rst;
    ArrayList<String> out = new ArrayList<String>();
    
    try {
      pst = con.prepareStatement("select CHARACTERID from CHARACTER");
      rst = pst.executeQuery();
      while (rst.next())
      {
       out.add(rst.getString(1)); 
      }
      
    } catch (SQLException e) {
      Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
      lgr.log(Level.SEVERE, e.getMessage(), e);
    }
    
    return out;      
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
      pst.setString(2, c.getID());
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
    ArrayList<String> list = new ArrayList<String>();
    
    /*
     * get the places name (only mandatory info)
     */
    try {
      pst = con.prepareStatement("select PLACENAME from PLACE where PLACEID=?");
      pst.setString(1, id);
      res = pst.executeQuery();
        if (res.next()){
            name = res.getString(1);
        }
    } catch (SQLException e) {
        Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
        lgr.log(Level.SEVERE, e.getMessage(), e);
    }    
    
    place = new PlaceModel(id, name);
    
    /*
     * get all optional info and add it to placemodel
     */
    try {
      pst = con.prepareStatement("select MAP.MAPID, NUMWIDTH, NUMLENGTH, WIDTH, LENGTH from MAP, MAPPEDPLACE, PLACE where MAP.MAPID=MAPPEDPLACE.MAPID and PLACE.PLACEID=MAPPEDPLACE.PLACEID and PLACE.PLACEID=?");
      pst.setString(1, id);
      res = pst.executeQuery();
        
        if (res.next()){
            MapModel toAdd = new MapModel(res.getString(1), res.getInt(2), res.getInt(3), res.getFloat(4), res.getFloat(5));
            toAdd.setSubplaceID(getSubplaces(toAdd.getMapID()));
            place.setMap(toAdd);
        }
      
      pst = con.prepareStatement("select EVENTNAME.EVENTID, EVENTNAME.NAME from EVENTNAME, EVENT where PLACEID=? and EVENT.EVENTID=EVENTNAME.EVENTID");
      pst.setString(1, id);
      res = pst.executeQuery();
      while (res.next())
      {
        list.add(res.getString(1));
      }
      place.setEvents(list);
      
      list = new ArrayList<String>();
      pst = con.prepareStatement("with allCharLinks as (select * from originates union (select a.characterid, e.placeid from event e join attends a on e.eventid = a.eventid) ) select CH.characterid, CH.name from allCharLinks ACL join character CH on ACL.characterid = CH.characterid where ACL.placeid=?");
      pst.setString(1, id);
      res=pst.executeQuery();
      while (res.next())
      {
        list.add(res.getString(1));
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
    
    
    
    public ArrayList<CharacterModel> getCharactersDescription(String eventId)
    {
        ArrayList<CharacterModel> characters = new ArrayList<CharacterModel>();
        PreparedStatement pst = null;
        ResultSet res = null;
        
        try {
            pst = con.prepareStatement("select characterid from attends where eventid = ?");
            pst.setString(1, eventId);
            res = pst.executeQuery();
            while(res.next()){
                characters.add(getCharacter(res.getString(1)));
            }
            
        } catch (SQLException e) {
            Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
        }
        return characters;
    }


   
  public EventModel getEvent(String eventId)
  {
   PreparedStatement pst;
   ResultSet res;
   EventModel event = null;
   
   try {
     pst = con.prepareStatement("select EN.name, E.placeid, (E.beginning).year, (E.beginning).month, (E.beginning).day, (E.enddate).year, (E.enddate).month, (E.enddate).day from EVENT E JOIN EVENTNAME EN on E.eventid = EN.eventid where E.EVENTID=?");
     pst.setString(1, eventId);
     res = pst.executeQuery();
     if (res.next())
     {
       event = new EventModel(eventId,res.getString(1),this.makePlace(res.getString(2)),new NarrationDate(res.getInt(3),res.getInt(4),res.getInt(5)),new NarrationDate(res.getInt(6),res.getInt(7),res.getInt(8)));
       event.setEventDescription(getEventDescription(eventId));
     }
   } catch (SQLException e) {
     Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
     lgr.log(Level.SEVERE, e.getMessage(), e);
   }
   
   return event;
   
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
        EventModel event = new EventModel(eventId,res.getString(2),this.makePlace(res.getString(3)),new NarrationDate(res.getInt(4),res.getInt(5),res.getInt(6)),new NarrationDate(res.getInt(7),res.getInt(8),res.getInt(9)));
        event.setEventDescription(getEventDescription(eventId));
        events.add(event);
      }
    } catch (SQLException e) {
      Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
      lgr.log(Level.SEVERE, e.getMessage(), e);
    }
    return events;
  }
  
  public String nextEventID()
  {
    int count = 0;
    int tempcount;
    String ret = "EV";
    try {
      PreparedStatement pst = con.prepareStatement("select EVENTID from EVENTNAME");
      ResultSet res  = pst.executeQuery();
      while (res.next())
      {
        tempcount = Integer.parseInt(res.getString(1).substring(2));
        if (tempcount > count)
        {
          count = tempcount;
        }
      }
    } catch (SQLException e) {
      Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
      lgr.log(Level.SEVERE, e.getMessage(), e);
    }
    return (ret+Integer.toString(count+1));
  }
  
  public String nextPlaceID()
  {
    int count = 0;
    int tempcount;
    String ret = "PL";
    try {
      PreparedStatement pst = con.prepareStatement("select PLACEID from PLACE");
      ResultSet res  = pst.executeQuery();
      while (res.next())
      {
        tempcount = Integer.parseInt(res.getString(1).substring(2));
        if (tempcount > count)
        {
          count = tempcount;
        }
      }
    } catch (SQLException e) {
      Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
      lgr.log(Level.SEVERE, e.getMessage(), e);
    }
    return (ret+Integer.toString(count+1));
  }
  
    public String nextCharacterID()
    {
        int count = 0;
        int tempcount;
        String ret = "CH";
        try {
            PreparedStatement pst = con.prepareStatement("select characterid from character");
            ResultSet res  = pst.executeQuery();
            while (res.next())
            {
                tempcount = Integer.parseInt(res.getString(1).substring(2));
                if (tempcount > count)
                {
                    count = tempcount;
                }
            }
        } catch (SQLException e) {
            Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
        }
        return (ret+Integer.toString(count+1));
    }

    public String nextRelationID()
    {
        int count = 0;
        int tempcount;
        String ret = "REL";
        try {
            PreparedStatement pst = con.prepareStatement("select relationid from relationlist");
            ResultSet res  = pst.executeQuery();
            while (res.next())
            {
                tempcount = Integer.parseInt(res.getString(1).substring(3));
                if (tempcount > count)
                {
                    count = tempcount;
                }
            }
        } catch (SQLException e) {
            Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
        }
        return (ret+Integer.toString(count+1));
    }
    
  public String nextMapID()
  {
    int count = 0;
    int tempcount;
    String ret = "MA";
    try {
            PreparedStatement pst = con.prepareStatement("select MAPID from MAP");
            ResultSet res  = pst.executeQuery();
            while (res.next())
            {
                tempcount = Integer.parseInt(res.getString(1).substring(2));
                if (tempcount > count)
                {
                    count = tempcount;
                }
            }
     } catch (SQLException e) {
            Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
     }
     return (ret+Integer.toString(count+1));
  }
    
  public void saveNewEvent(EventModel event)
  {
    PreparedStatement pst = null;
    String newID = nextEventID();
    
    try {
      pst = con.prepareStatement("insert into EVENTNAME values(? , ? )");
      pst.setString(1, newID);
      pst.setString(2, event.getName());
      pst.executeUpdate();
      
      if (event.getEventDescription() != null)
      {
        pst = con.prepareStatement("insert into EVENTDESCRIPTION values(?, ?)");
        pst.setString(1, newID);
        pst.setString(2, event.getEventDescription());
        //System.out.println(newID + " " + event.getEventDescription());
        pst.executeUpdate();
      }
      else System.out.println("no description");
      
      pst = con.prepareStatement("insert into EVENT values(?, ?, (? ,? , ?), (? ,? ,?))");
      pst.setString(1, newID);
      pst.setString(2, event.getEventPlace().getID());
      pst.setInt(3, event.getStartDate().getYear());
      pst.setInt(4, event.getStartDate().getMonth());
      pst.setInt(5, event.getStartDate().getDay());
      pst.setInt(6, event.getEndDate().getYear());
      pst.setInt(7, event.getEndDate().getMonth());
      pst.setInt(8, event.getEndDate().getDay());
      pst.executeUpdate();
      
    } catch (SQLException e) {
      Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
      lgr.log(Level.SEVERE, e.getMessage(), e);
    }
  }
  
  public void saveEvent(EventModel event)
  {
    PreparedStatement pst = null;
    ResultSet res = null;
    
    try {
      pst = con.prepareStatement("update EVENTNAME set NAME=? where EVENTID=?");
      pst.setString(1, event.getName());
      pst.setString(2, event.getID());
      pst.executeUpdate();
      
      pst = con.prepareStatement("select DESCRIPTION from EVENTDESCRIPTION where EVENTID=?");
      pst.setString(1, event.getID());
      res = pst.executeQuery();
      if (res.next())
      {
        if (event.getEventDescription() == null)
        {
          pst = con.prepareStatement("delete from EVENTDESCRIPTION where EVENTID=?");
          pst.setString(1, event.getID());
          pst.executeUpdate();
        }
        else
        {
          pst = con.prepareStatement("update EVENTDESCRIPTION set DESCRIPTION=? where EVENTID=?");
          pst.setString(1, event.getEventDescription());
          pst.setString(2, event.getID());
          pst.executeUpdate();
        }
      }
      else
      {
        if (event.getEventDescription() != null)
        {
          pst = con.prepareStatement("insert into EVENTDESCRIPTION values(? , ?)");
          pst.setString(1, event.getID());
          pst.setString(2, event.getEventDescription());
          pst.executeUpdate();
        }
      }
      
      pst = con.prepareStatement("update EVENT set PLACEID=?, BEGINNING.YEAR=?, BEGINNING.MONTH=?, BEGINNING.DAY=?, ENDDATE.YEAR=?, ENDDATE.MONTH=?, ENDDATE.DAY=? where EVENTID=?");
      pst.setString(1, event.getEventPlace().getID());
      pst.setInt(2, event.getStartDate().getYear());
      pst.setInt(3, event.getStartDate().getMonth());
      pst.setInt(4, event.getStartDate().getDay());
      pst.setInt(5, event.getEndDate().getYear());
      pst.setInt(6, event.getEndDate().getMonth());
      pst.setInt(7, event.getEndDate().getDay());
      pst.setString(8, event.getID());
      pst.executeUpdate();
      
    } catch (SQLException e) {
      Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
      lgr.log(Level.SEVERE, e.getMessage(), e);
    }
  }
  
  public ArrayList<String> getAllSubplaces()
  {
   PreparedStatement pst;
   ResultSet res;
   ArrayList<String> out = new ArrayList<String>();
   try {
     pst = con.prepareStatement("select PLACEID from subplace");
     res = pst.executeQuery();
     while(res.next())
     {
       out.add(res.getString(1));
     }    
   } catch (SQLException e) {
      Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
      lgr.log(Level.SEVERE, e.getMessage(), e);
   }
   return out;
  }
  
  public void savePlace(PlaceModel place, boolean isNew)
  {
    System.out.println(place.getLocation());
    PreparedStatement pst = null;
    String id;
    if (isNew)
    {
      id = nextPlaceID();
      place.setID(id); 
    }
    else id = place.getID();
    
    
    try {
      if (isNew)
      {
        pst = con.prepareStatement("insert into PLACE values(? , ? )");
        pst.setString(1, id);
        pst.setString(2, place.getName());
      }
      else
      {
        pst = con.prepareStatement("update PLACE set PLACENAME=? where PLACEID=?");
        pst.setString(2, id);
        pst.setString(1, place.getName());
      }      
      pst.executeUpdate();
      
      if (place.getParentID() != null)
      {
        ArrayList<String> subplaces = this.getAllSubplaces();
        MapModel parentMap = makePlace(place.getParentID()).getMap();
        //Map<String, Integer> subplaces = parentMap.getSubplaceID();
        if (!subplaces.contains(id))
        {
          pst = con.prepareStatement("insert into SUBPLACE values(?, ? , ?)");
          pst.setString(1, id);
          pst.setInt(2, place.getLocation());
          pst.setString(3, parentMap.getMapID());
        }
        else
        {
          pst = con.prepareStatement("update SUBPLACE set SQUAREID=?, MAPID=? where PLACEID=?");
          pst.setInt(1, place.getLocation());
          pst.setString(2, parentMap.getMapID());
          pst.setString(3, id);
          parentMap.removeSubplace(id);
        }
        pst.executeUpdate();
        parentMap.addSubplace(id, place.getLocation());
      }

    } catch (SQLException e) {
      Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
      lgr.log(Level.SEVERE, e.getMessage(), e);
    }
  }
    
    public Map<String,Integer> getSubplaces(String mapId)
    {
        //System.out.println(place.getLocation());
        Map<String,Integer> subplaces = new HashMap<>();
        PreparedStatement pst = null;

        try {
            pst = con.prepareStatement("select placeid, squareid from subplace where mapid = ?");
            pst.setString(1, mapId);
            ResultSet res = pst.executeQuery();
            while(res.next()){
                subplaces.put(res.getString(1),res.getInt(2));
            }
            
        } catch (SQLException e) {
            Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
        }
        
        
        return subplaces;
    }
    
    public void saveNewCharacter(CharacterModel newCharacter, boolean isNew)
    {
        PreparedStatement pst = null;
        String newCharID;
        
        try {
            
            
            /* Creation du personnage */
            if(isNew){
                newCharID = nextCharacterID();
                pst = con.prepareStatement("insert into CHARACTER values(? , ? )");
                pst.setString(1, newCharID);
                pst.setString(2, newCharacter.getName());
                pst.executeUpdate();
            
                pst = con.prepareStatement("insert into ORIGINATES values(? , ? )");
                pst.setString(1, newCharID);
                pst.setString(2, newCharacter.getBirthPlace());
                pst.executeUpdate();
                /* update d'un personnege */
            } else {
                newCharID = newCharacter.getID();
                pst = con.prepareStatement("update CHARACTER set name=? where characterid=?");
                pst.setString(1, newCharacter.getID());
                pst.setString(2, newCharacter.getName());
                pst.executeUpdate();
                
                pst = con.prepareStatement("update ORIGINATES set placeid=? where characterid=?");
                pst.setString(1, newCharacter.getID());
                pst.setString(2, newCharacter.getBirthPlace());
                pst.executeUpdate();

            
            
            }
        
            
            
            TreeSet<RelationData> relations = newCharacter.getRelationsByType();
            for (RelationData relationToAdd : relations) {
                
                /* Recuperation de l'id de la  relation */
                pst = con.prepareStatement("select relationid from relationlist where relationtype = ?");
                pst.setString(1, relationToAdd.getRelationName());
                ResultSet res = pst.executeQuery();
                String newRelId;
                if(res.next()){
                    newRelId = res.getString(1);
                /* insertion si il s'agit d'une nouvelle relation */
                } else {
                    newRelId = nextRelationID();
                    pst = con.prepareStatement("insert into RELATIONLIST values(? , ? )");
                    pst.setString(1, newRelId);
                    pst.setString(2, relationToAdd.getRelationName());
                    pst.executeUpdate();
                }
                
                /* Suppression de toutes les relations en DB */
                pst = con.prepareStatement("delete from TIMELESSRELATION where  source=? or target = ?");
                pst.setString(1, newCharID);
                pst.setString(2, newCharID);
                pst.executeUpdate();

                pst = con.prepareStatement("delete from DATERELATION where  source=? or target = ?");
                pst.setString(1, newCharID);
                pst.setString(2, newCharID);
                pst.executeUpdate();
                
                pst = con.prepareStatement("delete from RANGERELATION where  source=? or target = ?");
                pst.setString(1, newCharID);
                pst.setString(2, newCharID);
                pst.executeUpdate();
                
                
                /* Ajout de toutes les relations en DB */
                if (relationToAdd.getStart() == null){
                    pst = con.prepareStatement("insert into TIMELESSRELATION values(? , ? , ? )");

                } else if (relationToAdd.getEnd() == null){
                    pst = con.prepareStatement("insert into DATERELATION values(? , ? , ? , (? , ? , ?) )");
                    pst.setInt(4, relationToAdd.getStart().getYear());
                    pst.setInt(5, relationToAdd.getStart().getMonth());
                    pst.setInt(6, relationToAdd.getStart().getDay());
                } else {
                    pst = con.prepareStatement("insert into RANGERELATION values(? , ? , ? , (? , ? , ?) , (? , ? , ?) )");
                    pst.setInt(4, relationToAdd.getStart().getYear());
                    pst.setInt(5, relationToAdd.getStart().getMonth());
                    pst.setInt(6, relationToAdd.getStart().getDay());
                    pst.setInt(7, relationToAdd.getEnd().getYear());
                    pst.setInt(8, relationToAdd.getEnd().getMonth());
                    pst.setInt(9, relationToAdd.getEnd().getDay());
                }
                
                
                if (relationToAdd.getIsTarget()) {
                    pst.setString(1, relationToAdd.getTargetCharacterID());
                    pst.setString(2, newCharID);
                } else {
                    pst.setString(1, newCharID);
                    pst.setString(2, relationToAdd.getTargetCharacterID());
                }
                
                pst.setString(3, newRelId);
                pst.executeUpdate();
                
                
            }
            
            
            LinkedList<String> relatedEventsID= newCharacter.getRelatedEventsID();
            /* Supprime tout les elements de attends du character */
            pst = con.prepareStatement("delete from ATTENDS where characterid = ?");
            pst.setString(1, newCharID);
            pst.executeUpdate();
            
            /* Et les réinscrit */
            for (String eventId : relatedEventsID) {
                pst = con.prepareStatement("insert into ATTENDS values(? , ? )");
                pst.setString(1, newCharID);
                pst.setString(2, eventId);
                pst.executeUpdate();
            }

            
            
        } catch (SQLException e) {
            Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
            lgr.log(Level.SEVERE, e.getMessage(), e);
        }
    }
    
    public void saveMap(MapModel map, PlaceModel place)
    {
      PreparedStatement pst;
      String id = nextMapID();
      try {
        pst = con.prepareStatement("insert into MAP values(? , ? , ?, ?, ?)");
        pst.setString(1, id);
        pst.setInt(2, map.getNumWidth());
        pst.setInt(3, map.getNumLength());
        pst.setFloat(4, map.getWidth());
        pst.setFloat(5, map.getLength());
        pst.executeUpdate();
        
        pst = con.prepareStatement("insert into MAPPEDPLACE values(? ,?)");
        pst.setString(1, place.getID());
        pst.setString(2, id);
        pst.executeUpdate();
        
      } catch (SQLException e) {      
        Logger lgr = Logger.getLogger(DatabaseCoordinator.class.getName());
        lgr.log(Level.SEVERE, e.getMessage(), e);
      }     
    }
}
