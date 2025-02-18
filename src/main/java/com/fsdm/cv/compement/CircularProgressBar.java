package com.fsdm.cv.compement;


import javax.swing.*;
import java.awt.*;

public class CircularProgressBar extends JComponent {
    private int progress = 0;

    public void setProgress(int progress) {
        this.progress = progress;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        int size = Math.min(width, height);
        int x = (width - size) / 2;
        int y = (height - size) / 2;

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillOval(x, y, size, size);

        g2d.setColor(Color.BLUE);
        g2d.fillArc(x, y, size, size, 90, -360 * progress / 100);

        g2d.setColor(Color.WHITE);
        int innerSize = size / 4;
        g2d.fillOval(x + innerSize, y + innerSize, size - 2 * innerSize, size - 2 * innerSize);
    }
}