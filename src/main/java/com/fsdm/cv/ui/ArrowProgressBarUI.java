package com.fsdm.cv.ui;

        import javax.swing.*;
        import javax.swing.plaf.basic.BasicProgressBarUI;
        import java.awt.*;
        import java.awt.geom.AffineTransform;

        public class ArrowProgressBarUI extends BasicProgressBarUI {
            @Override
            protected void paintIndeterminate(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = c.getWidth();
                int height = c.getHeight();
                int arrowWidth = width / 2;
                int arrowHeight = height / 2;

                // Apply rotation transformation
                AffineTransform originalTransform = g2d.getTransform();
                g2d.rotate(Math.toRadians(180), width / 2.0, height / 2.0);

                // Draw arrow shape
                Polygon arrow = new Polygon();
                arrow.addPoint(0, height / 2);
                arrow.addPoint(arrowWidth, 0);
                arrow.addPoint(arrowWidth, height / 4);
                arrow.addPoint(width, height / 4);
                arrow.addPoint(width, 3 * height / 4);
                arrow.addPoint(arrowWidth, 3 * height / 4);
                arrow.addPoint(arrowWidth, height);
                arrow.addPoint(0, height / 2);

                g2d.setColor(new Color(220, 220, 220));
                g2d.fill(arrow);

                // Animate color inside the arrow
                int offset = (int) (System.currentTimeMillis() % 1000 / 1000.0 * width);
                GradientPaint gradient = new GradientPaint(offset, 0, Color.BLUE, offset + arrowWidth, 0, Color.CYAN, true);
                g2d.setPaint(gradient);
                g2d.fill(arrow);

                // Restore original transformation
                g2d.setTransform(originalTransform);
            }
        }