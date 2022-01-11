package solver;
import java.util.*;
import java.lang.*;
import java.io.*;
import game.*;

/**
 * Contrainte portant sur la différence
 */
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
    //Retourne les CaseBlanche affectées par la contrainte
    public Set<CaseBlanche>getScope()
    {
        Set<CaseBlanche> setCaseBlanche = new HashSet<>(); 
        setCaseBlanche.add(v1);
        setCaseBlanche.add(v2);
        return setCaseBlanche;
    }

    @Override
    //Verifie que la contraite est satisfaite par la map en parametre 
    public boolean isSatisfiedBy(Map<CaseBlanche,Integer>verifMap)
    {
        //Test si les variables sont contenu dans la map
        if(verifMap.containsKey(v1) && verifMap.containsKey(v2))
        {
            // Retourner si la valeur corresponds à la différence des deux
            return (!verifMap.get(v1).equals(verifMap.get(v2)));

           
            
        }
       throw new IllegalArgumentException("Les cases blanches ne sont pas inclus dans la map");
    }
}