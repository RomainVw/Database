package narrationmanager.gui;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.event.ActionEvent;

import java.util.Vector;
import java.util.Map;
import java.util.ArrayList;

import narrationmanager.gui.util.EditionWindow;

import narrationmanager.gui.tables.EventsTable;

import narrationmanager.model.PlaceModel;
import narrationmanager.model.EventModel;
import narrationmanager.model.MapModel;

import narrationmanager.controller.Controller;

public class MapChoiceWindow extends EditionWindow<MapModel>
{ 
  private PlaceModel parent;
  private PlaceModel child;
  
  private JButton applyButton;
  private JButton cancelButton;
  
  int output = -1;
  
  ArrayList<JButton> buttons;
  
  public MapChoiceWindow(Controller controller, MapModel map)
  {
    super(controller, map);
  }
  
  protected void buildUI() //TODO: comment in English!
  { 
    buttons = new ArrayList<JButton>();
    applyButton = new JButton("Apply");
    cancelButton = new JButton("Cancel");
    
    applyButton.addActionListener((ActionEvent e) -> applyActionPerformed());
    cancelButton.addActionListener((ActionEvent e)-> cancelActionPerformed());
    
    JPanel applyCancel = new JPanel(new GridLayout(1, 2));
    applyCancel.add(applyButton);
    applyCancel.add(cancelButton);
    
    
    GridLayout grid = new GridLayout(editionTarget.getNumLength(), editionTarget.getNumWidth());
    JPanel panel = new JPanel(grid);
    Map<String, Integer> subplaces = editionTarget.getSubplaceID();
    
    
    int index = 0;
    for (int i = 0; i < editionTarget.getNumWidth(); i++)
    {
      for (int j = 0; j < editionTarget.getNumLength(); j ++)
      {
        index = j + i*editionTarget.getNumLength();
        if (buttons == null)
        buttons.add(index, new JButton());
        panel.add(buttons.get(index));
        if (subplaces!=null && subplaces.containsValue(j+(i*editionTarget.getNumLength())))
        {
          buttons.get(index).setBackground(Color.RED);
        }
        else
        {
          final int finalInt=index;
          buttons.get(index).addActionListener((ActionEvent e) -> spotClicked(finalInt));
        }
      }
    }
    
    add("North", panel);
    add("South", applyCancel);
    pack();
    setLocationRelativeTo(null);
    setVisible(true);
  }
  
  private void applyActionPerformed()
  {
    setExitOption(output);
    dispose();
  }
  
  private void cancelActionPerformed()
  {
    setExitOption(CANCEL_EXIT_OPTION);
    dispose();
  }
  
  private void spotClicked(int index)
  {
    output = index;
    buttons.get(index).setBackground(Color.BLUE);
  }
  
  protected MapModel getDefaultTargetInstance()
  {
    return null;
  }
  
  protected String getWindowTitle()
  {
    String s = "Choose Location";
    return s;	  
  }
}
