package game;
import observer.*;

public class Grille extends AbstractModelEcoutable {
 
    private String[][] grilleToString;
    private int nbLigne;
    private int nbColonne;
    public Grille(){
        this.grilleToString=new RecupeGrille().RecupGrilleFacile();
        this.nbLigne=grilleToString.length;
        this.nbColonne=grilleToString[0].length;

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