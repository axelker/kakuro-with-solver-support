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



public class Fenetre extends JFrame implements ActionListener  {
    private Grille grille; 
    private JPanel cp; 
    public Fenetre(Grille grille)
    {
        super("Kakuro");  // nom de la fenetre 
        this.grille=grille;
        //Taille fenetre et quitter fenetre quand cliquer 
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        //Centrer la fentre 
        this.setLocationRelativeTo(null); 

        cp=(JPanel) this.getContentPane();  
        cp.setLayout(new GridLayout(1,2)); // DECOUPE LE PANEL EN 3 LIGNE / 1 COLONNE

        //VUE GRAPHIQUE ET AJOUT VUE À FENETRE //
        VueGraphique vue = new VueGraphique(grille); 
        cp.add(vue);

        /*// ajout d'un bouton // 
        JPanel jPanel2=new JPanel(); //NE PAS TOUCHER 
        jPanel2.setLayout(null);//NE PAS TOUCHER 
        JButton bouton = new JButton("Nom du bouton"); // Création d'un bouton
        bouton.setBounds(40, 30, 170, 50); // Position x par rapport à l'angle haut, 
        //Position y par rapport à l'angle haut et largeur et hauteur du composant

        //Ajouter FENETRE  en ECOUTE//
        bouton.addActionListener(this); //NE PAS TOUCHER (APPART SI NOM VARIABLE BOUTON EST DIFFERENT)
        jPanel2.add(bouton);    //NE PAS TOUCHER 
        cp.add(jPanel2); //NE PAS TOUCHER */


        this.setVisible(true); 
    }
  
// Redefinition du clique bouton OBLIGATOIRE
    @Override
    public void actionPerformed(ActionEvent e)
    {
        
            //Prends la valeur texte du bouton pour tester si plusieurs bouton le quelle a été selectionné
            JButton Bsrc= (JButton) e.getSource(); //NE PAS TOUCHER 
            String BoutonPresse= Bsrc.getText(); //NE PAS TOUCHER 
            //SI LE TEXTE RECUPEERER EST CELUI DU BOUTON FAIRE ACTION
            if(BoutonPresse.equals("v"))  
            {
                //Afficher les domaine sur la droite
            }
               
    }

    
}