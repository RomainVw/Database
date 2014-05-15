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
  private JButton editCharacter=new JButton("Edit character");
  private JButton createEvent=new JButton("Create new event");
  private JButton editEvent=new JButton("Edit event");
  private JButton createPlace=new JButton("Create place");
  private JButton editPlace = new JButton("Edit Place");
  
  private Controller controller;
	
  public MainMenu(Controller controller)
  {
    super("Narration manager");
    
    this.controller=controller;
    
    //Traitement du panel main
    JPanel main=new JPanel(new GridLayout(6,1));
    main.add(createCharacter);
    main.add(editCharacter);
    main.add(createEvent);
    main.add(editEvent);
    main.add(createPlace);
    main.add(editPlace);
    
    //Ajout des listeners
    createCharacter.addActionListener((ActionEvent e)->controller.createCharacter());
    editCharacter.addActionListener((ActionEvent e)->controller.editCharacter());
    createEvent.addActionListener((ActionEvent e)->controller.createEvent());
    editEvent.addActionListener((ActionEvent e)->controller.editEvent());
    createPlace.addActionListener((ActionEvent e)->controller.createPlace());
    editPlace.addActionListener((ActionEvent e)->controller.editPlace());
    
    //Fignolage
    add(main);
    pack();
    setLocationRelativeTo(null);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
  }
}
