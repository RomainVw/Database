package narrationmanager.gui;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;

import narrationmanager.controller.Controller;

import narrationmanager.model.CharacterModel;
import narrationmanager.model.RelationData;
import narrationmanager.model.PlaceModel;

import narrationmanager.gui.util.EditionWindow;
import narrationmanager.gui.util.EditionTablePanel;

import narrationmanager.gui.tables.RelationEditionTable;

import java.util.function.Function;

import java.util.Vector;


public class CharacterEditionWindow extends EditionWindow<CharacterModel>
{
  public static final String TAB1_TABLE_CHOOSER_OPT1="Sort relations by type";
  public static final String TAB1_TABLE_CHOOSER_OPT2="Sort relations by target character";
	
  //Tab 1
  private JTextField tab1NameField;
  private JComboBox<PlaceModel> tab1BirthPlaceChooser;
  private JComboBox<String> tab1TableSorter;
  private EditionTablePanel tab1TablePanel;
  
  //Global buttons
  private JButton applyButton;
  private JButton cancelButton;
	
  public CharacterEditionWindow(Controller controller)
  {
    super(controller);
  }
  
  public CharacterEditionWindow(Controller controller,CharacterModel toEdit)
  {
    super(controller,toEdit);
  }
  
  protected void buildUI()
  {
    JTabbedPane tabs=new JTabbedPane();	  
    
    //Initialization
    tab1NameField=new JTextField();
    tab1BirthPlaceChooser=new JComboBox<>(new Vector<PlaceModel>(controller.getAllPlaces()));
    tab1TableSorter=new JComboBox<>();
    applyButton=new JButton("Apply");
    cancelButton=new JButton("Cancel");
    
    //Treatment of edition target
    RelationEditionTable tab1Table=new RelationEditionTable(true);
    tab1TablePanel=new EditionTablePanel<RelationData>(editionTarget.getRelationsByType(),this::getRelationToInsert,tab1Table,this::editRelation);
    tab1NameField.setText(editionTarget.getName());
    
    String birthPlaceID=editionTarget.getBirthPlace();
    if(birthPlaceID!=null)  tab1BirthPlaceChooser.setSelectedItem(controller.loadPlace(birthPlaceID));
    else  tab1BirthPlaceChooser.setSelectedIndex(0);
    
    //Panels
    JPanel tab1=new JPanel(new BorderLayout());
    JPanel buttonsPanel=new JPanel(new GridLayout(1,2));
    JPanel tab1Header=new JPanel(new GridLayout(3,2));
    
    //Treatment of tab1Header
    tab1Header.add(new JLabel("Name:"));
    tab1Header.add(tab1NameField);
    tab1Header.add(new JLabel("Origin:"));
    tab1Header.add(tab1BirthPlaceChooser);
    tab1Header.add(new JLabel("Relations:"));
    
    //Treatment of buttonsPanel
    buttonsPanel.add(cancelButton);
    buttonsPanel.add(applyButton);
    
    //Treatment of tab1TableSorter
    tab1TableSorter.addItem(TAB1_TABLE_CHOOSER_OPT1);
    tab1TableSorter.addItem(TAB1_TABLE_CHOOSER_OPT2);    
    tab1TableSorter.setSelectedIndex(0);

    //Treatment of tab1
    tab1.add("North",tab1Header);
    tab1.add("Center",tab1TablePanel);
    tab1.add("South",tab1TableSorter);
    
    //Treatment of action listeners
    applyButton.addActionListener((ActionEvent e)->applyActionPerformed());
    cancelButton.addActionListener((ActionEvent e)->cancelActionPerformed());
    tab1TableSorter.addActionListener((ActionEvent e)->tab1TableSorterActionPerformed());
    
    //Treatment of tabs
    tabs.add(tab1,0);
    tabs.setTitleAt(0,"Summary");
    setLayout(new BorderLayout());
    add("North",tabs);
    add("Center",buttonsPanel);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }
  
  protected CharacterModel getDefaultTargetInstance()
  {
    return CharacterModel.getDefaultInstance();
  }
  
  protected String getWindowTitle()
  {
    return "Character editor";	  
  }
  
  private RelationData getRelationToInsert()
  {     
    CharacterModel[] choices=controller.getAllCharactersInArray();
    
    CharacterModel target=null;
    
    if(choices.length>0)
      target=(CharacterModel) JOptionPane.showInputDialog(null,"Please select the target of this relation.","Add relation...",JOptionPane.QUESTION_MESSAGE,null,choices,choices[0]);
    else JOptionPane.showMessageDialog(this,"Error: no relation can be added, as there is currently no character available in database","Error",JOptionPane.ERROR_MESSAGE);
    
    if(target!=null)
    {
      String relationName=JOptionPane.showInputDialog("Please enter the new relation's name");	
      
      if(relationName!=null) return new RelationData(relationName,target.getID(),true);  
    }
    
    return null;
  }
  
  private void editRelation(RelationData toEdit)
  {
    String newName=JOptionPane.showInputDialog("Please enter this relation's new type",toEdit.getRelationName());
    
    if(newName!=null) toEdit.setRelationName(newName);
  }
  
  private void applyActionPerformed()
  {
    editionTarget.setName(tab1NameField.getText());
    editionTarget.setRelations(tab1TablePanel.getTableContent());
    editionTarget.setBirthPlace(((PlaceModel)tab1BirthPlaceChooser.getSelectedItem()).getID());
    
    setExitOption(OK_EXIT_OPTION);
    dispose();
  }
  
  private void cancelActionPerformed()
  {
    setExitOption(CANCEL_EXIT_OPTION);
    dispose();
  }
  
  private void tab1TableSorterActionPerformed()
  {
    switch(tab1TableSorter.getItemAt(tab1TableSorter.getSelectedIndex()))
    {
      case TAB1_TABLE_CHOOSER_OPT1: tab1TablePanel.fillTableWith(editionTarget.getRelationsByType()); break;
      case TAB1_TABLE_CHOOSER_OPT2: tab1TablePanel.fillTableWith(editionTarget.getRelationsByCharacter()); break;
      default: break;
    }
    
  }
}
