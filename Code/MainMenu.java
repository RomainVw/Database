package narrationManager.gui;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.GridLayout;

import java.awt.event.ActionEvent;

import narrationManager.controller.Controller;

public class MainMenu extends JFrame
{
  private JButton createCharacter=new JButton("Create new character");
  private JButton createEvent=new JButton("Create new event");
  private JButton createPlace=new JButton("Create place");
  
  private Controller controller;
	
  public MainMenu(Controller controller)
  {
    super("Narrationn manager");
    
    this.controller=controller;
    
    //Traitement du panel main
    JPanel main=new JPanel(new GridLayout(3,1));
    main.add(createCharacter);
    main.add(createEvent);
    main.add(createPlace);
    
    //Ajout des listeners
    createCharacter.addActionListener((ActionEvent e)->createCharacterClicked());
    createEvent.addActionListener((ActionEvent e)->createEventClicked());
    createPlace.addActionListener((ActionEvent e)->createPlaceClicked());
    
    //Fignolage
    add(main);
    setLocationRelativeTo(null);
    pack();
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }
  
  private void createCharacterClicked()
  {
    controller.createCharacter();
  }
  
  private void createEventClicked()
  {
  	  
  }
  
  private void createPlaceClicked()
  {
  	  
  }
  
}
