package narrationmanager.gui.tables;

import java.awt.event.ActionEvent;

import narrationmanager.model.RelationData;
import narrationmanager.model.NarrationDate;

public class RelationEditionTable extends EditionTable<RelationData>
{
  public static final int COLUMNS_COUNT=4;
  public static final int COLUMN_PREF_WIDTH=200;
  
  public static final int TYPE_COLUMN=0;
  public static final int TARGET_NAME_COLUMN=1;
  public static final int START_DATE_COLUMN=2;
  public static final int END_DATE_COLUMN=3;

  public RelationEditionTable(boolean editable)
  {
    super(editable);
    
    createDefaultRenderers();
    createDefaultEditors();
    
    getColumnModel().getColumn(TYPE_COLUMN).setPreferredWidth(COLUMN_PREF_WIDTH);
    getColumnModel().getColumn(TARGET_NAME_COLUMN).setPreferredWidth(COLUMN_PREF_WIDTH);
    getColumnModel().getColumn(START_DATE_COLUMN).setPreferredWidth(COLUMN_PREF_WIDTH);
    getColumnModel().getColumn(END_DATE_COLUMN).setPreferredWidth(COLUMN_PREF_WIDTH);
  }
  
  public Class<?> getColumnClass(int column)
  {
    switch(column)
    {
      case TYPE_COLUMN: return String.class;
      case TARGET_NAME_COLUMN: return String.class;
      case START_DATE_COLUMN: return NarrationDate.class;
      case END_DATE_COLUMN: return NarrationDate.class;
      default: return null;
    }
  }
  
  public String getColumnName(int column)
  {
    switch(column)
    {
      case TYPE_COLUMN: return "Relation type";
      case TARGET_NAME_COLUMN: return "Target character name";
      case START_DATE_COLUMN: return "Start date";
      case END_DATE_COLUMN: return "End date";
      default: return null;
    }	  
  }
  
  public Object getValueAt(int row,int column)
  {
    RelationData target=editionModel.getRowElement(row);
    
    switch(column)
    {
      case TYPE_COLUMN: return target.toString();
      case TARGET_NAME_COLUMN: return target.getTargetCharacterName();
      case START_DATE_COLUMN: return target.getStart();
      case END_DATE_COLUMN: return target.getEnd();
      default: return null;
    }  
  }
  
  public void setValueAt(Object value,int row,int column)
  {
    RelationData target=editionModel.getRowElement(row);
    
    switch(column)
    {
      case TYPE_COLUMN: 
        String newType=value.toString();
        boolean isTarget=newType.contains(RelationData.INVERSE_NAME_TAG);
        target.setIsTarget(isTarget);
      	target.setRelationName(newType.replace(RelationData.INVERSE_NAME_TAG,""));
      	break;
      case START_DATE_COLUMN: target.setStart((NarrationDate) value); break;
      case END_DATE_COLUMN: target.setEnd((NarrationDate) value); break; 
    }  
  }
  
  public boolean isCellEditable(int row,int column)
  { 	  
    return editable && column!=TARGET_NAME_COLUMN;
  }
  
  public int getColumnCount()
  {
    return COLUMNS_COUNT;	  
  }
}
