package narrationmanager.gui.util;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Container;
import java.awt.Window;

import java.awt.event.ActionEvent;

import java.util.Collection;
import java.util.LinkedList;

import java.util.function.Supplier;

import narrationmanager.gui.tables.EditionTable;


/**
A class represention a Panel containing an EditionTable and two buttons:
one to remove elements from it, the other one to insert some.

@author Baugnies Benjamin
@author Colson Olivier
@author Vanwelde Romain
**/
public class EditionTablePanel<T> extends JPanel
{
  private EditionTable<T> editionTable;
	
  private JButton insertButton=new JButton("Insert...");
  private JButton removeButton=new JButton("Remove selected lines");
	
  public EditionTablePanel(Collection<T> content,Supplier<T> insertFunction,EditionTable<T> editionTable)
  {
    this.editionTable=editionTable;
    
    editionTable.fillWith(content);
    
    //Panels creation
    JPanel mainPanel=new JPanel(new BorderLayout());
    JPanel buttonsPanel=new JPanel(new GridLayout(1,2));
    JPanel tablePanel=new JPanel(new BorderLayout());
    
    //Treatment of buttonsPanel
    buttonsPanel.add(insertButton);
    buttonsPanel.add(removeButton);
    
    //Treatment of tablePanel    
    tablePanel.add("North",editionTable.getTableHeader());
    tablePanel.add("Center",editionTable);
    
    //Treatment of action listeners:
    insertButton.addActionListener((ActionEvent e)-> insertElement(insertFunction));
    removeButton.addActionListener((ActionEvent e)-> removeSelectedElements());
    
    //Treatment of mainPanel
    mainPanel.add("Center",tablePanel);
    mainPanel.add("South",buttonsPanel);
    
    add(mainPanel);
  }
  
  private void insertElement(Supplier<T> insertFunction)
  {
    T toAdd=insertFunction.get();
    if(toAdd!=null) 
    {
      editionTable.addElement(toAdd);
      packParent();
    }
  }
  
  private void removeSelectedElements()
  {
    for(T element:editionTable.getSelectedElements())
    {
      editionTable.removeElement(element);	    
    }
    
    packParent();
  }
  
  public Collection<T> getTableContent()
  {
    return editionTable.getContent();	  
  }
  
  public void fillTableWith(Collection<T> collection)
  {
    editionTable.fillWith(collection);	  
  }
  
  private void packParent()
  {
    Container parent=getParent();
    
    while(! (parent instanceof Window))
    {
      parent=parent.getParent();	    
    }
    
    ((Window)parent).pack();
  }
}
