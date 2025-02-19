package com.fsdm.cv;

import com.fsdm.cv.cornerDetection.ElectrostaticModelCurnersDetection;
import com.fsdm.cv.cornerDetection.HarrisCornerDetection;
import com.fsdm.cv.cornerDetection.SusanCurnersDetections;
import com.fsdm.cv.frequential.FPB;
import com.fsdm.cv.frequential.FPBB;
import com.fsdm.cv.frequential.FPH;
import com.fsdm.cv.frequential.FPHB;
import com.fsdm.cv.ui.ArrowProgressBarUI;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

    // jpanels
    private JPanel actionsJpanel;
    private JPanel transformationJpanel;
    private JPanel FPHJpanel;
    private JPanel FFJpanel;
    private JPanel FPBJpanel;
    private JPanel div1;
    private JPanel div2;
    private JPanel div3;
    private JPanel div4;
    private JPanel div5;
    private JPanel div6;
    private JPanel div7;
    private JPanel bruitJpanel;
    private JButton gaussianNoise;
    private JButton poivreAndSelNoise;
    private JProgressBar progressBar;
    private JPanel curnersJpanel;
    private JPanel headerSeparator;
    private JButton érosionButton;
    private JButton dilatationButton;
    private JButton ouvertureButton;
    private JButton fermetureButton;

    File oImage;
    File fImage;

    private ArrowProgressBarUI progressBarUI;

    private final ExecutorService executorService;

    // Custom border class for rounded corners
    static class RoundedBorder extends AbstractBorder {

        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.setColor(c.getForeground());
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 1, this.radius + 1);
        }

        @Override
        public Insets getBorderInsets(Component c, Insets insets) {
            insets.left = insets.top = insets.right = insets.bottom = this.radius + 1;
            return insets;
        }
    }

    public home() {
        progressBar.setStringPainted(true);
        progressBar.setIndeterminate(true);


        //progressBar.setVisible(false);
        progressBarUI = new ArrowProgressBarUI();
        progressBar.setUI(progressBarUI);

        executorService = Executors.newVirtualThreadPerTaskExecutor();



        // Example filter button action listener
        moyenneur33Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                executorService.submit(() -> {
                    startSpin();
                    FiltersBasBasService filtersBasBasService = new FiltersBasBasService();
                    fImage = filtersBasBasService.Moyenneur33Filter(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });
            }
        });


        // drag and drop functionality
        screen.setTransferHandler(new TransferHandler() {
            @Override
            public boolean canImport(TransferSupport support) {
                return true;
            }

            @Override
            public boolean importData(TransferSupport support) {
                if (!canImport(support)) {
                    return false;
                }
                Transferable transferable = support.getTransferable();
                if (transferable.isDataFlavorSupported(DataFlavor.javaFileListFlavor)) {
                    try {
                        Object data = transferable.getTransferData(DataFlavor.javaFileListFlavor);
                        if (data instanceof java.util.List) {
                            for (Object item : (java.util.List) data) {
                                if (item instanceof File) {
                                    File file = (File) item;

                                    // check if image and convert it to RGB
                                    try {
                                        BufferedImage img = ImageIO.read(file);
                                        BufferedImage rgbImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
                                        rgbImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
                                        File newFile = new File("temp.png");
                                        ImageIO.write(rgbImage, "png", newFile);
                                        oImage = newFile;
                                        OptionsService optionsService = new OptionsService();
                                        optionsService.loadImage(file, originalmage);
                                    } catch (IOException ex) {
                                        throw new RuntimeException(ex);

                                    }
                                }
                            }
                        }
                    } catch (UnsupportedFlavorException | IOException e) {
                        e.printStackTrace();
                        return false;
                    }
                }
                return true;
            }
        });

        //  screen
        screen.setBackground(Color.decode("#c7d1c8"));


        // Initialize the originalmage JPanel with a image vieewer
        originalmage.setLayout(new BorderLayout());
        filtredImage.setLayout(new BorderLayout());

        // change the background color of the jpanels
        transformationJpanel.setBackground(Color.LIGHT_GRAY);
        FPHJpanel.setBackground(Color.LIGHT_GRAY);
        FFJpanel.setBackground(Color.LIGHT_GRAY);
        FPBJpanel.setBackground(Color.LIGHT_GRAY);
        bruitJpanel.setBackground(Color.LIGHT_GRAY);
        curnersJpanel.setBackground(Color.LIGHT_GRAY);

        // makeddivs ttransparent background
        div1.setOpaque(false);
        div1.setBackground(new Color(0, 0, 0, 0));

        div2.setOpaque(false);
        div2.setBackground(new Color(0, 0, 0, 0));

        div3.setOpaque(false);
        div3.setBackground(new Color(0, 0, 0, 0));

        div4.setOpaque(false);
        div4.setBackground(new Color(0, 0, 0, 0));

        div6.setOpaque(false);
        div6.setBackground(new Color(0, 0, 0, 0));

        div7.setOpaque(false);
        div7.setBackground(new Color(0, 0, 0, 0));

        actionsJpanel.setOpaque(false);
        actionsJpanel.setBackground(new Color(0, 0, 0, 0));

        headerSeparator.setOpaque(false);
        headerSeparator.setBackground(new Color(0, 0, 0, 0));


        // oopen button
        ouvrirButton.setFont(new Font("Arial", Font.BOLD, 14));
        ouvrirButton.setBackground(Color.GREEN);
        ouvrirButton.setForeground(Color.WHITE);
        ouvrirButton.setFocusPainted(false);
        ouvrirButton.setBorder(null);
        ouvrirButton.setContentAreaFilled(false);// 10 is the radius
        ouvrirButton.setContentAreaFilled(false);
        ouvrirButton.setOpaque(true);
        ouvrirButton.putClientProperty("JButton.buttonType", "roundRect");

        //save button
        enregistrerButton.setFont(new Font("Arial", Font.BOLD, 14));
        enregistrerButton.setBackground(new Color(0, 123, 255));
        enregistrerButton.setForeground(Color.WHITE);
        enregistrerButton.setFocusPainted(false);
        enregistrerButton.setBorder(null);
        enregistrerButton.setContentAreaFilled(false);// 10 is the radius
        enregistrerButton.setContentAreaFilled(false);
        enregistrerButton.setOpaque(true);
        enregistrerButton.putClientProperty("JButton.buttonType", "roundRect");


        // quit button
        quitterButton.setFont(new Font("Arial", Font.BOLD, 14));
        quitterButton.setBackground(Color.RED); // New background color
        quitterButton.setForeground(Color.WHITE);
        quitterButton.setFocusPainted(false);
        quitterButton.setBorder(null);
        quitterButton.setContentAreaFilled(false);
        quitterButton.setContentAreaFilled(false);
        quitterButton.setOpaque(true);
        quitterButton.putClientProperty("JButton.buttonType", "roundRect");


        // Add action listeners for buttons
        ouvrirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();

                    // convert the selected image to RGB
                    try {
                        BufferedImage img = ImageIO.read(selectedFile);
                        BufferedImage rgbImage = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
                        rgbImage.createGraphics().drawImage(img, 0, 0, Color.WHITE, null);
                        File newFile = new File("temp.png");
                        ImageIO.write(rgbImage, "png", newFile);
                        oImage = newFile;
                        OptionsService optionsService = new OptionsService();
                        optionsService.loadImage(selectedFile, originalmage);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);

                    }


                }
            }
        });

        enregistrerButton.addActionListener(new ActionListener() {
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
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(null, "Voulez-vous vraiment quitter l'application?", "Quitter", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {
                    OptionsService optionsService = new OptionsService();
                    optionsService.quitTheApplication();
                }
            }
        });

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
                startSpin();
                executorService.submit(() -> {
                    FiltersBasBasService filtersBasBasService = new FiltersBasBasService();
                    fImage = filtersBasBasService.Moyenneur33Filter(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        moyenneur55Button.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = FiltersBasBasService.moyenneur55(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        coniqueButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = FiltersBasBasService.coniqueFilter(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        gaussienne33Button.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = FiltersBasBasService.gaussienne33Filter(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        gaussienne55Button.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = FiltersBasBasService.gaussienne55Filter(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        pyramidalButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = FiltersBasBasService.pyramidalFilter(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        medianButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = FiltersBasBasService.medianFilter(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        laplacienButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = FiltersBasHautService.laplacienFilter(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        sobelButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = FiltersBasHautService.sobelFilter(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        gradientButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = FiltersBasHautService.gradientFilter(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        prewittButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = FiltersBasHautService.prewittFilter(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        robertButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = FiltersBasHautService.robertFilter(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        inversionButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = TransformationService.inversion(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        binarisationButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int suile = Integer.parseInt(suileTextField.getText());

                startSpin();
                executorService.submit(() -> {
                    fImage = TransformationService.binarisation(oImage, suile);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });


            }
        });
        contrasteButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int contraste = Integer.parseInt(contrasteTextField.getText());
                startSpin();
                executorService.submit(() -> {
                    fImage = TransformationService.contraste(oImage, contraste);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        divisionButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = TransformationService.division(oImage, 2);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        contrasteButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                int contraste = Integer.parseInt(contrasteTextField.getText());
                startSpin();
                executorService.submit(() -> {
                    fImage = TransformationService.contraste(oImage, contraste);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        divisionButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = TransformationService.division(oImage, 2);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        niveauDeGrisButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = TransformationService.niveauDeGris(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        histogramButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = TransformationService.histogram(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        contrast_amilorationButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {

                startSpin();
                executorService.submit(() -> {
                    fImage = TransformationService.enhanceContrast(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        FPBButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = FPB.applyButterworthLowPassFilter(oImage, 2, 2);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        FPBBButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = FPBB.applyButterworthHighPassFilter(oImage, 2, 2);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        FPHButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = FPH.applyHighPassFilter(oImage, 2, 2);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        FPHBButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = FPHB.applyButterworthHighPassFilter(oImage, 2, 2);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        gaussianNoise.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                double mean = 0;
                double variance = 0.1;

                startSpin();
                executorService.submit(() -> {
                    try {
                        fImage = Noise.gaussianNoise(oImage, mean, variance);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        poivreAndSelNoise.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    try {
                        fImage = Noise.poivreAndSelNoise(oImage);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        érosionButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = MorphologicalOperations.erosion(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        dilatationButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = MorphologicalOperations.dilation(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        ouvertureButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = MorphologicalOperations.opening(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
        fermetureButton.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                startSpin();
                executorService.submit(() -> {
                    fImage = MorphologicalOperations.closing(oImage);
                    OptionsService optionsService = new OptionsService();
                    optionsService.showFiltredImage(fImage, filtredImage);
                    stopSpin();
                });

            }
        });
    }

    public void startSpin() {
        progressBarUI.setProcessing(true);
        progressBar.setIndeterminate(true);
        progressBar.repaint();
    }

    public void stopSpin() {
        progressBarUI.setProcessing(false);
        progressBar.setIndeterminate(true);

        progressBar.repaint();
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

    public static File niveauDeGris(File image) {
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

    public static File histogram(File image) {
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

    public static File contrastAmiloration(File image, int p, int b) {
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