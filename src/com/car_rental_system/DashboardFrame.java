package com.car_rental_system;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private final int userId;
    private final String customerName;

    // =========================================================
    // COLORS
    // =========================================================

    private final Color BG =
            new Color(235, 245, 255);

    private final Color BLUE =
            new Color(25, 118, 210);

    private final Color DARK_BLUE =
            new Color(13, 71, 161);

    private final Color GREEN =
            new Color(40, 167, 69);

    private final Color ORANGE =
            new Color(255, 152, 0);

    private final Color RED =
            new Color(220, 53, 69);

    private final Color PURPLE =
            new Color(108, 76, 180);

    private final Color TEAL =
            new Color(0, 150, 136);

    private final Color GRAY =
            new Color(108, 117, 125);

    private final Color BORDER =
            new Color(210, 220, 230);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public DashboardFrame(
            int userId,
            String customerName) {

        this.userId = userId;
        this.customerName = customerName;

        setTitle("DriveX - Dashboard");

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setMinimumSize(
                new Dimension(950, 700)
        );

        setSize(1100, 800);

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

        // HEADER
        createHeader(mainPanel);

        // CENTER CONTENT
        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(0, 18)
                );

        centerPanel.setBackground(BG);

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        18,
                        25,
                        12,
                        25
                )
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // WELCOME CARD
        JPanel welcomeCard =
                createWelcomeCard();

        centerPanel.add(
                welcomeCard,
                BorderLayout.NORTH
        );

        // MAIN MENU + INFORMATION
        JPanel contentPanel =
                new JPanel(
                        new BorderLayout(0, 18)
                );

        contentPanel.setBackground(BG);

        // MENU
        JPanel menuPanel =
                createMenu();

        contentPanel.add(
                menuPanel,
                BorderLayout.CENTER
        );

        // INFORMATION CARD
        JPanel infoCard =
                createInfoCard();

        contentPanel.add(
                infoCard,
                BorderLayout.SOUTH
        );

        centerPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );

        // FOOTER
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

        header.setBackground(DARK_BLUE);

        header.setPreferredSize(
                new Dimension(
                        0,
                        105
                )
        );

        // -----------------------------------------------------
        // LEFT SIDE
        // -----------------------------------------------------

        JPanel leftPanel =
                new JPanel(
                        new GridBagLayout()
                );

        leftPanel.setOpaque(false);

        GridBagConstraints leftGbc =
                new GridBagConstraints();

        leftGbc.gridx = 0;
        leftGbc.gridy = 0;

        leftGbc.anchor =
                GridBagConstraints.WEST;

        leftGbc.insets =
                new Insets(
                        10,
                        35,
                        0,
                        20
                );

        JLabel title =
                new JLabel("DriveX");

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        34
                )
        );

        leftPanel.add(
                title,
                leftGbc
        );

        leftGbc.gridy = 1;

        leftGbc.insets =
                new Insets(
                        0,
                        37,
                        10,
                        20
                );

        JLabel subtitle =
                new JLabel(
                        "Smart Mobility. Better Journeys."
                );

        subtitle.setForeground(
                new Color(
                        220,
                        235,
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

        leftPanel.add(
                subtitle,
                leftGbc
        );

        header.add(
                leftPanel,
                BorderLayout.WEST
        );

        // -----------------------------------------------------
        // RIGHT SIDE
        // -----------------------------------------------------

        JPanel rightPanel =
                new JPanel(
                        new GridBagLayout()
                );

        rightPanel.setOpaque(false);

        GridBagConstraints rightGbc =
                new GridBagConstraints();

        rightGbc.gridx = 0;
        rightGbc.gridy = 0;

        rightGbc.anchor =
                GridBagConstraints.EAST;

        rightGbc.insets =
                new Insets(
                        0,
                        20,
                        0,
                        35
                );

        JLabel customer =
                new JLabel(
                        "Welcome, " +
                                customerName
                );

        customer.setForeground(
                Color.WHITE
        );

        customer.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        rightPanel.add(
                customer,
                rightGbc
        );

        header.add(
                rightPanel,
                BorderLayout.EAST
        );

        mainPanel.add(
                header,
                BorderLayout.NORTH
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

        card.setBackground(Color.WHITE);

        card.setBorder(
                new LineBorder(
                        BORDER,
                        1
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.anchor =
                GridBagConstraints.WEST;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1.0;

        gbc.insets =
                new Insets(
                        14,
                        25,
                        4,
                        25
                );

        JLabel title =
                new JLabel(
                        "Welcome to DriveX, "
                                + customerName
                                + "!"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        title.setForeground(
                new Color(
                        35,
                        45,
                        65
                )
        );

        card.add(
                title,
                gbc
        );

        gbc.gridy = 1;

        gbc.insets =
                new Insets(
                        2,
                        27,
                        14,
                        25
                );

        JLabel message =
                new JLabel(
                        "Choose a car, manage your bookings, "
                                + "make payments and share your experience."
                );

        message.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        message.setForeground(GRAY);

        card.add(
                message,
                gbc
        );

        return card;
    }

    // =========================================================
    // MENU
    // =========================================================

    private JPanel createMenu() {

        JPanel menuPanel =
                new JPanel(
                        new GridLayout(
                                3,
                                4,
                                18,
                                18
                        )
                );

        menuPanel.setBackground(BG);

        // -----------------------------------------------------
        // BOOK CAR
        // -----------------------------------------------------

        JButton bookCar =
                createMenuButton(
                        "BOOK CAR",
                        BLUE
                );

        bookCar.addActionListener(
                e -> openBooking()
        );

        menuPanel.add(bookCar);

        // -----------------------------------------------------
        // MY BOOKINGS
        // -----------------------------------------------------

        JButton myBookings =
                createMenuButton(
                        "MY BOOKINGS",
                        GREEN
                );

        myBookings.addActionListener(
                e -> {

                    MyBookingsFrame frame =
                            new MyBookingsFrame(
                                    userId,
                                    customerName
                            );

                    frame.setVisible(true);
                }
        );

        menuPanel.add(myBookings);

        // -----------------------------------------------------
        // PREMIUM CARS
        // -----------------------------------------------------

        JButton premiumCars =
                createMenuButton(
                        "PREMIUM CARS",
                        PURPLE
                );

        premiumCars.addActionListener(
                e -> {

                    PremiumCarsFrame frame =
                            new PremiumCarsFrame(
                                    userId,
                                    customerName
                            );

                    frame.setVisible(true);
                }
        );

        menuPanel.add(premiumCars);

        // -----------------------------------------------------
        // NORMAL CARS
        // -----------------------------------------------------

        JButton normalCars =
                createMenuButton(
                        "NORMAL CARS",
                        BLUE
                );

        normalCars.addActionListener(
                e -> {

                    NormalCarsFrame frame =
                            new NormalCarsFrame(
                                    userId,
                                    customerName
                            );

                    frame.setVisible(true);
                }
        );

        menuPanel.add(normalCars);

        // -----------------------------------------------------
        // MY PROFILE
        // -----------------------------------------------------

        JButton profile =
                createMenuButton(
                        "MY PROFILE",
                        ORANGE
                );

        profile.addActionListener(
                e -> {

                    ProfileFrame frame =
                            new ProfileFrame(
                                    userId,
                                    customerName
                            );

                    frame.setVisible(true);
                }
        );

        menuPanel.add(profile);

        // -----------------------------------------------------
        // PAYMENT
        // -----------------------------------------------------

        JButton payment =
                createMenuButton(
                        "PAYMENT",
                        GREEN
                );

        payment.addActionListener(
                e -> {

                    PaymentFrame frame =
                            new PaymentFrame(
                                    userId,
                                    customerName
                            );

                    frame.setVisible(true);
                }
        );

        menuPanel.add(payment);

        // -----------------------------------------------------
        // PAYMENT HISTORY
        // -----------------------------------------------------

        JButton paymentHistory =
                createMenuButton(
                        "PAYMENT HISTORY",
                        TEAL
                );

        paymentHistory.addActionListener(
                e -> {

                    PaymentHistoryFrame frame =
                            new PaymentHistoryFrame(
                                    userId,
                                    customerName
                            );

                    frame.setVisible(true);
                }
        );

        menuPanel.add(paymentHistory);

        // -----------------------------------------------------
        // REVIEWS
        // -----------------------------------------------------

        JButton reviews =
                createMenuButton(
                        "REVIEWS & RATING",
                        PURPLE
                );

        reviews.addActionListener(
                e -> {

                    ReviewsFrame frame =
                            new ReviewsFrame(
                                    userId,
                                    customerName
                            );

                    frame.setVisible(true);
                }
        );

        menuPanel.add(reviews);

        // -----------------------------------------------------
        // EMPTY SPACE
        // -----------------------------------------------------

        JPanel empty1 =
                createEmptyMenuPanel();

        menuPanel.add(empty1);

        // -----------------------------------------------------
        // LOGOUT
        // -----------------------------------------------------

        JButton logout =
                createMenuButton(
                        "LOGOUT",
                        RED
                );

        logout.addActionListener(
                e -> logout()
        );

        menuPanel.add(logout);

        // -----------------------------------------------------
        // EMPTY SPACE
        // -----------------------------------------------------

        JPanel empty2 =
                createEmptyMenuPanel();

        menuPanel.add(empty2);

        JPanel empty3 =
                createEmptyMenuPanel();

        menuPanel.add(empty3);

        return menuPanel;
    }

    // =========================================================
    // EMPTY MENU PANEL
    // =========================================================

    private JPanel createEmptyMenuPanel() {

        JPanel panel =
                new JPanel();

        panel.setOpaque(false);

        return panel;
    }

    // =========================================================
    // INFORMATION CARD
    // =========================================================

    private JPanel createInfoCard() {

        JPanel infoCard =
                new JPanel(
                        new GridBagLayout()
                );

        infoCard.setBackground(
                Color.WHITE
        );

        infoCard.setBorder(
                new LineBorder(
                        BORDER,
                        1
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.anchor =
                GridBagConstraints.WEST;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1.0;

        gbc.insets =
                new Insets(
                        12,
                        25,
                        3,
                        25
                );

        JLabel infoTitle =
                new JLabel(
                        "DriveX Customer Services"
                );

        infoTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        infoTitle.setForeground(
                DARK_BLUE
        );

        infoCard.add(
                infoTitle,
                gbc
        );

        gbc.gridy = 1;

        gbc.insets =
                new Insets(
                        2,
                        25,
                        12,
                        25
                );

        JLabel infoText =
                new JLabel(
                        "<html>"
                                + "• Book one vehicle at a time"
                                + "&nbsp;&nbsp;&nbsp;&nbsp;"
                                + "• Manage bookings"
                                + "&nbsp;&nbsp;&nbsp;&nbsp;"
                                + "• Make payments"
                                + "&nbsp;&nbsp;&nbsp;&nbsp;"
                                + "• View payment history"
                                + "&nbsp;&nbsp;&nbsp;&nbsp;"
                                + "• Update your profile"
                                + "&nbsp;&nbsp;&nbsp;&nbsp;"
                                + "• Review completed rentals"
                                + "</html>"
                );

        infoText.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        infoText.setForeground(GRAY);

        infoCard.add(
                infoText,
                gbc
        );

        return infoCard;
    }

    // =========================================================
    // OPEN BOOKING
    // =========================================================

    private void openBooking() {

        BookingFrame frame =
                new BookingFrame(
                        userId,
                        customerName
                );

        frame.setVisible(true);
    }

    // =========================================================
    // LOGOUT
    // =========================================================

    private void logout() {

        int choice =
                JOptionPane.showConfirmDialog(
                        this,

                        "Are you sure you want to logout from DriveX?",

                        "Confirm Logout",

                        JOptionPane.YES_NO_OPTION,

                        JOptionPane.QUESTION_MESSAGE
                );

        if (choice ==
                JOptionPane.YES_OPTION) {

            dispose();

            LoginFrame login =
                    new LoginFrame();

            login.setVisible(true);
        }
    }

    // =========================================================
    // MENU BUTTON
    // =========================================================

    private JButton createMenuButton(
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
                        12
                )
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setOpaque(true);

        return button;
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
                        45
                )
        );

        JLabel footer =
                new JLabel(
                        "DriveX • Drive • Explore • Enjoy",
                        SwingConstants.CENTER
                );

        footer.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        footer.setForeground(GRAY);

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