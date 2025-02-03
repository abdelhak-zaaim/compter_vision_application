package com.fsdm.cv;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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


    BufferedImage oImage;
    BufferedImage fImage;

    public home() {
        // Initialize the originalmage JPanel with a image vieewer
        originalmage.setLayout(new BorderLayout());

        ouvrirButton.addActionListener(new ActionListener() {
            @Override
           public void actionPerformed(ActionEvent e) {
               JFileChooser fileChooser = new JFileChooser();
               int result = fileChooser.showOpenDialog(null);
               if (result == JFileChooser.APPROVE_OPTION) {
                   File selectedFile = fileChooser.getSelectedFile();
                   OptionsService optionsService = new OptionsService();
                   optionsService.loadImage(selectedFile, originalmage);
               }
           }
        });
        enregistrerButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showSaveDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    OptionsService optionsService = new OptionsService();
                    optionsService.saveImage(selectedFile, fImage);
                }

            }
        });
        quitterButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                // show an confermation dialog
                int result = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter l'application?", "Quitter", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    OptionsService optionsService = new OptionsService();
                    optionsService.quitTheApplication();
                }

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("CV Application");

        home homePanel = new home();
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(homePanel.screen, BorderLayout.CENTER);
        panel.setPreferredSize(new Dimension(1000, 680));
        panel.setMinimumSize(new Dimension(300, 220));
        panel.setMaximumSize(new Dimension(1500, 1200));

        frame.setContentPane(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}