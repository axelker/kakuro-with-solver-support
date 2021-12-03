package game;
import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Classe permettant le tirage aleatoire et la conversion d'une grille kakuro contenu dans un fichier texte
 * au sein d'un tableau en java.
 */
public class RecupeGrille {
    //Constante grille facile
    private final int TailleLigneFacile=9;
    private final int TailleColonneFacile=9;

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
            //Parcour du fichier par caractère
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
    /** Permet 
     * @return retourne un tableau représentant une grille de kakuro de dificultée facile
     */
    public String[][] RecupGrilleFacile(){
        /*Random r = new Random();
        int numGrille=r.nextInt(3);
        return ConvertGrille("grille/facile/grille"+numGrille+".txt", TailleLigneFacile, TailleColonneFacile);
        */
        return ConvertGrille("grille/facile/grille0.txt", TailleLigneFacile, TailleColonneFacile);
    }
    
}