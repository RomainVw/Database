package narrationmanager.gui.tables;

import javax.swing.table.AbstractTableModel;

import java.util.Vector;
import java.util.Collection;

public class EditionTableModel<T> extends AbstractTableModel
{
  protected Vector<T> content=new Vector<>();	

  public EditionTableModel()
  {
    super();	  
  }
  
  public void fillWith(Collection<T> collection)
  {
    content=new Vector<>(collection);
    fireTableDataChanged();
  }
  
  public void addElement(T toAdd)
  {
    content.add(toAdd);
  }
  
  public void removeElement(T toRemove)
  {
    content.remove(toRemove);	 
    //fireTableDataChanged(); //TODO??
  }
  
  public Vector<T> cloneContent()
  {
    return (Vector<T>)content.clone();	  
  }
  
  public int getRowCount()
  {
    return content.size();	  
  }
  
  public T getRowElement(int row)
  {
    return content.get(row);	  
  }
  
  public Object getValueAt(int row, int col)
  {
    return "titi"; //TODO: OK, appelle celui de la table?
  }
  
  public int getColumnCount()
  {
    return 23; //TODO	  
  }
}
