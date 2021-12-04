package gui;
import game.*;
import observer.*;
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

    private JLabel titre=new JLabel("Cliquer sur une case pour afficher les valeurs de son domaine");

    public VueAideUtilisateur(Grille g,VueGraphique vuegraphique){
        this.grille=g;
        grille.ajoutEcouteur(this);
        this.vuegraphique=vuegraphique;
        this.domaine=CaseBlanche.setDomaine();
        setLayout(new GridLayout(2,1));
        titre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        this.add(titre);
    }
    
    public Set<Integer> getDomaine(){
        return this.domaine;
    }
    public void setDomaine(Set<Integer>domaine){
        this.domaine=domaine;
    }

    //Dessine les domaines sous forme de boutons cliquable
    public void DessinBouttonValDomaine(){
        if(domaine.isEmpty()){
            return;
        }
        titre.setText("Cliquer sur la valeur de votre choix");
        this.add(titre);
        JPanel boutonDomaine = new JPanel();
        boutonDomaine.setLayout(new GridLayout(3,3));
        //SINON CRÉER LES VALEURS DE DOMAINE SOUS FORME DE BOUTON CLIQUABLE
        for(int val : domaine){
            JButton b = new JButton(val+"");
            b.setBackground(Color.lightGray);
            b.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            b.addActionListener(this);
            boutonDomaine.add(b);
            
        }
        this.add(boutonDomaine);

    }
    //Modifie la case blanche étant cliqué
    public void setCaseModif(CaseBlanche b){
        this.caseModif=b;
        
    }
    //Met à jour les pannels
    @Override
    public void modeleMisAjour(Object source)
    {
        this.removeAll();
        this.DessinBouttonValDomaine();
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
            //Recuperer la valeur du bouton étant une valeur de domaine
            int valDomaine=Integer.parseInt(BoutonPresse);   
            //La case modifier a été initialiser
            if(!caseModif.equals(null)){
                //Appliquer la modification de la valeur de cette case
                caseModif.setValue(valDomaine);
                grille.setCaseGrille(caseModif, caseModif.getx(),caseModif.gety());     
            }     
           
               
    }
}