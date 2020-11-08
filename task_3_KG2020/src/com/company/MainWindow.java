package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow extends JFrame {
    private DrawPanel dp;

    public MainWindow() throws HeadlessException {
        dp = new DrawPanel();
        JPanel panel = new JPanel();
        JButton buttonCurve1 = new JButton("Ввести первую кривую");
        buttonCurve1.setPreferredSize(new Dimension(200,50));
        JButton buttonCurve2 = new JButton("Ввести вторую кривую");
        buttonCurve2.setPreferredSize(new Dimension(200,50));
        JButton buttonMove= new JButton("Постороить переход");
        buttonMove.setPreferredSize(new Dimension(200,50));
        JLabel label = new JLabel("Введите время");
        JTextField textTimer = new JTextField();
        label.setPreferredSize(new Dimension(200,50));
        textTimer.setPreferredSize(new Dimension(200,50));
        buttonMove.setPreferredSize(new Dimension(200,50));
        dp.setPreferredSize(new Dimension(500, 500));
        panel.add(buttonCurve1);
        panel.add(buttonCurve2);
        panel.add(buttonMove);
        panel.add(label);
        panel.add(textTimer);
        panel.add(dp);
        add(panel);
        setVisible(true);

        buttonCurve1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dp.setIs1lineComplete(true);
            }
        });

        buttonCurve2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dp.setIs2lineComplete(true);
            }
        });

        buttonMove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dp.setStartMove(true);
                dp.setTime(Integer.parseInt(textTimer.getText()));
                dp.repaint();
            }
        });
    }
}
