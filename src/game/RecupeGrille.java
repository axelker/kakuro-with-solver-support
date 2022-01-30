package game;
import java.util.*;
import java.lang.*;
import java.io.*;
import src.Constante;

/**
 * Classe permettant le tirage aleatoire et la conversion d'une grille kakuro contenu dans un fichier texte
 * au sein d'un tableau en java.
 */
public class RecupeGrille {

    //Random permettant tirage aleatoire d'une grille pour une grille
    private Random r = new Random();
    private int aleaFacile;

    public RecupeGrille(){
        this.aleaFacile=r.nextInt(Constante.nbGrilleFacile);
    }
    /**
     * Récupère un fichier contenant une grille de kakuro 
     * puis la convertis en un tableau à deux dimension représentant cette grille.
     * @return retourne une grille kakuro sous forme de tableau String à deux dimensions
     */
    public String[][] ConvertGrille(String cheminFichier,int nbLigne,int nbColonne){
        String[][] tab = new String[nbLigne][nbColonne];
        List<String>listeLigne= new ArrayList<String>();

        try {
            //Recuperer les caractères contenu dans le fichier 
            Scanner scan = new Scanner(new File(cheminFichier));
            int indice=0;
            //Parcour du fichier par caractère et ajout à la liste
            while (scan.hasNext()) {
                    listeLigne.add(scan.next());
                    indice++;
                    
            }
            scan.close();
            indice=0;
            for(int i=0;i<nbLigne;i++){
                for(int j=0;j<nbColonne;j++){
                    tab[i][j]=listeLigne.get(indice);
                    indice++;
                }
            }
                
        }
        //Ouvertur a échouée
        catch (IOException e) {
            System.out.println("Fichier non ouvert");
            e.printStackTrace();
        }
        return tab;

    }
    /** Permet de tirer aléatoirement une grille de kakuro parmis celle disponible
     * @return retourne un tableau représentant une grille de kakuro de dificultée facile
     */
    public String[][] RecupGrilleFacile(){
        
        return ConvertGrille("grille/facile/grille"+this.aleaFacile+".txt",Constante.TailleLigneFacile,Constante.TailleColonneFacile);
    }
    /** Permet de récuperer la solution de la grille facile acutelle
     * @return solution de la grille facile
     */
    public String[][] RecupSolutionFacile(){
       
        return ConvertGrille("grille/facile/solution"+this.aleaFacile+".txt",Constante.TailleLigneFacile,Constante.TailleColonneFacile);
    }

    public String[][] RecupeGrilleTest(){
        return ConvertGrille("grille/facile/grilletest.txt",5,5);
    }
    public String[][] RecupSolutionTest(){
        return ConvertGrille("grille/facile/grilletestsolution.txt", 5, 5);
    }
    
}