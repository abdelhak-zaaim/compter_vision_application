package com.fsdm.cv.cornerDetection;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class ElectrostaticModelCurnersDetection {

    public static File ElectrostaticCornersDetection(File imageFile, double threshold) {
        try {
            BufferedImage img = ImageIO.read(imageFile);
            int width = img.getWidth();
            int height = img.getHeight();

            // Convert image to grayscale
            double[][] grayscale = new double[height][width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgb = img.getRGB(x, y);
                    int red = (rgb >> 16) & 0xFF;
                    int green = (rgb >> 8) & 0xFF;
                    int blue = rgb & 0xFF;
                    grayscale[y][x] = (red + green + blue) / 3.0;
                }
            }


            // Apply electrostatic model for corner detection
            double[][] result = performElectrostaticCornerDetection(grayscale, threshold);

            // Convert result to BufferedImage
            BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (result[y][x] == 1) {
                        resultImage.setRGB(x, y, Color.RED.getRGB());
                    } else {
                        int gray = (int) grayscale[y][x];
                        resultImage.setRGB(x, y, img.getRGB(x, y));
                    }
                }
            }

            // Save the result image
            File outputFile = new File("electrostatic_corners_detected.png");
            ImageIO.write(resultImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static double[][] performElectrostaticCornerDetection(double[][] image, double threshold) {
        int height = image.length;
        int width = image[0].length;
        double[][] result = new double[height][width];


        // Implement the electrostatic model for corner detection
        // This is a placeholder for the actual algorithm
        for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
                double centerPixel = image[y][x];
                double electrostaticForce = 0;

                // Calculate electrostatic force (placeholder logic)
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dx = -1; dx <= 1; dx++) {
                        if (dx != 0 || dy != 0) {
                            double neighborPixel = image[y + dy][x + dx];
                            electrostaticForce += Math.abs(centerPixel - neighborPixel);
                        }
                    }
                }

                if (electrostaticForce > threshold) {
                    result[y][x] = 1; // Mark corner
                } else {
                    result[y][x] = 0; // Not a corner
                }
            }
        }
        return result;
    }
}