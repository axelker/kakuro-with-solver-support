package game;
import java.util.*;

public class CaseBlanche {

    private String nom;
    private Integer value;
    private Set<Integer> domaine; 

    public CaseBlanche(String nom){
        this.value=null;
        this.domaine=setDomaine();
    }

    public int getValue(){
        return value;
    }
    public void setValue(Integer value){
        this.value=value;
    }
    public Set<Integer>getDomaine(){
        return this.domaine;
    }
    private Set<Integer> setDomaine(){
        Set<Integer>d = new HashSet<Integer>();
        for(int i=1;i<10;i++){
            d.add(i);
        }
        return d;
    }
     

    @Override
    public String toString(){
        if(this.value.equals(null)){
            return "v";
        }
        return this.value+"";
       
    }

}