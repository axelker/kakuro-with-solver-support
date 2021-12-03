package observer;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;


public interface ModeleEcoutable {

    public void ajoutEcouteur(EcouteurModel e);
    public void retraitEcouteur(EcouteurModel e);
}