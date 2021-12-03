package main;
import game.*;
import gui.Fenetre;

//import view.*;
import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {




    public static void main(String[] args) {
       
       Grille g = new Grille();
       System.out.println(g.toString());
       Fenetre f = new Fenetre(g);
        
    }

}