package game;

public class CaseNoire extends Case {

    public CaseNoire(int x,int y){
        super(x,y);
    }

    @Override
    public String toString(){
        return "!";
    }
}