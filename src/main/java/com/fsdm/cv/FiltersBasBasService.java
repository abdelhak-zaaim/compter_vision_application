package com.fsdm.cv;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FiltersBasBasService {
    public File Moyenneur33Filter(File oImage) {
        try {
            BufferedImage image = ImageIO.read(oImage);
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage filteredImage = new BufferedImage(width, height, image.getType());

            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    int sumRed = 0, sumGreen = 0, sumBlue = 0;

                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dx = -1; dx <= 1; dx++) {
                            int rgb = image.getRGB(x + dx, y + dy);
                            sumRed += (rgb >> 16) & 0xFF;
                            sumGreen += (rgb >> 8) & 0xFF;
                            sumBlue += rgb & 0xFF;
                        }
                    }

                    int avgRed = sumRed / 9;
                    int avgGreen = sumGreen / 9;
                    int avgBlue = sumBlue / 9;

                    int avgRgb = (avgRed << 16) | (avgGreen << 8) | avgBlue;
                    filteredImage.setRGB(x, y, avgRgb);
                }
            }

            File outputFile = new File("filtered_image.png");
            ImageIO.write(filteredImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File moyenneur55(File oImage) {
        try {
            BufferedImage image = ImageIO.read(oImage);
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage filteredImage = new BufferedImage(width, height, image.getType());

            for (int y = 2; y < height - 2; y++) {
                for (int x = 2; x < width - 2; x++) {
                    int sumRed = 0, sumGreen = 0, sumBlue = 0;

                    for (int dy = -2; dy <= 2; dy++) {
                        for (int dx = -2; dx <= 2; dx++) {
                            int rgb = image.getRGB(x + dx, y + dy);
                            sumRed += (rgb >> 16) & 0xFF;
                            sumGreen += (rgb >> 8) & 0xFF;
                            sumBlue += rgb & 0xFF;
                        }
                    }

                    int avgRed = sumRed / 25;
                    int avgGreen = sumGreen / 25;
                    int avgBlue = sumBlue / 25;

                    int avgRgb = (avgRed << 16) | (avgGreen << 8) | avgBlue;
                    filteredImage.setRGB(x, y, avgRgb);
                }
            }

            File outputFile = new File("filtered_image.png");
            ImageIO.write(filteredImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File coniqueFilter(File oImage) {
        try {
            BufferedImage image = ImageIO.read(oImage);
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage filteredImage = new BufferedImage(width, height, image.getType());

            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    int sumRed = 0, sumGreen = 0, sumBlue = 0;

                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dx = -1; dx <= 1; dx++) {
                            int rgb = image.getRGB(x + dx, y + dy);
                            sumRed += (rgb >> 16) & 0xFF;
                            sumGreen += (rgb >> 8) & 0xFF;
                            sumBlue += rgb & 0xFF;
                        }
                    }

                    int avgRed = sumRed / 9;
                    int avgGreen = sumGreen / 9;
                    int avgBlue = sumBlue / 9;

                    int avgRgb = (avgRed << 16) | (avgGreen << 8) | avgBlue;
                    filteredImage.setRGB(x, y, avgRgb);
                }
            }

            File outputFile = new File("filtered_image.png");
            ImageIO.write(filteredImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File gaussienne33Filter(File oImage) {
        try {
            BufferedImage image = ImageIO.read(oImage);
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage filteredImage = new BufferedImage(width, height, image.getType());

            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    int sumRed = 0, sumGreen = 0, sumBlue = 0;

                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dx = -1; dx <= 1; dx++) {
                            int rgb = image.getRGB(x + dx, y + dy);
                            sumRed += (rgb >> 16) & 0xFF;
                            sumGreen += (rgb >> 8) & 0xFF;
                            sumBlue += rgb & 0xFF;
                        }
                    }

                    int avgRed = sumRed / 9;
                    int avgGreen = sumGreen / 9;
                    int avgBlue = sumBlue / 9;

                    int avgRgb = (avgRed << 16) | (avgGreen << 8) | avgBlue;
                    filteredImage.setRGB(x, y, avgRgb);
                }
            }

            File outputFile = new File("filtered_image.png");
            ImageIO.write(filteredImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File gaussienne55Filter(File oImage) {
        try {
            BufferedImage image = ImageIO.read(oImage);
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage filteredImage = new BufferedImage(width, height, image.getType());

            for (int y = 2; y < height - 2; y++) {
                for (int x = 2; x < width - 2; x++) {
                    int sumRed = 0, sumGreen = 0, sumBlue = 0;

                    for (int dy = -2; dy <= 2; dy++) {
                        for (int dx = -2; dx <= 2; dx++) {
                            int rgb = image.getRGB(x + dx, y + dy);
                            sumRed += (rgb >> 16) & 0xFF;
                            sumGreen += (rgb >> 8) & 0xFF;
                            sumBlue += rgb & 0xFF;
                        }
                    }

                    int avgRed = sumRed / 25;
                    int avgGreen = sumGreen / 25;
                    int avgBlue = sumBlue / 25;

                    int avgRgb = (avgRed << 16) | (avgGreen << 8) | avgBlue;
                    filteredImage.setRGB(x, y, avgRgb);
                }
            }

            File outputFile = new File("filtered_image.png");
            ImageIO.write(filteredImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static File pyramidalFilter(File oImage) {
        try {
            BufferedImage image = ImageIO.read(oImage);
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage filteredImage = new BufferedImage(width, height, image.getType());

            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    int sumRed = 0, sumGreen = 0, sumBlue = 0;

                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dx = -1; dx <= 1; dx++) {
                            int rgb = image.getRGB(x + dx, y + dy);
                            sumRed += (rgb >> 16) & 0xFF;
                            sumGreen += (rgb >> 8) & 0xFF;
                            sumBlue += rgb & 0xFF;
                        }
                    }

                    int avgRed = sumRed / 9;
                    int avgGreen = sumGreen / 9;
                    int avgBlue = sumBlue / 9;

                    int avgRgb = (avgRed << 16) | (avgGreen << 8) | avgBlue;
                    filteredImage.setRGB(x, y, avgRgb);
                }
            }

            File outputFile = new File("filtered_image.png");
            ImageIO.write(filteredImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


    public static File medianFilter(File oImage) {
        try {
            BufferedImage image = ImageIO.read(oImage);
            int width = image.getWidth();
            int height = image.getHeight();
            BufferedImage filteredImage = new BufferedImage(width, height, image.getType());

            for (int y = 1; y < height - 1; y++) {
                for (int x = 1; x < width - 1; x++) {
                    int[] reds = new int[9];
                    int[] greens = new int[9];
                    int[] blues = new int[9];

                    int index = 0;
                    for (int dy = -1; dy <= 1; dy++) {
                        for (int dx = -1; dx <= 1; dx++) {
                            int rgb = image.getRGB(x + dx, y + dy);
                            reds[index] = (rgb >> 16) & 0xFF;
                            greens[index] = (rgb >> 8) & 0xFF;
                            blues[index] = rgb & 0xFF;
                            index++;
                        }
                    }

                    int medianRed = median(reds);
                    int medianGreen = median(greens);
                    int medianBlue = median(blues);

                    int medianRgb = (medianRed << 16) | (medianGreen << 8) | medianBlue;
                    filteredImage.setRGB(x, y, medianRgb);
                }
            }

            File outputFile = new File("filtered_image.png");
            ImageIO.write(filteredImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static int median(int[] values) {
        int[] sortedValues = values.clone();
        Arrays.sort(sortedValues);
        return sortedValues[sortedValues.length / 2];
    }
}