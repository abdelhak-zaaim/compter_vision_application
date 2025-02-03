package com.fsdm.cv;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class FiltersBasHautService {
    public static File laplacienFilter(File imageFile) {
        try {
            BufferedImage image = ImageIO.read(imageFile);
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage filteredImage = new BufferedImage(width, height, image.getType());

            int[][] laplacianKernel = {
                    {0, -1, 0},
                    {-1, 4, -1},
                    {0, -1, 0}
            };

            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    int sumRed = 0, sumGreen = 0, sumBlue = 0;

                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dx = -1; dx <= 1; dx++) {
                            int rgb = image.getRGB(x + dx, y + dy);
                            int kernelValue = laplacianKernel[dy + 1][dx + 1];

                            sumRed += ((rgb >> 16) & 0xFF) * kernelValue;
                            sumGreen += ((rgb >> 8) & 0xFF) * kernelValue;
                            sumBlue += (rgb & 0xFF) * kernelValue;
                        }
                    }

                    int newRed = Math.min(Math.max(sumRed, 0), 255);
                    int newGreen = Math.min(Math.max(sumGreen, 0), 255);
                    int newBlue = Math.min(Math.max(sumBlue, 0), 255);

                    int newRgb = (newRed << 16) | (newGreen << 8) | newBlue;
                    filteredImage.setRGB(x, y, newRgb);
                }
            }

            File outputFile = new File("laplacian_filtered_image.png");
            ImageIO.write(filteredImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File sobelFilter(File imageFile) {
        try {
            BufferedImage image = ImageIO.read(imageFile);
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage filteredImage = new BufferedImage(width, height, image.getType());

            int[][] sobelX = {
                    {-1, 0, 1},
                    {-2, 0, 2},
                    {-1, 0, 1}
            };

            int[][] sobelY = {
                    {-1, -2, -1},
                    {0, 0, 0},
                    {1, 2, 1}
            };

            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    int sumXRed = 0, sumXGreen = 0, sumXBlue = 0;
                    int sumYRed = 0, sumYGreen = 0, sumYBlue = 0;

                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dx = -1; dx <= 1; dx++) {
                            int rgb = image.getRGB(x + dx, y + dy);
                            int red = (rgb >> 16) & 0xFF;
                            int green = (rgb >> 8) & 0xFF;
                            int blue = rgb & 0xFF;

                            sumXRed += red * sobelX[dy + 1][dx + 1];
                            sumXGreen += green * sobelX[dy + 1][dx + 1];
                            sumXBlue += blue * sobelX[dy + 1][dx + 1];

                            sumYRed += red * sobelY[dy + 1][dx + 1];
                            sumYGreen += green * sobelY[dy + 1][dx + 1];
                            sumYBlue += blue * sobelY[dy + 1][dx + 1];
                        }
                    }

                    int newRed = Math.min(Math.max((int) Math.sqrt(sumXRed * sumXRed + sumYRed * sumYRed), 0), 255);
                    int newGreen = Math.min(Math.max((int) Math.sqrt(sumXGreen * sumXGreen + sumYGreen * sumYGreen), 0), 255);
                    int newBlue = Math.min(Math.max((int) Math.sqrt(sumXBlue * sumXBlue + sumYBlue * sumYBlue), 0), 255);

                    int newRgb = (newRed << 16) | (newGreen << 8) | newBlue;
                    filteredImage.setRGB(x, y, newRgb);
                }
            }

            File outputFile = new File("sobel_filtered_image.png");
            ImageIO.write(filteredImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File gradientFilter(File imageFile) {
        try {
            BufferedImage image = ImageIO.read(imageFile);
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage filteredImage = new BufferedImage(width, height, image.getType());

            int[][] gradientKernel = {
                    {-1, -1, -1},
                    {-1, 8, -1},
                    {-1, -1, -1}
            };

            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    int sumRed = 0, sumGreen = 0, sumBlue = 0;

                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dx = -1; dx <= 1; dx++) {
                            int rgb = image.getRGB(x + dx, y + dy);
                            int kernelValue = gradientKernel[dy + 1][dx + 1];

                            sumRed += ((rgb >> 16) & 0xFF) * kernelValue;
                            sumGreen += ((rgb >> 8) & 0xFF) * kernelValue;
                            sumBlue += (rgb & 0xFF) * kernelValue;
                        }
                    }

                    int newRed = Math.min(Math.max(sumRed, 0), 255);
                    int newGreen = Math.min(Math.max(sumGreen, 0), 255);
                    int newBlue = Math.min(Math.max(sumBlue, 0), 255);

                    int newRgb = (newRed << 16) | (newGreen << 8) | newBlue;
                    filteredImage.setRGB(x, y, newRgb);
                }
            }

            File outputFile = new File("gradient_filtered_image.png");
            ImageIO.write(filteredImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static File prewittFilter(File imageFile) {
        try {
            BufferedImage image = ImageIO.read(imageFile);
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage filteredImage = new BufferedImage(width, height, image.getType());

            int[][] prewittX = {
                    {-1, 0, 1},
                    {-1, 0, 1},
                    {-1, 0, 1}
            };

            int[][] prewittY = {
                    {-1, -1, -1},
                    {0, 0, 0},
                    {1, 1, 1}
            };

            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    int sumXRed = 0, sumXGreen = 0, sumXBlue = 0;
                    int sumYRed = 0, sumYGreen = 0, sumYBlue = 0;

                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dx = -1; dx <= 1; dx++) {
                            int rgb = image.getRGB(x + dx, y + dy);
                            int red = (rgb >> 16) & 0xFF;
                            int green = (rgb >> 8) & 0xFF;
                            int blue = rgb & 0xFF;

                            sumXRed += red * prewittX[dy + 1][dx + 1];
                            sumXGreen += green * prewittX[dy + 1][dx + 1];
                            sumXBlue += blue * prewittX[dy + 1][dx + 1];

                            sumYRed += red * prewittY[dy + 1][dx + 1];
                            sumYGreen += green * prewittY[dy + 1][dx + 1];
                            sumYBlue += blue * prewittY[dy + 1][dx + 1];
                        }
                    }

                    int newRed = Math.min(Math.max((int) Math.sqrt(sumXRed * sumXRed + sumYRed * sumYRed), 0), 255);
                    int newGreen = Math.min(Math.max((int) Math.sqrt(sumXGreen * sumXGreen + sumYGreen * sumYGreen), 0), 255);
                    int newBlue = Math.min(Math.max((int) Math.sqrt(sumXBlue * sumXBlue + sumYBlue * sumYBlue), 0), 255);

                    int newRgb = (newRed << 16) | (newGreen << 8) | newBlue;
                    filteredImage.setRGB(x, y, newRgb);
                }
            }

            File outputFile = new File("prewitt_filtered_image.png");
            ImageIO.write(filteredImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static File robertFilter(File imageFile) {
        try {
            BufferedImage image = ImageIO.read(imageFile);
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage filteredImage = new BufferedImage(width, height, image.getType());

            int[][] robertsX = {
                    {1, 0},
                    {0, -1}
            };

            int[][] robertsY = {
                    {0, 1},
                    {-1, 0}
            };

            for (int y = 0; y < height - 1; y++) {
                for (int x = 0; x < width - 1; x++) {
                    int sumXRed = 0, sumXGreen = 0, sumXBlue = 0;
                    int sumYRed = 0, sumYGreen = 0, sumYBlue = 0;

                    for (int dy = 0; dy <= 1; dy++) {
                        for (int dx = 0; dx <= 1; dx++) {
                            int rgb = image.getRGB(x + dx, y + dy);
                            int red = (rgb >> 16) & 0xFF;
                            int green = (rgb >> 8) & 0xFF;
                            int blue = rgb & 0xFF;

                            sumXRed += red * robertsX[dy][dx];
                            sumXGreen += green * robertsX[dy][dx];
                            sumXBlue += blue * robertsX[dy][dx];

                            sumYRed += red * robertsY[dy][dx];
                            sumYGreen += green * robertsY[dy][dx];
                            sumYBlue += blue * robertsY[dy][dx];
                        }
                    }

                    int newRed = Math.min(Math.max((int) Math.sqrt(sumXRed * sumXRed + sumYRed * sumYRed), 0), 255);
                    int newGreen = Math.min(Math.max((int) Math.sqrt(sumXGreen * sumXGreen + sumYGreen * sumYGreen), 0), 255);
                    int newBlue = Math.min(Math.max((int) Math.sqrt(sumXBlue * sumXBlue + sumYBlue * sumYBlue), 0), 255);

                    int newRgb = (newRed << 16) | (newGreen << 8) | newBlue;
                    filteredImage.setRGB(x, y, newRgb);
                }
            }

            File outputFile = new File("roberts_filtered_image.png");
            ImageIO.write(filteredImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

