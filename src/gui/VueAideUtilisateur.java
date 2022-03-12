package gui;
import game.*;
import observer.*;
import src.Constante;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;


public class VueAideUtilisateur extends JPanel implements EcouteurModel,ActionListener{
    /**
     * Domaine permettant d'etre réduit selon la caseBlanche
     */
    private Set<Integer> domaine;
    //Grille du kakuro
    private Grille grille;
    //Case blanche qui seras initialiser plutard lors d'un clique
    private CaseBlanche caseModif=null;
    //Vue graphique de la grille kakuro
    private VueGraphique vuegraphique;
    //Titre pour aide
    private JLabel titre=new JLabel("Cliquer sur une case pour afficher les valeurs de son domaine");
    private JLabel indication = new JLabel(new ImageIcon("image/Indication.png"));

    //Couleur background
    private Color background = Constante.backgroundColor;

    private Fenetre fenetre;

    public VueAideUtilisateur(Grille g,VueGraphique vuegraphique,Fenetre fenetre){
        this.grille=g;
        this.fenetre=fenetre;
        grille.ajoutEcouteur(this);
        this.vuegraphique=vuegraphique;
        this.domaine=CaseBlanche.setDomaine();
        this.setLayout(new GridLayout(1,1));
        titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        this.setBackground(background);

        this.add(indication);
    }
    
    public Set<Integer> getDomaine(){
        return this.domaine;
    }
    public void setDomaine(Set<Integer>domaine){
        this.domaine=domaine;
    }

    //Dessine les domaines sous forme de boutons cliquable
    public void DessinBouttonValDomaine(){
        this.setLayout(new GridLayout(2,1));

        if(domaine.isEmpty()){
            return;
        }
        //Modif titre 
        if(!this.caseModif.equals(null)){
            titre.setText("Cliquer sur une valeur pour modifier la case blanche de coordonnée (" + this.caseModif.getx()+","+this.caseModif.gety()+")");
        }
        else {
            titre.setText("Cliquer sur la valeur de votre choix");

        }
        this.add(titre);
        //Ajout des boutons au jpanel
        JPanel boutonDomaine = new JPanel();
        boutonDomaine.setLayout(new GridLayout(3,3));
        
        //SINON CRÉER LES VALEURS DE DOMAINE SOUS FORME DE BOUTON CLIQUABLE
       
        for(int val : this.caseModif.getDomaine()){
                if(val!=0){
                    JButton b = new JButton(val+"");
                    b.setBackground(Constante.MenuBarBackground);
                    b.setBorder(BorderFactory.createLineBorder(Color.black, 2));
                    b.addActionListener(this);
                    boutonDomaine.add(b);
                }
                
        }
        this.add(boutonDomaine);

    }
    /**
     * Affichage partie terminée
     *  */
    public void Partietermine(){
        if(grille.finish()){
            this.removeAll();
            titre.setText("Partie Terminée bravo !");
            JButton recommencer = new JButton("Recommencer");
            recommencer.setBackground(Constante.MenuBarBackground);
            recommencer.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent e){
                    fenetre.CreationElementFenetre();
                    
                }
            });
            this.add(titre);
            this.add(recommencer);

        }
    }
    /**
     * Met à jour la valeur de la case blanche en attribut de classe
     */
    public void setCaseModif(CaseBlanche b){
        this.caseModif=b;
        
    }
    /**
     * Met à jour les pannels
     * */
    @Override
    public void modeleMisAjour(Object source)
    {
        this.removeAll();
        this.DessinBouttonValDomaine();
        this.Partietermine();
        vuegraphique.removeAll();
        vuegraphique.Dessin();
        vuegraphique.AjoutPanel();
        vuegraphique.updateUI(); 
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        
            //Prends la valeur texte du bouton pour tester si plusieurs bouton le quelle a été selectionné
            JButton Bsrc= (JButton) e.getSource(); 
            String BoutonPresse= Bsrc.getText();  
            //Recuperer la valeur du bouton étant une valeur de domaine (chiffre)
            int valDomaine=Integer.parseInt(BoutonPresse); 
              
            //SI La case à modifier a été initialiser et la partie n'est pas finis on change la valeur de la case par celle cliqué
            if(!caseModif.equals(null) && !grille.finish()){
                //Appliquer la modification de la valeur de cette case
                caseModif.setValue(valDomaine);
                grille.setCaseGrille(caseModif, caseModif.getx(),caseModif.gety());     
            }     
           
               
    }
}