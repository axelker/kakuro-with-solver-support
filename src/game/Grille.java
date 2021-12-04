package game;
import java.util.*;
import observer.*;
import src.Constante;

public class Grille extends AbstractModelEcoutable {
    //Recupérateur de grille
    RecupeGrille recup = new RecupeGrille();

    //Permet de stocker la grille sous forme de string et la solution 
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

    public Grille(){
        //Recupe une grille facile sous forme de string et sa solution
        this.grilleToString= recup.RecupGrilleFacile();
        this.solutionToString=recup.RecupSolutionFacile();

        //Longeur des lignes et colonne du tableau à deux dimensions
        this.nbLigne=grilleToString.length;
        this.nbColonne=grilleToString[0].length;

        //Construit les différentes map de cases 
        ConstruitGrilleCase();
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


    }

    //Construire la map des lignes des cases blanches avec comme clé la Caseopération Map<CaseOperation,List<CaseBlanche>>ligneMap;

    //Construire la map des colonnes des cases blanches avec comme clé la Caseopération Map<CaseOperation,List<CaseBlanche>>colonneMap;

    //Reduire les valeur des domaines de case blanche en parcourant les deux maps ( si val domaine plus grands que val opération alors supprimer val domaine)


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