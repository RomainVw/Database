package narrationmanager.gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;

import narrationmanager.controller.Controller;

public class MainMenu extends JFrame
{
  private JButton createCharacter=new JButton("Create new character");
  private JButton createEvent=new JButton("Create new event");
  private JButton editEvent=new JButton("Edit event");
  private JButton createPlace=new JButton("Create place");
  
  private Controller controller;
	
  public MainMenu(Controller controller)
  {
    super("Narration manager");
    
    this.controller=controller;
    
    //Traitement du panel main
    JPanel main=new JPanel(new GridLayout(4,1));
    main.add(createCharacter);
    main.add(createEvent);
    main.add(editEvent);
    main.add(createPlace);
    
    //Ajout des listeners
    createCharacter.addActionListener((ActionEvent e)->createCharacterClicked());
    createEvent.addActionListener((ActionEvent e)->createEventClicked());
    editEvent.addActionListener((ActionEvent e)->editEventClicked());
    createPlace.addActionListener((ActionEvent e)->createPlaceClicked());
    
    //Fignolage
    add(main);
    pack();
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }
  
  private void createCharacterClicked()
  {
    controller.createCharacter();
  }
  
  private void createEventClicked()
  {
    controller.createEvent();	  
  }
  
  private void editEventClicked()
  {
    controller.editEvent();	  
  }
  
  private void createPlaceClicked()
  {
  	  
  }
  
}
