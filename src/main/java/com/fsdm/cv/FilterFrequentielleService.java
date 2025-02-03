package com.fsdm.cv;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FilterFrequentielleService {
    public static File FPBFilter(File imageFile) {
        try {
            BufferedImage image = ImageIO.read(imageFile);
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage filteredImage = new BufferedImage(width, height, image.getType());

            // Apply a low-pass filter in the frequency domain
            // This is a simplified example and may not be a true frequency domain filter
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;

                    // Apply a simple low-pass filter
                    int newRed = (int) (red * 0.5);
                    int newGreen = (int) (green * 0.5);
                    int newBlue = (int) (blue * 0.5);

                    int newRgb = (newRed << 16) | (newGreen << 8) | newBlue;
                    filteredImage.setRGB(x, y, newRgb);
                }
            }

            File outputFile = new File("FPB_filtered_image.png");
            ImageIO.write(filteredImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File FPBBFilter(File image){
        try {
            BufferedImage imageBuffered = ImageIO.read(image);
            int width = imageBuffered.getWidth();
            int height = imageBuffered.getHeight();
            BufferedImage binarisedImage = new BufferedImage(width, height, imageBuffered.getType());
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = imageBuffered.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;
                    int gray = (red + green + blue) / 3;
                    int binarisedRgb = gray < 128 ? 0 : 0xFFFFFF;
                    binarisedImage.setRGB(x, y, binarisedRgb);
                }
            }
            File outputFile = new File("FPBB_filtered_image.png");
            ImageIO.write(binarisedImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static File FPHFilter(File image){
        try {
            BufferedImage imageBuffered = ImageIO.read(image);
            int width = imageBuffered.getWidth();
            int height = imageBuffered.getHeight();
            BufferedImage binarisedImage = new BufferedImage(width, height, imageBuffered.getType());
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = imageBuffered.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;
                    int gray = (red + green + blue) / 3;
                    int binarisedRgb = gray < 128 ? 0 : 0xFFFFFF;
                    binarisedImage.setRGB(x, y, binarisedRgb);
                }
            }
            File outputFile = new File("FPH_filtered_image.png");
            ImageIO.write(binarisedImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File FPHBFilter(File image){
        try {
            BufferedImage imageBuffered = ImageIO.read(image);
            int width = imageBuffered.getWidth();
            int height = imageBuffered.getHeight();
            BufferedImage binarisedImage = new BufferedImage(width, height, imageBuffered.getType());
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = imageBuffered.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;
                    int gray = (red + green + blue) / 3;
                    int binarisedRgb = gray < 128 ? 0 : 0xFFFFFF;
                    binarisedImage.setRGB(x, y, binarisedRgb);
                }
            }
            File outputFile = new File("FPHB_filtered_image.png");
            ImageIO.write(binarisedImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}