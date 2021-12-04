package gui;
import game.*;
import observer.*;
import src.Constante;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class VueGraphique extends JPanel implements EcouteurModel,ActionListener
{

    private Grille grille;
    private JPanel cp = new JPanel();
    private VueAideUtilisateur cpAide;
    private final ArrayList<String> domaine = Constante.domaineToString;
    private Map<JButton,CaseBlanche>MapCaseBlanche = new HashMap<JButton,CaseBlanche>();

    public VueGraphique(Grille g)
    {
        this.grille=g;
        grille.ajoutEcouteur(this);
        cp.setLayout(new GridLayout(grille.getNbLigne(),grille.getNbColonne()));
        cpAide=new VueAideUtilisateur(grille,this);
        setLayout(new GridLayout(1,2));
        this.add(cp);
        this.add(cpAide);
        Dessin();
    }

    public void Dessin(){
        cp.removeAll();

        for(int i=0;i<this.grille.getNbLigne();i++){
            for(int j=0;j<this.grille.getNbColonne();j++){
                JLabel lab = new JLabel();

                //Cas ou c'est une case opération construire un label
                if(grille.getElement(i,j).contains("/")){
                    lab=new JLabel(grille.getElement(i,j));
                    lab.setForeground(Color.white);
                    lab.setBackground(Color.black);
                    lab.setBorder(BorderFactory.createLineBorder(Color.white, 1));        
                    lab.setOpaque(true);
                    cp.add(lab);
                }

                //Cas ou c'est une case  blanche contruire un bouton cliquable et ajouter celui-ci en tant que clé pour la case en question à la map 
                if(grille.getElement(i,j).equals(" ") || this.domaine.contains(grille.getElement(i,j))){
                    JButton b = new JButton(grille.getElement(i,j));
                    b.setBackground(Color.white);
                    b.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    b.addActionListener(this);
                    MapCaseBlanche.put(b,grille.getCaseGrille(i, j));
                    cp.add(b);
                    
                }
                //Cas ou c'est une case noire construire un label
                if(grille.getElement(i,j).equals("!")){
                    lab=new JLabel();
                    lab.setForeground(Color.white);
                    lab.setBackground(Color.black);
                    lab.setBorder(BorderFactory.createLineBorder(Color.white, 1));        
                    lab.setOpaque(true);
                    cp.add(lab);
                }
                  
                
            }
        }
    }

    public void AjoutPanel(){
        this.add(cp);
        this.add(cpAide);
    }
   
    @Override
    public void modeleMisAjour(Object source)
    {
        this.Dessin();
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        
            //Recuperer le boutoncliqué
            JButton Bsrc= (JButton) e.getSource(); 
            //Si la map des cases blanche n'est pas vide sortir la case correspondant au bouton cliqué 
            if(!MapCaseBlanche.isEmpty()){
                if(MapCaseBlanche.containsKey(Bsrc) && !grille.finish()){
                    //System.out.println(MapCaseBlanche.get(Bsrc).getx()+" "+MapCaseBlanche.get(Bsrc).gety());
                    CaseBlanche c = MapCaseBlanche.get(Bsrc);
                    //Case à modifier 
                    cpAide.setCaseModif(c);
                    cpAide.removeAll();
                    Dessin();
                    //Dessin le pannel de bouton avec les valeur du domaine de la case c
                    cpAide.DessinBouttonValDomaine();
                    //AjoutPanel();
                    updateUI();
                }
            }
            
               
    }

}