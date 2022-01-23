package game;


public class Coordonne {
    private int x;
    private int y;

    public Coordonne(int x,int y){
        this.x=x;
        this.y=y;
    }

    public int getx(){
        return this.x;
    }
    public int gety(){
        return this.y;
    }
    public void setx(int x){
        this.x=x;
    }
    public void sety(int y){
        this.y=y;
    }
    //Redefinition du equals par rapport au entier x et y
    @Override
    public boolean equals(Object c) 
    {
        if(this==c)
        {
            return true;
        }

        else if(c instanceof Coordonne)
            {
                Coordonne tmp = (Coordonne)c;
                if(tmp.getx()==this.getx() && tmp.gety()==this.gety())
                {
                    return true;
                }
            }
        
        return false;
    }
    //Redefinition du hash pour manipuler les map 
    @Override
    public int hashCode()
    {
        String hash = ""+x+" "+y;
        return hash.hashCode();
    }

    @Override
    public String toString(){
        return "("+x+","+y+" )";
    }
}