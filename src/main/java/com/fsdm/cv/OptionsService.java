package com.fsdm.cv;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class OptionsService {
    public void loadImage(File selectedFile, JPanel originalmage) {
        try {
            BufferedImage oImage = ImageIO.read(selectedFile);

            int width = originalmage.getWidth();
            int height = (int) (oImage.getHeight() * ((double) width / oImage.getWidth()));
            if (height > originalmage.getHeight()) {
                height = originalmage.getHeight();
                width = (int) (oImage.getWidth() * ((double) height / oImage.getHeight()));
            }
            Image scaledImage = oImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
            originalmage.removeAll();
            // make the image completely shown
            imageLabel.setHorizontalAlignment(JLabel.CENTER);
            imageLabel.setVerticalAlignment(JLabel.CENTER);

            originalmage.add(imageLabel, BorderLayout.CENTER);

            originalmage.revalidate();
            originalmage.repaint();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void showFiltredImage(BufferedImage fImage, JPanel filtredImage) {
        int width = filtredImage.getWidth();
        int height = (int) (fImage.getHeight() * ((double) width / fImage.getWidth()));
        if (height > filtredImage.getHeight()) {
            height = filtredImage.getHeight();
            width = (int) (fImage.getWidth() * ((double) height / fImage.getHeight()));
        }
        Image scaledImage = fImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        JLabel imageLabel = new JLabel(new ImageIcon(scaledImage));
        filtredImage.removeAll();
        // make the image completely shown
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setVerticalAlignment(JLabel.CENTER);

        filtredImage.add(imageLabel, BorderLayout.CENTER);

        filtredImage.revalidate();
        filtredImage.repaint();
    }

    public void saveImage(File selectedFile, BufferedImage fImage) {
        try {
            ImageIO.write(fImage, "png", selectedFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public  void quitTheApplication() {
        System.exit(0);
    }
}