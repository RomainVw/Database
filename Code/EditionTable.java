package narrationmanager.gui.tables;

import javax.swing.JTable;

import java.util.Collection;
import java.util.LinkedList;

/**
An abstract class easing the extension of JTable.

@author Baugnies Benjamin
@author Colson Olivier
@author Vanwelde Romain
**/
public abstract class EditionTable<T> extends JTable
{
  protected EditionTableModel<T> editionModel;
  protected boolean editable;
	
  public EditionTable(boolean editable)
  {
    super();
    editionModel=new EditionTableModel<>();
    editionModel.setEditionTable(this);
    setModel(editionModel);
    this.editable=editable;
  }
  
  public void fillWith(Collection<T> content)
  {
    editionModel.fillWith(content);
  }
  
  public void addElement(T toAdd)
  {
    editionModel.addElement(toAdd);	  
  }
  
  public void removeElement(T toRemove)
  {
    editionModel.removeElement(toRemove);
  }
  
  public Collection<T> getContent()
  {
    return editionModel.cloneContent();	  
  }
  
  public LinkedList<T> getSelectedElements()
  {
    LinkedList<T> rslt=new LinkedList<>();
    
    for(int row:getSelectedRows())
    {
      rslt.add(editionModel.getRowElement(row));	    
    }
    
    return rslt;
  }
  
  public void refresh()
  {
    editionModel.fireTableDataChanged();	  
  }
  
  public boolean isCellEditable(int row, int column)
  {    
    return editable;
  }
}
