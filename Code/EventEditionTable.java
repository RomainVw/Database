package narrationmanager.gui.tables;

import java.util.Collection;

import javax.swing.JTable;

import narrationmanager.model.NarrationDate;
import narrationmanager.model.EventModel;

import narrationmanager.gui.tables.EditionTable;

public class EventEditionTable extends EditionTable<EventModel>
{
  public static final int COLUMN_COUNT=4;
  public static final int ROW_HEIGHT=40;
  public static final int DESCRIPTION_COLUMN_PREF_WIDTH=500;
  
  public static final int NAME_COLUMN=0;
  public static final int START_DATE_COLUMN=1;
  public static final int END_DATE_COLUMN=2;
  public static final int DESCRIPTION_COLUMN=3;

  private boolean editable;
  
  public EventEditionTable(boolean editable)
  {
    super(editable);
    
    this.editable=editable;
    
    getColumnModel().getColumn(DESCRIPTION_COLUMN).setPreferredWidth(DESCRIPTION_COLUMN_PREF_WIDTH);
    
    createDefaultRenderers();
    createDefaultEditors();
    setRowHeight(ROW_HEIGHT);
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
  
  public void setValueAt(Object value,int row,int col)
  {
    EventModel target=editionModel.getRowElement(row);
  	  
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
    EventModel target=editionModel.getRowElement(row);
    
    switch(column)
    {
      case NAME_COLUMN: return target.getName();
      case START_DATE_COLUMN: return target.getStartDate();
      case END_DATE_COLUMN: return target.getEndDate();
      case DESCRIPTION_COLUMN: 
        String description=target.getEventDescription();
      	return (description==null) ? "":description;
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
