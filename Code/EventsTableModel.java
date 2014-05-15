package narrationmanager.gui.tables.models;

import javax.swing.table.AbstractTableModel;

import java.util.Vector;

import narrationmanager.model.NarrationDate;
import narrationmanager.model.EventModel;

import narrationmanager.gui.tables.EventsTable;

public class EventsTableModel extends AbstractTableModel
{
  private Vector<EventModel> dataModel=new Vector<>();
	
  public EventsTableModel()
  {
    super();
  }
  

  public int getRowCount()
  {
    return dataModel.size();	  
  }

  
  public void addEvent(EventModel toAdd)
  {
    dataModel.add(toAdd);	  
  }
  

}


