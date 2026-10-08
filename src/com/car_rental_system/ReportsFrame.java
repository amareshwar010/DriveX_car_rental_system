package com.car_rental_system;

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.sql.*;

public class ReportsFrame extends JFrame {

    private JPanel mainPanel;

    // =====================================================
    // COLORS
    // =====================================================

    private final Color BG =
            new Color(235, 245, 255);

    private final Color PURPLE =
            new Color(67, 56, 140);

    private final Color BLUE =
            new Color(25, 118, 210);

    private final Color GREEN =
            new Color(40, 167, 69);

    private final Color ORANGE =
            new Color(255, 152, 0);

    private final Color RED =
            new Color(220, 53, 69);

    private final Color TEAL =
            new Color(0, 150, 136);

    private final Color PINK =
            new Color(194, 70, 125);

    private final Color GRAY =
            new Color(108, 117, 125);

    private final Color DARK_TEXT =
            new Color(45, 45, 65);

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public ReportsFrame() {

        setTitle("DriveX - Reports");

        setSize(1200, 800);

        setMinimumSize(
                new Dimension(1000, 700)
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setResizable(true);

        createUI();

        loadReports();
    }

    // =====================================================
    // CREATE UI
    // =====================================================

    private void createUI() {

        mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(BG);

        setContentPane(mainPanel);

        // =================================================
        // HEADER
        // =================================================

        createHeader();

        // =================================================
        // REPORT AREA
        // =================================================

        createReportArea();

        // =================================================
        // FOOTER
        // =================================================

        createFooter();
    }

    // =====================================================
    // HEADER
    // =====================================================

    private void createHeader() {

        JPanel header =
                new JPanel(
                        new BorderLayout()
                );

        header.setBackground(PURPLE);

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        35,
                        15,
                        35
                )
        );

        header.setPreferredSize(
                new Dimension(
                        0,
                        100
                )
        );

        // =================================================
        // LEFT HEADER
        // =================================================

        JPanel leftPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        leftPanel.setOpaque(false);

        JLabel title =
                new JLabel("DriveX");

        title.setForeground(
                Color.WHITE
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        32
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Reports & Analytics Dashboard"
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
                        16
                )
        );

        leftPanel.add(title);

        leftPanel.add(subtitle);

        header.add(
                leftPanel,
                BorderLayout.WEST
        );

        // =================================================
        // RIGHT HEADER
        // =================================================

        JLabel rightText =
                new JLabel(
                        "Drive • Explore • Enjoy"
                );

        rightText.setForeground(
                Color.WHITE
        );

        rightText.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        header.add(
                rightText,
                BorderLayout.EAST
        );

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );
    }

    // =====================================================
    // REPORT AREA
    // =====================================================

    private void createReportArea() {

        JPanel contentPanel =
                new JPanel(
                        new BorderLayout()
                );

        contentPanel.setOpaque(false);

        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        30,
                        15,
                        30
                )
        );

        // =================================================
        // HEADING
        // =================================================

        JPanel headingPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        headingPanel.setOpaque(false);

        JLabel heading =
                new JLabel(
                        "Business Overview"
                );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        heading.setForeground(
                DARK_TEXT
        );

        JLabel description =
                new JLabel(
                        "Monitor customers, vehicles, bookings, payments and reviews."
                );

        description.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        description.setForeground(
                GRAY
        );

        headingPanel.add(heading);

        headingPanel.add(description);

        contentPanel.add(
                headingPanel,
                BorderLayout.NORTH
        );

        // =================================================
        // STAT CARD AREA
        // =================================================

        JPanel cardsPanel =
                new JPanel(
                        new GridLayout(
                                4,
                                4,
                                15,
                                15
                        )
                );

        cardsPanel.setOpaque(false);

        cardsPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        18,
                        0,
                        10,
                        0
                )
        );

        // =================================================
        // ROW 1
        // =================================================

        cardsPanel.add(
                createStatCard(
                        "TOTAL CUSTOMERS",
                        "0",
                        BLUE,
                        "customers"
                )
        );

        cardsPanel.add(
                createStatCard(
                        "TOTAL CARS",
                        "0",
                        PURPLE,
                        "cars"
                )
        );

        cardsPanel.add(
                createStatCard(
                        "AVAILABLE CARS",
                        "0",
                        GREEN,
                        "available"
                )
        );

        cardsPanel.add(
                createStatCard(
                        "RENTED CARS",
                        "0",
                        ORANGE,
                        "rented"
                )
        );

        // =================================================
        // ROW 2
        // =================================================

        cardsPanel.add(
                createStatCard(
                        "MAINTENANCE CARS",
                        "0",
                        RED,
                        "maintenance"
                )
        );

        cardsPanel.add(
                createStatCard(
                        "TOTAL BOOKINGS",
                        "0",
                        TEAL,
                        "bookings"
                )
        );

        cardsPanel.add(
                createStatCard(
                        "CONFIRMED / BOOKED",
                        "0",
                        BLUE,
                        "confirmed"
                )
        );

        cardsPanel.add(
                createStatCard(
                        "COMPLETED",
                        "0",
                        GREEN,
                        "completed"
                )
        );

        // =================================================
        // ROW 3
        // =================================================

        cardsPanel.add(
                createStatCard(
                        "CANCELLED",
                        "0",
                        RED,
                        "cancelled"
                )
        );

        cardsPanel.add(
                createStatCard(
                        "TOTAL PAYMENTS",
                        "0",
                        PURPLE,
                        "payments"
                )
        );

        cardsPanel.add(
                createStatCard(
                        "PAID PAYMENTS",
                        "0",
                        GREEN,
                        "paid"
                )
        );

        cardsPanel.add(
                createStatCard(
                        "REFUNDED",
                        "0",
                        ORANGE,
                        "refunded"
                )
        );

        // =================================================
        // ROW 4
        // =================================================

        cardsPanel.add(
                createStatCard(
                        "CURRENT REVENUE",
                        "₹ 0.00",
                        GREEN,
                        "revenue"
                )
        );

        cardsPanel.add(
                createStatCard(
                        "TOTAL REVIEWS",
                        "0",
                        PINK,
                        "reviews"
                )
        );

        cardsPanel.add(
                createStatCard(
                        "AVERAGE RATING",
                        "0.0 / 5",
                        ORANGE,
                        "rating"
                )
        );

        cardsPanel.add(
                createStatCard(
                        "REFUNDED AMOUNT",
                        "₹ 0.00",
                        RED,
                        "refundAmount"
                )
        );

        contentPanel.add(
                cardsPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // BUTTON AREA
        // =================================================

        JPanel buttonPanel =
                new JPanel(
                        new BorderLayout()
                );

        buttonPanel.setOpaque(false);

        // =================================================
        // REFRESH BUTTON
        // =================================================

        JButton refreshButton =
                createButton(
                        "REFRESH REPORTS",
                        BLUE
                );

        refreshButton.addActionListener(
                e -> loadReports()
        );

        // =================================================
        // BACK BUTTON
        // =================================================

        JButton backButton =
                createButton(
                        "BACK TO DASHBOARD",
                        GRAY
                );

        backButton.addActionListener(
                e -> {

                    new AdminDashboardFrame()
                            .setVisible(true);

                    dispose();
                }
        );

        buttonPanel.add(
                refreshButton,
                BorderLayout.WEST
        );

        buttonPanel.add(
                backButton,
                BorderLayout.EAST
        );

        contentPanel.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );
    }

    // =====================================================
    // CREATE STAT CARD
    // =====================================================

    private JPanel createStatCard(
            String title,
            String value,
            Color accent,
            String key) {

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(
                                        215,
                                        220,
                                        230
                                ),
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                10,
                                16,
                                10,
                                12
                        )
                )
        );

        // =================================================
        // ACCENT STRIP
        // =================================================

        JPanel strip =
                new JPanel();

        strip.setBackground(
                accent
        );

        strip.setPreferredSize(
                new Dimension(
                        7,
                        0
                )
        );

        card.add(
                strip,
                BorderLayout.WEST
        );

        // =================================================
        // CONTENT
        // =================================================

        JPanel content =
                new JPanel(
                        new GridBagLayout()
                );

        content.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.WEST;

        JLabel titleLabel =
                new JLabel(title);

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        titleLabel.setForeground(
                GRAY
        );

        content.add(
                titleLabel,
                gbc
        );

        // =================================================
        // VALUE
        // =================================================

        JLabel valueLabel =
                new JLabel(value);

        valueLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        valueLabel.setForeground(
                accent
        );

        valueLabel.setName(key);

        gbc.gridy = 1;

        gbc.insets =
                new Insets(
                        5,
                        0,
                        0,
                        0
                );

        content.add(
                valueLabel,
                gbc
        );

        card.add(
                content,
                BorderLayout.CENTER
        );

        return card;
    }

    // =====================================================
    // CREATE BUTTON
    // =====================================================

    private JButton createButton(
            String text,
            Color background) {

        JButton button =
                new JButton(text);

        button.setPreferredSize(
                new Dimension(
                        180,
                        40
                )
        );

        button.setBackground(
                background
        );

        button.setForeground(
                Color.WHITE
        );

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =====================================================
    // LOAD REPORTS
    // =====================================================

    private void loadReports() {

        try (
                Connection con =
                        DBConnection.getConnection()
        ) {

            int totalCustomers =
                    getCount(
                            con,
                            "SELECT COUNT(*) FROM users"
                    );

            int totalCars =
                    getCount(
                            con,
                            "SELECT COUNT(*) FROM cars"
                    );

            int availableCars =
                    getCount(
                            con,
                            "SELECT COUNT(*) FROM cars WHERE status='Available'"
                    );

            int rentedCars =
                    getCount(
                            con,
                            "SELECT COUNT(*) FROM cars WHERE status='Rented'"
                    );

            int maintenanceCars =
                    getCount(
                            con,
                            "SELECT COUNT(*) FROM cars WHERE status='Maintenance'"
                    );

            int totalBookings =
                    getCount(
                            con,
                            "SELECT COUNT(*) FROM bookings"
                    );

            int confirmedBookings =
                    getCount(
                            con,
                            "SELECT COUNT(*) FROM bookings "
                                    + "WHERE status IN ('Confirmed','Booked')"
                    );

            int completedBookings =
                    getCount(
                            con,
                            "SELECT COUNT(*) FROM bookings "
                                    + "WHERE status='Completed'"
                    );

            int cancelledBookings =
                    getCount(
                            con,
                            "SELECT COUNT(*) FROM bookings "
                                    + "WHERE status='Cancelled'"
                    );

            int totalPayments =
                    getCount(
                            con,
                            "SELECT COUNT(*) FROM payments"
                    );

            int paidPayments =
                    getCount(
                            con,
                            "SELECT COUNT(*) FROM payments "
                                    + "WHERE status='Paid'"
                    );

            int refundedPayments =
                    getCount(
                            con,
                            "SELECT COUNT(*) FROM payments "
                                    + "WHERE status='Refunded'"
                    );

            int totalReviews =
                    getCount(
                            con,
                            "SELECT COUNT(*) FROM reviews"
                    );

            double revenue =
                    getDouble(
                            con,
                            "SELECT COALESCE(SUM(amount),0) "
                                    + "FROM payments WHERE status='Paid'"
                    );

            double refundedAmount =
                    getDouble(
                            con,
                            "SELECT COALESCE(SUM(amount),0) "
                                    + "FROM payments WHERE status='Refunded'"
                    );

            double averageRating =
                    getDouble(
                            con,
                            "SELECT COALESCE(AVG(rating),0) FROM reviews"
                    );

            // =================================================
            // UPDATE VALUES
            // =================================================

            updateValue(
                    "customers",
                    String.valueOf(totalCustomers)
            );

            updateValue(
                    "cars",
                    String.valueOf(totalCars)
            );

            updateValue(
                    "available",
                    String.valueOf(availableCars)
            );

            updateValue(
                    "rented",
                    String.valueOf(rentedCars)
            );

            updateValue(
                    "maintenance",
                    String.valueOf(maintenanceCars)
            );

            updateValue(
                    "bookings",
                    String.valueOf(totalBookings)
            );

            updateValue(
                    "confirmed",
                    String.valueOf(confirmedBookings)
            );

            updateValue(
                    "completed",
                    String.valueOf(completedBookings)
            );

            updateValue(
                    "cancelled",
                    String.valueOf(cancelledBookings)
            );

            updateValue(
                    "payments",
                    String.valueOf(totalPayments)
            );

            updateValue(
                    "paid",
                    String.valueOf(paidPayments)
            );

            updateValue(
                    "refunded",
                    String.valueOf(refundedPayments)
            );

            updateValue(
                    "revenue",
                    String.format(
                            "₹ %.2f",
                            revenue
                    )
            );

            updateValue(
                    "reviews",
                    String.valueOf(totalReviews)
            );

            updateValue(
                    "rating",
                    String.format(
                            "%.1f / 5",
                            averageRating
                    )
            );

            updateValue(
                    "refundAmount",
                    String.format(
                            "₹ %.2f",
                            refundedAmount
                    )
            );

        } catch (SQLException ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load DriveX reports.\n\n"
                            + "Please check the database connection "
                            + "and try again.",
                    "DriveX - Reports Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // COUNT QUERY
    // =====================================================

    private int getCount(
            Connection con,
            String query)
            throws SQLException {

        try (
                PreparedStatement ps =
                        con.prepareStatement(query);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getInt(1);
            }
        }

        return 0;
    }

    // =====================================================
    // DOUBLE QUERY
    // =====================================================

    private double getDouble(
            Connection con,
            String query)
            throws SQLException {

        try (
                PreparedStatement ps =
                        con.prepareStatement(query);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            if (rs.next()) {

                return rs.getDouble(1);
            }
        }

        return 0.0;
    }

    // =====================================================
    // UPDATE CARD VALUE
    // =====================================================

    private void updateValue(
            String key,
            String value) {

        Component[] components =
                mainPanel.getComponents();

        for (Component component :
                components) {

            searchComponent(
                    component,
                    key,
                    value
            );
        }
    }

    // =====================================================
    // SEARCH COMPONENT RECURSIVELY
    // =====================================================

    private boolean searchComponent(
            Component component,
            String key,
            String value) {

        if (component instanceof JLabel) {

            JLabel label =
                    (JLabel) component;

            if (key.equals(
                    label.getName()
            )) {

                label.setText(value);

                return true;
            }
        }

        if (component instanceof Container) {

            Container container =
                    (Container) component;

            Component[] children =
                    container.getComponents();

            for (Component child :
                    children) {

                if (searchComponent(
                        child,
                        key,
                        value
                )) {

                    return true;
                }
            }
        }

        return false;
    }

    // =====================================================
    // FOOTER
    // =====================================================

    private void createFooter() {

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
                        "DriveX • Smart Mobility. Better Journeys.",
                        SwingConstants.CENTER
                );

        footer.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        footer.setForeground(
                GRAY
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