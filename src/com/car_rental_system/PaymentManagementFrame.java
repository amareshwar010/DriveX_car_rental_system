package com.car_rental_system;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class PaymentManagementFrame extends JFrame {

    private JTextField txtPaymentId;
    private JTextField txtTransactionId;
    private JComboBox<String> cmbStatus;
    private JTextField txtSearch;

    private JTable paymentTable;
    private DefaultTableModel tableModel;

    // =========================================================
    // COLORS
    // =========================================================

    private final Color BG_COLOR = new Color(235, 245, 255);
    private final Color PURPLE = new Color(67, 56, 140);
    private final Color PRIMARY = new Color(25, 118, 210);
    private final Color GREEN = new Color(40, 167, 69);
    private final Color ORANGE = new Color(255, 152, 0);
    private final Color RED = new Color(220, 53, 69);
    private final Color GRAY = new Color(108, 117, 125);
    private final Color BORDER = new Color(210, 220, 230);
    private final Color DARK_TEXT = new Color(45, 45, 65);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public PaymentManagementFrame() {

        setTitle("DriveX - Payment Management");

        setSize(1250, 800);
        setMinimumSize(new Dimension(1050, 700));

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
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

        JPanel mainPanel = new JPanel(
                new BorderLayout()
        );

        mainPanel.setBackground(BG_COLOR);

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

        JPanel header = new JPanel(
                new BorderLayout()
        );

        header.setBackground(PURPLE);

        header.setBorder(
                new EmptyBorder(
                        15,
                        30,
                        15,
                        30
                )
        );

        JLabel title =
                new JLabel("DriveX");

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Payment Management"
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

        JPanel titlePanel =
                new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new BoxLayout(
                        titlePanel,
                        BoxLayout.Y_AXIS
                )
        );

        titlePanel.add(title);

        titlePanel.add(
                Box.createVerticalStrut(3)
        );

        titlePanel.add(subtitle);

        header.add(
                titlePanel,
                BorderLayout.WEST
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
                new JPanel(
                        new BorderLayout(
                                0,
                                12
                        )
                );

        centerPanel.setBackground(BG_COLOR);

        centerPanel.setBorder(
                new EmptyBorder(
                        15,
                        25,
                        10,
                        25
                )
        );

        JPanel controlAndSearch =
                new JPanel(
                        new BorderLayout(
                                0,
                                12
                        )
                );

        controlAndSearch.setOpaque(false);

        createControlCard(
                controlAndSearch
        );

        createSearchCard(
                controlAndSearch
        );

        centerPanel.add(
                controlAndSearch,
                BorderLayout.NORTH
        );

        createTableCard(centerPanel);

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // CONTROL CARD
    // =========================================================

    private void createControlCard(
            JPanel parent) {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                0,
                                12
                        )
                );

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                12,
                                15,
                                12,
                                15
                        )
                )
        );

        JLabel title =
                new JLabel(
                        "Payment Actions"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        title.setForeground(PURPLE);

        card.add(
                title,
                BorderLayout.NORTH
        );

        JPanel actionPanel =
                new JPanel(
                        new GridBagLayout()
                );

        actionPanel.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        3,
                        6,
                        3,
                        6
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.CENTER;

        // -----------------------------------------------------
        // PAYMENT ID
        // -----------------------------------------------------

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;

        actionPanel.add(
                createFieldLabel(
                        "Payment ID"
                ),
                gbc
        );

        txtPaymentId =
                new JTextField();

        styleReadOnlyField(
                txtPaymentId
        );

        gbc.gridx = 1;
        gbc.weightx = 0.8;

        actionPanel.add(
                txtPaymentId,
                gbc
        );

        // -----------------------------------------------------
        // TRANSACTION ID
        // -----------------------------------------------------

        gbc.gridx = 2;
        gbc.weightx = 0;

        actionPanel.add(
                createFieldLabel(
                        "Transaction ID"
                ),
                gbc
        );

        txtTransactionId =
                new JTextField();

        styleReadOnlyField(
                txtTransactionId
        );

        gbc.gridx = 3;
        gbc.weightx = 1.2;

        actionPanel.add(
                txtTransactionId,
                gbc
        );

        // -----------------------------------------------------
        // STATUS
        // -----------------------------------------------------

        gbc.gridx = 4;
        gbc.weightx = 0;

        actionPanel.add(
                createFieldLabel(
                        "Status"
                ),
                gbc
        );

        cmbStatus =
                new JComboBox<>(
                        new String[]{
                                "Paid",
                                "Pending",
                                "Failed",
                                "Refunded"
                        }
                );

        cmbStatus.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        cmbStatus.setPreferredSize(
                new Dimension(
                        130,
                        34
                )
        );

        gbc.gridx = 5;
        gbc.weightx = 0.7;

        actionPanel.add(
                cmbStatus,
                gbc
        );

        // -----------------------------------------------------
        // UPDATE
        // -----------------------------------------------------

        JButton btnUpdate =
                createButton(
                        "UPDATE STATUS",
                        PRIMARY
                );

        gbc.gridx = 6;
        gbc.weightx = 0.8;

        actionPanel.add(
                btnUpdate,
                gbc
        );

        // -----------------------------------------------------
        // VIEW
        // -----------------------------------------------------

        JButton btnView =
                createButton(
                        "VIEW",
                        GREEN
                );

        gbc.gridx = 7;
        gbc.weightx = 0.5;

        actionPanel.add(
                btnView,
                gbc
        );

        // -----------------------------------------------------
        // SECOND ROW
        // -----------------------------------------------------

        JLabel note =
                new JLabel(
                        "Payment records should normally be updated, not deleted."
                );

        note.setFont(
                new Font(
                        "Arial",
                        Font.ITALIC,
                        12
                )
        );

        note.setForeground(GRAY);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 6;
        gbc.weightx = 1;

        actionPanel.add(
                note,
                gbc
        );

        // -----------------------------------------------------
        // REFUND
        // -----------------------------------------------------

        JButton btnRefund =
                createButton(
                        "MARK REFUNDED",
                        ORANGE
                );

        gbc.gridx = 6;
        gbc.gridwidth = 1;
        gbc.weightx = 0.8;

        actionPanel.add(
                btnRefund,
                gbc
        );

        // -----------------------------------------------------
        // CLEAR
        // -----------------------------------------------------

        JButton btnClear =
                createButton(
                        "CLEAR",
                        GRAY
                );

        gbc.gridx = 7;
        gbc.weightx = 0.5;

        actionPanel.add(
                btnClear,
                gbc
        );

        card.add(
                actionPanel,
                BorderLayout.CENTER
        );

        // -----------------------------------------------------
        // ACTION LISTENERS
        // -----------------------------------------------------

        btnUpdate.addActionListener(
                e -> updatePaymentStatus()
        );

        btnRefund.addActionListener(
                e -> markRefunded()
        );

        btnView.addActionListener(
                e -> viewPayment()
        );

        btnClear.addActionListener(
                e -> clearFields()
        );

        parent.add(
                card,
                BorderLayout.NORTH
        );
    }

    // =========================================================
    // SEARCH CARD
    // =========================================================

    private void createSearchCard(
            JPanel parent) {

        JPanel card =
                new JPanel(
                        new GridBagLayout()
                );

        card.setBackground(Color.WHITE);

        card.setBorder(
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

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        3,
                        6,
                        3,
                        6
                );

        gbc.gridy = 0;
        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        JLabel searchLabel =
                new JLabel(
                        "Search Payment"
                );

        searchLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        gbc.gridx = 0;
        gbc.weightx = 0;

        card.add(
                searchLabel,
                gbc
        );

        txtSearch =
                new JTextField();

        txtSearch.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        txtSearch.setPreferredSize(
                new Dimension(
                        350,
                        34
                )
        );

        gbc.gridx = 1;
        gbc.weightx = 1;

        card.add(
                txtSearch,
                gbc
        );

        JButton btnSearch =
                createButton(
                        "SEARCH",
                        PRIMARY
                );

        gbc.gridx = 2;
        gbc.weightx = 0;

        card.add(
                btnSearch,
                gbc
        );

        JButton btnShowAll =
                createButton(
                        "SHOW ALL",
                        PURPLE
                );

        gbc.gridx = 3;

        card.add(
                btnShowAll,
                gbc
        );

        JLabel searchInfo =
                new JLabel(
                        "Transaction ID • Customer • Phone • Method • Status • Booking ID"
                );

        searchInfo.setFont(
                new Font(
                        "Arial",
                        Font.ITALIC,
                        11
                )
        );

        searchInfo.setForeground(GRAY);

        gbc.gridx = 4;
        gbc.weightx = 1;

        card.add(
                searchInfo,
                gbc
        );

        btnSearch.addActionListener(
                e -> searchPayments()
        );

        btnShowAll.addActionListener(
                e -> loadPayments()
        );

        txtSearch.addActionListener(
                e -> searchPayments()
        );

        parent.add(
                card,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // TABLE CARD
    // =========================================================

    private void createTableCard(
            JPanel centerPanel) {

        JPanel tableCard =
                new JPanel(
                        new BorderLayout(
                                0,
                                8
                        )
                );

        tableCard.setBackground(Color.WHITE);

        tableCard.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER,
                                1
                        ),
                        new EmptyBorder(
                                10,
                                15,
                                12,
                                15
                        )
                )
        );

        JLabel tableTitle =
                new JLabel(
                        "Payment Records"
                );

        tableTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        tableTitle.setForeground(PURPLE);

        tableCard.add(
                tableTitle,
                BorderLayout.NORTH
        );

        String[] columns = {
                "Payment ID",
                "Transaction ID",
                "Booking ID",
                "Customer",
                "Phone",
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
                        "Arial",
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

        paymentTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                12
                        )
                );

        paymentTable.getTableHeader()
                .setBackground(PURPLE);

        paymentTable.getTableHeader()
                .setForeground(Color.WHITE);

        paymentTable.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                35
                        )
                );

        int[] widths = {
                85,
                180,
                90,
                180,
                120,
                120,
                130,
                125,
                110
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

        // Center selected columns
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

        // Status renderer
        paymentTable
                .getColumnModel()
                .getColumn(8)
                .setCellRenderer(
                        new PaymentStatusRenderer()
                );

        JScrollPane scrollPane =
                new JScrollPane(
                        paymentTable
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        BORDER
                )
        );

        tableCard.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // -----------------------------------------------------
        // TABLE SELECTION
        // -----------------------------------------------------

        paymentTable
                .getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                int row =
                                        paymentTable
                                                .getSelectedRow();

                                if (row != -1) {

                                    txtPaymentId
                                            .setText(
                                                    tableModel
                                                            .getValueAt(
                                                                    row,
                                                                    0
                                                            )
                                                            .toString()
                                            );

                                    txtTransactionId
                                            .setText(
                                                    tableModel
                                                            .getValueAt(
                                                                    row,
                                                                    1
                                                            )
                                                            .toString()
                                            );

                                    cmbStatus
                                            .setSelectedItem(
                                                    tableModel
                                                            .getValueAt(
                                                                    row,
                                                                    8
                                                            )
                                                            .toString()
                                            );
                                }
                            }
                        }
                );

        centerPanel.add(
                tableCard,
                BorderLayout.CENTER
        );
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

        footer.setBackground(
                new Color(
                        225,
                        235,
                        248
                )
        );

        footer.setBorder(
                new EmptyBorder(
                        8,
                        25,
                        8,
                        25
                )
        );

        JButton backButton =
                createButton(
                        "BACK TO DASHBOARD",
                        GRAY
                );

        backButton.setPreferredSize(
                new Dimension(
                        180,
                        35
                )
        );

        backButton.addActionListener(
                e -> {

                    new AdminDashboardFrame()
                            .setVisible(true);

                    dispose();
                }
        );

        footer.add(
                backButton,
                BorderLayout.WEST
        );

        JLabel footerText =
                new JLabel(
                        "DriveX • Drive • Explore • Enjoy",
                        SwingConstants.CENTER
                );

        footerText.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        footerText.setForeground(GRAY);

        footer.add(
                footerText,
                BorderLayout.CENTER
        );

        mainPanel.add(
                footer,
                BorderLayout.SOUTH
        );
    }

    // =========================================================
    // LOAD PAYMENTS
    // =========================================================

    private void loadPayments() {

        tableModel.setRowCount(0);

        String sql =
                "SELECT p.id AS payment_id, "
                        + "p.transaction_id, "
                        + "p.booking_id, "
                        + "u.name AS customer, "
                        + "u.phone, "
                        + "p.amount, "
                        + "p.payment_method, "
                        + "p.payment_date, "
                        + "p.status "
                        + "FROM payments p "
                        + "JOIN users u ON p.user_id = u.id "
                        + "ORDER BY p.id DESC";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                tableModel.addRow(
                        new Object[]{
                                rs.getInt(
                                        "payment_id"
                                ),

                                rs.getString(
                                        "transaction_id"
                                ),

                                rs.getInt(
                                        "booking_id"
                                ),

                                rs.getString(
                                        "customer"
                                ),

                                rs.getString(
                                        "phone"
                                ),

                                String.format(
                                        "₹ %.2f",
                                        rs.getDouble(
                                                "amount"
                                        )
                                ),

                                rs.getString(
                                        "payment_method"
                                ),

                                rs.getDate(
                                        "payment_date"
                                ),

                                rs.getString(
                                        "status"
                                )
                        }
                );
            }

        } catch (SQLException ex) {

            showDatabaseError();
        }
    }

    // =========================================================
    // SEARCH PAYMENTS
    // =========================================================

    private void searchPayments() {

        String search =
                txtSearch.getText().trim();

        if (search.isEmpty()) {

            loadPayments();

            return;
        }

        tableModel.setRowCount(0);

        String sql =
                "SELECT p.id AS payment_id, "
                        + "p.transaction_id, "
                        + "p.booking_id, "
                        + "u.name AS customer, "
                        + "u.phone, "
                        + "p.amount, "
                        + "p.payment_method, "
                        + "p.payment_date, "
                        + "p.status "
                        + "FROM payments p "
                        + "JOIN users u ON p.user_id = u.id "
                        + "WHERE p.transaction_id LIKE ? "
                        + "OR u.name LIKE ? "
                        + "OR u.phone LIKE ? "
                        + "OR p.payment_method LIKE ? "
                        + "OR p.status LIKE ? "
                        + "OR CAST(p.booking_id AS CHAR) LIKE ? "
                        + "ORDER BY p.id DESC";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            String value =
                    "%" + search + "%";

            for (int i = 1; i <= 6; i++) {

                ps.setString(
                        i,
                        value
                );
            }

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                while (rs.next()) {

                    tableModel.addRow(
                            new Object[]{
                                    rs.getInt(
                                            "payment_id"
                                    ),

                                    rs.getString(
                                            "transaction_id"
                                    ),

                                    rs.getInt(
                                            "booking_id"
                                    ),

                                    rs.getString(
                                            "customer"
                                    ),

                                    rs.getString(
                                            "phone"
                                    ),

                                    String.format(
                                            "₹ %.2f",
                                            rs.getDouble(
                                                    "amount"
                                            )
                                    ),

                                    rs.getString(
                                            "payment_method"
                                    ),

                                    rs.getDate(
                                            "payment_date"
                                    ),

                                    rs.getString(
                                            "status"
                                    )
                            }
                    );
                }
            }

        } catch (SQLException ex) {

            showDatabaseError();
        }
    }

    // =========================================================
    // UPDATE STATUS
    // =========================================================

    private void updatePaymentStatus() {

        if (txtPaymentId.getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a payment record first.",
                    "Payment Not Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int paymentId =
                Integer.parseInt(
                        txtPaymentId
                                .getText()
                                .trim()
                );

        String newStatus =
                cmbStatus
                        .getSelectedItem()
                        .toString();

        try (
                Connection con =
                        DBConnection.getConnection()
        ) {

            String currentStatus =
                    getPaymentStatus(
                            con,
                            paymentId
                    );

            if (currentStatus == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "The selected payment record "
                                + "could not be found.",
                        "Payment Not Found",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            if (currentStatus.equalsIgnoreCase(
                    "Refunded")
                    &&
                    !newStatus.equalsIgnoreCase(
                            "Refunded")) {

                JOptionPane.showMessageDialog(
                        this,
                        "A refunded payment cannot be changed "
                                + "back to another payment status.",
                        "Invalid Status Change",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,

                            "Update payment #"
                                    + paymentId
                                    + " to status \""
                                    + newStatus
                                    + "\"?",

                            "Confirm Payment Update",

                            JOptionPane.YES_NO_OPTION
                    );

            if (choice !=
                    JOptionPane.YES_OPTION) {

                return;
            }

            String sql =
                    "UPDATE payments "
                            + "SET status=? "
                            + "WHERE id=?";

            try (
                    PreparedStatement ps =
                            con.prepareStatement(sql)
            ) {

                ps.setString(
                        1,
                        newStatus
                );

                ps.setInt(
                        2,
                        paymentId
                );

                ps.executeUpdate();
            }

            JOptionPane.showMessageDialog(
                    this,

                    "Payment status updated successfully.\n\n"
                            + "New Status: "
                            + newStatus,

                    "Payment Updated",

                    JOptionPane.INFORMATION_MESSAGE
            );

            loadPayments();

            clearFields();

        } catch (SQLException ex) {

            showDatabaseError();
        }
    }

    // =========================================================
    // MARK REFUNDED
    // =========================================================

    private void markRefunded() {

        if (txtPaymentId.getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a payment record first.",
                    "Payment Not Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int paymentId =
                Integer.parseInt(
                        txtPaymentId
                                .getText()
                                .trim()
                );

        try (
                Connection con =
                        DBConnection.getConnection()
        ) {

            String currentStatus =
                    getPaymentStatus(
                            con,
                            paymentId
                    );

            if (currentStatus == null) {

                JOptionPane.showMessageDialog(
                        this,
                        "The selected payment could not be found.",
                        "Payment Not Found",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            if (currentStatus.equalsIgnoreCase(
                    "Refunded")) {

                JOptionPane.showMessageDialog(
                        this,
                        "This payment has already been marked as refunded.",
                        "Already Refunded",
                        JOptionPane.INFORMATION_MESSAGE
                );

                return;
            }

            if (!currentStatus.equalsIgnoreCase(
                    "Paid")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Only payments with status \"Paid\" "
                                + "can be marked as refunded.",
                        "Refund Not Allowed",
                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,

                            "Mark payment #"
                                    + paymentId
                                    + " as REFUNDED?\n\n"
                                    + "This changes the payment record "
                                    + "but does not process an actual "
                                    + "bank or UPI refund.",

                            "Confirm Refund Status",

                            JOptionPane.YES_NO_OPTION,

                            JOptionPane.WARNING_MESSAGE
                    );

            if (choice !=
                    JOptionPane.YES_OPTION) {

                return;
            }

            String sql =
                    "UPDATE payments "
                            + "SET status='Refunded' "
                            + "WHERE id=?";

            try (
                    PreparedStatement ps =
                            con.prepareStatement(sql)
            ) {

                ps.setInt(
                        1,
                        paymentId
                );

                ps.executeUpdate();
            }

            JOptionPane.showMessageDialog(
                    this,

                    "Payment marked as refunded successfully.\n\n"
                            + "Note: DriveX has updated the record only. "
                            + "No real financial transaction was processed.",

                    "Refund Status Updated",

                    JOptionPane.INFORMATION_MESSAGE
            );

            loadPayments();

            clearFields();

        } catch (SQLException ex) {

            showDatabaseError();
        }
    }

    // =========================================================
    // VIEW PAYMENT
    // =========================================================

    private void viewPayment() {

        int row =
                paymentTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a payment record first.",
                    "Payment Not Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String paymentId =
                tableModel
                        .getValueAt(
                                row,
                                0
                        )
                        .toString();

        String transactionId =
                tableModel
                        .getValueAt(
                                row,
                                1
                        )
                        .toString();

        String bookingId =
                tableModel
                        .getValueAt(
                                row,
                                2
                        )
                        .toString();

        String customer =
                tableModel
                        .getValueAt(
                                row,
                                3
                        )
                        .toString();

        String phone =
                tableModel
                        .getValueAt(
                                row,
                                4
                        )
                        .toString();

        String amount =
                tableModel
                        .getValueAt(
                                row,
                                5
                        )
                        .toString();

        String method =
                tableModel
                        .getValueAt(
                                row,
                                6
                        )
                        .toString();

        String date =
                tableModel
                        .getValueAt(
                                row,
                                7
                        )
                        .toString();

        String status =
                tableModel
                        .getValueAt(
                                row,
                                8
                        )
                        .toString();

        String message =
                "PAYMENT DETAILS\n\n"
                        + "Payment ID     : "
                        + paymentId
                        + "\n"
                        + "Transaction ID : "
                        + transactionId
                        + "\n"
                        + "Booking ID     : "
                        + bookingId
                        + "\n\n"
                        + "Customer       : "
                        + customer
                        + "\n"
                        + "Phone          : "
                        + phone
                        + "\n\n"
                        + "Amount         : "
                        + amount
                        + "\n"
                        + "Method         : "
                        + method
                        + "\n"
                        + "Payment Date   : "
                        + date
                        + "\n"
                        + "Status         : "
                        + status;

        JOptionPane.showMessageDialog(
                this,
                message,
                "DriveX - Payment Details",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================================
    // GET PAYMENT STATUS
    // =========================================================

    private String getPaymentStatus(
            Connection con,
            int paymentId)
            throws SQLException {

        String sql =
                "SELECT status "
                        + "FROM payments "
                        + "WHERE id=?";

        try (
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    paymentId
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    return rs.getString(
                            "status"
                    );
                }
            }
        }

        return null;
    }

    // =========================================================
    // CLEAR
    // =========================================================

    private void clearFields() {

        txtPaymentId.setText("");

        txtTransactionId.setText("");

        cmbStatus.setSelectedIndex(0);

        paymentTable.clearSelection();
    }

    // =========================================================
    // DATABASE ERROR
    // =========================================================

    private void showDatabaseError() {

        JOptionPane.showMessageDialog(
                this,

                "Unable to complete the requested operation.\n\n"
                        + "Please check the DriveX database connection "
                        + "and try again.",

                "DriveX Database Error",

                JOptionPane.ERROR_MESSAGE
        );
    }

    // =========================================================
    // FIELD LABEL
    // =========================================================

    private JLabel createFieldLabel(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        label.setForeground(
                DARK_TEXT
        );

        return label;
    }

    // =========================================================
    // READ ONLY FIELD STYLE
    // =========================================================

    private void styleReadOnlyField(
            JTextField field) {

        field.setEditable(false);

        field.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        field.setBackground(
                new Color(
                        245,
                        245,
                        245
                )
        );

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(
                                        215,
                                        220,
                                        225
                                )
                        ),
                        new EmptyBorder(
                                5,
                                8,
                                5,
                                8
                        )
                )
        );

        field.setPreferredSize(
                new Dimension(
                        150,
                        34
                )
        );
    }

    // =========================================================
    // BUTTON DESIGN
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
                        "Arial",
                        Font.BOLD,
                        11
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
                        125,
                        34
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

                component.setBackground(
                        table.getSelectionBackground()
                );

                component.setForeground(
                        table.getSelectionForeground()
                );

            } else {

                component.setBackground(
                        Color.WHITE
                );

                component.setForeground(
                        Color.DARK_GRAY
                );
            }

            if (value != null &&
                    column == 8 &&
                    !isSelected) {

                String status =
                        value.toString();

                if (status.equalsIgnoreCase(
                        "Paid")) {

                    component.setForeground(
                            new Color(
                                    40,
                                    167,
                                    69
                            )
                    );

                } else if (
                        status.equalsIgnoreCase(
                                "Refunded")) {

                    component.setForeground(
                            new Color(
                                    108,
                                    76,
                                    180
                            )
                    );

                } else if (
                        status.equalsIgnoreCase(
                                "Failed")) {

                    component.setForeground(
                            new Color(
                                    220,
                                    53,
                                    69
                            )
                    );

                } else {

                    component.setForeground(
                            new Color(
                                    255,
                                    152,
                                    0
                            )
                    );
                }

                component.setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                12
                        )
                );
            }

            return component;
        }
    }
}