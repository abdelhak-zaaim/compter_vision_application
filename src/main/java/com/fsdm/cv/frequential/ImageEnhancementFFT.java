package com.fsdm.cv.frequential;


import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.jtransforms.fft.DoubleFFT_2D;

public class ImageEnhancementFFT {

    public static File applyBandPassFilter(File imageFile, double lowCutoffFrequency, double highCutoffFrequency) {
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

            // Apply FFT
            DoubleFFT_2D fft = new DoubleFFT_2D(height, width);
            double[][] fftData = new double[height][2 * width];
            for (int y = 0; y < height; y++) {
                System.arraycopy(grayscale[y], 0, fftData[y], 0, width);
            }
            fft.realForwardFull(fftData);

            // Apply Band Pass filter
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    double distance = Math.sqrt(Math.pow(y - height / 2, 2) + Math.pow(x - width / 2, 2));
                    if (distance < lowCutoffFrequency || distance > highCutoffFrequency) {
                        fftData[y][2 * x] = 0;
                        fftData[y][2 * x + 1] = 0;
                    }
                }
            }

            // Apply inverse FFT
            fft.realInverseFull(fftData, true);
            double[][] filteredImage = new double[height][width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    filteredImage[y][x] = fftData[y][2 * x];
                }
            }

            // Normalize and convert to BufferedImage
            BufferedImage resultImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int value = (int) Math.min(Math.max(filteredImage[y][x], 0), 255);
                    int gray = (value << 16) | (value << 8) | value;
                    resultImage.setRGB(x, y, gray);
                }
            }

            // Save the result image
            File outputFile = new File("enhanced_image.png");
            ImageIO.write(resultImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        File inputFile = new File("input_image.png");
        double lowCutoffFrequency = 10.0; // Example low cutoff frequency
        double highCutoffFrequency = 50.0; // Example high cutoff frequency
        File outputFile = applyBandPassFilter(inputFile, lowCutoffFrequency, highCutoffFrequency);
        if (outputFile != null) {
            System.out.println("Enhanced image saved to " + outputFile.getPath());
        } else {
            System.out.println("Failed to enhance image.");
        }
    }
}
