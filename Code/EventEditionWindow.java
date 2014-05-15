package narrationmanager.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;

import java.util.Vector;

import narrationmanager.gui.util.EditionWindow;

import narrationmanager.gui.tables.EventEditionTable;

import narrationmanager.model.PlaceModel;
import narrationmanager.model.EventModel;

import narrationmanager.controller.Controller;

public class EventEditionWindow extends EditionWindow<EventModel>
{ 
  private JLabel placeLabel;
  
  private JButton applyButton;
  private JButton cancelButton;
  
  private JComboBox<PlaceModel> placeChooser;
  
  private EventEditionTable editorTable;
 
  
  public EventEditionWindow(Controller controller)
  {
    super(controller);
  }
  
  public EventEditionWindow(Controller controller,EventModel toEdit)
  {
    super(controller,toEdit);
  }
  
  protected void buildUI() //TODO: comment in English!
  {    
    editorTable=new EventEditionTable(true);
    applyButton=new JButton("Apply");
    cancelButton=new JButton("Cancel");
    placeLabel=new JLabel("Event's place:");
  
    //Gestion de la table
    editorTable.addElement(editionTarget);
    
    //Gestion de placeChooser
    Vector<PlaceModel> places=new Vector<>(controller.getAllPlaces());
    placeChooser=new JComboBox<>(places);
    
    PlaceModel targetPlace=editionTarget.getEventPlace();
    if(targetPlace!=null) placeChooser.setSelectedItem(targetPlace);
    else 
    {
      placeChooser.setSelectedIndex(0);
      placeChooserActionPerformed();
    }
    
    //Création des Panels
    JPanel tablePanel=new JPanel(new BorderLayout());
    JPanel placeInfo=new JPanel(new GridLayout(1,2));
    JPanel buttons=new JPanel(new GridLayout(1,2));
    
    //Gestion de tablePanel
    tablePanel.add("North",editorTable.getTableHeader());
    tablePanel.add("Center",editorTable);
    
    //Gestion de placeInfo  
    placeInfo.add(placeLabel);
    placeInfo.add(placeChooser);
    
    //Gestion de buttons
    buttons.add(cancelButton);
    buttons.add(applyButton);
    
    //Ajout des ActionListeners;
    applyButton.addActionListener((ActionEvent e)-> applyActionPerformed());
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
  
  private void applyActionPerformed()
  {//Mettre le résultat du dialog à ok
    setExitOption(OK_EXIT_OPTION);
    dispose();
  }
  
  private void cancelActionPerformed()
  {//mettre le résultat du dialog à pas ok
    setExitOption(CANCEL_EXIT_OPTION);
    dispose();
  }
  
  private void placeChooserActionPerformed()
  {
    PlaceModel selected=placeChooser.getItemAt(placeChooser.getSelectedIndex());  
    editionTarget.setEventPlace(selected);
  }
  
  protected EventModel getDefaultTargetInstance()
  {
    return EventModel.defaultInstance();
  }
  
  protected String getWindowTitle()
  {
    return "Event editor";	  
  }
}
