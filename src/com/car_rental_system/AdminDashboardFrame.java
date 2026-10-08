package com.car_rental_system;

import javax.swing.*;
import java.awt.*;

public class AdminDashboardFrame extends JFrame {

    // =========================================================
    // COLORS
    // =========================================================

    private final Color BG =
            new Color(245, 243, 255);

    private final Color PURPLE =
            new Color(108, 76, 180);

    private final Color DARK_PURPLE =
            new Color(67, 56, 140);

    private final Color BLUE =
            new Color(25, 118, 210);

    private final Color GREEN =
            new Color(40, 167, 69);

    private final Color ORANGE =
            new Color(255, 152, 0);

    private final Color TEAL =
            new Color(0, 150, 136);

    private final Color MAGENTA =
            new Color(194, 24, 91);

    private final Color RED =
            new Color(220, 53, 69);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public AdminDashboardFrame() {

        setTitle("DriveX - Admin Dashboard");

        setSize(1200, 800);

        setMinimumSize(
                new Dimension(1000, 700)
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setResizable(true);

        createUI();

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );
    }

    // =========================================================
    // CREATE UI
    // =========================================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(BG);

        setContentPane(mainPanel);

        createHeader(mainPanel);

        createCenter(mainPanel);

        createFooter(mainPanel);
    }

    // =========================================================
    // HEADER
    // =========================================================

    private void createHeader(
            JPanel mainPanel) {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(
                DARK_PURPLE
        );

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        30,
                        20,
                        30
                )
        );

        // -----------------------------------------------------
        // LEFT
        // -----------------------------------------------------

        JPanel left =
                new JPanel();

        left.setOpaque(false);

        left.setLayout(
                new BoxLayout(
                        left,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel("DriveX");

        title.setForeground(
                Color.WHITE
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        36
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Admin Dashboard"
                );

        subtitle.setForeground(
                new Color(
                        225,
                        220,
                        255
                )
        );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        left.add(title);

        left.add(
                Box.createVerticalStrut(3)
        );

        left.add(subtitle);

        header.add(
                left,
                BorderLayout.WEST
        );

        // -----------------------------------------------------
        // RIGHT
        // -----------------------------------------------------

        JLabel right =
                new JLabel(
                        "Smart Mobility. Better Journeys."
                );

        right.setForeground(
                Color.WHITE
        );

        right.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        header.add(
                right,
                BorderLayout.EAST
        );

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );
    }

    // =========================================================
    // CENTER
    // =========================================================

    private void createCenter(
            JPanel mainPanel) {

        JPanel center =
                new JPanel(
                        new BorderLayout(
                                0,
                                20
                        )
                );

        center.setBackground(BG);

        center.setBorder(
                BorderFactory.createEmptyBorder(
                        30,
                        40,
                        20,
                        40
                )
        );

        // -----------------------------------------------------
        // HEADING
        // -----------------------------------------------------

        JLabel heading =
                new JLabel(
                        "Management Console",
                        SwingConstants.CENTER
                );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        heading.setForeground(
                DARK_PURPLE
        );

        center.add(
                heading,
                BorderLayout.NORTH
        );

        // -----------------------------------------------------
        // BUTTON GRID
        // -----------------------------------------------------

        JPanel buttonPanel =
                new JPanel(
                        new GridBagLayout()
                );

        buttonPanel.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        12,
                        12,
                        12,
                        12
                );

        gbc.fill =
                GridBagConstraints.BOTH;

        gbc.weightx = 1.0;

        gbc.weighty = 1.0;

        // -----------------------------------------------------
        // BUTTONS
        // -----------------------------------------------------

        JButton carButton =
                createButton(
                        "CAR MANAGEMENT",
                        BLUE
                );

        JButton customerButton =
                createButton(
                        "CUSTOMER MANAGEMENT",
                        GREEN
                );

        JButton bookingButton =
                createButton(
                        "BOOKING MANAGEMENT",
                        ORANGE
                );

        JButton paymentButton =
                createButton(
                        "PAYMENT MANAGEMENT",
                        TEAL
                );

        JButton reviewsButton =
                createButton(
                        "REVIEWS & RATINGS",
                        MAGENTA
                );

        JButton reportsButton =
                createButton(
                        "REPORTS",
                        PURPLE
                );

        // -----------------------------------------------------
        // ROW 1
        // -----------------------------------------------------

        gbc.gridx = 0;
        gbc.gridy = 0;

        buttonPanel.add(
                carButton,
                gbc
        );

        gbc.gridx = 1;

        buttonPanel.add(
                customerButton,
                gbc
        );

        gbc.gridx = 2;

        buttonPanel.add(
                bookingButton,
                gbc
        );

        // -----------------------------------------------------
        // ROW 2
        // -----------------------------------------------------

        gbc.gridx = 0;
        gbc.gridy = 1;

        buttonPanel.add(
                paymentButton,
                gbc
        );

        gbc.gridx = 1;

        buttonPanel.add(
                reviewsButton,
                gbc
        );

        gbc.gridx = 2;

        buttonPanel.add(
                reportsButton,
                gbc
        );

        center.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // BOOKING MANAGEMENT
        // =====================================================

        bookingButton.addActionListener(
                e -> {

                    setVisible(false);

                    BookingManagementFrame bookingFrame =
                            new BookingManagementFrame(
                                    AdminDashboardFrame.this
                            );

                    bookingFrame.setVisible(true);
                }
        );

        // =====================================================
        // CAR MANAGEMENT
        // =====================================================

        carButton.addActionListener(
                e -> {

                    setVisible(false);

                    new CarManagementFrame()
                            .setVisible(true);
                }
        );

        // =====================================================
        // CUSTOMER MANAGEMENT
        // =====================================================

        customerButton.addActionListener(
                e -> {

                    setVisible(false);

                    new CustomerManagementFrame()
                            .setVisible(true);
                }
        );

        // =====================================================
        // PAYMENT MANAGEMENT
        // =====================================================

        paymentButton.addActionListener(
                e -> {

                    setVisible(false);

                    new PaymentManagementFrame()
                            .setVisible(true);
                }
        );

        // =====================================================
        // REVIEWS
        // =====================================================

        reviewsButton.addActionListener(
                e -> {

                    setVisible(false);

                    new AdminReviewsFrame()
                            .setVisible(true);
                }
        );

        // =====================================================
        // REPORTS
        // =====================================================

        reportsButton.addActionListener(
                e -> {

                    setVisible(false);

                    new ReportsFrame()
                            .setVisible(true);
                }
        );

        // =====================================================
        // LOGOUT
        // =====================================================

        JButton logout =
                createButton(
                        "LOGOUT",
                        RED
                );

        logout.setPreferredSize(
                new Dimension(
                        180,
                        50
                )
        );

        logout.addActionListener(
                e -> logout()
        );

        JPanel logoutPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                0,
                                5
                        )
                );

        logoutPanel.setOpaque(false);

        logoutPanel.add(logout);

        center.add(
                logoutPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                center,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // CREATE BUTTON
    // =========================================================

    private JButton createButton(
            String text,
            Color color) {

        JButton button =
                new JButton(text);

        button.setBackground(color);

        button.setForeground(
                Color.WHITE
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setPreferredSize(
                new Dimension(
                        240,
                        100
                )
        );

        return button;
    }

    // =========================================================
    // LOGOUT
    // =========================================================

    private void logout() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,

                        "Are you sure you want to logout?",

                        "DriveX - Logout",

                        JOptionPane.YES_NO_OPTION,

                        JOptionPane.QUESTION_MESSAGE
                );

        if (
                result ==
                        JOptionPane.YES_OPTION
        ) {

            dispose();

            new LoginFrame().setVisible(true);
        }
    }

    // =========================================================
    // FOOTER
    // =========================================================

    private void createFooter(
            JPanel mainPanel) {

        JPanel footer =
                new JPanel(
                        new BorderLayout()
                );

        footer.setBackground(BG);

        footer.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        10,
                        12,
                        10
                )
        );

        JLabel label =
                new JLabel(
                        "DriveX • Drive • Explore • Enjoy",
                        SwingConstants.CENTER
                );

        label.setForeground(
                new Color(
                        108,
                        117,
                        125
                )
        );

        label.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        footer.add(
                label,
                BorderLayout.CENTER
        );

        mainPanel.add(
                footer,
                BorderLayout.SOUTH
        );
    }
}