package com.car_rental_system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class WelcomeFrame extends JFrame {

    private JButton startButton;

    // =========================================================
    // COLORS
    // =========================================================

    private final Color BG =
            new Color(235, 245, 255);

    private final Color BLUE =
            new Color(25, 118, 210);

    private final Color TEXT =
            new Color(70, 70, 70);

    private final Color LIGHT_TEXT =
            new Color(100, 100, 100);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public WelcomeFrame() {

        setTitle("DriveX");

        /*
         * Use the available screen size.
         * The frame will automatically adjust
         * to different monitor/laptop resolutions.
         */
        setExtendedState(JFrame.MAXIMIZED_BOTH);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setResizable(true);

        createUI();
    }

    // =========================================================
    // MAIN UI
    // =========================================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(BG);

        setContentPane(mainPanel);

        // Header
        createHeader(mainPanel);

        // Center
        createCenterContent(mainPanel);

        // Footer
        createFooter(mainPanel);
    }

    // =========================================================
    // HEADER
    // =========================================================

    private void createHeader(JPanel mainPanel) {

        JPanel headerPanel =
                new JPanel(
                        new GridBagLayout()
                );

        headerPanel.setBackground(BLUE);

        /*
         * Preferred height.
         * Width automatically expands with window.
         */
        headerPanel.setPreferredSize(
                new Dimension(
                        0,
                        130
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.anchor =
                GridBagConstraints.CENTER;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // ================= BRAND =================

        JLabel title =
                new JLabel("DriveX");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        38
                )
        );

        title.setForeground(Color.WHITE);

        title.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        gbc.insets =
                new Insets(
                        10,
                        20,
                        2,
                        20
                );

        headerPanel.add(
                title,
                gbc
        );

        // ================= TAGLINE =================

        gbc.gridy = 1;

        gbc.insets =
                new Insets(
                        0,
                        20,
                        10,
                        20
                );

        JLabel subtitle =
                new JLabel(
                        "Smart Mobility. Better Journeys."
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        17
                )
        );

        subtitle.setForeground(
                Color.WHITE
        );

        subtitle.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        headerPanel.add(
                subtitle,
                gbc
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );
    }

    // =========================================================
    // CENTER CONTENT
    // =========================================================

    private void createCenterContent(
            JPanel mainPanel) {

        JPanel centerPanel =
                new JPanel(
                        new GridBagLayout()
                );

        centerPanel.setBackground(BG);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.anchor =
                GridBagConstraints.CENTER;

        /*
         * Card will remain a comfortable size
         * while staying centered on any screen.
         */
        JPanel card =
                createWelcomeCard();

        centerPanel.add(
                card,
                gbc
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // WELCOME CARD
    // =========================================================

    private JPanel createWelcomeCard() {

        JPanel card =
                new JPanel(
                        new GridBagLayout()
                );

        card.setBackground(
                Color.WHITE
        );

        card.setPreferredSize(
                new Dimension(
                        520,
                        310
                )
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        210,
                                        220,
                                        230
                                ),
                                1
                        ),

                        BorderFactory.createEmptyBorder(
                                20,
                                30,
                                20,
                                30
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1.0;

        // ================= WELCOME =================

        JLabel welcome =
                new JLabel(
                        "Welcome to DriveX"
                );

        welcome.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        welcome.setForeground(BLUE);

        welcome.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        gbc.gridy = 0;

        gbc.insets =
                new Insets(
                        5,
                        10,
                        20,
                        10
                );

        card.add(
                welcome,
                gbc
        );

        // ================= DESCRIPTION =================

        JLabel description =
                new JLabel(
                        "Your journey starts here."
                );

        description.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        19
                )
        );

        description.setForeground(TEXT);

        description.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        gbc.gridy = 1;

        gbc.insets =
                new Insets(
                        0,
                        10,
                        10,
                        10
                );

        card.add(
                description,
                gbc
        );

        // ================= SECOND DESCRIPTION =================

        JLabel description2 =
                new JLabel(
                        "Choose the right car for every journey."
                );

        description2.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        description2.setForeground(
                LIGHT_TEXT
        );

        description2.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        gbc.gridy = 2;

        gbc.insets =
                new Insets(
                        0,
                        10,
                        25,
                        10
                );

        card.add(
                description2,
                gbc
        );

        // ================= START BUTTON =================

        startButton =
                new JButton(
                        "GET STARTED"
                );

        startButton.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        17
                )
        );

        startButton.setForeground(
                Color.WHITE
        );

        startButton.setBackground(
                BLUE
        );

        startButton.setFocusPainted(false);

        startButton.setBorder(
                BorderFactory.createEmptyBorder()
        );

        startButton.setCursor(
                new java.awt.Cursor(
                        java.awt.Cursor.HAND_CURSOR
                )
        );

        startButton.setPreferredSize(
                new Dimension(
                        220,
                        48
                )
        );

        gbc.gridy = 3;

        gbc.fill =
                GridBagConstraints.NONE;

        gbc.anchor =
                GridBagConstraints.CENTER;

        gbc.insets =
                new Insets(
                        0,
                        10,
                        5,
                        10
                );

        card.add(
                startButton,
                gbc
        );

        // ================= ACTION =================

        startButton.addActionListener(
                e -> {

                    LoginFrame login =
                            new LoginFrame();

                    login.setVisible(true);

                    dispose();
                }
        );

        return card;
    }

    // =========================================================
    // FOOTER
    // =========================================================

    private void createFooter(
            JPanel mainPanel) {

        JPanel footerPanel =
                new JPanel(
                        new BorderLayout()
                );

        footerPanel.setBackground(BG);

        footerPanel.setPreferredSize(
                new Dimension(
                        0,
                        55
                )
        );

        JLabel footer =
                new JLabel(
                        "Drive • Explore • Enjoy"
                );

        footer.setFont(
                new Font(
                        "Arial",
                        Font.ITALIC,
                        14
                )
        );

        footer.setForeground(
                LIGHT_TEXT
        );

        footer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        footerPanel.add(
                footer,
                BorderLayout.CENTER
        );

        mainPanel.add(
                footerPanel,
                BorderLayout.SOUTH
        );
    }
}