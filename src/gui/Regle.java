package gui;
import game.*;
import observer.*;
import src.Constante;

import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class Regle extends JPanel {
    private JLabel regle = new JLabel(new ImageIcon("image/regle.png"));
    private Color couleur = Constante.backgroundColor;

    public Regle(Fenetre fenetre){
        this.setLayout(new BorderLayout());
        this.setBackground(couleur);
        this.add(regle,BorderLayout.CENTER);
        this.add(new MenuBar(fenetre),BorderLayout.NORTH);

    }
}