package com.car_rental_system;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class BookingFrame extends JFrame {

    private final int userId;
    private final String customerName;
    private final int selectedCarId;

    private JLabel customerLabel;
    private JLabel carLabel;
    private JLabel priceLabel;
    private JLabel totalDaysLabel;
    private JLabel totalAmountLabel;

    private JTextField startDateField;
    private JTextField endDateField;

    private JButton calculateButton;
    private JButton confirmButton;

    private double pricePerDay = 0;

    // =========================================================
    // COLORS
    // =========================================================

    private final Color BG = new Color(235, 245, 255);
    private final Color BLUE = new Color(25, 118, 210);
    private final Color DARK_BLUE = new Color(13, 71, 161);
    private final Color GREEN = new Color(40, 167, 69);
    private final Color RED = new Color(220, 53, 69);
    private final Color GRAY = new Color(108, 117, 125);
    private final Color BORDER = new Color(210, 220, 230);
    private final Color TEXT = new Color(35, 45, 65);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public BookingFrame(int userId, String customerName) {

        this(
                userId,
                customerName,
                -1
        );
    }

    public BookingFrame(
            int userId,
            String customerName,
            int selectedCarId) {

        this.userId = userId;
        this.customerName = customerName;
        this.selectedCarId = selectedCarId;

        setTitle("DriveX - Book Car");

        setSize(1000, 700);
        setMinimumSize(new Dimension(800, 650));

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setResizable(true);

        createUI();

        customerLabel.setText(
                "Customer: " + customerName
        );

        if (selectedCarId != -1) {

            loadSelectedCar();

        } else {

            showNoCarSelected();
        }
    }

    // =========================================================
    // CREATE UI
    // =========================================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(BG);

        setContentPane(mainPanel);

        createHeader(mainPanel);
        createBookingArea(mainPanel);
        createFooter(mainPanel);
    }

    // =========================================================
    // HEADER
    // =========================================================

    private void createHeader(
            JPanel mainPanel) {

        JPanel header =
                new JPanel(new BorderLayout());

        header.setBackground(DARK_BLUE);

        header.setBorder(
                new EmptyBorder(
                        18,
                        30,
                        18,
                        30
                )
        );

        // -----------------------------------------------------
        // LEFT HEADER
        // -----------------------------------------------------

        JPanel leftHeader =
                new JPanel();

        leftHeader.setOpaque(false);

        leftHeader.setLayout(
                new BoxLayout(
                        leftHeader,
                        BoxLayout.Y_AXIS
                )
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

        JLabel subtitle =
                new JLabel(
                        "Book Your Journey"
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

        leftHeader.add(title);
        leftHeader.add(
                Box.createVerticalStrut(2)
        );
        leftHeader.add(subtitle);

        // -----------------------------------------------------
        // RIGHT HEADER
        // -----------------------------------------------------

        customerLabel =
                new JLabel();

        customerLabel.setForeground(
                Color.WHITE
        );

        customerLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        customerLabel.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        header.add(
                leftHeader,
                BorderLayout.WEST
        );

        header.add(
                customerLabel,
                BorderLayout.EAST
        );

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );
    }

    // =========================================================
    // BOOKING AREA
    // =========================================================

    private void createBookingArea(
            JPanel mainPanel) {

        JPanel outerPanel =
                new JPanel(
                        new GridBagLayout()
                );

        outerPanel.setBackground(BG);

        outerPanel.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        15,
                        25
                )
        );

        JPanel card =
                new JPanel(
                        new BorderLayout()
                );

        card.setBackground(Color.WHITE);

        card.setBorder(
                new LineBorder(
                        BORDER,
                        1
                )
        );

        // Make card responsive but keep a comfortable width.
        card.setPreferredSize(
                new Dimension(
                        850,
                        500
                )
        );

        // -----------------------------------------------------
        // CARD HEADER
        // -----------------------------------------------------

        JPanel cardHeader =
                new JPanel(new BorderLayout());

        cardHeader.setBackground(Color.WHITE);

        cardHeader.setBorder(
                new EmptyBorder(
                        22,
                        28,
                        12,
                        28
                )
        );

        JLabel heading =
                new JLabel(
                        "Book Your Car"
                );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        heading.setForeground(
                DARK_BLUE
        );

        cardHeader.add(
                heading,
                BorderLayout.WEST
        );

        card.add(
                cardHeader,
                BorderLayout.NORTH
        );

        // -----------------------------------------------------
        // CARD CONTENT
        // -----------------------------------------------------

        JPanel content =
                new JPanel(
                        new GridBagLayout()
                );

        content.setBackground(Color.WHITE);

        content.setBorder(
                new EmptyBorder(
                        5,
                        28,
                        20,
                        28
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        7,
                        8,
                        7,
                        8
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1.0;

        // -----------------------------------------------------
        // VEHICLE TITLE
        // -----------------------------------------------------

        JLabel carTitle =
                createFieldTitle(
                        "Selected Vehicle"
                );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;

        content.add(
                carTitle,
                gbc
        );

        // -----------------------------------------------------
        // VEHICLE INFORMATION PANEL
        // -----------------------------------------------------

        JPanel vehiclePanel =
                new JPanel(
                        new BorderLayout()
                );

        vehiclePanel.setBackground(
                new Color(
                        245,
                        249,
                        255
                )
        );

        vehiclePanel.setBorder(
                new LineBorder(
                        BORDER,
                        1
                )
        );

        vehiclePanel.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                10,
                                12,
                                10,
                                12
                        )
                )
        );

        carLabel =
                new JLabel(
                        "No car selected"
                );

        carLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        carLabel.setForeground(TEXT);

        vehiclePanel.add(
                carLabel,
                BorderLayout.CENTER
        );

        priceLabel =
                new JLabel(
                        "Price / Day: ₹ 0.00"
                );

        priceLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        priceLabel.setForeground(GREEN);

        priceLabel.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        vehiclePanel.add(
                priceLabel,
                BorderLayout.EAST
        );

        gbc.gridy = 1;
        gbc.gridwidth = 2;

        content.add(
                vehiclePanel,
                gbc
        );

        // -----------------------------------------------------
        // START DATE
        // -----------------------------------------------------

        JLabel startTitle =
                createFieldTitle(
                        "Start Date"
                );

        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 1;

        content.add(
                startTitle,
                gbc
        );

        // -----------------------------------------------------
        // RETURN DATE
        // -----------------------------------------------------

        JLabel endTitle =
                createFieldTitle(
                        "Return Date"
                );

        gbc.gridx = 1;

        content.add(
                endTitle,
                gbc
        );

        // -----------------------------------------------------
        // DATE FIELDS
        // -----------------------------------------------------

        startDateField =
                new JTextField();

        styleTextField(
                startDateField
        );

        startDateField.setToolTipText(
                "Format: YYYY-MM-DD"
        );

        gbc.gridy = 3;
        gbc.gridx = 0;
        gbc.weightx = 1.0;

        content.add(
                startDateField,
                gbc
        );

        endDateField =
                new JTextField();

        styleTextField(
                endDateField
        );

        endDateField.setToolTipText(
                "Format: YYYY-MM-DD"
        );

        gbc.gridx = 1;

        content.add(
                endDateField,
                gbc
        );

        // -----------------------------------------------------
        // DATE FORMAT INFO
        // -----------------------------------------------------

        JLabel dateInfo =
                new JLabel(
                        "Enter dates in YYYY-MM-DD format"
                );

        dateInfo.setFont(
                new Font(
                        "Arial",
                        Font.ITALIC,
                        11
                )
        );

        dateInfo.setForeground(GRAY);

        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        content.add(
                dateInfo,
                gbc
        );

        // -----------------------------------------------------
        // CALCULATE BUTTON
        // -----------------------------------------------------

        calculateButton =
                createButton(
                        "CALCULATE AMOUNT",
                        BLUE
                );

        calculateButton.setPreferredSize(
                new Dimension(
                        0,
                        42
                )
        );

        calculateButton.addActionListener(
                e -> calculateAmount()
        );

        gbc.gridy = 5;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        gbc.insets =
                new Insets(
                        12,
                        8,
                        8,
                        8
                );

        content.add(
                calculateButton,
                gbc
        );

        // -----------------------------------------------------
        // TOTAL SUMMARY
        // -----------------------------------------------------

        JPanel summaryPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                20,
                                0
                        )
                );

        summaryPanel.setBackground(
                new Color(
                        248,
                        250,
                        253
                )
        );

        summaryPanel.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                10,
                                15,
                                10,
                                15
                        )
                )
        );

        // TOTAL DAYS

        JPanel daysPanel =
                new JPanel(
                        new BorderLayout(
                                8,
                                0
                        )
                );

        daysPanel.setOpaque(false);

        JLabel totalDaysTitle =
                new JLabel(
                        "Total Rental Days"
                );

        totalDaysTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        totalDaysTitle.setForeground(GRAY);

        totalDaysLabel =
                new JLabel("0");

        totalDaysLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        totalDaysLabel.setForeground(
                DARK_BLUE
        );

        totalDaysLabel.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        daysPanel.add(
                totalDaysTitle,
                BorderLayout.WEST
        );

        daysPanel.add(
                totalDaysLabel,
                BorderLayout.EAST
        );

        // TOTAL AMOUNT

        JPanel amountPanel =
                new JPanel(
                        new BorderLayout(
                                8,
                                0
                        )
                );

        amountPanel.setOpaque(false);

        JLabel totalAmountTitle =
                new JLabel(
                        "Total Amount"
                );

        totalAmountTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        totalAmountTitle.setForeground(GRAY);

        totalAmountLabel =
                new JLabel(
                        "₹ 0.00"
                );

        totalAmountLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        totalAmountLabel.setForeground(
                GREEN
        );

        totalAmountLabel.setHorizontalAlignment(
                SwingConstants.RIGHT
        );

        amountPanel.add(
                totalAmountTitle,
                BorderLayout.WEST
        );

        amountPanel.add(
                totalAmountLabel,
                BorderLayout.EAST
        );

        summaryPanel.add(
                daysPanel
        );

        summaryPanel.add(
                amountPanel
        );

        gbc.gridy = 6;
        gbc.gridwidth = 2;

        gbc.insets =
                new Insets(
                        8,
                        8,
                        10,
                        8
                );

        content.add(
                summaryPanel,
                gbc
        );

        // -----------------------------------------------------
        // CONFIRM + CANCEL
        // -----------------------------------------------------

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                15,
                                0
                        )
                );

        buttonPanel.setOpaque(false);

        confirmButton =
                createButton(
                        "CONFIRM BOOKING",
                        GREEN
                );

        confirmButton.setPreferredSize(
                new Dimension(
                        0,
                        45
                )
        );

        confirmButton.addActionListener(
                e -> confirmBooking()
        );

        JButton cancelButton =
                createButton(
                        "CANCEL",
                        RED
                );

        cancelButton.setPreferredSize(
                new Dimension(
                        0,
                        45
                )
        );

        cancelButton.addActionListener(
                e -> dispose()
        );

        buttonPanel.add(
                confirmButton
        );

        buttonPanel.add(
                cancelButton
        );

        gbc.gridy = 7;
        gbc.gridwidth = 2;

        gbc.insets =
                new Insets(
                        8,
                        8,
                        5,
                        8
                );

        content.add(
                buttonPanel,
                gbc
        );

        card.add(
                content,
                BorderLayout.CENTER
        );

        // -----------------------------------------------------
        // CENTER CARD
        // -----------------------------------------------------

        GridBagConstraints cardGbc =
                new GridBagConstraints();

        cardGbc.gridx = 0;
        cardGbc.gridy = 0;

        cardGbc.weightx = 1.0;
        cardGbc.weighty = 1.0;

        cardGbc.fill =
                GridBagConstraints.BOTH;

        outerPanel.add(
                card,
                cardGbc
        );

        mainPanel.add(
                outerPanel,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // FIELD TITLE
    // =========================================================

    private JLabel createFieldTitle(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(GRAY);

        return label;
    }

    // =========================================================
    // TEXT FIELD STYLE
    // =========================================================

    private void styleTextField(
            JTextField field) {

        field.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        field.setPreferredSize(
                new Dimension(
                        0,
                        40
                )
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                5,
                                10,
                                5,
                                10
                        )
                )
        );
    }

    // =========================================================
    // LOAD SELECTED CAR
    // =========================================================

    private void loadSelectedCar() {

        String sql =
                "SELECT car_number, brand, model, " +
                "car_type, category, price_per_day, status " +
                "FROM cars " +
                "WHERE id = ?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    selectedCarId
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    String carNumber =
                            rs.getString(
                                    "car_number"
                            );

                    String brand =
                            rs.getString(
                                    "brand"
                            );

                    String model =
                            rs.getString(
                                    "model"
                            );

                    String type =
                            rs.getString(
                                    "car_type"
                            );

                    String category =
                            rs.getString(
                                    "category"
                            );

                    pricePerDay =
                            rs.getDouble(
                                    "price_per_day"
                            );

                    String status =
                            rs.getString(
                                    "status"
                            );

                    carLabel.setText(
                            carNumber
                                    + " • "
                                    + brand
                                    + " "
                                    + model
                                    + " • "
                                    + type
                                    + " • "
                                    + category
                    );

                    priceLabel.setText(
                            String.format(
                                    "Price / Day: ₹ %.2f",
                                    pricePerDay
                            )
                    );

                    if ("Maintenance".equalsIgnoreCase(
                            status)) {

                        confirmButton.setEnabled(
                                false
                        );

                        calculateButton.setEnabled(
                                false
                        );

                        carLabel.setText(
                                carLabel.getText()
                                        + " • MAINTENANCE"
                        );

                        carLabel.setForeground(
                                RED
                        );

                        JOptionPane.showMessageDialog(
                                this,

                                "This car is currently under maintenance.\n"
                                        + "Please select another vehicle.",

                                "Car Unavailable",

                                JOptionPane.WARNING_MESSAGE
                        );
                    }

                } else {

                    showNoCarSelected();
                }
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to load the selected car.\n"
                            + "Please try again.",

                    "DriveX",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // NO CAR SELECTED
    // =========================================================

    private void showNoCarSelected() {

        carLabel.setText(
                "Please select a car from Normal Cars or Premium Cars."
        );

        priceLabel.setText(
                "Price / Day: ₹ 0.00"
        );

        calculateButton.setEnabled(
                false
        );

        confirmButton.setEnabled(
                false
        );
    }

    // =========================================================
    // CALCULATE AMOUNT
    // =========================================================

    private void calculateAmount() {

        try {

            LocalDate startDate =
                    LocalDate.parse(
                            startDateField
                                    .getText()
                                    .trim()
                    );

            LocalDate endDate =
                    LocalDate.parse(
                            endDateField
                                    .getText()
                                    .trim()
                    );

            LocalDate today =
                    LocalDate.now();

            if (startDate.isBefore(today)) {

                showWarning(
                        "Start date cannot be earlier than today."
                );

                return;
            }

            if (!endDate.isAfter(startDate)) {

                showWarning(
                        "Return date must be after the start date."
                );

                return;
            }

            if (isCarBookedForDates(
                    startDate,
                    endDate)) {

                showWarning(
                        "This car is already booked for the selected dates.\n"
                                + "Please choose different dates or another car."
                );

                return;
            }

            long totalDays =
                    ChronoUnit.DAYS.between(
                            startDate,
                            endDate
                    );

            double totalAmount =
                    totalDays * pricePerDay;

            totalDaysLabel.setText(
                    String.valueOf(
                            totalDays
                    )
            );

            totalAmountLabel.setText(
                    String.format(
                            "₹ %.2f",
                            totalAmount
                    )
            );

        } catch (Exception e) {

            showWarning(
                    "Please enter valid dates.\n"
                            + "Use the format YYYY-MM-DD."
            );
        }
    }

    // =========================================================
    // CHECK CAR DATE AVAILABILITY
    // =========================================================

    private boolean isCarBookedForDates(
            LocalDate startDate,
            LocalDate endDate) {

        String sql =
                "SELECT id " +
                        "FROM bookings " +
                        "WHERE car_id = ? " +
                        "AND status IN ('Confirmed', 'Booked') " +
                        "AND start_date <= ? " +
                        "AND end_date >= ? " +
                        "LIMIT 1";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    selectedCarId
            );

            ps.setDate(
                    2,
                    Date.valueOf(endDate)
            );

            ps.setDate(
                    3,
                    Date.valueOf(startDate)
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                return rs.next();
            }

        } catch (SQLException e) {

            showWarning(
                    "Unable to check car availability.\n"
                            + "Please try again."
            );

            return true;
        }
    }

    // =========================================================
    // CHECK CUSTOMER ACTIVE BOOKING
    // =========================================================

    private boolean hasActiveBooking() {

        String sql =
                "SELECT id, car_id, start_date, end_date, status " +
                        "FROM bookings " +
                        "WHERE user_id = ? " +
                        "AND status IN ('Confirmed', 'Booked') " +
                        "LIMIT 1";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    userId
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    JOptionPane.showMessageDialog(
                            this,

                            "Active Booking Exists\n\n"
                                    + "You already have an active booking with DriveX.\n"
                                    + "Please return the currently booked car before booking another vehicle.",

                            "DriveX - Active Booking",

                            JOptionPane.WARNING_MESSAGE
                    );

                    return true;
                }
            }

        } catch (SQLException e) {

            showWarning(
                    "Unable to check your current bookings.\n"
                            + "Please try again."
            );

            return true;
        }

        return false;
    }

    // =========================================================
    // CONFIRM BOOKING
    // =========================================================

    private void confirmBooking() {

        if (selectedCarId == -1) {

            showWarning(
                    "Please select a car before booking."
            );

            return;
        }

        if (hasActiveBooking()) {
            return;
        }

        LocalDate startDate;
        LocalDate endDate;

        try {

            startDate =
                    LocalDate.parse(
                            startDateField
                                    .getText()
                                    .trim()
                    );

            endDate =
                    LocalDate.parse(
                            endDateField
                                    .getText()
                                    .trim()
                    );

        } catch (Exception e) {

            showWarning(
                    "Please enter valid dates.\n"
                            + "Use the format YYYY-MM-DD."
            );

            return;
        }

        LocalDate today =
                LocalDate.now();

        if (startDate.isBefore(today)) {

            showWarning(
                    "Start date cannot be earlier than today."
            );

            return;
        }

        if (!endDate.isAfter(startDate)) {

            showWarning(
                    "Return date must be after the start date."
            );

            return;
        }

        long totalDays =
                ChronoUnit.DAYS.between(
                        startDate,
                        endDate
                );

        double totalAmount =
                totalDays * pricePerDay;

        // -----------------------------------------------------
        // FINAL AVAILABILITY CHECK
        // -----------------------------------------------------

        if (isCarBookedForDates(
                startDate,
                endDate)) {

            showWarning(
                    "This car is no longer available for the selected dates.\n"
                            + "Please choose different dates or another car."
            );

            return;
        }

        // -----------------------------------------------------
        // CONFIRMATION
        // -----------------------------------------------------

        int choice =
                JOptionPane.showConfirmDialog(
                        this,

                        "Confirm your DriveX booking?\n\n"
                                + "Customer: "
                                + customerName
                                + "\n"
                                + "Vehicle: "
                                + carLabel.getText()
                                + "\n"
                                + "Start Date: "
                                + startDate
                                + "\n"
                                + "Return Date: "
                                + endDate
                                + "\n"
                                + "Total Days: "
                                + totalDays
                                + "\n"
                                + String.format(
                                "Total Amount: ₹ %.2f",
                                totalAmount
                        ),

                        "Confirm Booking",

                        JOptionPane.YES_NO_OPTION,

                        JOptionPane.QUESTION_MESSAGE
                );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        String insertSql =
                "INSERT INTO bookings " +
                        "(user_id, car_id, booking_date, " +
                        "start_date, end_date, total_days, " +
                        "total_amount, status) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        Connection con = null;

        try {

            con =
                    DBConnection.getConnection();

            con.setAutoCommit(false);

            // -------------------------------------------------
            // LOCK CAR
            // -------------------------------------------------

            String carSql =
                    "SELECT price_per_day, status " +
                            "FROM cars " +
                            "WHERE id = ? " +
                            "FOR UPDATE";

            try (
                    PreparedStatement carPs =
                            con.prepareStatement(carSql)
            ) {

                carPs.setInt(
                        1,
                        selectedCarId
                );

                try (
                        ResultSet rs =
                                carPs.executeQuery()
                ) {

                    if (!rs.next()) {

                        con.rollback();

                        showWarning(
                                "Selected car could not be found."
                        );

                        return;
                    }

                    String status =
                            rs.getString(
                                    "status"
                            );

                    if ("Maintenance".equalsIgnoreCase(
                            status)) {

                        con.rollback();

                        showWarning(
                                "This car is currently under maintenance."
                        );

                        return;
                    }

                    pricePerDay =
                            rs.getDouble(
                                    "price_per_day"
                            );
                }
            }

            // -------------------------------------------------
            // FINAL OVERLAP CHECK
            // -------------------------------------------------

            String overlapSql =
                    "SELECT id " +
                            "FROM bookings " +
                            "WHERE car_id = ? " +
                            "AND status IN ('Confirmed', 'Booked') " +
                            "AND start_date <= ? " +
                            "AND end_date >= ? " +
                            "LIMIT 1";

            try (
                    PreparedStatement overlapPs =
                            con.prepareStatement(
                                    overlapSql
                            )
            ) {

                overlapPs.setInt(
                        1,
                        selectedCarId
                );

                overlapPs.setDate(
                        2,
                        Date.valueOf(endDate)
                );

                overlapPs.setDate(
                        3,
                        Date.valueOf(startDate)
                );

                try (
                        ResultSet rs =
                                overlapPs.executeQuery()
                ) {

                    if (rs.next()) {

                        con.rollback();

                        showWarning(
                                "This car has just been booked for the selected dates.\n"
                                        + "Please choose another date range or vehicle."
                        );

                        return;
                    }
                }
            }

            // -------------------------------------------------
            // INSERT BOOKING
            // -------------------------------------------------

            try (
                    PreparedStatement ps =
                            con.prepareStatement(
                                    insertSql,
                                    Statement.RETURN_GENERATED_KEYS
                            )
            ) {

                ps.setInt(
                        1,
                        userId
                );

                ps.setInt(
                        2,
                        selectedCarId
                );

                ps.setDate(
                        3,
                        Date.valueOf(
                                LocalDate.now()
                        )
                );

                ps.setDate(
                        4,
                        Date.valueOf(
                                startDate
                        )
                );

                ps.setDate(
                        5,
                        Date.valueOf(
                                endDate
                        )
                );

                ps.setLong(
                        6,
                        totalDays
                );

                ps.setDouble(
                        7,
                        totalAmount
                );

                ps.setString(
                        8,
                        "Confirmed"
                );

                ps.executeUpdate();

                int bookingId = -1;

                try (
                        ResultSet keys =
                                ps.getGeneratedKeys()
                ) {

                    if (keys.next()) {

                        bookingId =
                                keys.getInt(1);
                    }
                }

                con.commit();

                JOptionPane.showMessageDialog(
                        this,

                        "Booking Confirmed Successfully!\n\n"
                                + "Booking ID: "
                                + bookingId
                                + "\n"
                                + "Vehicle: "
                                + carLabel.getText()
                                + "\n"
                                + "Start Date: "
                                + startDate
                                + "\n"
                                + "Return Date: "
                                + endDate
                                + "\n"
                                + String.format(
                                "Total Amount: ₹ %.2f",
                                totalAmount
                        )
                                + "\n\n"
                                + "You can complete the payment from the Payment section.",

                        "DriveX - Booking Confirmed",

                        JOptionPane.INFORMATION_MESSAGE
                );

                dispose();
            }

        } catch (SQLException e) {

            try {

                if (con != null) {
                    con.rollback();
                }

            } catch (SQLException ignored) {
            }

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to confirm your booking.\n"
                            + "Please try again.",

                    "DriveX",

                    JOptionPane.ERROR_MESSAGE
            );

        } finally {

            try {

                if (con != null) {
                    con.setAutoCommit(true);
                    con.close();
                }

            } catch (SQLException ignored) {
            }
        }
    }

    // =========================================================
    // WARNING
    // =========================================================

    private void showWarning(
            String message) {

        JOptionPane.showMessageDialog(
                this,

                message,

                "DriveX",

                JOptionPane.WARNING_MESSAGE
        );
    }

    // =========================================================
    // BUTTON CREATOR
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

        footerPanel.setBorder(
                new EmptyBorder(
                        5,
                        10,
                        10,
                        10
                )
        );

        JLabel footer =
                new JLabel(
                        "DriveX • Drive • Explore • Enjoy"
                );

        footer.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        footer.setForeground(GRAY);

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