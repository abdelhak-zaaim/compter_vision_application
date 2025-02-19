package com.fsdm.cv;


import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class MorphologicalOperations {
   public static File erosion(File image) {
      try {
         BufferedImage imageBuffered = ImageIO.read(image);
         int width = imageBuffered.getWidth();
         int height = imageBuffered.getHeight();
         BufferedImage erodedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

         // Define a 3x3 structuring element (square)
         int[][] structuringElement = {
                 {1, 1, 1},
                 {1, 1, 1},
                 {1, 1, 1}
         };

         for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
               int minPixel = 255; // Start with the maximum grayscale value

               // Apply the structuring element
               for (int j = -1; j <= 1; j++) {
                  for (int i = -1; i <= 1; i++) {
                     int pixel = new java.awt.Color(imageBuffered.getRGB(x + i, y + j)).getRed();
                     if (structuringElement[j + 1][i + 1] == 1) {
                        minPixel = Math.min(minPixel, pixel);
                     }
                  }
               }

               // Set the minimum value to the current pixel
               int newRgb = (minPixel << 16) | (minPixel << 8) | minPixel;
               erodedImage.setRGB(x, y, newRgb);
            }
         }

         File outputFile = new File("eroded_image.png");
         ImageIO.write(erodedImage, "png", outputFile);
         return outputFile;
      } catch (IOException e) {
         e.printStackTrace();
         return null;
      }
   }

   public static File dilation(File image) {
      try {
         BufferedImage imageBuffered = ImageIO.read(image);
         int width = imageBuffered.getWidth();
         int height = imageBuffered.getHeight();
         BufferedImage dilatedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

         // Define a 3x3 structuring element (square)
         int[][] structuringElement = {
                 {1, 1, 1},
                 {1, 1, 1},
                 {1, 1, 1}
         };

         for (int y = 1; y < height - 1; y++) {
            for (int x = 1; x < width - 1; x++) {
               int maxPixel = 0; // Start with the minimum grayscale value

               // Apply the structuring element
               for (int j = -1; j <= 1; j++) {
                  for (int i = -1; i <= 1; i++) {
                     int pixel = new java.awt.Color(imageBuffered.getRGB(x + i, y + j)).getRed();
                     if (structuringElement[j + 1][i + 1] == 1) {
                        maxPixel = Math.max(maxPixel, pixel);
                     }
                  }
               }

               // Set the maximum value to the current pixel
               int newRgb = (maxPixel << 16) | (maxPixel << 8) | maxPixel;
               dilatedImage.setRGB(x, y, newRgb);
            }
         }

         File outputFile = new File("dilated_image.png");
         ImageIO.write(dilatedImage, "png", outputFile);
         return outputFile;
      } catch (IOException e) {
         e.printStackTrace();
         return null;
      }
   }


   public static BufferedImage erosion(BufferedImage image) {
      int width = image.getWidth();
      int height = image.getHeight();
      BufferedImage erodedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

      // Define a 3x3 structuring element (square)
      int[][] structuringElement = {
              {1, 1, 1},
              {1, 1, 1},
              {1, 1, 1}
      };

      for (int y = 1; y < height - 1; y++) {
         for (int x = 1; x < width - 1; x++) {
            int minPixel = 255;

            for (int j = -1; j <= 1; j++) {
               for (int i = -1; i <= 1; i++) {
                  int pixel = new java.awt.Color(image.getRGB(x + i, y + j)).getRed();
                  if (structuringElement[j + 1][i + 1] == 1) {
                     minPixel = Math.min(minPixel, pixel);
                  }
               }
            }

            int newRgb = (minPixel << 16) | (minPixel << 8) | minPixel;
            erodedImage.setRGB(x, y, newRgb);
         }
      }

      return erodedImage;
   }

   public static BufferedImage dilation(BufferedImage image) {
      int width = image.getWidth();
      int height = image.getHeight();
      BufferedImage dilatedImage = new BufferedImage(width, height, BufferedImage.TYPE_BYTE_GRAY);

      // Define a 3x3 structuring element (square)
      int[][] structuringElement = {
              {1, 1, 1},
              {1, 1, 1},
              {1, 1, 1}
      };

      for (int y = 1; y < height - 1; y++) {
         for (int x = 1; x < width - 1; x++) {
            int maxPixel = 0;

            for (int j = -1; j <= 1; j++) {
               for (int i = -1; i <= 1; i++) {
                  int pixel = new java.awt.Color(image.getRGB(x + i, y + j)).getRed();
                  if (structuringElement[j + 1][i + 1] == 1) {
                     maxPixel = Math.max(maxPixel, pixel);
                  }
               }
            }

            int newRgb = (maxPixel << 16) | (maxPixel << 8) | maxPixel;
            dilatedImage.setRGB(x, y, newRgb);
         }
      }

      return dilatedImage;
   }

   public static File opening(File imageFile) {
      try {
         BufferedImage imageBuffered = ImageIO.read(imageFile);

         // Step 1: Apply Erosion
         BufferedImage erodedImage = erosion(imageBuffered);

         // Step 2: Apply Dilation on the Eroded Image
         BufferedImage openedImage = dilation(erodedImage);

         File outputFile = new File("opened_image.png");
         ImageIO.write(openedImage, "png", outputFile);
         return outputFile;
      } catch (IOException e) {
         e.printStackTrace();
         return null;
      }
   }

   public static File closing(File imageFile) {
      try {
         BufferedImage imageBuffered = ImageIO.read(imageFile);

         // Step 1: Apply Dilation
         BufferedImage dilatedImage = dilation(imageBuffered);

         // Step 2: Apply Erosion on the Dilated Image
         BufferedImage closedImage = erosion(dilatedImage);

         File outputFile = new File("closed_image.png");
         ImageIO.write(closedImage, "png", outputFile);
         return outputFile;
      } catch (IOException e) {
         e.printStackTrace();
         return null;
      }
   }

}
