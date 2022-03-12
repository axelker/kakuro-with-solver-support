package gui;
import game.*;
import observer.*;
import src.Constante;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.*;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;


public class MenuBar extends JMenuBar {
    //MENU ITEM
    private JMenu action = new JMenu("Navigation");
    private JMenu regle = new JMenu("Règle");
    //SOUS MENU
    private JMenuItem accueil = new JMenuItem("Accueil");
    private JMenuItem nouvellePartie = new JMenuItem("Nouvelle Partie");
    private JMenuItem enregistre = new JMenuItem("Capturer l'écran");
    private JMenuItem quitter = new JMenuItem("Quitter");
    private JMenuItem afficheRegle = new JMenuItem("Consulter les règles");
    //FENETRE PRINCIPALE
    private Fenetre fenetrePrincipale;

    //Couleur MenuBar
    private Color couleur = Constante.MenuBarBackground;

    public MenuBar(Fenetre fenetre){
        this.fenetrePrincipale=fenetre;
        this.action.add(accueil);
        this.action.add(nouvellePartie);
        this.action.add(enregistre);
        this.action.add(quitter);
        this.regle.add(afficheRegle);
        this.add(action);
        this.add(regle);
        this.Listener();
        this.setBackground(this.couleur);
    }


    //Ecoute des sous menus 
    public void Listener(){
        this.quitter.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                fenetrePrincipale.dispose();
            }
        });
        this.afficheRegle.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                fenetrePrincipale.CreationRegle();
                
            }
        });
        this.accueil.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               fenetrePrincipale.CreatingMenu();
                
            }
        });
        this.nouvellePartie.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               fenetrePrincipale.CreationElementFenetre();
                
            }
        });
        this.enregistre.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               captureEcran();
                
            }
        });
                
    }

    public void captureEcran(){
        try {
            Robot robot = new Robot();
            //Dimension de l'écran
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            //capture d'écran
            BufferedImage bi = robot.createScreenCapture(new Rectangle(dimension.width, dimension.height));
            //Tirer alea pour le nom de la capture
            Random rand = new Random();
            int alea = rand.nextInt(50000);
            //enregistrer l'image
            ImageIO.write(bi, "jpg", new File("image/ScreenShot"+alea+".jpg"));
        } catch (AWTException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    
    }


}