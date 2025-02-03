package com.fsdm.cv;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class home {
    private JButton button1;
    private JPanel screen;

    public home() {
        button1.addActionListener(new ActionListener() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                button1.setText("its clicked");
            }
        });
        button1.addComponentListener(new ComponentAdapter() {
            /**
             * @param e the event to be processed
             */
            @Override
            public void componentResized(ComponentEvent e) {

                super.componentResized(e);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("home");
        frame.setContentPane(new home().screen);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
