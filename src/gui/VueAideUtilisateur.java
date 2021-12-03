package gui;
import game.*;
import observer.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;


public class VueAideUtilisateur extends JPanel implements EcouteurModel,ActionListener{

    private Set<Integer> domaine;
    private Grille grille;
    private CaseBlanche caseModif=null;
    private VueGraphique vuegraphique;

    public VueAideUtilisateur(Grille g,VueGraphique vuegraphique){
        this.grille=g;
        grille.ajoutEcouteur(this);
        this.vuegraphique=vuegraphique;
        this.domaine=CaseBlanche.setDomaine();
        setLayout(new GridLayout(3,3));
    }
    
    public Set<Integer> getDomaine(){
        return this.domaine;
    }
    public void setDomaine(Set<Integer>domaine){
        this.domaine=domaine;
    }

    public void DessinBouttonValDomaine(){
        if(domaine.isEmpty()){
            return;
        }
        //SINON AFFICHER LES VALEURS DE DOMAINE SOUS FORME DE BOUTON CLIQUABLE
        for(int val : domaine){
            JButton b = new JButton(val+"");
            b.setBackground(Color.white);
            b.setBorder(BorderFactory.createLineBorder(Color.black, 1));
            b.addActionListener(this);
            this.add(b);
        }

    }
    public void setCaseModif(CaseBlanche b){
        this.caseModif=b;
        
    }
    @Override
    public void modeleMisAjour(Object source)
    {
        this.DessinBouttonValDomaine();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        
            //Prends la valeur texte du bouton pour tester si plusieurs bouton le quelle a été selectionné
            JButton Bsrc= (JButton) e.getSource(); 
            String BoutonPresse= Bsrc.getText();  
            int valDomaine=Integer.parseInt(BoutonPresse);   
            System.out.println(valDomaine);  
            if(!caseModif.equals(null)){
                caseModif.setValue(valDomaine);
                grille.setCaseGrille(caseModif, caseModif.getx(),caseModif.gety());
                vuegraphique.removeAll();
                vuegraphique.Dessin();
                vuegraphique.AjoutPanel();
                vuegraphique.updateUI();      
            }     
           
               
    }
}