package game;


public class Case {

    protected Coordonne c;

    public Case(int x,int y){
        this.c=new Coordonne(x,y);
        
    }

    public int getx(){
        return this.c.getx();
    }
    public int gety(){
        return this.c.gety();
    }
    public Coordonne getCoordonne(){
        return this.c;
    }
    public String convert0(int value){
        if(value==0){
            return " ";
        }
        return ""+value;
    }
}