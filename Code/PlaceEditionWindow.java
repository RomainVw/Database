package narrationmanager.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.BorderLayout;

import java.awt.event.ActionEvent;

import java.util.Vector;

import narrationmanager.gui.util.EditionWindow;

import narrationmanager.gui.tables.EventsTable;

import narrationmanager.model.PlaceModel;
import narrationmanager.model.EventModel;

import narrationmanager.controller.Controller;

public class PlaceEditionWindow extends EditionWindow<PlaceModel>
{
  private JLabel nameLabel;
  
  private JButton applyButton;
  private JButton cancelButton;
  
  private JTextField nameField;
  
  public PlaceEditionWindow(Controller controller)
  {
   super(controller); 
  }
  
  protected void buildUI()
  {
    applyButton = new JButton("Apply");
    cancelButton = new JButton("Cancel");
    nameLabel = new JLabel("Place Name:");
    nameField = new JTextField();
    
    JPanel buttons = new JPanel(new GridLayout(1, 2));
    JPanel namePanel = new JPanel(new GridLayout(1, 2));
    
    namePanel.add(nameLabel);
    namePanel.add(nameField);
    
    buttons.add(cancelButton);
    buttons.add(applyButton);
    
    nameField.addActionListener((ActionEvent e) -> textEntered());
    applyButton.addActionListener((ActionEvent e) -> applyActionPerformed());
    cancelButton.addActionListener((ActionEvent e)-> cancelActionPerformed());
    
    add("North", namePanel);
    add("South", buttons);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }
  
  private void textEntered()
  {
    System.out.println(nameField.getText());
    editionTarget.setName(nameField.getText());
  }
  
  private void applyActionPerformed()
  {
    setExitOption(OK_EXIT_OPTION);
    dispose();
  }
  
  private void cancelActionPerformed()
  {
    setExitOption(CANCEL_EXIT_OPTION);
    dispose();
  }
  
  protected PlaceModel getDefaultTargetInstance()
  {
    return PlaceModel.defaultInstance();
  }
  
  protected String getWindowTitle()
  {
    return "Place editor";
  }
  
  
  
}
