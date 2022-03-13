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

public class Fenetre extends JFrame implements EcouteurModel {
    private Grille grille; 
    private JPanel cp; 

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
        CreatingMenu();

        this.setVisible(true); 
    }

    @Override
    public void modeleMisAjour(Object source){
        System.out.println(grille.toString());

    }
    //Creation jeu avec grille
    public void CreationElementFenetre(){
        cp=(JPanel) this.getContentPane();  
        cp.setLayout(new BorderLayout()); 
        cp.removeAll();
        //VUE GRAPHIQUE ET AJOUT VUE À FENETRE //
        this.grille.retraitEcouteur(this);
        this.grille=new Grille();
        this.grille.ajoutEcouteur(this);
        cp.add(new MenuBar(this),BorderLayout.NORTH);
        //VUE GRAPHIQUE ET AJOUT VUE À FENETRE //
        VueGraphique vue = new VueGraphique(this.grille,this); 
        cp.add(vue,BorderLayout.CENTER);
        cp.updateUI();

    }
    //Creation panel regle
    public void CreationRegle(){
        cp=(JPanel) this.getContentPane();  
        cp.setLayout(new BorderLayout()); 
        cp.removeAll();
        cp.add(new Regle(this),BorderLayout.CENTER);
        cp.updateUI();

    }
    //Creation menu
    public void CreatingMenu(){
        cp=(JPanel) this.getContentPane();  
        cp.setLayout(new BorderLayout()); 
        cp.removeAll();
        cp.add(new Menu(this),BorderLayout.CENTER);
        cp.updateUI();

    }
    //Creation aide sur les bouton d'aide à la résolution
    public void CreatingAide(){
        cp=(JPanel) this.getContentPane();  
        cp.setLayout(new BorderLayout()); 
        cp.removeAll();
        cp.add(new Aide(this),BorderLayout.CENTER);
        cp.updateUI();
    }

    public void CreatingAide2(){
        cp=(JPanel) this.getContentPane();  
        cp.setLayout(new BorderLayout()); 
        cp.removeAll();
        cp.add(new Aide2(this),BorderLayout.CENTER);
        cp.updateUI();
    }

      
}