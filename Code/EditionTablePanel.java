package narrationmanager.gui.util;

import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;

import java.util.Collection;

import java.util.function.Supplier;
import java.util.function.Consumer;

import narrationmanager.gui.tables.EditionTable;

public class EditionTablePanel<T> extends JPanel
{
  private Collection<T> content;
  
  private JButton insertButton=new JButton("Insert...");
  private JButton removeButton=new JButton("Remove selected lines");
  private JButton editButton=new JButton("Edit line");
	
  public EditionTablePanel(Collection<T> content,Supplier<T> insertFunction,EditionTable<T> editionTable,Consumer<T> editFunction)
  {
    this.content=content;
    
    editionTable.fillWith(content);
    
    //Panels creation
    JPanel mainPanel=new JPanel(new BorderLayout());
    JPanel buttonsPanel=new JPanel(new GridLayout(1,3));
    JPanel tablePanel=new JPanel(new BorderLayout());
    
    //Treatment of buttonsPanel
    buttonsPanel.add(insertButton);
    buttonsPanel.add(removeButton);
    buttonsPanel.add(editButton);
    
    //Treatment of tablePanel
    tablePanel.add("North",editionTable.getTableHeader());
    tablePanel.add("Center",editionTable);
    
    //Treatment of action listeners:
    insertButton.addActionListener((ActionEvent e)-> insertElement(insertFunction));
    removeButton.addActionListener((ActionEvent e)-> removeSelectedElements());
    removeButton.addActionListener((ActionEvent e)-> editElement(editFunction));
    
    //Treatment of mainPanel
    mainPanel.add("Center",tablePanel);
    mainPanel.add("South",buttonsPanel);
    
    add(mainPanel);
  }
  
  private void insertElement(Supplier<T> insertFunction)
  {
    T toInsert=insertFunction.get(); //TODO il comprend apply()?
    //content.add(toInsert);
    //TODO ajouter à la table
  }
  
  private void removeSelectedElements()
  {
    //TODO: retirer de content ce qui est sélectionné dans la table	  
  }
  
  private void editElement(Consumer<T> editFunction)
  {
    //TODO	  
  }
}
