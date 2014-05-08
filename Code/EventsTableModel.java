package narrationManager.gui.tables.models;

import javax.swing.table.AbstractTableModel;

import java.util.Vector;

import java.time.LocalDate;

import narrationManager.model.EventModel;

import narrationManager.gui.tables.EventsTable;

public class EventsTableModel extends AbstractTableModel
{
  private Vector<EventModel> dataModel=new Vector<>();
	
  public EventsTableModel()
  {
    super();
  }
  
  public String getColumnName(int col)
  {
    switch(col)
    {
      case EventsTable.NAME_COLUMN: return "Event name";
      case EventsTable.START_DATE_COLUMN: return "Start date";
      case EventsTable.END_DATE_COLUMN: return "End date";
      case EventsTable.DESCRIPTION_COLUMN: return "Description";
    }
  }
  
  public int getRowCount()
  {
    return dataModel.size();	  
  }
  
  public int getColumnCount()
  {
    return EventsTable.COLUMN_COUNT;	  
  }
  
  public Object getValueAt(int row, int column)
  {
    EventModel target=dataModel.elementAt(row);
    
    switch(column)
    {
      case EventsTable.NAME_COLUMN: return target.getEventName();
      case EventsTable.START_DATE_COLUMN: return target.getStartDate();
      case EventsTable.END_DATE_COLUMN: return target.getEndDate();
      case EventsTable.DESCRIPTION_COLUMN: 
      	      String description=target.getEventDescription();
      	      return (description==null) ? "":description;
      default: System.err.println("Error: wrong column"); return null;
    }
  }
  
  public void add(EventModel toAdd)
  {
    dataModel.add(toAdd);	  
  }
  
  public void setValueAt(Object value,int row,int col)
  {
    switch(col)
    {
      case EventsTable.NAME_COLUMN: target.setEventName((String) value); break;
      case EventsTable.START_DATE_COLUMN: target.setStartDate((LocalDate) value); break;
      case EventsTable.END_DATE_COLUMN: target.setEndDate((LocalDate) value); break;
      case EventsTable.DESCRIPTION_COLUMN: target.setEventDescription((String) value); break;	    
    }
  }
}


