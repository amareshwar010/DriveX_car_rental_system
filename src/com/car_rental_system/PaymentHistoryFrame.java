package com.car_rental_system;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class PaymentHistoryFrame extends JFrame {

    private final int userId;
    private final String customerName;

    private JTable paymentTable;
    private DefaultTableModel tableModel;

    private final Color BG = new Color(235, 245, 255);
    private final Color BLUE = new Color(25, 118, 210);
    private final Color DARK_BLUE = new Color(13, 71, 161);
    private final Color GREEN = new Color(40, 167, 69);
    private final Color GRAY = new Color(108, 117, 125);
    private final Color RED = new Color(220, 53, 69);
    private final Color PURPLE = new Color(108, 76, 180);
    private final Color ORANGE = new Color(255, 152, 0);
    private final Color DARK_TEXT = new Color(45, 45, 65);
    private final Color BORDER = new Color(210, 220, 230);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public PaymentHistoryFrame(
            int userId,
            String customerName) {

        this.userId = userId;
        this.customerName = customerName;

        setTitle("DriveX - Payment History");

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setSize(1250, 780);

        setMinimumSize(
                new Dimension(1000, 680)
        );

        setLocationRelativeTo(null);

        setResizable(true);

        createUI();

        loadPaymentHistory();
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
        // LEFT
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
                        34
                )
        );


        JLabel subtitle =
                new JLabel(
                        "Payment History"
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
        // CUSTOMER
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
        gbc.gridy = 0;

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.fill =
                GridBagConstraints.BOTH;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        15,
                        0
                );


        // =====================================================
        // TABLE CARD
        // =====================================================

        JPanel tableCard =
                createTableCard();


        centerPanel.add(
                tableCard,
                gbc
        );


        // =====================================================
        // ACTION PANEL
        // =====================================================

        JPanel actionPanel =
                createActionPanel();


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
                actionPanel,
                gbc
        );


        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // TABLE CARD
    // =========================================================

    private JPanel createTableCard() {

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
        // TOP
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
                        "Your Payment History"
                );

        heading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        heading.setForeground(
                DARK_TEXT
        );


        JLabel description =
                new JLabel(
                        "View all payments made through your DriveX account."
                );

        description.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        description.setForeground(GRAY);

        description.setBorder(
                BorderFactory.createEmptyBorder(
                        4,
                        0,
                        0,
                        0
                )
        );


        topPanel.add(heading);

        topPanel.add(description);


        card.add(
                topPanel,
                BorderLayout.NORTH
        );


        // -----------------------------------------------------
        // TABLE
        // -----------------------------------------------------

        String[] columns = {

                "Payment ID",
                "Transaction ID",
                "Booking ID",
                "Car Number",
                "Vehicle",
                "Amount",
                "Method",
                "Payment Date",
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


        paymentTable =
                new JTable(tableModel);


        paymentTable.setRowHeight(32);

        paymentTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        paymentTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        paymentTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_OFF
        );

        paymentTable.setShowGrid(true);

        paymentTable.setGridColor(
                new Color(
                        225,
                        230,
                        235
                )
        );


        // -----------------------------------------------------
        // TABLE HEADER
        // -----------------------------------------------------

        paymentTable.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                12
                        )
                );

        paymentTable.getTableHeader()
                .setBackground(BLUE);

        paymentTable.getTableHeader()
                .setForeground(Color.WHITE);

        paymentTable.getTableHeader()
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

                85,
                170,
                90,
                110,
                180,
                110,
                120,
                125,
                105
        };


        for (int i = 0;
             i < widths.length;
             i++) {

            paymentTable
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
             i < paymentTable.getColumnCount();
             i++) {

            paymentTable
                    .getColumnModel()
                    .getColumn(i)
                    .setCellRenderer(center);
        }


        // -----------------------------------------------------
        // STATUS COLUMN
        // -----------------------------------------------------

        paymentTable
                .getColumnModel()
                .getColumn(8)
                .setCellRenderer(
                        new PaymentStatusRenderer()
                );


        // -----------------------------------------------------
        // SCROLL PANE
        // -----------------------------------------------------

        JScrollPane scrollPane =
                new JScrollPane(
                        paymentTable
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
    // ACTION PANEL
    // =========================================================

    private JPanel createActionPanel() {

        JPanel panel =
                new JPanel(
                        new GridBagLayout()
                );

        panel.setBackground(Color.WHITE);

        panel.setBorder(
                new LineBorder(
                        BORDER,
                        1
                )
        );

        panel.setPreferredSize(
                new Dimension(
                        0,
                        75
                )
        );


        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridy = 0;

        gbc.insets =
                new Insets(
                        10,
                        8,
                        10,
                        8
                );

        gbc.anchor =
                GridBagConstraints.CENTER;


        // =====================================================
        // VIEW RECEIPT
        // =====================================================

        JButton viewReceipt =
                createButton(
                        "VIEW RECEIPT",
                        BLUE
                );

        viewReceipt.setPreferredSize(
                new Dimension(
                        180,
                        42
                )
        );

        viewReceipt.addActionListener(
                e -> viewReceipt()
        );


        gbc.gridx = 0;

        panel.add(
                viewReceipt,
                gbc
        );


        // =====================================================
        // REFRESH
        // =====================================================

        JButton refresh =
                createButton(
                        "REFRESH",
                        GREEN
                );

        refresh.setPreferredSize(
                new Dimension(
                        160,
                        42
                )
        );

        refresh.addActionListener(
                e -> loadPaymentHistory()
        );


        gbc.gridx = 1;

        panel.add(
                refresh,
                gbc
        );


        // =====================================================
        // BACK
        // =====================================================

        JButton back =
                createButton(
                        "BACK",
                        GRAY
                );

        back.setPreferredSize(
                new Dimension(
                        160,
                        42
                )
        );

        back.addActionListener(
                e -> dispose()
        );


        gbc.gridx = 2;

        panel.add(
                back,
                gbc
        );


        return panel;
    }

    // =========================================================
    // LOAD PAYMENT HISTORY
    // =========================================================

    private void loadPaymentHistory() {

        if (tableModel == null) {
            return;
        }

        tableModel.setRowCount(0);


        String sql =
                "SELECT p.id, " +
                "p.transaction_id, " +
                "p.booking_id, " +
                "c.car_number, " +
                "c.brand, " +
                "c.model, " +
                "p.amount, " +
                "p.payment_method, " +
                "p.payment_date, " +
                "p.status " +
                "FROM payments p " +
                "JOIN bookings b " +
                "ON p.booking_id = b.id " +
                "JOIN cars c " +
                "ON b.car_id = c.id " +
                "WHERE p.user_id = ? " +
                "ORDER BY p.id DESC";


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

                while (rs.next()) {

                    int paymentId =
                            rs.getInt("id");


                    String transactionId =
                            rs.getString(
                                    "transaction_id"
                            );


                    int bookingId =
                            rs.getInt(
                                    "booking_id"
                            );


                    String carNumber =
                            rs.getString(
                                    "car_number"
                            );


                    String vehicle =
                            rs.getString(
                                    "brand"
                            )
                            + " "
                            +
                            rs.getString(
                                    "model"
                            );


                    double amount =
                            rs.getDouble(
                                    "amount"
                            );


                    String method =
                            rs.getString(
                                    "payment_method"
                            );


                    Date paymentDate =
                            rs.getDate(
                                    "payment_date"
                            );


                    String status =
                            rs.getString(
                                    "status"
                            );


                    tableModel.addRow(
                            new Object[]{

                                    paymentId,

                                    transactionId,

                                    bookingId,

                                    carNumber,

                                    vehicle,

                                    String.format(
                                            "₹ %.2f",
                                            amount
                                    ),

                                    method,

                                    paymentDate,

                                    status
                            }
                    );
                }
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to load payment history.\n\n"
                    + "Please try again.",

                    "DriveX - Payment History",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // VIEW RECEIPT
    // =========================================================

    private void viewReceipt() {

        int selectedRow =
                paymentTable.getSelectedRow();


        if (selectedRow == -1) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please select a payment to view its receipt.",

                    "No Payment Selected",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        int paymentId =
                Integer.parseInt(
                        tableModel.getValueAt(
                                selectedRow,
                                0
                        ).toString()
                );


        showReceipt(paymentId);
    }

    // =========================================================
    // SHOW RECEIPT
    // =========================================================

    private void showReceipt(
            int paymentId) {

        String sql =
                "SELECT p.transaction_id, " +
                "p.booking_id, " +
                "p.amount, " +
                "p.payment_method, " +
                "p.payment_date, " +
                "b.start_date, " +
                "b.end_date, " +
                "b.total_days, " +
                "c.car_number, " +
                "c.brand, " +
                "c.model " +
                "FROM payments p " +
                "JOIN bookings b " +
                "ON p.booking_id = b.id " +
                "JOIN cars c " +
                "ON b.car_id = c.id " +
                "WHERE p.id = ? " +
                "AND p.user_id = ?";


        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    paymentId
            );

            ps.setInt(
                    2,
                    userId
            );


            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    String transactionId =
                            rs.getString(
                                    "transaction_id"
                            );


                    int bookingId =
                            rs.getInt(
                                    "booking_id"
                            );


                    double amount =
                            rs.getDouble(
                                    "amount"
                            );


                    String paymentMethod =
                            rs.getString(
                                    "payment_method"
                            );


                    String paymentDate =
                            rs.getDate(
                                    "payment_date"
                            ).toString();


                    String startDate =
                            rs.getDate(
                                    "start_date"
                            ).toString();


                    String endDate =
                            rs.getDate(
                                    "end_date"
                            ).toString();


                    int totalDays =
                            rs.getInt(
                                    "total_days"
                            );


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


                    PaymentBillFrame bill =
                            new PaymentBillFrame(

                                    transactionId,

                                    bookingId,

                                    customerName,

                                    carNumber,

                                    brand,

                                    model,

                                    startDate,

                                    endDate,

                                    totalDays,

                                    amount,

                                    paymentMethod,

                                    paymentDate
                            );


                    bill.setVisible(true);

                } else {

                    JOptionPane.showMessageDialog(
                            this,

                            "Payment receipt could not be found.",

                            "DriveX",

                            JOptionPane.WARNING_MESSAGE
                    );
                }
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to open the payment receipt.\n\n"
                    + "Please try again.",

                    "DriveX",

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

    private static class PaymentStatusRenderer
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


            if (isSelected) {

                setBackground(
                        table.getSelectionBackground()
                );

                setForeground(
                        Color.WHITE
                );

                return component;
            }


            setBackground(Color.WHITE);


            if (column == 8 &&
                    value != null) {

                String status =
                        value.toString();


                if (status.equalsIgnoreCase(
                        "Paid")) {

                    setForeground(
                            new Color(
                                    40,
                                    167,
                                    69
                            )
                    );

                } else if (
                        status.equalsIgnoreCase(
                                "Refunded")) {

                    setForeground(
                            new Color(
                                    108,
                                    76,
                                    180
                            )
                    );

                } else if (
                        status.equalsIgnoreCase(
                                "Failed")) {

                    setForeground(
                            new Color(
                                    220,
                                    53,
                                    69
                            )
                    );

                } else {

                    setForeground(
                            new Color(
                                    255,
                                    152,
                                    0
                            )
                    );
                }


                setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                12
                        )
                );

            } else {

                setForeground(
                        Color.DARK_GRAY
                );
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