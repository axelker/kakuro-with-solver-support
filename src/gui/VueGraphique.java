package gui;
import game.*;
import observer.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class VueGraphique extends JPanel implements EcouteurModel
{

    private Grille grille;
    public VueGraphique(Grille g)
    {
        this.grille=g;
        grille.ajoutEcouteur(this);
        setLayout(new GridLayout(grille.getNbLigne(),grille.getNbColonne()));
        Dessin();
    }

   
    public void Dessin(){
        
        for(int i=0;i<this.grille.getNbLigne();i++){
            for(int j=0;j<this.grille.getNbColonne();j++){
                JLabel lab;

                //Cas ou c'est une case opération
                lab=new JLabel(grille.getElement(i,j));
                lab.setForeground(Color.white);
                lab.setBackground(Color.black);
                lab.setBorder(BorderFactory.createLineBorder(Color.white, 1));        


                //RAJOUTER CONDITION SI L"ELEMENT EST UN CHIFFRE CONTENU DANS LISTE
                //ENTRE 1 ET 9
                
                if(grille.getElement(i,j).equals("v")){
                    lab=new JLabel();
                    lab.setBackground(Color.white);
                    lab.setBorder(BorderFactory.createLineBorder(Color.black, 1));

                }
                if(grille.getElement(i,j).equals("!")){
                    lab=new JLabel();
                    lab.setForeground(Color.white);
                    lab.setBackground(Color.black);
                    lab.setBorder(BorderFactory.createLineBorder(Color.white, 1));        

                }
                  
                lab.setOpaque(true);
                this.add(lab);
            }
        }
    }

    @Override
    public void modeleMisAjour(Object source)
    {
        this.Dessin();
        System.out.println(grille.toString());
    }

}