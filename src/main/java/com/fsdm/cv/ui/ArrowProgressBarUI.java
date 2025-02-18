package com.fsdm.cv.ui;

                import javax.swing.*;
                import javax.swing.plaf.basic.BasicProgressBarUI;
                import java.awt.*;
                import java.awt.geom.AffineTransform;

                public class ArrowProgressBarUI extends BasicProgressBarUI {
                    private boolean isProcessing = false;

                    public void setProcessing(boolean processing) {
                        isProcessing = processing;
                    }

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

                        g2d.setColor(new Color(137, 168, 178));
                        g2d.fill(arrow);

                        // Set color based on processing state
                        if (isProcessing) {
                            int offset = (int) (System.currentTimeMillis() % 1000 / 1000.0 * width);
                            GradientPaint gradient = new GradientPaint(offset, 0, new Color(137, 168, 178), offset + arrowWidth, 0, new Color(229, 225, 218), true);
                            g2d.setPaint(gradient);
                        } else {
                            g2d.setColor(Color.BLUE);
                        }
                        g2d.fill(arrow);

                        // Restore original transformation
                        g2d.setTransform(originalTransform);
                    }
                }