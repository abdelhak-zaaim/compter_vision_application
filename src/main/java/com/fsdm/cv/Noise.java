package com.fsdm.cv;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

public class Noise {

    public static File gaussianNoise(File imageFile,double mean,double variance) throws IOException {
        // Read the image
        BufferedImage image = ImageIO.read(imageFile);
        Random rand = new Random();

        // Loop through all pixels
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                int rgb = image.getRGB(x, y);
                int a = (rgb >> 24) & 0xff;
                int r = (rgb >> 16) & 0xff;
                int g = (rgb >> 8) & 0xff;
                int b = rgb & 0xff;

                // Generate Gaussian noise for each channel
                r = clip((int) (r + (rand.nextGaussian() * variance * 255 + mean)));
                g = clip((int) (g + (rand.nextGaussian() * variance * 255 + mean)));
                b = clip((int) (b + (rand.nextGaussian() * variance * 255 + mean)));

                // Reassemble the pixel with noise
                image.setRGB(x, y, (a << 24) | (r << 16) | (g << 8) | b);
            }
        }

        // Create a new file to save the noisy image
        String filePath = imageFile.getPath();
        String newFilePath = filePath.substring(0, filePath.lastIndexOf('.')) + "_gaussianNoisy.png";
        File output = new File(newFilePath);

        // Write the processed image to disk
        ImageIO.write(image, "png", output);

        return output;
    }

    // Helper method to ensure RGB values are within 0-255 range
    private static int clip(int value) {
        return Math.max(0, Math.min(value, 255));
    }

    public static File poivreAndSelNoise(File originalImage) throws IOException {
        // Read the image
        BufferedImage image = ImageIO.read(originalImage);
        Random rand = new Random();

        // Probability of adding noise to a pixel
        double noiseDensity = 0.05;  // 5% of pixels will be affected

        // Loop through all pixels
        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                if (rand.nextDouble() < noiseDensity) {
                    // If we decide to add noise to this pixel
                    if (rand.nextBoolean()) {
                        // Salt noise (white)
                        image.setRGB(x, y, 0xFFFFFFFF);
                    } else {
                        // Pepper noise (black)
                        image.setRGB(x, y, 0xFF000000);
                    }
                }
            }
        }

        // Create a new file to save the noisy image
        String filePath = originalImage.getPath();
        String newFilePath = filePath.substring(0, filePath.lastIndexOf('.')) + "_noisy.png";
        File output = new File(newFilePath);

        // Write the processed image to disk
        ImageIO.write(image, "png", output);

        return output;
    }
}