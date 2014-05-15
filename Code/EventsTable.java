package narrationmanager.gui.tables;

import java.util.Collection;

import javax.swing.JTable;

import narrationmanager.model.NarrationDate;
import narrationmanager.model.EventModel;

import narrationmanager.gui.tables.models.tables.EditionTable;

import narrationmanager.gui.tables.models.EventsTableModel;

public class EventsTable extends EditionTable<EventModel>
{
  public static final int COLUMN_COUNT=4;
  public static final int ROW_HEIGHT=40;
  public static final int DESCRIPTION_COLUMN_MIN_WIDTH=500;
  
  public static final int NAME_COLUMN=0;
  public static final int START_DATE_COLUMN=1;
  public static final int END_DATE_COLUMN=2;
  public static final int DESCRIPTION_COLUMN=3;
  
  private EventsTableModel model=new EventsTableModel();
  private boolean editable;
  
  public EventsTable(boolean editable)
  {
    super();
    
    this.editable=editable;
    
    setModel(model);
    
    getColumnModel().getColumn(DESCRIPTION_COLUMN).setPreferredWidth(DESCRIPTION_COLUMN_MIN_WIDTH);
    
    createDefaultRenderers();
    createDefaultEditors();
    setRowHeight(ROW_HEIGHT);
  }
  
  public void fillWith(Collection<EventModel> toAdd)
  {
    for(EventModel event:toAdd)
    {
      addEvent(event);
    }
  }
  
  public void addEvent(EventModel toAdd)
  {
    model.addEvent(toAdd);
  }
  
  public Class<?> getColumnClass(int columnIndex)
  {
    switch(columnIndex)
    {
      case NAME_COLUMN: return String.class;
      case START_DATE_COLUMN: return NarrationDate.class;
      case END_DATE_COLUMN: return NarrationDate.class;
      case DESCRIPTION_COLUMN: return String.class;
      default: System.err.println("Error: wrong column index"); return null;
    }
  }
  
  public boolean isCellEditable(int row, int column)
  {    
    return editable;
  }
  
  public void setValueAt(Object value,int row,int col)
  {
    EventModel target=dataModel.elementAt(row);
  	  
    switch(col)
    {
      case NAME_COLUMN: target.setName((String) value); break;
      case START_DATE_COLUMN: target.setStartDate((NarrationDate) value); break;
      case END_DATE_COLUMN: target.setEndDate((NarrationDate) value); break;
      case DESCRIPTION_COLUMN: target.setEventDescription((String) value); break;	    
    }
  }
  
  public Object getValueAt(int row, int column)
  {
    EventModel target=dataModel.elementAt(row);
    
    switch(column)
    {
      case NAME_COLUMN: return target.getName();
      case START_DATE_COLUMN: return target.getStartDate();
      case END_DATE_COLUMN: return target.getEndDate();
      case DESCRIPTION_COLUMN: 
        String description=target.getEventDescription();
      	return (description==null) ? :description;
      default: System.err.println("Error: wrong column"); return null;
    }
  }
  
  public String getColumnName(int col)
  {
    switch(col)
    {
      case NAME_COLUMN: return "Event name";
      case START_DATE_COLUMN: return "Start date";
      case END_DATE_COLUMN: return "End date";
      case DESCRIPTION_COLUMN: return "Description";
      default: System.err.println("wrong column index"); return null;
    }
  }
  
  public int getColumnCount()
  {
    return COLUMN_COUNT;	  
  }
}
