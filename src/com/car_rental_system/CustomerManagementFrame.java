package com.car_rental_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class CustomerManagementFrame extends JFrame {

    private JTextField txtId;
    private JTextField txtName;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JTextField txtPhone;
    private JTextField txtSearch;

    private JTable customerTable;
    private DefaultTableModel tableModel;

    // =====================================================
    // COLORS
    // =====================================================

    private final Color BG_COLOR =
            new Color(235, 245, 255);

    private final Color PRIMARY =
            new Color(25, 118, 210);

    private final Color PURPLE =
            new Color(67, 56, 140);

    private final Color GREEN =
            new Color(40, 167, 69);

    private final Color ORANGE =
            new Color(255, 152, 0);

    private final Color RED =
            new Color(220, 53, 69);

    private final Color GRAY =
            new Color(108, 117, 125);

    private final Color BORDER =
            new Color(210, 220, 230);

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public CustomerManagementFrame() {

        setTitle("DriveX - Customer Management");

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setMinimumSize(
                new Dimension(1050, 650)
        );

        setSize(1250, 780);

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        setResizable(true);

        createUI();

        loadCustomers();
    }

    // =====================================================
    // CREATE UI
    // =====================================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(
                BG_COLOR
        );

        setContentPane(mainPanel);

        // HEADER
        createHeader(mainPanel);

        // CENTER
        JPanel centerPanel =
                new JPanel(new BorderLayout(0, 15));

        centerPanel.setBackground(
                BG_COLOR
        );

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        18,
                        20,
                        10,
                        20
                )
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // FORM
        JPanel formCard =
                createFormCard();

        centerPanel.add(
                formCard,
                BorderLayout.NORTH
        );

        // SEARCH + TABLE
        JPanel lowerPanel =
                new JPanel(new BorderLayout(0, 15));

        lowerPanel.setBackground(
                BG_COLOR
        );

        JPanel searchCard =
                createSearchCard();

        lowerPanel.add(
                searchCard,
                BorderLayout.NORTH
        );

        JPanel tableCard =
                createTableCard();

        lowerPanel.add(
                tableCard,
                BorderLayout.CENTER
        );

        centerPanel.add(
                lowerPanel,
                BorderLayout.CENTER
        );

        // FOOTER
        createFooter(mainPanel);
    }

    // =====================================================
    // HEADER
    // =====================================================

    private void createHeader(
            JPanel mainPanel) {

        JPanel header =
                new JPanel(new BorderLayout());

        header.setBackground(
                PURPLE
        );

        header.setPreferredSize(
                new Dimension(0, 95)
        );

        JPanel titlePanel =
                new JPanel(new GridBagLayout());

        titlePanel.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor =
                GridBagConstraints.WEST;

        gbc.insets =
                new Insets(10, 30, 0, 10);

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

        titlePanel.add(
                title,
                gbc
        );

        gbc.gridy = 1;

        gbc.insets =
                new Insets(0, 32, 10, 10);

        JLabel subtitle =
                new JLabel(
                        "Customer Management"
                );

        subtitle.setForeground(
                new Color(225, 220, 255)
        );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        titlePanel.add(
                subtitle,
                gbc
        );

        header.add(
                titlePanel,
                BorderLayout.WEST
        );

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );
    }

    // =====================================================
    // FORM CARD
    // =====================================================

    private JPanel createFormCard() {

        JPanel card =
                new JPanel(new BorderLayout());

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        BorderFactory.createEmptyBorder(
                                15,
                                18,
                                15,
                                18
                        )
                )
        );

        // =================================================
        // TITLE
        // =================================================

        JLabel title =
                new JLabel(
                        "CUSTOMER DETAILS"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        19
                )
        );

        title.setForeground(
                PURPLE
        );

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        0,
                        10,
                        0
                )
        );

        card.add(
                title,
                BorderLayout.NORTH
        );

        // =================================================
        // FORM
        // =================================================

        JPanel formPanel =
                new JPanel(
                        new GridBagLayout()
                );

        formPanel.setBackground(
                Color.WHITE
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.insets =
                new Insets(6, 5, 6, 12);

        // -------------------------------------------------
        // ID
        // -------------------------------------------------

        txtId =
                new JTextField();

        txtId.setEditable(false);

        txtId.setBackground(
                new Color(245, 245, 245)
        );

        addFormField(
                formPanel,
                gbc,
                0,
                "Customer ID",
                txtId
        );

        // -------------------------------------------------
        // NAME
        // -------------------------------------------------

        txtName =
                new JTextField();

        addFormField(
                formPanel,
                gbc,
                1,
                "Full Name",
                txtName
        );

        // -------------------------------------------------
        // USERNAME
        // -------------------------------------------------

        txtUsername =
                new JTextField();

        addFormField(
                formPanel,
                gbc,
                2,
                "Username",
                txtUsername
        );

        // -------------------------------------------------
        // PASSWORD
        // -------------------------------------------------

        txtPassword =
                new JPasswordField();

        addFormField(
                formPanel,
                gbc,
                3,
                "Password",
                txtPassword
        );

        // -------------------------------------------------
        // PHONE
        // -------------------------------------------------

        txtPhone =
                new JTextField();

        addFormField(
                formPanel,
                gbc,
                4,
                "Phone",
                txtPhone
        );

        card.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // BUTTONS
        // =================================================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                10,
                                0
                        )
                );

        buttonPanel.setBackground(
                Color.WHITE
        );

        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        5,
                        0,
                        5
                )
        );

        JButton btnAdd =
                createButton(
                        "ADD CUSTOMER",
                        GREEN
                );

        JButton btnUpdate =
                createButton(
                        "UPDATE",
                        PRIMARY
                );

        JButton btnDelete =
                createButton(
                        "DELETE",
                        RED
                );

        JButton btnClear =
                createButton(
                        "CLEAR",
                        ORANGE
                );

        buttonPanel.add(btnAdd);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        buttonPanel.add(btnClear);

        card.add(
                buttonPanel,
                BorderLayout.SOUTH
        );

        // =================================================
        // ACTIONS
        // =================================================

        btnAdd.addActionListener(
                e -> addCustomer()
        );

        btnUpdate.addActionListener(
                e -> updateCustomer()
        );

        btnDelete.addActionListener(
                e -> deleteCustomer()
        );

        btnClear.addActionListener(
                e -> clearFields()
        );

        return card;
    }

    // =====================================================
    // ADD FORM FIELD
    // =====================================================

    private void addFormField(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String labelText,
            JComponent component) {

        gbc.gridy = row;

        // Label
        gbc.gridx = 0;
        gbc.weightx = 0.08;

        JLabel label =
                new JLabel(labelText);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        panel.add(
                label,
                gbc
        );

        // Field
        gbc.gridx = 1;
        gbc.weightx = 0.32;

        component.setPreferredSize(
                new Dimension(
                        0,
                        34
                )
        );

        panel.add(
                component,
                gbc
        );
    }

    // =====================================================
    // SEARCH CARD
    // =====================================================

    private JPanel createSearchCard() {

        JPanel card =
                new JPanel(
                        new GridBagLayout()
                );

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        BorderFactory.createEmptyBorder(
                                10,
                                15,
                                10,
                                15
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridy = 0;

        gbc.insets =
                new Insets(5, 5, 5, 10);

        gbc.anchor =
                GridBagConstraints.CENTER;

        JLabel searchLabel =
                new JLabel(
                        "Search Customer"
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

        txtSearch.setPreferredSize(
                new Dimension(
                        300,
                        34
                )
        );

        gbc.gridx = 1;
        gbc.weightx = 1;
        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        card.add(
                txtSearch,
                gbc
        );

        JButton btnSearch =
                createButton(
                        "SEARCH",
                        PRIMARY
                );

        btnSearch.setPreferredSize(
                new Dimension(
                        110,
                        34
                )
        );

        gbc.gridx = 2;
        gbc.weightx = 0;
        gbc.fill =
                GridBagConstraints.NONE;

        card.add(
                btnSearch,
                gbc
        );

        JButton btnShowAll =
                createButton(
                        "SHOW ALL",
                        PURPLE
                );

        btnShowAll.setPreferredSize(
                new Dimension(
                        110,
                        34
                )
        );

        gbc.gridx = 3;

        card.add(
                btnShowAll,
                gbc
        );

        // =================================================
        // ACTIONS
        // =================================================

        btnSearch.addActionListener(
                e -> searchCustomers()
        );

        btnShowAll.addActionListener(
                e -> loadCustomers()
        );

        txtSearch.addActionListener(
                e -> searchCustomers()
        );

        return card;
    }

    // =====================================================
    // TABLE CARD
    // =====================================================

    private JPanel createTableCard() {

        JPanel card =
                new JPanel(new BorderLayout());

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                BORDER
                        ),
                        BorderFactory.createEmptyBorder(
                                12,
                                15,
                                15,
                                15
                        )
                )
        );

        JLabel title =
                new JLabel(
                        "REGISTERED CUSTOMERS"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        title.setForeground(
                PURPLE
        );

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        0,
                        10,
                        0
                )
        );

        card.add(
                title,
                BorderLayout.NORTH
        );

        // =================================================
        // TABLE
        // =================================================

        String[] columns = {
                "ID",
                "Name",
                "Username",
                "Phone"
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

        customerTable =
                new JTable(
                        tableModel
                );

        customerTable.setRowHeight(
                30
        );

        customerTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        customerTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        customerTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_ALL_COLUMNS
        );

        customerTable.setShowGrid(true);

        customerTable.setGridColor(
                new Color(225, 230, 235)
        );

        customerTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                13
                        )
                );

        customerTable.getTableHeader()
                .setBackground(
                        PURPLE
                );

        customerTable.getTableHeader()
                .setForeground(
                        Color.WHITE
                );

        customerTable.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                34
                        )
                );

        // Column widths
        customerTable.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(60);

        customerTable.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(250);

        customerTable.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(250);

        customerTable.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(180);

        JScrollPane scrollPane =
                new JScrollPane(
                        customerTable
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        BORDER
                )
        );

        card.add(
                scrollPane,
                BorderLayout.CENTER
        );

        // =================================================
        // TABLE SELECTION
        // =================================================

        customerTable.getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                int row =
                                        customerTable
                                                .getSelectedRow();

                                if (row != -1) {

                                    loadSelectedCustomer(
                                            row
                                    );
                                }
                            }
                        }
                );

        return card;
    }

    // =====================================================
    // FOOTER
    // =====================================================

    private void createFooter(
            JPanel mainPanel) {

        JPanel bottomPanel =
                new JPanel(new BorderLayout());

        bottomPanel.setBackground(
                BG_COLOR
        );

        bottomPanel.setPreferredSize(
                new Dimension(
                        0,
                        55
                )
        );

        JPanel leftPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                20,
                                8
                        )
                );

        leftPanel.setOpaque(false);

        JButton btnBack =
                createButton(
                        "BACK TO DASHBOARD",
                        GRAY
                );

        btnBack.setPreferredSize(
                new Dimension(
                        180,
                        35
                )
        );

        leftPanel.add(
                btnBack
        );

        bottomPanel.add(
                leftPanel,
                BorderLayout.WEST
        );

        JLabel footer =
                new JLabel(
                        "Drive • Explore • Enjoy",
                        SwingConstants.CENTER
                );

        footer.setFont(
                new Font(
                        "Arial",
                        Font.ITALIC,
                        13
                )
        );

        footer.setForeground(
                GRAY
        );

        bottomPanel.add(
                footer,
                BorderLayout.CENTER
        );

        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // Back action
        btnBack.addActionListener(
                e -> {

                    new AdminDashboardFrame()
                            .setVisible(true);

                    dispose();
                }
        );
    }

    // =====================================================
    // LOAD SELECTED CUSTOMER
    // =====================================================

    private void loadSelectedCustomer(
            int row) {

        txtId.setText(
                tableModel
                        .getValueAt(
                                row,
                                0
                        )
                        .toString()
        );

        txtName.setText(
                tableModel
                        .getValueAt(
                                row,
                                1
                        )
                        .toString()
        );

        txtUsername.setText(
                tableModel
                        .getValueAt(
                                row,
                                2
                        )
                        .toString()
        );

        txtPhone.setText(
                tableModel
                        .getValueAt(
                                row,
                                3
                        )
                        .toString()
        );

        // Password intentionally remains empty
        txtPassword.setText("");
    }

    // =====================================================
    // ADD CUSTOMER
    // =====================================================

    private void addCustomer() {

        String name =
                txtName.getText().trim();

        String username =
                txtUsername.getText().trim();

        String password =
                new String(
                        txtPassword.getPassword()
                ).trim();

        String phone =
                txtPhone.getText().trim();

        if (!validateFields(
                name,
                username,
                password,
                phone)) {

            return;
        }

        try (
                Connection con =
                        DBConnection.getConnection()
        ) {

            // =================================================
            // CHECK USERNAME
            // =================================================

            String usernameSql =
                    "SELECT id FROM users WHERE username=?";

            try (
                    PreparedStatement ps =
                            con.prepareStatement(
                                    usernameSql
                            )
            ) {

                ps.setString(
                        1,
                        username
                );

                try (
                        ResultSet rs =
                                ps.executeQuery()
                ) {

                    if (rs.next()) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Username Already Registered\n\n"
                                        + "The username \""
                                        + username
                                        + "\" is already associated with "
                                        + "a DriveX account.\n\n"
                                        + "Please choose a different username.",
                                "Username Already Registered",
                                JOptionPane.WARNING_MESSAGE
                        );

                        txtUsername.requestFocus();

                        return;
                    }
                }
            }

            // =================================================
            // CHECK PHONE
            // =================================================

            String phoneSql =
                    "SELECT id FROM users WHERE phone=?";

            try (
                    PreparedStatement ps =
                            con.prepareStatement(
                                    phoneSql
                            )
            ) {

                ps.setString(
                        1,
                        phone
                );

                try (
                        ResultSet rs =
                                ps.executeQuery()
                ) {

                    if (rs.next()) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Phone Number Already Registered\n\n"
                                        + "The phone number \""
                                        + phone
                                        + "\" is already associated with "
                                        + "a DriveX account.\n\n"
                                        + "Please use a different phone number.",
                                "Phone Number Already Registered",
                                JOptionPane.WARNING_MESSAGE
                        );

                        txtPhone.requestFocus();

                        return;
                    }
                }
            }

            // =================================================
            // INSERT
            // =================================================

            String sql =
                    "INSERT INTO users("
                            + "name, username, password, phone"
                            + ") VALUES (?, ?, ?, ?)";

            try (
                    PreparedStatement ps =
                            con.prepareStatement(sql)
            ) {

                ps.setString(
                        1,
                        name
                );

                ps.setString(
                        2,
                        username
                );

                ps.setString(
                        3,
                        password
                );

                ps.setString(
                        4,
                        phone
                );

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        this,
                        "Customer Account Created Successfully\n\n"
                                + "The customer has been added to the "
                                + "DriveX system.",
                        "DriveX - Customer Added",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();

                loadCustomers();
            }

        } catch (SQLException ex) {

            showDatabaseError(ex);
        }
    }

    // =====================================================
    // UPDATE CUSTOMER
    // =====================================================

    private void updateCustomer() {

        if (txtId.getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a customer from the table first.",
                    "DriveX - Customer Not Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String id =
                txtId.getText().trim();

        String name =
                txtName.getText().trim();

        String username =
                txtUsername.getText().trim();

        String password =
                new String(
                        txtPassword.getPassword()
                ).trim();

        String phone =
                txtPhone.getText().trim();

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the customer's full name.",
                    "DriveX - Invalid Name",
                    JOptionPane.WARNING_MESSAGE
            );

            txtName.requestFocus();

            return;
        }

        if (username.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a username.",
                    "DriveX - Invalid Username",
                    JOptionPane.WARNING_MESSAGE
            );

            txtUsername.requestFocus();

            return;
        }

        if (!phone.matches("\\d{10}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Phone number must contain exactly 10 digits.",
                    "DriveX - Invalid Phone Number",
                    JOptionPane.WARNING_MESSAGE
            );

            txtPhone.requestFocus();

            return;
        }

        try (
                Connection con =
                        DBConnection.getConnection()
        ) {

            // =================================================
            // DUPLICATE USERNAME
            // =================================================

            String usernameSql =
                    "SELECT id FROM users "
                            + "WHERE username=? AND id<>?";

            try (
                    PreparedStatement ps =
                            con.prepareStatement(
                                    usernameSql
                            )
            ) {

                ps.setString(
                        1,
                        username
                );

                ps.setInt(
                        2,
                        Integer.parseInt(id)
                );

                try (
                        ResultSet rs =
                                ps.executeQuery()
                ) {

                    if (rs.next()) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Username Already Registered\n\n"
                                        + "The username \""
                                        + username
                                        + "\" belongs to another "
                                        + "DriveX account.\n\n"
                                        + "Please choose a different username.",
                                "Username Already Registered",
                                JOptionPane.WARNING_MESSAGE
                        );

                        txtUsername.requestFocus();

                        return;
                    }
                }
            }

            // =================================================
            // DUPLICATE PHONE
            // =================================================

            String phoneSql =
                    "SELECT id FROM users "
                            + "WHERE phone=? AND id<>?";

            try (
                    PreparedStatement ps =
                            con.prepareStatement(
                                    phoneSql
                            )
            ) {

                ps.setString(
                        1,
                        phone
                );

                ps.setInt(
                        2,
                        Integer.parseInt(id)
                );

                try (
                        ResultSet rs =
                                ps.executeQuery()
                ) {

                    if (rs.next()) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Phone Number Already Registered\n\n"
                                        + "The phone number \""
                                        + phone
                                        + "\" belongs to another "
                                        + "DriveX account.\n\n"
                                        + "Please use a different phone number.",
                                "Phone Number Already Registered",
                                JOptionPane.WARNING_MESSAGE
                        );

                        txtPhone.requestFocus();

                        return;
                    }
                }
            }

            // =================================================
            // CONFIRM
            // =================================================

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to update "
                                    + "this customer?",
                            "DriveX - Confirm Update",
                            JOptionPane.YES_NO_OPTION
                    );

            if (choice != JOptionPane.YES_OPTION) {

                return;
            }

            String sql;

            // =================================================
            // WITHOUT PASSWORD CHANGE
            // =================================================

            if (password.isEmpty()) {

                sql =
                        "UPDATE users "
                                + "SET name=?, username=?, phone=? "
                                + "WHERE id=?";

            } else {

                // =================================================
                // PASSWORD VALIDATION
                // =================================================

                if (password.length() < 6) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Password must contain at least "
                                    + "6 characters.",
                            "DriveX - Invalid Password",
                            JOptionPane.WARNING_MESSAGE
                    );

                    txtPassword.requestFocus();

                    return;
                }

                sql =
                        "UPDATE users "
                                + "SET name=?, username=?, "
                                + "password=?, phone=? "
                                + "WHERE id=?";
            }

            try (
                    PreparedStatement ps =
                            con.prepareStatement(sql)
            ) {

                if (password.isEmpty()) {

                    ps.setString(
                            1,
                            name
                    );

                    ps.setString(
                            2,
                            username
                    );

                    ps.setString(
                            3,
                            phone
                    );

                    ps.setInt(
                            4,
                            Integer.parseInt(id)
                    );

                } else {

                    ps.setString(
                            1,
                            name
                    );

                    ps.setString(
                            2,
                            username
                    );

                    ps.setString(
                            3,
                            password
                    );

                    ps.setString(
                            4,
                            phone
                    );

                    ps.setInt(
                            5,
                            Integer.parseInt(id)
                    );
                }

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        this,
                        "Customer Details Updated Successfully.",
                        "DriveX - Update Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();

                loadCustomers();
            }

        } catch (SQLException ex) {

            showDatabaseError(ex);
        }
    }

    // =====================================================
    // DELETE CUSTOMER
    // =====================================================

    private void deleteCustomer() {

        if (txtId.getText()
                .trim()
                .isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a customer from the table first.",
                    "DriveX - Customer Not Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int customerId =
                Integer.parseInt(
                        txtId.getText()
                                .trim()
                );

        String customerName =
                txtName.getText().trim();

        try (
                Connection con =
                        DBConnection.getConnection()
        ) {

            // =================================================
            // CHECK BOOKING HISTORY
            // =================================================

            String checkSql =
                    "SELECT COUNT(*) "
                            + "FROM bookings WHERE user_id=?";

            try (
                    PreparedStatement ps =
                            con.prepareStatement(
                                    checkSql
                            )
            ) {

                ps.setInt(
                        1,
                        customerId
                );

                try (
                        ResultSet rs =
                                ps.executeQuery()
                ) {

                    if (rs.next()
                            && rs.getInt(1) > 0) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Customer Cannot Be Deleted\n\n"
                                        + "The customer \""
                                        + customerName
                                        + "\" has existing booking "
                                        + "history in the DriveX system.\n\n"
                                        + "Deleting this customer could "
                                        + "affect booking records.\n\n"
                                        + "Please keep the customer "
                                        + "account for historical records.",
                                "DriveX - Delete Not Allowed",
                                JOptionPane.WARNING_MESSAGE
                        );

                        return;
                    }
                }
            }

            // =================================================
            // CONFIRM DELETE
            // =================================================

            int choice =
                    JOptionPane.showConfirmDialog(
                            this,
                            "Are you sure you want to delete "
                                    + "customer \""
                                    + customerName
                                    + "\"?\n\n"
                                    + "This action cannot be undone.",
                            "DriveX - Confirm Customer Deletion",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );

            if (choice != JOptionPane.YES_OPTION) {

                return;
            }

            // =================================================
            // DELETE
            // =================================================

            String deleteSql =
                    "DELETE FROM users WHERE id=?";

            try (
                    PreparedStatement ps =
                            con.prepareStatement(
                                    deleteSql
                            )
            ) {

                ps.setInt(
                        1,
                        customerId
                );

                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        this,
                        "Customer Deleted Successfully.",
                        "DriveX - Delete Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();

                loadCustomers();
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "This customer cannot be deleted because "
                            + "related records exist in DriveX.\n\n"
                            + "Please keep the customer account "
                            + "for historical records.",
                    "DriveX - Delete Not Allowed",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // =====================================================
    // LOAD CUSTOMERS
    // =====================================================

    private void loadCustomers() {

        tableModel.setRowCount(0);

        String sql =
                "SELECT id, name, username, phone "
                        + "FROM users ORDER BY id DESC";

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
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("username"),
                                rs.getString("phone")
                        }
                );
            }

        } catch (SQLException ex) {

            showDatabaseError(ex);
        }
    }

    // =====================================================
    // SEARCH CUSTOMERS
    // =====================================================

    private void searchCustomers() {

        String search =
                txtSearch.getText().trim();

        if (search.isEmpty()) {

            loadCustomers();

            return;
        }

        tableModel.setRowCount(0);

        String sql =
                "SELECT id, name, username, phone "
                        + "FROM users "
                        + "WHERE name LIKE ? "
                        + "OR username LIKE ? "
                        + "OR phone LIKE ? "
                        + "ORDER BY id DESC";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            String value =
                    "%" + search + "%";

            ps.setString(
                    1,
                    value
            );

            ps.setString(
                    2,
                    value
            );

            ps.setString(
                    3,
                    value
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                while (rs.next()) {

                    tableModel.addRow(
                            new Object[]{
                                    rs.getInt("id"),
                                    rs.getString("name"),
                                    rs.getString("username"),
                                    rs.getString("phone")
                            }
                    );
                }
            }

        } catch (SQLException ex) {

            showDatabaseError(ex);
        }
    }

    // =====================================================
    // VALIDATION
    // =====================================================

    private boolean validateFields(
            String name,
            String username,
            String password,
            String phone) {

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the customer's full name.",
                    "DriveX - Invalid Name",
                    JOptionPane.WARNING_MESSAGE
            );

            txtName.requestFocus();

            return false;
        }

        if (username.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a username.",
                    "DriveX - Invalid Username",
                    JOptionPane.WARNING_MESSAGE
            );

            txtUsername.requestFocus();

            return false;
        }

        if (password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a password.",
                    "DriveX - Invalid Password",
                    JOptionPane.WARNING_MESSAGE
            );

            txtPassword.requestFocus();

            return false;
        }

        if (password.length() < 6) {

            JOptionPane.showMessageDialog(
                    this,
                    "Password must contain at least 6 characters.",
                    "DriveX - Invalid Password",
                    JOptionPane.WARNING_MESSAGE
            );

            txtPassword.requestFocus();

            return false;
        }

        if (!phone.matches("\\d{10}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Phone number must contain exactly 10 digits.",
                    "DriveX - Invalid Phone Number",
                    JOptionPane.WARNING_MESSAGE
            );

            txtPhone.requestFocus();

            return false;
        }

        return true;
    }

    // =====================================================
    // CLEAR
    // =====================================================

    private void clearFields() {

        txtId.setText("");

        txtName.setText("");

        txtUsername.setText("");

        txtPassword.setText("");

        txtPhone.setText("");

        customerTable.clearSelection();

        txtName.requestFocus();
    }

    // =====================================================
    // DATABASE ERROR
    // =====================================================

    private void showDatabaseError(
            SQLException ex) {

        JOptionPane.showMessageDialog(
                this,
                "Unable to complete the requested operation.\n\n"
                        + "Please check the database connection "
                        + "and try again.",
                "DriveX - Database Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    // =====================================================
    // BUTTON DESIGN
    // =====================================================

    private JButton createButton(
            String text,
            Color color) {

        JButton button =
                new JButton(text);

        button.setBackground(
                color
        );

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

        button.setFocusPainted(
                false
        );

        button.setBorderPainted(
                false
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }
}