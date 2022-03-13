package gui;
import game.*;
import observer.*;
import src.Constante;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Aide2 extends JPanel {
    private JLabel aide = new JLabel(new ImageIcon("image/Aide2.png"));
    private Color couleur = Constante.backgroundColor;

    public Aide2(Fenetre fenetre){
        this.setLayout(new BorderLayout());
        this.setBackground(couleur);
        this.add(aide,BorderLayout.CENTER);
        this.add(new MenuBar(fenetre),BorderLayout.NORTH);

    }
}