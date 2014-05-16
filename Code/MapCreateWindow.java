package narrationmanager.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionEvent;

import java.util.Vector;
import java.util.Map;
import java.util.ArrayList;

import narrationmanager.gui.util.EditionWindow;

import narrationmanager.model.PlaceModel;
import narrationmanager.model.EventModel;
import narrationmanager.model.MapModel;

import narrationmanager.controller.Controller;

public class MapCreateWindow extends EditionWindow<MapModel>
{ 
  private JLabel question;
  private JLabel lenLabel;
  private JLabel widLabel;
  private JLabel lenSLabel;
  private JLabel widSLabel;
  
  private JTextField lenField;
  private JTextField widField;
  private JTextField lenSField;
  private JTextField widSField;
  
  private JButton applyButton;
  private JButton cancelButton;
  
  public MapCreateWindow(Controller controller)
  {
    super(controller);
  }
  
  protected void buildUI() 
  { 
    applyButton = new JButton("Yes");
    cancelButton = new JButton("No");
    
    lenField = new JTextField();
    widField = new JTextField();
    lenSField = new JTextField();
    widSField = new JTextField();
    
    question = new JLabel("Add a map to the new place?");
    lenLabel= new JLabel("Length in squares:");
    widLabel= new JLabel("Height in squares:");
    lenSLabel= new JLabel("Length of a square:");
    widSLabel= new JLabel("Height of a square:");
    
    applyButton.addActionListener((ActionEvent e) -> applyActionPerformed());
    cancelButton.addActionListener((ActionEvent e)-> cancelActionPerformed());
    
    JPanel applyCancel = new JPanel(new GridLayout(1, 2));
    applyCancel.add(applyButton);
    applyCancel.add(cancelButton);
    
    lenField.addActionListener((ActionEvent e)-> numLengthEntered());
    widField.addActionListener((ActionEvent e)-> numWidthEntered());
    lenSField.addActionListener((ActionEvent e)-> lenEntered());
    widSField.addActionListener((ActionEvent e)-> widEntered());
    
    JPanel panel = new JPanel(new GridLayout(4, 2));
    
    panel.add(lenLabel);
    panel.add(lenField);
    panel.add(widLabel);
    panel.add(widField);
    panel.add(lenSLabel);
    panel.add(lenSField);
    panel.add(widSLabel);
    panel.add(widSField);
    
    JPanel questionP  = new JPanel(new GridLayout(1, 1));
    questionP.add(question);
    
    add("North", questionP);
    add("Center", panel);
    add("South", applyCancel);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
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
  
  public void numLengthEntered()
  {
   editionTarget.setNumLength(Integer.parseInt(lenField.getText()));
  }
  
  public void numWidthEntered()
  {
   editionTarget.setNumWidth(Integer.parseInt(widField.getText())); 
  }
  
  public void lenEntered()
  {
   editionTarget.setLength(Float.parseFloat(lenSField.getText()));
  }
  
  public void widEntered()
  {
   editionTarget.setWidth(Float.parseFloat(widSField.getText())); 
  }
  
  
  protected MapModel getDefaultTargetInstance()
  {
    return MapModel.defaultInstance();
  }
  
  protected String getWindowTitle()
  {
    String s = "Create map";
    return s;	  
  }
  
  protected MapModel getDefaultInstance()
  {
    return MapModel.defaultInstance();
  }
}
