package game;
import java.util.*;

import javax.swing.SpringLayout.Constraints;

import observer.*;
import solver.ArcConsistency;
import solver.Constraint;
import solver.DifferenceConstraint;
import solver.PetitConstraint;
import solver.SommeConstraint;
import src.Constante;

public class Grille extends AbstractModelEcoutable {
    //Recupérateur de grille
    RecupeGrille recup = new RecupeGrille();

    //Permet de stocker la grille et la solution  sous forme de string 
    private String[][] grilleToString;
    private String[][] solutionToString;

    //Stock les cases
    private Map<Coordonne,CaseBlanche>MapCaseBlanche = new HashMap<Coordonne,CaseBlanche>();
    private List<CaseNoire>listeCaseNoire = new ArrayList<CaseNoire>();
    private List<CaseOperation>listeCaseOperation = new ArrayList<CaseOperation>();

    //Stock solution case Blanche
    private Map<Coordonne,CaseBlanche>MapSolutionCaseBlanche = new HashMap<Coordonne,CaseBlanche>();

    //Colonne ligne grille
    private int nbLigne;
    private int nbColonne;

    //Contraintes
    Set<Constraint> constraints = new HashSet<Constraint>();

    //Set voisins
    Set<Set<CaseBlanche>> voisins = new HashSet<Set<CaseBlanche>>();

    public Grille(){
        //Recupe une grille facile sous forme de string et sa solution
        this.grilleToString= recup.RecupGrilleFacile();
        this.solutionToString=recup.RecupSolutionFacile();

        //Longeur des lignes et colonne du tableau à deux dimensions
        this.nbLigne=grilleToString.length;
        this.nbColonne=grilleToString[0].length;

        //Construit les différentes map de cases 
        ConstruitGrilleCase();
        createVoisins();
        //Met a jour le tableau de String
        MiseAjourGrille();
        //Construit la solution
        ConstruitSolutionCase();

    }
    //Retourn nombre de ligne grille
    public int getNbLigne(){
        return this.nbLigne;
    }
    //Retourn nombre de colonne grille
    public int getNbColonne(){
        return this.nbColonne;
    }
    //Retourne L'élément de la grille string à l'indice i,h
    public String getElement(int i,int j){
        return this.grilleToString[i][j];
    }
    //Retourne la case blanche dans la map à la coordonnée i,j 
    public CaseBlanche getCaseGrille(int i,int j){
        return this.MapCaseBlanche.get(new Coordonne(i,j));
    }
    //Modifie la valeur de la caseblanche en parametre  à la coordonnée i,j 
    public void setCaseGrille(CaseBlanche c,int i,int j){
        this.MapCaseBlanche.put(new Coordonne(i,j),c);
       
                     
        MiseAjourGrille();
        fireChangement(); 
    }


    //Construit les listes des cases ainsi que la map de cases blanche 
    public void ConstruitGrilleCase(){
        for(int i=0;i<this.nbLigne;i++)
        {
            
            for(int j=0;j<this.nbColonne;j++)
            {
                if(grilleToString[i][j].equals("v") || grilleToString[i][j].equals("0"))
                {
                    MapCaseBlanche.put(new Coordonne(i,j),new CaseBlanche(i,j));
                }
                if(grilleToString[i][j].contains("/")){

                    String res =grilleToString[i][j];
                    final String separateur="/";
                    String motDecoupe[]=res.split(separateur);
                    int valueLigne= Integer.parseInt(motDecoupe[0]);
                    int valueColonne= Integer.parseInt(motDecoupe[1]);

                    listeCaseOperation.add(new CaseOperation(i,j,valueLigne,valueColonne));
                    
                }
                if(grilleToString[i][j].equals("!")){
                    listeCaseNoire.add(new CaseNoire(i,j));
                }

            }
        }
    }

    //Construit les listes des cases ainsi que la map de cases blanche 
    public void ConstruitSolutionCase(){
        for(int i=0;i<this.nbLigne;i++)
        { 
            for(int j=0;j<this.nbColonne;j++)
            {
                if(Constante.domaineToString.contains(solutionToString[i][j]))
                {
                    CaseBlanche b = new CaseBlanche(i,j);
                    b.setValue(Integer.parseInt(solutionToString[i][j]));
                    MapSolutionCaseBlanche.put(new Coordonne(i,j),b);
                }
            }
        }
    }

    //Met à jour la grille string representant les cases grace à la map de case blanche et au liste des autres cases
    public void MiseAjourGrille(){
        for(Coordonne c : MapCaseBlanche.keySet()){
            CaseBlanche b = MapCaseBlanche.get(c);
            grilleToString[b.getx()][b.gety()]=b.toString();

        }
        for(CaseNoire n : listeCaseNoire){
            grilleToString[n.getx()][n.gety()]=n.toString();
            
        }
        for(CaseOperation o : listeCaseOperation){
            grilleToString[o.getx()][o.gety()]=o.toString();
            
        }
        applysolver();
        fireChangement();


    }
    //Ensemble de case adjacente blanche sur les lignes de la case blanche en parametre
    public Set<CaseBlanche>adjLigne(CaseBlanche b){
        Set<CaseBlanche>ligne=new HashSet<CaseBlanche>();
        int i = b.getx();
        int tmpIncr=i+1;
        int tmpDec=i-1;

        while(MapCaseBlanche.get(new Coordonne(tmpIncr,b.gety()))!=null){
            ligne.add(MapCaseBlanche.get(new Coordonne(tmpIncr,b.gety())));
            tmpIncr++;
        }
        while(MapCaseBlanche.get(new Coordonne(tmpDec,b.gety()))!=null){
            ligne.add(MapCaseBlanche.get(new Coordonne(tmpDec,b.gety())));
            tmpDec--;
        }
        return ligne;
    }
    //Ensemble de case adjacente blanche sur les colonnes de la case blanche en parametre
    public Set<CaseBlanche>adjColonne(CaseBlanche b){
        Set<CaseBlanche>colonne=new HashSet<CaseBlanche>();
        int j = b.gety();
        int tmpIncr=j+1;
        int tmpDec=j-1;

        while(MapCaseBlanche.get(new Coordonne(b.getx(),tmpIncr))!=null){
            colonne.add(MapCaseBlanche.get(new Coordonne(b.getx(),tmpIncr)));
            tmpIncr++;
        }
        while(MapCaseBlanche.get(new Coordonne(b.getx(),tmpDec))!=null){
            colonne.add(MapCaseBlanche.get(new Coordonne(b.getx(),tmpDec)));
            tmpDec--;
        }
        return colonne;
    }
    //Retourn ensemble de case blanche ajdacente d'une case blanche
    public Set<CaseBlanche> adjacent(CaseBlanche b){

        Set<CaseBlanche>adj = new HashSet<CaseBlanche>();
        if(!adjLigne(b).isEmpty())
            adj.addAll(adjLigne(b));

        if(!adjColonne(b).isEmpty())
            adj.addAll(adjColonne(b));
        return adj;
    }

    public void adjacentOperation(){
        for(CaseOperation o : listeCaseOperation){
            int i=o.getx()+1;
            while(MapCaseBlanche.get(new Coordonne(i,o.gety()))!=null && o.getValueLigne()!=0){
                MapCaseBlanche.get(new Coordonne(i,o.gety())).setCaseOpLigne(o.getValueLigne());
                i++;
            }

            int j=o.gety()+1;
            while(MapCaseBlanche.get(new Coordonne(o.getx(),j))!=null && o.getValueColonne()!=0){
                MapCaseBlanche.get(new Coordonne(o.getx(),j)).setCaseOpColonne(o.getValueColonne());
                j++;
            }

        }
    }

    // Création des voisins ligne/colonne entre chaque case blanche
    public void createVoisins(){

        adjacentOperation();
        applysolver();
        //this.constraints=new HashSet<Constraint>();
    }


    public void createConstraint(){
        Set<Coordonne>caseDifference  = new HashSet<Coordonne>();
        for(CaseBlanche caseB : MapCaseBlanche.values()){
            //////////////////Contrainte plus petit que la valeur de l'op ////////////////////
             this.constraints.add(new PetitConstraint(caseB, caseB.getMinValueOp()));   //Constrainte plus petit sur la valeur de l'op correspondante
            if(caseB.getValue()>0){
                caseDifference.add(caseB.getCoordonne());
            }
        }
        //COMPLEXITÉ ELEVÉ
        for(Coordonne c : caseDifference){
         //Applique contrainte de différence sur la case blanche et les cases blanche voisines de celle ci
            for(CaseBlanche casevoisin : adjacent(MapCaseBlanche.get(c))){
                this.constraints.add(new DifferenceConstraint(MapCaseBlanche.get(c), casevoisin));
                
            }
        }
        MiseAjourGrille();
        fireChangement(); 

    }
    //Contrainte de borne par rapport à la case blanche
    public void constraintBorne(CaseBlanche caseB){
        this.constraints.add(new PetitConstraint(caseB, caseB.getMinValueOp()));
        MiseAjourGrille();
        fireChangement(); 
    }
    //Applique contrainte de différence
    public void constraintDifference(int i,int j){
         //Applique contrainte de différence sur la case blanche et les cases blanche voisines de celle ci
         for(CaseBlanche casevoisin : adjacent(MapCaseBlanche.get(new Coordonne(i,j)))){
            this.constraints.add(new DifferenceConstraint(MapCaseBlanche.get(new Coordonne(i,j)), casevoisin));
              
        }
              
        MiseAjourGrille();
        fireChangement(); 
    }
    //Supprimer les/la contraintes portant sur la case blanche
    public void supprimerContrainte(CaseBlanche caseBlanche){
       List<Constraint>listesup = new ArrayList<Constraint>();
        for(Constraint c : this.constraints){
            if(c.getScope().contains(caseBlanche)){
                
                listesup.add(c);
                System.out.println(c.toString());
            }
        }
        this.constraints.removeAll(listesup); 
        MiseAjourGrille();
        fireChangement(); 
    }
    //Supprimer toute les contraintes 
    public void supprimerAllContrainte(){
        for(Coordonne c : this.MapCaseBlanche.keySet()){
            this.MapCaseBlanche.get(c).modifDomaine(CaseBlanche.setDomaine());
        }
        this.constraints=new HashSet<Constraint>();
        MiseAjourGrille();
        fireChangement(); 
    }

    //Retourne set de case blanche ayant les plus petit domaines
    public Set<CaseBlanche> minDomaineCaseBlanche(){
        int min=11;
        Set<CaseBlanche> minCaseBlanche = new HashSet<CaseBlanche>();
        for(CaseBlanche mincase : this.MapCaseBlanche.values()){
            if(mincase.getDomaine().size()<min){
                min=mincase.getDomaine().size();
            }
            
        }
        for(CaseBlanche mincase : this.MapCaseBlanche.values()){
            if(min==mincase.getDomaine().size()){
                minCaseBlanche.add(mincase);
            }
            
        }
        return minCaseBlanche;
    }

    public void SolutionCase(CaseBlanche caseB){
        CaseBlanche change = this.MapSolutionCaseBlanche.get(caseB.getCoordonne());
        CaseBlanche actualgrille = this.MapCaseBlanche.get(caseB.getCoordonne());
        actualgrille.setValue(change.getValue());
        this.setCaseGrille(actualgrille, actualgrille.getx(), actualgrille.gety());
    }
    public void SolutionGrille(){
        this.MapCaseBlanche=this.MapSolutionCaseBlanche;
        MiseAjourGrille();
        fireChangement(); 
    }

    public void applysolver(){
         //Case blanche associé à leur domaine
         Map<CaseBlanche,Set<Integer>> map = new HashMap<CaseBlanche,Set<Integer>>();
         Set<Integer>tmpDomaine = new HashSet<Integer>();

         //Initialisation des domaines des cases blanches dans la nouvelle map
         for(CaseBlanche b : MapCaseBlanche.values()){
             if(!b.getDomaineBase().isEmpty()){
                tmpDomaine.addAll(b.getDomaineBase());
                map.put(b,tmpDomaine);
                tmpDomaine = new HashSet<Integer>();
                
             }
             else {
                 
                tmpDomaine.addAll(b.getDomaine());
                map.put(b,tmpDomaine);
                tmpDomaine = new HashSet<Integer>();

             }
             
         }
         //Appliquer le solver sur la map et les contraintes spécifiées
         ArcConsistency arc = new ArcConsistency(this.constraints);
        arc.ac1(map);

         //Mettre à jour la mapCasBlanche stockant les cases blanches 
         for(CaseBlanche b : map.keySet()){
             MapCaseBlanche.put(b.getCoordonne(),b);
            
            
         }
         
    }


    public boolean finish(){

        for(Coordonne c : MapCaseBlanche.keySet()){
            //Si une des valeurs des cases n'est pas égale alors solution non trouvé
            if(!MapCaseBlanche.get(c).equals(MapSolutionCaseBlanche.get(c))){
                return false;
            }
           
        }
        //Solution egale à la mapCaseBlanche
        return true;
    }

    @Override
    public String toString()
    {
        String res="Grille Kakuro";
        res+=System.lineSeparator();
        for(int r=0;r<nbColonne;r++){res+="----";}
        for(int i=0;i<nbLigne;i++)
        {
            res+=System.lineSeparator();
            for(int j=0;j<nbColonne;j++)
            {
                res+=" " +this.grilleToString[i][j]+" ";
            }
        }
       
        res+=System.lineSeparator();
        for(int r=0;r<nbColonne;r++){res+="----";}

        return res;
    }

}