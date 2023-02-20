package com.link_intersystems.car.ui;

import com.link_intersystems.car.offers.ui.CarOfferView;
import com.link_intersystems.swing.ComponentUtils;

import javax.swing.*;
import java.awt.*;

import static javax.swing.WindowConstants.*;

public class MainFrame {

    private JFrame mainFrame = new JFrame();

    public MainFrame(CarOfferView carOfferView) {
        mainFrame.setSize(800, 600);
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);

        ComponentUtils.centerOnScreen(mainFrame);

        Container contentPane = mainFrame.getContentPane();
        Component viewComponent = carOfferView.getViewComponent();
        contentPane.add(viewComponent, BorderLayout.CENTER);
    }


    public void show() {
        mainFrame.setVisible(true);
    }
}