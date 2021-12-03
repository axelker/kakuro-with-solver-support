package observer;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;


public abstract class AbstractModelEcoutable implements ModeleEcoutable {

     // Liste des differentes vue    
     private ArrayList<EcouteurModel> ecouteurs;
    public AbstractModelEcoutable()
    {
        this.ecouteurs=new ArrayList<>();
    }

    public void ajoutEcouteur(EcouteurModel e)
    {
        this.ecouteurs.add(e);
    }

    public void retraitEcouteur(EcouteurModel e)
    {
        this.ecouteurs.remove(e);
    }

    protected void fireChangement()
    {
        for(EcouteurModel e : ecouteurs)
        {
            e.modeleMisAjour(this);
        }
    }


}