package com.fsdm.cv.ui;


import javax.swing.*;
import javax.swing.plaf.basic.BasicProgressBarUI;
import java.awt.*;

public class CircularProgressBarUI extends BasicProgressBarUI {
    @Override
    protected void paintDeterminate(Graphics g, JComponent c) {
        paintCircular(g, c);
    }

    @Override
    protected void paintIndeterminate(Graphics g, JComponent c) {
        paintCircular(g, c);
    }

    private void paintCircular(Graphics g, JComponent c) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = c.getWidth();
        int height = c.getHeight();
        int size = Math.min(width, height);
        int x = (width - size) / 2;
        int y = (height - size) / 2;

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillOval(x, y, size, size);

        g2d.setColor(Color.BLUE);
        int progress = progressBar.getValue();
        g2d.fillArc(x, y, size, size, 90, -360 * progress / 100);

        g2d.setColor(Color.WHITE);
        int innerSize = size / 4;
        g2d.fillOval(x + innerSize, y + innerSize, size - 2 * innerSize, size - 2 * innerSize);
    }
}
