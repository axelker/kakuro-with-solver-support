package solver;
import java.util.*;
import java.lang.*;
import java.io.*;
import game.*;


public class ArcConsistency {

    private Set<Constraint>contraint;

    public ArcConsistency(Set<Constraint>contraint) 
    {
           
        
        this.contraint=contraint;
        
    }
    
    //Contrainte unaire (Noeud)
    public boolean enforceNodeConsistency(Map<CaseBlanche,Set<Integer>>map)
    {
        //Parcourir les CaseBlanche de la map 
        for(CaseBlanche x : map.keySet())
        {
            //Initialisation d'un nouveau set domaine qui permettras d'ajouter les valeur du domaine non satisfaisante selon la contraintes
            Set<Integer> new_domaine_remove = new HashSet<>();
            Set<Integer>domaine = map.get(x); // Recupérer le domaine de la CaseBlanche x 
            for(Integer d : domaine) // Parcourir les valeurs de ce domaine
            {
                // Nouvelle map pour effectuer le test de satisfaction 
                Map<CaseBlanche,Integer> m = new HashMap<>();
                m.put(x,d);
                for(Constraint c : this.contraint) 
                {
                    //SI contrainte unaire car une CaseBlanche au sein de la contrainte et que la CaseBlanche x est celle contenu dans la contrainte
                    if(c.getScope().size()==1 && c.getScope().contains(x))
                    {
                        //Tester si la contrainte n'est pas satisfaite alors ajouter la valeur du domaine pour la supprimer par la suite de la map
                        if(!c.isSatisfiedBy(m))
                        {
                            new_domaine_remove.add(d);
                        }
                    }
                }
                
                
            }
            // supprimer les valeurs des domaines ne satisfaisant pas les contraintes
            map.get(x).removeAll(new_domaine_remove);
            //MODIFI LE DOMAINE DE LA CASE
            x.modifDomaine(map.get(x));
                      
            
        }
        //Boucle sur la map pour tester si il y'a un domaine vide
        for(CaseBlanche x : map.keySet())
        {
            if(map.get(x).isEmpty()) // Si domaine de x vide retourner faux
            {
                return false;
            }
        }
        //Aucun domaine vide
        return true;
    }


    public boolean revise(CaseBlanche v1,Set<Integer> domaine1,CaseBlanche v2,Set<Integer> domaine2)
    {
        boolean del = false;
         // Nouvelle map pour effectuer le test de satisfaction 
         Map<CaseBlanche,Integer> m = new HashMap<>();
         Set<Integer>domaineRemove = new HashSet<>();

        //Parcour du domaine de v1
        
        for(Integer d1 : domaine1)
        {
            //Ajout de de la CaseBlanche v1 et sa valeur
            m.put(v1,d1);
            boolean viable = false;
            //Parcour du domaine de v2
            for(Integer d2 : domaine2)
            {
                //Ajout de de la CaseBlanche v2 et sa valeur
                m.put(v2,d2);
                boolean toutSatisfait = true;
                for(Constraint c : this.contraint)
                {
                    if(c.getScope().size()==2 && c.getScope().contains(v1) && c.getScope().contains(v2))
                    {
                        //SI non satisfait 
                        if(!c.isSatisfiedBy(m))
                        {
                            toutSatisfait=false;
                            break;
                        }
                    }
                        
                    

                }
                //SI tout est satisfait alors la valeur du domaine est viable
                if(toutSatisfait) {
                    viable=true;
                    break;
                }
            }
            //Valeur non viable
            if(!viable)
            {
                //Ajouter les domaines qui vont etre supprimer à la liste adequate
                domaineRemove.add(d1);
                del=true;
            }
        }
        //Supprimer toutes les valeurs du domaine non viable
        domaine1.removeAll(domaineRemove);
        //MODIFI LE DOMAINE DE LA CASE
        v1.modifDomaine(domaine1);

        return del;
    }


    public boolean ac1(Map<CaseBlanche,Set<Integer>>map)
    {
        if(!enforceNodeConsistency(map))
        {
            return false;
        }
        boolean change;
        do {
            change=false;
            for(CaseBlanche v1 : map.keySet())
            {
                for(CaseBlanche v2 : map.keySet())
                {
                    if(!v1.equals(v2))
                    {
                        if(revise(v1,map.get(v1),v2,map.get(v2)))
                        {
                            change=true;
                        }
                    }
                }
            }
        }while(change);

        for(CaseBlanche x : map.keySet())
        {
            if(map.get(x).isEmpty())
            {
                return false;
            }
        }
        return true;

        
    }

    // public boolean reviseBis(Map<CaseBlanche,Set<Integer>>map){
    //     boolean del = false;
    //     // Nouvelle map pour effectuer le test de satisfaction 
    //     Map<CaseBlanche,Integer> m = new HashMap<>();
    //     Set<Integer>domaineRemove = new HashSet<>();

    //     for(Constraint c : this.contraint)
    //     {
    //         if(c.getScope().size()>2)
    //         {
    //             //SI non satisfait 
    //             if(!c.isSatisfiedBy(m))
    //             {
    //                 toutSatisfait=false;
    //                 break;
    //             }
    //         }
                       
    //     }
    //         //SI tout est satisfait alors la valeur du domaine est viable
    //         if(toutSatisfait) {
    //             viable=true;
    //             break;
    //         }
    //        //Valeur non viable
    //        if(!viable)
    //        {
    //            //Ajouter les domaines qui vont etre supprimer à la liste adequate
    //            domaineRemove.add(d1);
    //            del=true;
    //        }
    //    //Supprimer toutes les valeurs du domaine non viable
    //    domaine1.removeAll(domaineRemove);
    //    //MODIFI LE DOMAINE DE LA CASE
    //    v1.modifDomaine(domaine1);

    //    return del;
    // }

    // public boolean ac3(Map<CaseBlanche,Set<Integer>>map)
    // {
    //     if(!enforceNodeConsistency(map))
    //     {
    //         return false;
    //     }
    //     //Iterator qui nous permettras de sortir les scop des contraintes
    //     Iterator iterConstrainte = this.contraint.iterator();
    //     //Recuperer un scope de variable ayant une contrainte et l'ajouter au couple de case blanche
    //     LinkedList<Set<CaseBlanche>>coupleCaseBlanche = getVoisinConstraint(iterConstrainte.next().getScope());
        

      
    //    while(!coupleCaseBlanche.isEmpty()){
    //     Set<CaseBlanche> voisin = coupleCaseBlanche.pullFirst();
    //     Iterator itervoisin = voisin.iterator();
    //     CaseBlanche v1 = itervoisin.next();
    //     CaseBlanche v2 = itervoisin.next();
    //         if(revise(v1,map.get(v1),v2,map.get(v2)))
    //         {
    //             if()
    //             coupleCaseBlanche.push(iterConstrainte.next());
    //         }
        
    //    }
       

    //     for(CaseBlanche x : map.keySet())
    //     {
    //         if(map.get(x).isEmpty())
    //         {
    //             return false;
    //         }
    //     }
    //     return true;

        
    // }

    // public LinkedList<Set<CaseBlanche>> getVoisinConstraint(Set<CaseBlanche>set){
    //     LinkedList<Set<CaseBlanche>> retourvoisin = new LinkedList<Set<CaseBlanche>>();
    //     for(Constraint c : this.contraint){
    //         if(c.getScope().containsAll(set)){
    //             retourvoisin.push(c.getScope());
    //         }
    //     }
    //     return retourvoisin;
    // }




}