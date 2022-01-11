package gui;
import game.*;
import observer.*;
import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.border.Border;
import java.awt.GraphicsEnvironment;

/**
 * Fenetre représentant le jeu
 */

public class Fenetre extends JFrame implements EcouteurModel,ActionListener {
    private Grille grille; 
    private JPanel cp; 
    private JButton recommencer = new JButton("Recommencer");
    public Fenetre(Grille grille)
    {
        super("Kakuro");  // nom de la fenetre 
        this.grille=grille;
        this.grille.ajoutEcouteur(this);
        //Taille fenetre et quitter fenetre quand cliquer 
        this.setSize(1200, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        //Centrer la fentre 
        this.setLocationRelativeTo(null); 

        //Ajout des différentes vue
        CreationElementFenetre();

        this.setVisible(true); 
    }

    @Override
    public void modeleMisAjour(Object source){
        System.out.println(grille.toString());

    }

    public void CreationElementFenetre(){
        cp=(JPanel) this.getContentPane();  
        cp.setLayout(new BorderLayout()); 
        //VUE GRAPHIQUE ET AJOUT VUE À FENETRE //
        VueGraphique vue = new VueGraphique(this.grille); 
        cp.add(vue,BorderLayout.CENTER);
        recommencer.addActionListener(this);
        cp.add(recommencer,BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
            //Prends la valeur texte du bouton pour tester si plusieurs bouton le quelle a été selectionné
            JButton Bsrc= (JButton) e.getSource(); 
            String BoutonPresse= Bsrc.getText();  
            if(BoutonPresse.equalsIgnoreCase("recommencer")){

                //VUE GRAPHIQUE ET AJOUT VUE À FENETRE //
                this.grille.retraitEcouteur(this);
                this.grille=new Grille();
                this.grille.ajoutEcouteur(this);
                cp.removeAll();
                CreationElementFenetre();
                cp.updateUI();
            }

    }
      
}