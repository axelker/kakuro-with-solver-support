package game;
import java.util.*;
import observer.*;

public class Grille extends AbstractModelEcoutable {
    
    //Permet de stocker la grille sous forme de string 
    private String[][] grilleToString;
    //Stock les cases
    private Map<Coordonne,CaseBlanche>MapCaseBlanche = new HashMap<Coordonne,CaseBlanche>();
    private List<CaseNoire>listeCaseNoire = new ArrayList<CaseNoire>();
    private List<CaseOperation>listeCaseOperation = new ArrayList<CaseOperation>();
    //Colonne ligne grille
    private int nbLigne;
    private int nbColonne;

    public Grille(){
        //Recupe une grille facile sous forme de string
        this.grilleToString=new RecupeGrille().RecupGrilleFacile();
        //Longeur des lignes et colonne du tableau à deux dimensions
        this.nbLigne=grilleToString.length;
        this.nbColonne=grilleToString[0].length;
        //Construit les différentes map de cases 
        ConstruitGrilleCase();
        //Met a jour le tableau de String
        MiseAjourGrille();

    }

    public int getNbLigne(){
        return this.nbLigne;
    }
    public int getNbColonne(){
        return this.nbColonne;
    }
    public String getElement(int i,int j){
        return this.grilleToString[i][j];
    }
    public CaseBlanche getCaseGrille(int i,int j){
        return this.MapCaseBlanche.get(new Coordonne(i,j));
    }
    public void setCaseGrille(CaseBlanche c,int i,int j){
        this.MapCaseBlanche.put(new Coordonne(i,j),c);
        MiseAjourGrille();
        fireChangement();
    }

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

    @Override
    public String toString()
    {
        String res="";
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