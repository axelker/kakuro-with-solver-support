package game;

public class CaseOperation {

    private int valueLigne;
    private int valueColonne;

    public CaseOperation(int x,int y){
        this.valueLigne=x;
        this.valueColonne=y;
    }

    public int getValueLigne(){
        return this.valueLigne;
    }
    public int getValueColonne(){
        return this.valueColonne;
    }
    /*//Redefinition du equals par rapport au valeur de la case
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
                if(tmp.getName().equals(this.getName()))
                {
                    return true;
                }
            }
        
        return false;
    }
    
    @Override
    public int hashCode()
    {
        return this.nom.hashCode();
    }*/
    
    public String convert0(int value){
        if(value==0){
            return " ";
        }
        return ""+value;
    }
    @Override 
    public String toString(){
        return convert0(this.valueLigne)+"/"+convert0(this.valueColonne);
    }
}