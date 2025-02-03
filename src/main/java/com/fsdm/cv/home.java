package com.fsdm.cv;


import javax.swing.*;
import java.awt.*;

public class home {
    private JButton ouvrirButton;
    private JPanel screen;
    private JButton enregistrerButton;
    private JButton quitterButton;
    private JButton laplacienButton;
    private JButton sobelButton;
    private JButton gradientButton;
    private JButton prewittButton;
    private JButton robertButton;
    private JButton moyenneur33Button;
    private JButton moyenneur55Button;
    private JButton coniqueButton;
    private JButton gaussienne33Button;
    private JButton gaussienne55Button;
    private JButton pyramidalButton;
    private JButton medianButton;
    private JButton inversionButton;
    private JButton binarisationButton;
    private JTextField suileTextField;
    private JButton contrasteButton;
    private JTextField contrasteTextField;
    private JButton divisionButton;
    private JButton niveauDeGrisButton;
    private JButton histogramButton;
    private JButton contrast_amilorationButton;
    private JTextField pContrast;
    private JTextField bContrast;
    private JButton FPBButton;
    private JButton FPBBButton;
    private JButton FPHButton;
    private JButton FPHBButton;
    private JPanel originalmage;
    private JPanel filtredImage;

    public home() {

    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("home");


        JPanel panel = new JPanel();
        panel.add(new home().screen);
        panel.setPreferredSize(new Dimension(1000, 680));
        panel.setMinimumSize(new Dimension(300, 220));
        panel.setMaximumSize(new Dimension(1500, 1200));


        frame.setContentPane(panel);


        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
