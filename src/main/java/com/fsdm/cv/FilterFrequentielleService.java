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

            float[] kernel = {
                    1/16f, 1/8f, 1/16f,
                    1/8f, 1/4f, 1/8f,
                    1/16f, 1/8f, 1/16f
            };

            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    float sumRed = 0, sumGreen = 0, sumBlue = 0;

                    for (int ky = -1; ky <= 1; ky++) {
                        for (int kx = -1; kx <= 1; kx++) {
                            int rgb = image.getRGB(x + kx, y + ky);
                            int red = (rgb >> 16) & 0xFF;
                            int green = (rgb >> 8) & 0xFF;
                            int blue = rgb & 0xFF;

                            float kernelValue = kernel[(ky + 1) * 3 + (kx + 1)];
                            sumRed += red * kernelValue;
                            sumGreen += green * kernelValue;
                            sumBlue += blue * kernelValue;
                        }
                    }

                    int newRed = Math.min(Math.max((int) sumRed, 0), 255);
                    int newGreen = Math.min(Math.max((int) sumGreen, 0), 255);
                    int newBlue = Math.min(Math.max((int) sumBlue, 0), 255);

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