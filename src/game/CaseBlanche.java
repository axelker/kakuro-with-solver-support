package game;
import java.util.*;

public class CaseBlanche extends Case {

    private Integer value;
    private Set<Integer> domaine; 
    private Set<Integer> domaineBase;
    private int caseopLigne;
    private int caseopColonne;
   

    public CaseBlanche(int x,int y){
        super(x,y);
        this.value=0;
        this.domaine=setDomaine();
        this.domaineBase=new HashSet<>();
        this.caseopLigne=Integer.MAX_VALUE;
        this.caseopColonne=Integer.MAX_VALUE;
       
        
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
    public Set<Integer>getDomaineBase(){
        return this.domaineBase;
    }
    public static Set<Integer> setDomaine(){
        Set<Integer>d = new HashSet<Integer>();
        for(int i=0;i<10;i++){
            d.add(i);
        }
        return d;
    }
    public int getMinValueOp(){
       if(caseopColonne<caseopLigne){
           return caseopColonne;
       }
       
        return caseopLigne;
       
    }
    
    public void setCaseOpLigne(int op){
        this.caseopLigne=op;
        
    }
    public void setCaseOpColonne(int op){
        this.caseopColonne=op;
       
    }
    
    public void modifDomaine(Set<Integer>d){
        if(this.domaineBase.isEmpty())
        {
            this.domaineBase=d;
        }
        this.domaine=d;
        
    }
    public void domainetoBase(){
        this.domaine=this.domaineBase;
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