package gui;
import game.*;
import observer.*;
import java.io.*;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.border.Border;
import java.awt.GraphicsEnvironment;



public class Fenetre extends JFrame  {
    private Grille grille; 
    private JPanel cp; 
    public Fenetre(Grille grille)
    {
        super("Kakuro");  // nom de la fenetre 
        this.grille=grille;
        //Taille fenetre et quitter fenetre quand cliquer 
        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        //Centrer la fentre 
        this.setLocationRelativeTo(null); 

        cp=(JPanel) this.getContentPane();  
        cp.setLayout(new GridLayout(1,2)); // DECOUPE LE PANEL EN 1 ligne et 2 colonne

        //VUE GRAPHIQUE ET AJOUT VUE À FENETRE //
        VueGraphique vue = new VueGraphique(grille); 
        cp.add(vue);
        this.setVisible(true); 
    }
      
}