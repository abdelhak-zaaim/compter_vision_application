package com.fsdm.cv;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TransformationService {
    public static File inversion(File selectedFile) {
        try {
            BufferedImage image = ImageIO.read(selectedFile);
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage invertedImage = new BufferedImage(width, height, image.getType());

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);
                    int red = 255 - ((rgb >> 16) & 0xFF);
                    int green = 255 - ((rgb >> 8) & 0xFF);
                    int blue = 255 - (rgb & 0xFF);

                    int invertedRgb = (red << 16) | (green << 8) | blue;
                    invertedImage.setRGB(x, y, invertedRgb);
                }
            }

            File outputFile = new File("inverted_image.png");
            ImageIO.write(invertedImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File binarisation(File image, int suile){
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
                    int binarisedRgb = gray < suile ? 0 : 0xFFFFFF;
                    binarisedImage.setRGB(x, y, binarisedRgb);
                }
            }
            File outputFile = new File("binarised_image.png");
            ImageIO.write(binarisedImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File contraste(File image, int contraste){
        try {
            BufferedImage imageBuffered = ImageIO.read(image);
            int width = imageBuffered.getWidth();
            int height = imageBuffered.getHeight();
            BufferedImage contrasteImage = new BufferedImage(width, height, imageBuffered.getType());
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = imageBuffered.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;
                    int newRed = red + contraste;
                    int newGreen = green + contraste;
                    int newBlue = blue + contraste;
                    int contrasteRgb = (newRed << 16) | (newGreen << 8) | newBlue;
                    contrasteImage.setRGB(x, y, contrasteRgb);
                }
            }
            File outputFile = new File("contraste_image.png");
            ImageIO.write(contrasteImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File division(File image, int division){
        try {
            BufferedImage imageBuffered = ImageIO.read(image);
            int width = imageBuffered.getWidth();
            int height = imageBuffered.getHeight();
            BufferedImage divisionImage = new BufferedImage(width, height, imageBuffered.getType());
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = imageBuffered.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;
                    int newRed = red / division;
                    int newGreen = green / division;
                    int newBlue = blue / division;
                    int divisionRgb = (newRed << 16) | (newGreen << 8) | newBlue;
                    divisionImage.setRGB(x, y, divisionRgb);
                }
            }
            File outputFile = new File("division_image.png");
            ImageIO.write(divisionImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
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

    public static File histogram(File oImage) {
        try {
            BufferedImage image = ImageIO.read(oImage);
            int width = image.getWidth();
            int height = image.getHeight();
            int[] histogram = new int[256];

            // Calculate histogram
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;
                    int gray = (red + green + blue) / 3;
                    histogram[gray]++;
                }
            }

            // Find the maximum value in the histogram
            int max = 0;
            for (int i = 0; i < 256; i++) {
                if (histogram[i] > max) {
                    max = histogram[i];
                }
            }

            // Create the histogram image
            BufferedImage histogramImage = new BufferedImage(256, 100, BufferedImage.TYPE_INT_ARGB);
            for (int x = 0; x < 256; x++) {
                int value = (int) ((histogram[x] / (double) max) * 100);
                for (int y = 0; y < 100; y++) {
                    int rgb = (y < value) ? 0xFF000000 : 0xFFFFFFFF;
                    histogramImage.setRGB(x, 99 - y, rgb);
                }
            }

            // Save the histogram image
            File outputFile = new File("histogram_image.png");
            ImageIO.write(histogramImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File contrastAmiloration(File oImage, int p, int b) {
        try {
            BufferedImage image = ImageIO.read(oImage);
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage contrastAmilorationImage = new BufferedImage(width, height, image.getType());

            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = image.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;

                    int newRed = p * red + b;
                    int newGreen = p * green + b;
                    int newBlue = p * blue + b;

                    if (newRed > 255) {
                        newRed = 255;
                    } else if (newRed < 0) {
                        newRed = 0;
                    }

                    if (newGreen > 255) {
                        newGreen = 255;
                    } else if (newGreen < 0) {
                        newGreen = 0;
                    }

                    if (newBlue > 255) {
                        newBlue = 255;
                    } else if (newBlue < 0) {
                        newBlue = 0;
                    }

                    int contrastAmilorationRgb = (newRed << 16) | (newGreen << 8) | newBlue;
                    contrastAmilorationImage.setRGB(x, y, contrastAmilorationRgb);
                }
            }

            File outputFile = new File("contrast_amiloration_image.png");
            ImageIO.write(contrastAmilorationImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}