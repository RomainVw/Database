package narrationmanager.gui.tables;

import javax.swing.JTable;

import java.util.Collection;

public abstract class EditionTable<T> extends JTable
{
  protected EditionTableModel<T> editionModel;
  protected boolean editable;
	
  public EditionTable(EditionTableModel<T> editionModel, boolean editable)
  {
    super(editionModel);
    this.editionModel=editionModel;
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
}
