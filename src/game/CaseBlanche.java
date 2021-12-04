package game;
import java.util.*;

public class CaseBlanche extends Case {

    private Integer value;
    private Set<Integer> domaine; 

    public CaseBlanche(int x,int y){
        super(x,y);
        this.value=0;
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
    public static Set<Integer> setDomaine(){
        Set<Integer>d = new HashSet<Integer>();
        for(int i=1;i<10;i++){
            d.add(i);
        }
        return d;
    }
   
      //Redefinition du equals par rapport au valeur de la case et ces coordonnées
    @Override
    public boolean equals(Object c) 
    {
        if(this==c)
        {
            return true;
        }

        else if(c instanceof CaseBlanche)
            {
                CaseBlanche tmp = (CaseBlanche)c;
                if(tmp.getValue()==this.getValue() && tmp.getCoordonne().equals(this.getCoordonne()))
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
        String res=this.toString()+" "+getx()+" "+gety();
        return res.hashCode();
    }

    @Override
    public String toString(){
        
        return this.convert0(this.value);
       
    }

}