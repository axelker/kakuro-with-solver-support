package solver;
import java.util.*;
import java.lang.*;
import java.io.*;
import game.*;


public class SommeConstraint implements Constraint {
    private Set<CaseBlanche> scopeCaseBlanche;
    private int valeur;
    
    public SommeConstraint(Set<CaseBlanche>scope,int valeur){
        this.scopeCaseBlanche=new HashSet<>(scope);
        this.valeur=valeur;
    }
    public int getValeur(){
        return this.valeur;
    }
    @Override
    //Retourne les CaseBlanche affectées par la contrainte
    public Set<CaseBlanche>getScope()
    {
       return this.scopeCaseBlanche;
    }

    @Override
    //Verifie que la contraite est satisfaite par la map en parametre 
    public boolean isSatisfiedBy(Map<CaseBlanche,Integer>verifMap)
    {
        Integer somme=0;
        for(CaseBlanche val : scopeCaseBlanche){
           //Test si les variables sont contenu dans la map
            if(verifMap.containsKey(val))
            {
                somme+=verifMap.get(val);        
            }
            else {
                throw new IllegalArgumentException("Les cases blanches ne sont pas inclus dans la map");
            }
        }

        return somme==this.valeur;
         
    }

    @Override
    public boolean equals(Object c) 
    {
        if(this==c)
        {
            return true;
        }

        else if(c instanceof SommeConstraint)
            {
              SommeConstraint tmp = (SommeConstraint)c;
                if(tmp.getScope().containsAll(this.getScope()) && tmp.getValeur()==this.valeur)
                {
                    return true;
                }
            }
        
        return false;
    }
}

