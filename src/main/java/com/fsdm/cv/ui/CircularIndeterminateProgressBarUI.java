package com.fsdm.cv.ui;

        import javax.swing.*;
        import javax.swing.plaf.basic.BasicProgressBarUI;
        import java.awt.*;

        public class CircularIndeterminateProgressBarUI extends BasicProgressBarUI {
            @Override
            protected void paintIndeterminate(Graphics g, JComponent c) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                int width = c.getWidth();
                int height = c.getHeight();
                int size = Math.min(width, height); // Adjust the size to be the same as the component
                int x = (width - size) / 2;
                int y = (height - size) / 2;

                // Draw background circle
                g2d.setColor(new Color(220, 220, 220));
                g2d.fillOval(x, y, size, size);

                // Draw gradient arc
                GradientPaint gradient = new GradientPaint(x, y, Color.BLUE, x + size, y + size, Color.CYAN, true);
                g2d.setPaint(gradient);
                int angle = (int) (System.currentTimeMillis() % 1000 / 1000.0 * 360);
                g2d.fillArc(x, y, size, size, angle, 90);

                // Draw inner circle
                g2d.setColor(Color.WHITE);
                int innerSize = size / 4;
                g2d.fillOval(x + innerSize, y + innerSize, size - 2 * innerSize, size - 2 * innerSize);
            }
        }