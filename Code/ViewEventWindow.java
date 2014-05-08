package narrationManager.gui;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.Dialog;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;

import java.util.Vector;

import narrationManager.gui.tables.EventsTable;

public class ViewEventWindow extends JDialog
{
  public static final String DEFAULT_EVENT_NAME="New event";
	
  private Controller controller;
  
  private JLabel placeLabel=new JLabel("Event's place:");
  
  private JButton applyButton=new JButton("Apply");
  private JButton cancelButton=new JButton("Cancel");
  
  private JComboBox<PlaceModel> placeChooser;
  
  public ViewEventWindow(Controller controller)
  {
    super((Dialog)null,"Event editor",true);
    this.controller=controller;
    buildUI();
  }
  
  public ViewEventWindow(Controller controller)
  {
    super((Dialog)null,"Event editor",true);
    this.controller=controller;
    buildUI();
  }
  
  private void buildUI()
  {    
    //ajouter la table au Nord
    
    //Création des Panels
    JPanel main=new JPanel();
    JPanel placeInfo=new JPanel(new GridLayout(1,2));
    JPanel buttons=new JPanel(new GridLayout(1,2));
    
    //Gestion de placeInfo
    Vector<PlaceModel> places=new Vector<>(controller.getAllPlaces());
    placeChooser=new JComboBox<>(places);
    
    placeInfo.add(placeLabel);
    placeInfo.add(placeChooser);
    
    //Gestion de buttons
    buttons.add(cancelButton);
    buttons.add(applyButton);
    
    //Ajout des ActionListeners;
    ok.addAtcionLisener((ActionEvent e)-> okActionPerformed());
    cancel.addAtcionLisener((ActionEvent e)-> cancelActionPerformed());
    placeChooser.addAtcionLisener((ActionEvent e)-> placeChooserActionPerformed());
    
    //Fignolage
    add(main);
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
