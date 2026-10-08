package com.car_rental_system;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.time.LocalDate;

public class PaymentFrame extends JFrame {

    private final int userId;
    private final String customerName;

    private JTable bookingTable;
    private DefaultTableModel tableModel;

    private JComboBox<String> paymentMethodCombo;

    // =========================================================
    // COLORS
    // =========================================================

    private final Color BG = new Color(235, 245, 255);
    private final Color BLUE = new Color(25, 118, 210);
    private final Color DARK_BLUE = new Color(13, 71, 161);
    private final Color GREEN = new Color(40, 167, 69);
    private final Color ORANGE = new Color(255, 152, 0);
    private final Color RED = new Color(220, 53, 69);
    private final Color GRAY = new Color(108, 117, 125);
    private final Color DARK_TEXT = new Color(45, 45, 65);
    private final Color BORDER = new Color(210, 220, 230);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public PaymentFrame(
            int userId,
            String customerName) {

        this.userId = userId;
        this.customerName = customerName;

        setTitle("DriveX - Payment");

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setSize(1200, 780);

        setMinimumSize(
                new Dimension(1000, 680)
        );

        setLocationRelativeTo(null);

        setResizable(true);

        createUI();

        loadBookings();
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

        createCenterContent(mainPanel);

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
                BorderFactory.createEmptyBorder(
                        15,
                        30,
                        15,
                        30
                )
        );

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
                new JPanel();

        leftPanel.setLayout(
                new BoxLayout(
                        leftPanel,
                        BoxLayout.Y_AXIS
                )
        );

        leftPanel.setOpaque(false);


        JLabel title =
                new JLabel("DriveX");

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        32
                )
        );


        JLabel subtitle =
                new JLabel(
                        "Secure & Simple Payments"
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
                        "Segoe UI",
                        Font.PLAIN,
                        15
                )
        );

        subtitle.setBorder(
                BorderFactory.createEmptyBorder(
                        3,
                        2,
                        0,
                        0
                )
        );


        leftPanel.add(title);

        leftPanel.add(subtitle);


        // -----------------------------------------------------
        // RIGHT SIDE
        // -----------------------------------------------------

        JLabel customer =
                new JLabel(
                        "Customer: " +
                        customerName
                );

        customer.setForeground(Color.WHITE);

        customer.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        14
                )
        );

        customer.setHorizontalAlignment(
                SwingConstants.RIGHT
        );


        header.add(
                leftPanel,
                BorderLayout.WEST
        );

        header.add(
                customer,
                BorderLayout.EAST
        );


        mainPanel.add(
                header,
                BorderLayout.NORTH
        );
    }

    // =========================================================
    // CENTER CONTENT
    // =========================================================

    private void createCenterContent(
            JPanel mainPanel) {

        JPanel centerPanel =
                new JPanel(new GridBagLayout());

        centerPanel.setBackground(BG);

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        20,
                        15,
                        20
                )
        );


        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;

        gbc.weightx = 1.0;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.CENTER;


        // =====================================================
        // BOOKING CARD
        // =====================================================

        JPanel bookingCard =
                createBookingCard();


        gbc.gridy = 0;

        gbc.weighty = 1.0;

        gbc.fill =
                GridBagConstraints.BOTH;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        12,
                        0
                );


        centerPanel.add(
                bookingCard,
                gbc
        );


        // =====================================================
        // PAYMENT CARD
        // =====================================================

        JPanel paymentCard =
                createPaymentCard();


        gbc.gridy = 1;

        gbc.weighty = 0;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        0,
                        0
                );


        centerPanel.add(
                paymentCard,
                gbc
        );


        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // BOOKING CARD
    // =========================================================

    private JPanel createBookingCard() {

        JPanel card =
                new JPanel(new BorderLayout());

        card.setBackground(Color.WHITE);

        card.setBorder(
                new LineBorder(
                        BORDER,
                        1
                )
        );


        // -----------------------------------------------------
        // TOP INFORMATION
        // -----------------------------------------------------

        JPanel topPanel =
                new JPanel();

        topPanel.setLayout(
                new BoxLayout(
                        topPanel,
                        BoxLayout.Y_AXIS
                )
        );

        topPanel.setOpaque(false);

        topPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        20,
                        10,
                        20
                )
        );


        JLabel heading =
                new JLabel(
                        "Select Booking for Payment"
                );

        heading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        21
                )
        );

        heading.setForeground(DARK_TEXT);


        JLabel info =
                new JLabel(
                        "Only confirmed or completed bookings without a payment can be selected."
                );

        info.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        info.setForeground(GRAY);

        info.setBorder(
                BorderFactory.createEmptyBorder(
                        4,
                        0,
                        0,
                        0
                )
        );


        topPanel.add(heading);

        topPanel.add(info);


        card.add(
                topPanel,
                BorderLayout.NORTH
        );


        // -----------------------------------------------------
        // TABLE
        // -----------------------------------------------------

        String[] columns = {

                "Booking ID",
                "Car Number",
                "Brand",
                "Model",
                "Start Date",
                "Return Date",
                "Days",
                "Amount",
                "Status"
        };


        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };


        bookingTable =
                new JTable(tableModel);


        bookingTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        bookingTable.setRowHeight(32);

        bookingTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        bookingTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_OFF
        );

        bookingTable.setShowGrid(true);

        bookingTable.setGridColor(
                new Color(
                        225,
                        230,
                        235
                )
        );


        // -----------------------------------------------------
        // TABLE HEADER
        // -----------------------------------------------------

        bookingTable.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                12
                        )
                );

        bookingTable.getTableHeader()
                .setBackground(BLUE);

        bookingTable.getTableHeader()
                .setForeground(Color.WHITE);

        bookingTable.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                35
                        )
                );


        // -----------------------------------------------------
        // COLUMN WIDTHS
        // -----------------------------------------------------

        int[] widths = {

                90,
                110,
                115,
                115,
                110,
                110,
                60,
                120,
                100
        };


        for (int i = 0;
             i < widths.length;
             i++) {

            bookingTable
                    .getColumnModel()
                    .getColumn(i)
                    .setPreferredWidth(
                            widths[i]
                    );
        }


        // -----------------------------------------------------
        // CENTER ALIGNMENT
        // -----------------------------------------------------

        DefaultTableCellRenderer center =
                new DefaultTableCellRenderer();

        center.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        for (int i = 0;
             i < bookingTable.getColumnCount();
             i++) {

            bookingTable
                    .getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(center);
        }


        // -----------------------------------------------------
        // STATUS RENDERER
        // -----------------------------------------------------

        bookingTable
                .getColumnModel()
                .getColumn(8)
                .setCellRenderer(
                        new PaymentStatusRenderer()
                );


        JScrollPane scrollPane =
                new JScrollPane(
                        bookingTable
                );

        scrollPane.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        20,
                        15,
                        20
                )
        );


        card.add(
                scrollPane,
                BorderLayout.CENTER
        );


        return card;
    }

    // =========================================================
    // PAYMENT CARD
    // =========================================================

    private JPanel createPaymentCard() {

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

        card.setPreferredSize(
                new Dimension(
                        0,
                        105
                )
        );


        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        10,
                        12,
                        10,
                        12
                );

        gbc.anchor =
                GridBagConstraints.CENTER;


        // =====================================================
        // PAYMENT METHOD LABEL
        // =====================================================

        JLabel methodLabel =
                new JLabel(
                        "Payment Method"
                );

        methodLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        methodLabel.setForeground(
                DARK_TEXT
        );


        gbc.gridx = 0;

        gbc.gridy = 0;

        gbc.weightx = 0;

        card.add(
                methodLabel,
                gbc
        );


        // =====================================================
        // PAYMENT METHOD COMBO
        // =====================================================

        paymentMethodCombo =
                new JComboBox<>(
                        new String[]{
                                "Cash",
                                "UPI",
                                "Debit Card",
                                "Credit Card"
                        }
                );

        paymentMethodCombo.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        paymentMethodCombo.setPreferredSize(
                new Dimension(
                        190,
                        38
                )
        );


        gbc.gridx = 1;

        gbc.weightx = 0;

        card.add(
                paymentMethodCombo,
                gbc
        );


        // =====================================================
        // MAKE PAYMENT
        // =====================================================

        JButton payButton =
                createButton(
                        "MAKE PAYMENT",
                        GREEN
                );

        payButton.setPreferredSize(
                new Dimension(
                        170,
                        40
                )
        );

        payButton.addActionListener(
                e -> makePayment()
        );


        gbc.gridx = 2;

        card.add(
                payButton,
                gbc
        );


        // =====================================================
        // REFRESH
        // =====================================================

        JButton refreshButton =
                createButton(
                        "REFRESH",
                        BLUE
                );

        refreshButton.setPreferredSize(
                new Dimension(
                        120,
                        40
                )
        );

        refreshButton.addActionListener(
                e -> loadBookings()
        );


        gbc.gridx = 3;

        card.add(
                refreshButton,
                gbc
        );


        // =====================================================
        // BACK
        // =====================================================

        JButton backButton =
                createButton(
                        "BACK",
                        GRAY
                );

        backButton.setPreferredSize(
                new Dimension(
                        120,
                        40
                )
        );

        backButton.addActionListener(
                e -> dispose()
        );


        gbc.gridx = 4;

        card.add(
                backButton,
                gbc
        );


        return card;
    }

    // =========================================================
    // LOAD BOOKINGS
    // =========================================================

    private void loadBookings() {

        if (tableModel == null) {
            return;
        }

        tableModel.setRowCount(0);


        String query =
                "SELECT b.id, c.car_number, c.brand, c.model, " +
                "b.start_date, b.end_date, b.total_days, " +
                "b.total_amount, b.status " +
                "FROM bookings b " +
                "JOIN cars c ON b.car_id = c.id " +
                "WHERE b.user_id=? " +
                "AND b.status IN ('Confirmed','Booked','Completed') " +
                "AND NOT EXISTS (" +
                "SELECT 1 FROM payments p " +
                "WHERE p.booking_id=b.id " +
                ") " +
                "ORDER BY b.id DESC";


        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(query)
        ) {

            ps.setInt(
                    1,
                    userId
            );


            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                while (rs.next()) {

                    Object[] row = {

                            rs.getInt(
                                    "id"
                            ),

                            rs.getString(
                                    "car_number"
                            ),

                            rs.getString(
                                    "brand"
                            ),

                            rs.getString(
                                    "model"
                            ),

                            rs.getDate(
                                    "start_date"
                            ),

                            rs.getDate(
                                    "end_date"
                            ),

                            rs.getInt(
                                    "total_days"
                            ),

                            String.format(
                                    "₹ %.2f",
                                    rs.getDouble(
                                            "total_amount"
                                    )
                            ),

                            rs.getString(
                                    "status"
                            )
                    };


                    tableModel.addRow(row);
                }
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to load payment details.\n\n" +
                    "Please try again.",

                    "Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // MAKE PAYMENT
    // =========================================================

    private void makePayment() {

        int row =
                bookingTable.getSelectedRow();


        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please select a booking first.",

                    "Select Booking",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        int bookingId =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        row,
                                        0
                                )
                                .toString()
                );


        String status =
                tableModel
                        .getValueAt(
                                row,
                                8
                        )
                        .toString();


        if ("Cancelled".equalsIgnoreCase(status)) {

            JOptionPane.showMessageDialog(
                    this,

                    "Payment cannot be made for a cancelled booking.",

                    "Payment Not Allowed",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        String carNumber =
                tableModel
                        .getValueAt(
                                row,
                                1
                        )
                        .toString();


        String brand =
                tableModel
                        .getValueAt(
                                row,
                                2
                        )
                        .toString();


        String model =
                tableModel
                        .getValueAt(
                                row,
                                3
                        )
                        .toString();


        String startDate =
                tableModel
                        .getValueAt(
                                row,
                                4
                        )
                        .toString();


        String returnDate =
                tableModel
                        .getValueAt(
                                row,
                                5
                        )
                        .toString();


        int totalDays =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        row,
                                        6
                                )
                                .toString()
                );


        String amountText =
                tableModel
                        .getValueAt(
                                row,
                                7
                        )
                        .toString()
                        .replace(
                                "₹",
                                ""
                        )
                        .trim();


        double amount =
                Double.parseDouble(
                        amountText
                );


        String paymentMethod =
                paymentMethodCombo
                        .getSelectedItem()
                        .toString();


        int confirmation =
                JOptionPane.showConfirmDialog(

                        this,

                        "Confirm payment?\n\n" +

                        "Booking ID: " +
                        bookingId + "\n" +

                        "Vehicle: " +
                        brand + " " +
                        model + "\n" +

                        "Car Number: " +
                        carNumber + "\n" +

                        "Amount: ₹" +
                        String.format(
                                "%.2f",
                                amount
                        ) + "\n" +

                        "Payment Method: " +
                        paymentMethod,

                        "Confirm Payment",

                        JOptionPane.YES_NO_OPTION,

                        JOptionPane.QUESTION_MESSAGE
                );


        if (confirmation !=
                JOptionPane.YES_OPTION) {

            return;
        }


        processPayment(

                bookingId,
                carNumber,
                brand,
                model,
                startDate,
                returnDate,
                totalDays,
                amount,
                paymentMethod
        );
    }

    // =========================================================
    // PROCESS PAYMENT
    // =========================================================

    private void processPayment(

            int bookingId,
            String carNumber,
            String brand,
            String model,
            String startDate,
            String returnDate,
            int totalDays,
            double amount,
            String paymentMethod) {


        String transactionId =
                "TXN" +
                System.currentTimeMillis();


        String checkQuery =
                "SELECT id FROM payments " +
                "WHERE booking_id=? " +
                "LIMIT 1";


        String insertQuery =
                "INSERT INTO payments " +
                "(transaction_id, booking_id, user_id, " +
                "amount, payment_method, payment_date, status) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?)";


        try (
                Connection con =
                        DBConnection.getConnection()
        ) {

            // -------------------------------------------------
            // DUPLICATE PAYMENT CHECK
            // -------------------------------------------------

            try (
                    PreparedStatement check =
                            con.prepareStatement(
                                    checkQuery
                            )
            ) {

                check.setInt(
                        1,
                        bookingId
                );


                try (
                        ResultSet rs =
                                check.executeQuery()
                ) {

                    if (rs.next()) {

                        JOptionPane.showMessageDialog(

                                this,

                                "Payment Already Completed\n\n" +
                                "This booking already has a payment record.\n" +
                                "A second payment cannot be created.",

                                "Duplicate Payment",

                                JOptionPane.WARNING_MESSAGE
                        );


                        loadBookings();

                        return;
                    }
                }
            }


            // -------------------------------------------------
            // INSERT PAYMENT
            // -------------------------------------------------

            try (
                    PreparedStatement ps =
                            con.prepareStatement(
                                    insertQuery
                            )
            ) {

                ps.setString(
                        1,
                        transactionId
                );

                ps.setInt(
                        2,
                        bookingId
                );

                ps.setInt(
                        3,
                        userId
                );

                ps.setDouble(
                        4,
                        amount
                );

                ps.setString(
                        5,
                        paymentMethod
                );

                ps.setDate(
                        6,
                        Date.valueOf(
                                LocalDate.now()
                        )
                );

                ps.setString(
                        7,
                        "Paid"
                );


                ps.executeUpdate();
            }


            // -------------------------------------------------
            // SUCCESS MESSAGE
            // -------------------------------------------------

            JOptionPane.showMessageDialog(

                    this,

                    "Payment Successful!\n\n" +
                    "Transaction ID: " +
                    transactionId,

                    "DriveX Payment",

                    JOptionPane.INFORMATION_MESSAGE
            );


            String paymentDate =
                    LocalDate.now().toString();


            // -------------------------------------------------
            // PAYMENT RECEIPT
            // -------------------------------------------------

            PaymentBillFrame bill =
                    new PaymentBillFrame(

                            transactionId,

                            bookingId,

                            customerName,

                            carNumber,

                            brand,

                            model,

                            startDate,

                            returnDate,

                            totalDays,

                            amount,

                            paymentMethod,

                            paymentDate
                    );


            bill.setVisible(true);


            loadBookings();


        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(

                    this,

                    "Payment could not be completed.\n\n" +
                    "Please try again.",

                    "Payment Error",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // BUTTON
    // =========================================================

    private JButton createButton(
            String text,
            Color color) {

        JButton button =
                new JButton(text);


        button.setBackground(color);

        button.setForeground(Color.WHITE);


        button.setFont(
                new Font(
                        "Segoe UI",
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
    // PAYMENT STATUS RENDERER
    // =========================================================

    private class PaymentStatusRenderer
            extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(

                JTable table,
                Object value,
                boolean isSelected,
                boolean hasFocus,
                int row,
                int column) {

            Component component =
                    super.getTableCellRendererComponent(

                            table,
                            value,
                            isSelected,
                            hasFocus,
                            row,
                            column
                    );


            setHorizontalAlignment(
                    SwingConstants.CENTER
            );


            if (!isSelected) {

                String status =
                        value == null
                                ? ""
                                : value.toString();


                if ("Completed".equalsIgnoreCase(
                        status)) {

                    setForeground(GREEN);

                } else if ("Confirmed".equalsIgnoreCase(
                        status)) {

                    setForeground(BLUE);

                } else if ("Booked".equalsIgnoreCase(
                        status)) {

                    setForeground(ORANGE);

                } else {

                    setForeground(
                            Color.DARK_GRAY
                    );
                }

            } else {

                setForeground(Color.WHITE);
            }


            return component;
        }
    }

    // =========================================================
    // FOOTER
    // =========================================================

    private void createFooter(
            JPanel mainPanel) {

        JPanel footer =
                new JPanel(
                        new GridBagLayout()
                );

        footer.setBackground(
                new Color(
                        225,
                        235,
                        248
                )
        );

        footer.setPreferredSize(
                new Dimension(
                        0,
                        45
                )
        );


        JLabel footerLabel =
                new JLabel(
                        "DriveX • Drive • Explore • Enjoy"
                );

        footerLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        footerLabel.setForeground(GRAY);


        footer.add(
                footerLabel
        );


        mainPanel.add(
                footer,
                BorderLayout.SOUTH
        );
    }
}