package narrationmanager.gui.tables;

import javax.swing.table.AbstractTableModel;

import java.util.Vector;
import java.util.Collection;

public class EditionTableModel<T> extends AbstractTableModel
{
  protected Vector<T> content=new Vector<>();
  
  private EditionTable<T> ownerTable;

  public EditionTableModel()
  {
    super();	  
  }
  
  public synchronized void fillWith(Collection<T> collection)
  {
    content=new Vector<>(collection);
    fireTableDataChanged();
  }
  
  public synchronized void addElement(T toAdd)
  {
    content.add(toAdd);
    fireTableDataChanged();
  }
  
  public synchronized void removeElement(T toRemove)
  {
    content.remove(toRemove);	 
    fireTableDataChanged();
  }
  
  public synchronized Vector<T> cloneContent()
  {
    return (Vector<T>)content.clone();	  
  }
  
  public int getRowCount()
  {
    return content.size();	  
  }
  
  public synchronized T getRowElement(int row)
  {
    return content.get(row);	  
  }
  
  public synchronized Object getValueAt(int row, int col)
  {
    //return "titi"; //TODO: OK, appelle celui de la table?
    return ownerTable.getValueAt(row,col);
  }
  
  public int getColumnCount()
  {
    //return 23; //TODO
    return ownerTable.getColumnCount();
  }
  
  public void setEditionTable(EditionTable<T> table)
  {
    ownerTable=table;	  
  }
  
  public synchronized String getColumnName(int column)
  {
    return ownerTable.getColumnName(column);	  
  }
  
  public void setValueAt(Object value,int row,int col)
  {
    ownerTable.setValueAt(value,row,col);	  
  }
}
