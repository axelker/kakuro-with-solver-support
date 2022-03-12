package gui;
import game.*;
import observer.*;
import src.Constante;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;


public class Menu extends JPanel {
    private JButton NouvellePartie= new JButton();
    private JButton Regle = new JButton();
    private JLabel menu = new JLabel(new ImageIcon("image/Menu-Kakuro.png"));
    private Color background = Constante.backgroundColor;
    private MenuBar menuBar;

    private Fenetre fenetre;

    public Menu(Fenetre fenetre){
        this.fenetre=fenetre;
        this.setLayout(null);
        //AJout menubar
        this.menuBar=new MenuBar(fenetre);
        this.menuBar.setBounds(0, 0, 1200, 20);
        this.add(menuBar);

        //AjoutBouton et config nouvelle partie
        this.NouvellePartie.setBounds(400, 245, 450, 110);
        this.NouvellePartie.setOpaque(false);
        this.NouvellePartie.setBorderPainted(false);
        this.NouvellePartie.setContentAreaFilled(false);
        this.add(this.NouvellePartie);
        //AJout et config  bouton regle
        this.Regle.setBounds(400, 435, 450, 110);
        this.Regle.setOpaque(false);
        this.Regle.setBorderPainted(false);
        this.Regle.setContentAreaFilled(false);
        this.add(this.Regle);

        //Background
        this.setBackground(background);
        //AJout menu image
        menu.setBounds(0, 0, 1200, 600);
        this.add(menu);

        //Mise sur ecoute boutton
        this.listnerBouton();
       
    }

    public void listnerBouton(){
        this.NouvellePartie.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                fenetre.CreationElementFenetre();
            }
        });
        this.Regle.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                fenetre.CreationRegle();
            }
        });
    }



}