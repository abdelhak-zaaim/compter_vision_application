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


    File oImage;
    File fImage;

    public home() {
        // Initialize the originalmage JPanel with a image vieewer
        originalmage.setLayout(new BorderLayout());
        filtredImage.setLayout(new BorderLayout());

        ouvrirButton.addActionListener(new ActionListener() {
            @Override
           public void actionPerformed(ActionEvent e) {
               JFileChooser fileChooser = new JFileChooser();
               int result = fileChooser.showOpenDialog(null);
               if (result == JFileChooser.APPROVE_OPTION) {
                   File selectedFile = fileChooser.getSelectedFile();
                     oImage = selectedFile;
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
        moyenneur33Button.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                FiltersBasBasService filtersBasBasService = new FiltersBasBasService();
                fImage = filtersBasBasService.Moyenneur33Filter(oImage);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        moyenneur55Button.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                fImage = FiltersBasBasService.moyenneur55(oImage);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        coniqueButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                fImage = FiltersBasBasService.coniqueFilter(oImage);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        gaussienne33Button.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                fImage = FiltersBasBasService.gaussienne33Filter(oImage);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        gaussienne55Button.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                fImage = FiltersBasBasService.gaussienne55Filter(oImage);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        pyramidalButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                fImage = FiltersBasBasService.pyramidalFilter(oImage);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        medianButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                fImage = FiltersBasBasService.medianFilter(oImage);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        laplacienButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                fImage = FiltersBasHautService.laplacienFilter(oImage);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        sobelButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                fImage = FiltersBasHautService.sobelFilter(oImage);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        gradientButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                fImage = FiltersBasHautService.gradientFilter(oImage);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        prewittButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                fImage = FiltersBasHautService.prewittFilter(oImage);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        robertButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                fImage = FiltersBasHautService.robertFilter(oImage);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        inversionButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                fImage = TransformationService.inversion(oImage);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        binarisationButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int suile = Integer.parseInt(suileTextField.getText());
                fImage = TransformationService.binarisation(oImage, suile);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        contrasteButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int contraste = Integer.parseInt(contrasteTextField.getText());
                fImage = TransformationService.contraste(oImage, contraste);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        divisionButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                fImage = TransformationService.division(oImage, 2);

            }
        });
        contrasteButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int contraste = Integer.parseInt(contrasteTextField.getText());
                fImage = TransformationService.contraste(oImage, contraste);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        divisionButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                fImage = TransformationService.division(oImage, 2);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        niveauDeGrisButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                fImage = TransformationService.niveauDeGris(oImage);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        histogramButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                fImage = TransformationService.histogram(oImage);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        contrast_amilorationButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int p = Integer.parseInt(pContrast.getText());
                int b = Integer.parseInt(bContrast.getText());
                fImage = TransformationService.contrastAmiloration(oImage, p, b);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        FPBButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                fImage = FilterFrequentielleService.FPBFilter(oImage);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        FPBBButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                fImage = FilterFrequentielleService.FPBBFilter(oImage);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        FPHButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                fImage = FilterFrequentielleService.FPHFilter(oImage);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

            }
        });
        FPHBButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                fImage = FilterFrequentielleService.FPHBFilter(oImage);
                OptionsService optionsService = new OptionsService();
                optionsService.showFiltredImage(fImage, filtredImage);

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

    public static File niveauDeGris(File image){
        try {
            BufferedImage imageBuffered = ImageIO.read(image);
            int width = imageBuffered.getWidth();
            int height = imageBuffered.getHeight();
            BufferedImage grayImage = new BufferedImage(width, height, imageBuffered.getType());
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = imageBuffered.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;
                    int gray = (red + green + blue) / 3;
                    int grayRgb = (gray << 16) | (gray << 8) | gray;
                    grayImage.setRGB(x, y, grayRgb);
                }
            }
            File outputFile = new File("gray_image.png");
            ImageIO.write(grayImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File histogram(File image){
        try {
            BufferedImage imageBuffered = ImageIO.read(image);
            int width = imageBuffered.getWidth();
            int height = imageBuffered.getHeight();
            int[] histogram = new int[256];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = imageBuffered.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;
                    int gray = (red + green + blue) / 3;
                    histogram[gray]++;
                }
            }
            int max = 0;
            for (int i = 0; i < histogram.length; i++) {
                if (histogram[i] > max) {
                    max = histogram[i];
                }
            }
            BufferedImage histogramImage = new BufferedImage(256, max, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < 256; x++) {
                for (int y = 0; y < histogram[x]; y++) {
                    histogramImage.setRGB(x, y, 0xFF000000);
                }
            }
            File outputFile = new File("histogram_image.png");
            ImageIO.write(histogramImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File contrastAmiloration(File image , int p, int b){
        try {
            BufferedImage imageBuffered = ImageIO.read(image);
            int width = imageBuffered.getWidth();
            int height = imageBuffered.getHeight();
            BufferedImage contrastImage = new BufferedImage(width, height, imageBuffered.getType());
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = imageBuffered.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;
                    int gray = (red + green + blue) / 3;
                    int contrastRgb = (int) (p * gray + b);
                    contrastImage.setRGB(x, y, contrastRgb);
                }
            }
            File outputFile = new File("contrast_image.png");
            ImageIO.write(contrastImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}