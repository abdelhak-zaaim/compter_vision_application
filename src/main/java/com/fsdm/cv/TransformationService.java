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

    public static File binarisation(File image, int suile) {
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

    public static File contraste(File image, int contraste) {
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

    public static File division(File image, int division) {
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

    public static File enhanceContrast(File inputFile) {
        try {
            BufferedImage img = ImageIO.read(inputFile);
            int width = img.getWidth();
            int height = img.getHeight();

            // Convert image to double array
            double[][][] imgArray = new double[height][width][3];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = img.getRGB(x, y);
                    imgArray[y][x][0] = (rgb >> 16) & 0xFF;
                    imgArray[y][x][1] = (rgb >> 8) & 0xFF;
                    imgArray[y][x][2] = rgb & 0xFF;
                }
            }

            // Compute min and max pixel values in the image
            double min = Double.MAX_VALUE;
            double max = Double.MIN_VALUE;
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    for (int c = 0; c < 3; c++) {
                        min = Math.min(min, imgArray[y][x][c]);
                        max = Math.max(max, imgArray[y][x][c]);
                    }
                }
            }

            // Compute P and B
            double P = 255.0 / (max - min);
            double B = -P * min;

            // Apply contrast enhancement
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    for (int c = 0; c < 3; c++) {
                        imgArray[y][x][c] = P * imgArray[y][x][c] + B;
                        imgArray[y][x][c] = Math.max(0, Math.min(255, imgArray[y][x][c]));
                    }
                }
            }

            // Convert double array back to BufferedImage
            BufferedImage enhancedImg = new BufferedImage(width, height, img.getType());
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int red = (int) imgArray[y][x][0];
                    int green = (int) imgArray[y][x][1];
                    int blue = (int) imgArray[y][x][2];
                    int rgb = (red << 16) | (green << 8) | blue;
                    enhancedImg.setRGB(x, y, rgb);
                }
            }

            // Save the enhanced image
            File outputFile = new File("output.png");
            ImageIO.write(enhancedImg, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}