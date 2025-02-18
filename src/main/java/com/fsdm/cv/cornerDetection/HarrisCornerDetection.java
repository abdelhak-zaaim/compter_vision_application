package com.fsdm.cv.cornerDetection;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;

public class HarrisCornerDetection {

    public static File HarrisCornersDetection(File imageFile, double k, double threshold) {
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

            // Compute gradients
            double[][] gradientX = new double[height][width];
            double[][] gradientY = new double[height][width];
            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    gradientX[y][x] = (grayscale[y][x + 1] - grayscale[y][x - 1]) / 2.0;
                    gradientY[y][x] = (grayscale[y + 1][x] - grayscale[y - 1][x]) / 2.0;
                }
            }

            // Compute Harris response matrix
            double[][] harrisResponse = new double[height][width];
            double maxResponse = 0;
            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    double sumXX = 0, sumYY = 0, sumXY = 0;
                    for (int ky = -1; ky <= 1; ky++) {
                        for (int kx = -1; kx <= 1; kx++) {
                            double gx = gradientX[y + ky][x + kx];
                            double gy = gradientY[y + ky][x + kx];
                            sumXX += gx * gx;
                            sumYY += gy * gy;
                            sumXY += gx * gy;
                        }
                    }

                    double det = sumXX * sumYY - sumXY * sumXY;
                    double trace = sumXX + sumYY;
                    harrisResponse[y][x] = det - k * trace * trace;

                    if (harrisResponse[y][x] > maxResponse) {
                        maxResponse = harrisResponse[y][x];
                    }
                }
            }

            // normalize the harrisResponse matrix
            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    harrisResponse[y][x] = harrisResponse[y][x] * 1000 / maxResponse;
                }
            }

            // Detect corners
            BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    if (harrisResponse[y][x] > threshold) {
                        resultImage.setRGB(x, y, Color.RED.getRGB());
                    } else {
                        resultImage.setRGB(x, y, img.getRGB(x, y));
                    }
                }
            }

            // Save the result image
            File outputFile = new File("harris_corners_detected.png");
            ImageIO.write(resultImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}