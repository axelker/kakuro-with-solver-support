package solver;
import java.util.*;
import java.lang.*;
import java.io.*;
import game.*;

/**
 * Contrainte UNAIRE portant sur la différence
 */
// NE PAS METTREE EN CONTRAINTE BINAIRE 
// V1 sert à spécifiée la valeur à ne pas avoir dans le domaine de V2
// La valeur n'est pas entrée directement dans le constructeur car le equals est redefinis selon les deux Cases.
    
public class DifferenceConstraint implements Constraint{

    private CaseBlanche v1;
    private CaseBlanche v2;

    public DifferenceConstraint(CaseBlanche v1,CaseBlanche v2)
    {
        this.v1=v1;
        this.v2=v2;
    }

    public CaseBlanche getV1()
    {
        return this.v1;

    }
    public CaseBlanche getV2()
    {
        return this.v2;

    }
     
    @Override
    //Retourne la case blanche affectée par la contrainte
    public Set<CaseBlanche>getScope()
    {
        Set<CaseBlanche> setCaseBlanche = new HashSet<>(); 
        // setCaseBlanche.add(v1);
        setCaseBlanche.add(v2);
        return setCaseBlanche;
    }

    @Override
    //Verifie que la contraite est satisfaite par la map en parametre 
    public boolean isSatisfiedBy(Map<CaseBlanche,Integer>verifMap)
    {
        //Test si les variables sont contenu dans la map
        if(verifMap.containsKey(v2))
        {
            // Retourner si la valeur corresponds à la différence des deux
            return (this.v1.getValue()!=verifMap.get(v2));

           
            
        }
       throw new IllegalArgumentException("La case blanche n'est pas inclus dans la map");
    }
     //Redefinition du equals par rapport au valeur de la case et ces coordonnées
     @Override
     public boolean equals(Object c) 
     {
         if(this==c)
         {
             return true;
         }
 
         else if(c instanceof DifferenceConstraint)
             {
               DifferenceConstraint tmp = (DifferenceConstraint)c;
                 if(tmp.getV1().getCoordonne().equals(this.v1.getCoordonne()) && tmp.getV2().getCoordonne().equals(this.v2.getCoordonne()))
                 {
                     return true;
                 }
             }
         
         return false;
     }
     
     //Hashcode toString pour manipuler Hashmap
     @Override
     public int hashCode()
     {
         String res=""+this.v1.getCoordonne().hashCode()+""+this.v2.getCoordonne().hashCode();
         return res.hashCode();
     }

     @Override
     public String toString(){
         return "Case : "+v1.getCoordonne().toString() +" " +v1.toString()+" "+v2.getCoordonne().toString() +" " +v2.toString()+" ";
     }
}