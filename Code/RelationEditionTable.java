package narrationmanager.gui.tables;

import javax.swing.JButton;

import java.awt.event.ActionEvent;

import narrationmanager.model.RelationData;

public class RelationEditionTable extends EditionTable<RelationData>
{
  public static final int COLUMNS_COUNT=3;
  
  public static final int TYPE_COLUMN=0;
  public static final int TARGET_NAME_COLUMN=1;
  public static final int EDIT_BUTTON_COLUMN=2;

  public RelationEditionTable(boolean editable)
  {
    super(new EditionTableModel<RelationData>(),editable);
    createDefaultRenderers();
    createDefaultEditors();
  }
  
 //TODO: décommenter quand tu auras régler le souci du TableModel!! 
 /* public Class<?> getColumnClass(int column)
  {
    switch(column)
    {
      case TYPE_COLUMN: return String.class;
      case TARGET_NAME_COLUMN: return String.class;
      case EDIT_BUTTON_COLUMN: return JButton.class;
      default: return null;
    }
  }
  
  public String getColumnName(int column)
  {
    switch(column)
    {
      case TYPE_COLUMN: return "Relation type";
      case TARGET_NAME_COLUMN: return "Target character name";
      case EDIT_BUTTON_COLUMN: return "Edition";
      default: return null;
    }	  
  }
  
  public Object getValueAt(int row,int column)
  {
    RelationData target=editionModel.getRowElement(row);
  	  
    JButton editButton=new JButton("Edit relation");
    editButton.addActionListener((ActionEvent e)->editRelation(target));
    
    switch(column)
    {
      case TYPE_COLUMN: return target.getRelationName();
      case TARGET_NAME_COLUMN: return target.getTargetCharacterName();
      case EDIT_BUTTON_COLUMN: return editButton;
      default: return null;
    }  
  }*/
  
  private void editRelation(RelationData target)
  {
    //TODO
    System.out.println("Papa a vu le fifi de lolo");
  }
}
