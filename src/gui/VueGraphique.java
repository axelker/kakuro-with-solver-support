package gui;
import game.*;
import observer.*;
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
    private final ArrayList<String> domaine = getDomaineString();
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

    public ArrayList<String> getDomaineString(){
        ArrayList<String>d = new ArrayList<String>();
        for(int i=1;i<10;i++)
        {
            d.add(""+i);
        }
        return d;
        
       }
    public void Dessin(){
        cp.removeAll();

        for(int i=0;i<this.grille.getNbLigne();i++){
            for(int j=0;j<this.grille.getNbColonne();j++){
                JLabel lab = new JLabel();

                //Cas ou c'est une case opération
                if(grille.getElement(i,j).contains("/")){
                lab=new JLabel(grille.getElement(i,j));
                lab.setForeground(Color.white);
                lab.setBackground(Color.black);
                lab.setBorder(BorderFactory.createLineBorder(Color.white, 1));        
                lab.setOpaque(true);
                cp.add(lab);
            }

                //RAJOUTER CONDITION SI L"ELEMENT EST UN CHIFFRE CONTENU DANS LISTE
                //ENTRE 1 ET 9
                
                if(grille.getElement(i,j).equals(" ") || this.domaine.contains(grille.getElement(i,j))){
                    JButton b = new JButton(grille.getElement(i,j));
                    b.setBackground(Color.white);
                    b.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                    b.addActionListener(this);
                    MapCaseBlanche.put(b,grille.getCaseGrille(i, j));
                    cp.add(b);
                    
                }
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
        System.out.println(grille.toString());
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        
            //Prends la valeur texte du bouton pour tester si plusieurs bouton le quelle a été selectionné
            JButton Bsrc= (JButton) e.getSource();  
            if(!MapCaseBlanche.isEmpty()){
                if(MapCaseBlanche.containsKey(Bsrc)){
                    //System.out.println(MapCaseBlanche.get(Bsrc).getx()+" "+MapCaseBlanche.get(Bsrc).gety());
                    CaseBlanche c = MapCaseBlanche.get(Bsrc);
                    cpAide.setCaseModif(c);
                    cpAide.removeAll();
                    Dessin();
                    cpAide.DessinBouttonValDomaine();
                    AjoutPanel();
                    updateUI();
                }
            }
            
               
    }

}