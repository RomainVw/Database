package narrationManager.gui.tables;

import java.util.Collection;

import java.time.LocalDate;

import javax.swing.JTable;

import narrationManager.model.EventModel;

import narrationManager.gui.tables.models.EventsTableModel;

public class EventsTable extends JTable
{
  public static final int COLUMN_COUNT=4;
  public static final int ROW_HEIGHT=80;
  
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
      case START_DATE_COLUMN: return LocalDate.class;
      case END_DATE_COLUMN: return LocalDate.class;
      case DESCRIPTION_COLUMN: return String.class;
      default: System.err.println("Error: wrong column index"); return null;
    }
  }
  
  public boolean isCellEditable(int row, int column)
  {    
    return editable;
  }
  
  
}
