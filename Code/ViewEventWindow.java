package narrationManager.gui;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;

import java.util.Vector;

import narrationManager.gui.tables.EventsTable;

import narrationManager.model.PlaceModel;
import narrationManager.model.EventModel;

import narrationManager.controller.Controller;

public class ViewEventWindow extends JDialog
{
  public static final String DEFAULT_EVENT_NAME="New event";
	
  private Controller controller;
  
  private EventModel editedEvent;
  
  private JLabel placeLabel=new JLabel("Event's place:");
  
  private JButton applyButton=new JButton("Apply");
  private JButton cancelButton=new JButton("Cancel");
  
  private JComboBox<PlaceModel> placeChooser;
  
  private EventsTable editorTable=new EventsTable(true);
  
  public ViewEventWindow(Controller controller)
  {
    super((Dialog)null,"Event editor",true);
    this.controller=controller;
    editedEvent=EventModel.defaultInstance();
    buildUI();
  }
  
  public ViewEventWindow(Controller controller,EventModel toEdit)
  {
    super((Dialog)null,"Event editor",true);
    this.controller=controller;
    editedEvent=toEdit; //TODO: gérer l'édition!!
    buildUI();
  }
  
  private void buildUI()
  {    
    editorTable.addEvent(editedEvent);
    
    //Création des Panels
    JPanel tablePanel=new JPanel(new BorderLayout());
    JPanel placeInfo=new JPanel(new GridLayout(1,2));
    JPanel buttons=new JPanel(new GridLayout(1,2));
    
    //Gestion de tablePanel
    tablePanel.add("North",editorTable.getTableHeader());
    tablePanel.add("Center",editorTable);
    //Gestion de placeInfo
    Vector<PlaceModel> places=new Vector<>(controller.getAllPlaces());
    placeChooser=new JComboBox<>(places);
    
    placeInfo.add(placeLabel);
    placeInfo.add(placeChooser);
    
    //Gestion de buttons
    buttons.add(cancelButton);
    buttons.add(applyButton);
    
    //Ajout des ActionListeners;
    applyButton.addActionListener((ActionEvent e)-> okActionPerformed());
    cancelButton.addActionListener((ActionEvent e)-> cancelActionPerformed());
    placeChooser.addActionListener((ActionEvent e)-> placeChooserActionPerformed());
    
    //Fignolage
    add("North",placeInfo);
    add("Center",tablePanel);
    add("South",buttons);
    
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }
  
  private void okActionPerformed()
  {//Mettre le résultat du dialog à ok
  }
  
  private void cancelActionPerformed()
  {//metre le résultat du dialog à pas ok
  }
  
  private void placeChooserActionPerformed()
  {
    
  }
}
