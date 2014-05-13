package narrationmanager.gui;

import javax.swing.JTabbedPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.GridLayout;

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
  private JTextField tab1NameField;
  private JComboBox<PlaceModel> tab1BirthPlaceChooser;
  private JComboBox<String> tab1TableSorter;
  private EditionTablePanel tab1TablePanel;
	
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
    
    
    //Treatment of edition target
    RelationEditionTable tab1Table=new RelationEditionTable(true);
    Function<Void,RelationData> insertFun=this::getRelationToInsert;
    tab1TablePanel=new EditionTablePanel(editionTarget.getRelationsByCharacter(),insertFun,tab1Table);
    
    //Panels
    JPanel tab1=new JPanel(new BorderLayout());
    JPanel tab1Header=new JPanel(new GridLayout(3,2));
    JPanel tab1Buttons=new JPanel(new GridLayout(3,1));
    
    //Treatment of tab1Header
    tab1Header.add(new JLabel("Name:"));
    tab1Header.add(tab1NameField);
    tab1Header.add(new JLabel("Origin:"));
    tab1Header.add(tab1BirthPlaceChooser);
    tab1Header.add(new JLabel("Relations:"));
    
    //Treatment of tab1Buttons
    tab1Buttons.add(tab1TableSorter);

    //Treatment of tab1
    tab1.add("North",tab1Header);
    tab1.add("Center",tab1TablePanel);
    
    //Treatment of tabs
    tabs.add(tab1,0);
    tabs.setTitleAt(0,"Summary");
    add(tabs);
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
  
  private RelationData getRelationToInsert(Void v)
  { //TODO
    System.out.println("lulu");
    return null;
  }
}
