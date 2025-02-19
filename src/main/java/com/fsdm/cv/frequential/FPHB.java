package com.fsdm.cv.frequential;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import org.jtransforms.fft.DoubleFFT_2D;

public class FPHB {

    public static File applyButterworthHighPassFilter(File imageFile, double cutoffFrequency, int filterOrder) {
        try {
            BufferedImage img = ImageIO.read(imageFile);
            int width = img.getWidth();
            int height = img.getHeight();

            // Convert image to grayscale if it's RGB
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

            // Convert the image to double for computation
            double[][] imgDouble = grayscale;

            // Frequency domain coordinates
            double[][] u = new double[height][width];
            double[][] v = new double[height][width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    u[y][x] = x - width / 2.0;
                    v[y][x] = y - height / 2.0;
                }
            }

            // Create High-Pass Butterworth filter
            double[][] D = new double[height][width];
            double[][] H_filter = new double[height][width];
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    D[y][x] = Math.sqrt(u[y][x] * u[y][x] + v[y][x] * v[y][x]);
                    H_filter[y][x] = 1.0 / (1.0 + Math.pow(D[y][x] / cutoffFrequency, 2 * filterOrder));
                }
            }

            // Apply FFT
            DoubleFFT_2D fft = new DoubleFFT_2D(height, width);
            double[][] fftData = new double[height][2 * width];
            for (int y = 0; y < height; y++) {
                System.arraycopy(imgDouble[y], 0, fftData[y], 0, width);
            }
            fft.realForwardFull(fftData);

            // Apply the High-Pass Butterworth filter
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    fftData[y][2 * x] *= H_filter[y][x];
                    fftData[y][2 * x + 1] *= H_filter[y][x];
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
            File outputFile = new File("filtered_image.png");
            ImageIO.write(resultImage, "png", outputFile);
            return outputFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}