package game;

public class CaseOperation extends Case {

    private int valueLigne;
    private int valueColonne;

    public CaseOperation(int x,int y,int ligneval,int colval){
        super(x,y);
        this.valueLigne=ligneval;
        this.valueColonne=colval;
    }

    public int getValueLigne(){
        return this.valueLigne;
    }
    public int getValueColonne(){
        return this.valueColonne;
    }
    //Redefinition du equals par rapport au valeur de la case
    @Override
    public boolean equals(Object c) 
    {
        if(this==c)
        {
            return true;
        }

        else if(c instanceof CaseOperation)
            {
                CaseOperation tmp = (CaseOperation)c;
                if(tmp.getValueLigne()==this.getValueLigne() && tmp.getValueColonne()==this.getValueColonne() && tmp.getCoordonne().equals(this.getCoordonne()))
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
        String res=this.toString()+" "+getValueLigne()+" "+getValueColonne()+" "+getx()+" "+gety();
        return res.hashCode();
    }
    
    @Override 
    public String toString(){
        return convert0(this.valueLigne)+"/"+convert0(this.valueColonne);
    }
}