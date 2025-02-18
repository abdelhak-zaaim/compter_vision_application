package com.fsdm.cv;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.util.Arrays;

public class CurnersDetections {
    public static File SusanCornersDetection(File imageFile, double threshold) {
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

            // Apply median and Gaussian blur
            grayscale = medianBlur(grayscale, 3);
            grayscale = gaussianBlur(grayscale, 5);

            // Perform Susan corner detection
            double[][] result = performSusanCornerDetection(grayscale, 3, threshold);

            // Convert result to BufferedImage
            BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    if (result[y][x] == 1) {
                        resultImage.setRGB(x, y, Color.RED.getRGB());
                    } else {
                        int gray = (int) grayscale[y][x];
                        int rgb = (gray << 16) | (gray << 8) | gray;
                        resultImage.setRGB(x, y, rgb);
                    }
                }
            }

            // Save the result image
            File outputFile = new File("susan_corners_detected.png");
            ImageIO.write(resultImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static double[][] medianBlur(double[][] image, int kernelSize) {
        int height = image.length;
        int width = image[0].length;
        double[][] result = new double[height][width];
        int radius = kernelSize / 2;

        for (int y = radius; y < height - radius; y++) {
            for (int x = radius; x < width - radius; x++) {
                double[] window = new double[kernelSize * kernelSize];
                int index = 0;
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        window[index++] = image[y + dy][x + dx];
                    }
                }
                Arrays.sort(window);
                result[y][x] = window[window.length / 2];
            }
        }
        return result;
    }

    private static double[][] gaussianBlur(double[][] image, int kernelSize) {
        int height = image.length;
        int width = image[0].length;
        double[][] result = new double[height][width];
        int radius = kernelSize / 2;
        double sigma = 1.0;
        double[][] kernel = new double[kernelSize][kernelSize];
        double sum = 0.0;

        for (int y = -radius; y <= radius; y++) {
            for (int x = -radius; x <= radius; x++) {
                kernel[y + radius][x + radius] = Math.exp(-(x * x + y * y) / (2 * sigma * sigma));
                sum += kernel[y + radius][x + radius];
            }
        }

        for (int y = 0; y < kernelSize; y++) {
            for (int x = 0; x < kernelSize; x++) {
                kernel[y][x] /= sum;
            }
        }

        for (int y = radius; y < height - radius; y++) {
            for (int x = radius; x < width - radius; x++) {
                double value = 0.0;
                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        value += image[y + dy][x + dx] * kernel[dy + radius][dx + radius];
                    }
                }
                result[y][x] = value;
            }
        }
        return result;
    }

    private static double[][] performSusanCornerDetection(double[][] image, int radius, double threshold) {
        int height = image.length;
        int width = image[0].length;
        double[][] result = new double[height][width];

        double g = 3 * Math.pow(2 * radius + 1, 2) / 4;

        for (int y = radius; y < height - radius; y++) {
            for (int x = radius; x < width - radius; x++) {
                double centerPixel = image[y][x];
                double similarPixels = 0;

                for (int dy = -radius; dy <= radius; dy++) {
                    for (int dx = -radius; dx <= radius; dx++) {
                        if (dx * dx + dy * dy <= radius * radius) {
                            double neighborPixel = image[y + dy][x + dx];
                            if (Math.abs(centerPixel - neighborPixel) < threshold) {
                                similarPixels++;
                            }
                        }
                    }
                }

                if (similarPixels < g / 2 && similarPixels > 0) {
                    result[y][x] = 1; // Mark corner
                } else {
                    result[y][x] = 0; // Not a corner
                }
            }
        }
        return result;
    }
}