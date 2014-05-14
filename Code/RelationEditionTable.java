package narrationmanager.gui.tables;

import java.awt.event.ActionEvent;

import narrationmanager.model.RelationData;

public class RelationEditionTable extends EditionTable<RelationData>
{
  public static final int COLUMNS_COUNT=2;
  
  public static final int TYPE_COLUMN=0;
  public static final int TARGET_NAME_COLUMN=1;

  public RelationEditionTable(boolean editable)
  {
    super(new EditionTableModel<RelationData>(),editable);
    createDefaultRenderers();
    createDefaultEditors();
  }
  
  public Class<?> getColumnClass(int column)
  {
    switch(column)
    {
      case TYPE_COLUMN: return String.class;
      case TARGET_NAME_COLUMN: return String.class;
      default: return null;
    }
  }
  
  public String getColumnName(int column)
  {
    switch(column)
    {
      case TYPE_COLUMN: return "Relation type";
      case TARGET_NAME_COLUMN: return "Target character name";
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
      default: return null;
    }  
  }
  
  public int getColumnCount()
  {
    return COLUMNS_COUNT;	  
  }
}
