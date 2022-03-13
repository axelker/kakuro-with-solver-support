package src;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Constante {

    //Eviter l'instanciation de la classe
    private Constante(){

    }
    public final static ArrayList<String> domaineToString = getDomaineString();
    //Constante grille facile
    public final static int TailleLigneFacile=9;
    public final static int TailleColonneFacile=9;
    public final static int nbGrilleFacile=3;
    
    //Constante couleur
    public final static Color backgroundColor = Color.decode("#FAF1E5");
    public final static Color MenuBarBackground = Color.decode("#E8E7DF");
    public final static Color caseFacile = Color.decode("#DAF7A6");

    private static ArrayList<String> getDomaineString(){
        ArrayList<String>d = new ArrayList<String>();
        for(int i=1;i<10;i++)
        {
            d.add(""+i);
        }
            return d;
       }

    
}