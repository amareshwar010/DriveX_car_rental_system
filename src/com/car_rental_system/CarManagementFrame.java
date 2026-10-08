package com.car_rental_system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

public class CarManagementFrame extends JFrame {

    private JTextField carNumberField;
    private JTextField brandField;
    private JTextField modelField;
    private JTextField priceField;
    private JTextField searchField;

    private JComboBox<String> typeCombo;
    private JComboBox<String> categoryCombo;
    private JComboBox<String> statusCombo;

    private JTable carTable;
    private DefaultTableModel tableModel;

    private int selectedCarId = -1;

    // =====================================================
    // COLORS
    // =====================================================

    private final Color backgroundColor =
            new Color(235, 245, 255);

    private final Color headerColor =
            new Color(67, 56, 140);

    private final Color borderColor =
            new Color(210, 220, 230);

    private final Color greenColor =
            new Color(40, 167, 69);

    private final Color blueColor =
            new Color(25, 118, 210);

    private final Color redColor =
            new Color(220, 53, 69);

    private final Color grayColor =
            new Color(108, 117, 125);

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public CarManagementFrame() {

        setTitle("DriveX - Car Management");

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

        loadCars();
    }

    // =====================================================
    // CREATE UI
    // =====================================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(
                backgroundColor
        );

        setContentPane(mainPanel);

        // =================================================
        // HEADER
        // =================================================

        createHeader(mainPanel);

        // =================================================
        // CENTER
        // =================================================

        JPanel centerPanel =
                new JPanel(new GridBagLayout());

        centerPanel.setBackground(
                backgroundColor
        );

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        18,
                        20,
                        18,
                        20
                )
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1;
        gbc.insets =
                new Insets(0, 0, 0, 10);

        // =================================================
        // FORM CARD
        // =================================================

        JPanel formCard =
                createFormCard();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0.32;

        centerPanel.add(
                formCard,
                gbc
        );

        // =================================================
        // TABLE CARD
        // =================================================

        JPanel tableCard =
                createTableCard();

        gbc.gridx = 1;
        gbc.weightx = 0.68;
        gbc.insets =
                new Insets(0, 10, 0, 0);

        centerPanel.add(
                tableCard,
                gbc
        );

        // =================================================
        // FOOTER
        // =================================================

        createFooter(mainPanel);
    }

    // =====================================================
    // HEADER
    // =====================================================

    private void createHeader(
            JPanel mainPanel) {

        JPanel headerPanel =
                new JPanel(new BorderLayout());

        headerPanel.setBackground(
                headerColor
        );

        headerPanel.setPreferredSize(
                new Dimension(0, 100)
        );

        JPanel titlePanel =
                new JPanel();

        titlePanel.setOpaque(false);

        titlePanel.setLayout(
                new GridBagLayout()
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor =
                GridBagConstraints.WEST;
        gbc.insets =
                new Insets(12, 30, 0, 10);

        JLabel titleLabel =
                new JLabel("DriveX");

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        32
                )
        );

        titleLabel.setForeground(
                Color.WHITE
        );

        titlePanel.add(
                titleLabel,
                gbc
        );

        gbc.gridy = 1;
        gbc.insets =
                new Insets(0, 32, 12, 10);

        JLabel subtitleLabel =
                new JLabel(
                        "Car Management"
                );

        subtitleLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        subtitleLabel.setForeground(
                Color.WHITE
        );

        titlePanel.add(
                subtitleLabel,
                gbc
        );

        headerPanel.add(
                titlePanel,
                BorderLayout.WEST
        );

        mainPanel.add(
                headerPanel,
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
                                borderColor
                        ),
                        BorderFactory.createEmptyBorder(
                                18,
                                18,
                                18,
                                18
                        )
                )
        );

        // =================================================
        // FORM TITLE
        // =================================================

        JLabel title =
                new JLabel(
                        "VEHICLE DETAILS",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        title.setForeground(
                headerColor
        );

        title.setBorder(
                BorderFactory.createEmptyBorder(
                        0,
                        0,
                        15,
                        0
                )
        );

        card.add(
                title,
                BorderLayout.NORTH
        );

        // =================================================
        // FORM CONTENT
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
                new Insets(7, 5, 7, 5);

        gbc.weightx = 0;

        // Car Number
        carNumberField =
                new JTextField();

        addFormRow(
                formPanel,
                gbc,
                0,
                "Car Number",
                carNumberField
        );

        // Brand
        brandField =
                new JTextField();

        addFormRow(
                formPanel,
                gbc,
                1,
                "Brand",
                brandField
        );

        // Model
        modelField =
                new JTextField();

        addFormRow(
                formPanel,
                gbc,
                2,
                "Model",
                modelField
        );

        // Type
        typeCombo =
                new JComboBox<>(
                        new String[]{
                                "Sedan",
                                "Hatchback",
                                "SUV",
                                "MUV",
                                "Luxury"
                        }
                );

        addFormRow(
                formPanel,
                gbc,
                3,
                "Car Type",
                typeCombo
        );

        // Category
        categoryCombo =
                new JComboBox<>(
                        new String[]{
                                "Normal",
                                "Premium"
                        }
                );

        addFormRow(
                formPanel,
                gbc,
                4,
                "Category",
                categoryCombo
        );

        // Price
        priceField =
                new JTextField();

        addFormRow(
                formPanel,
                gbc,
                5,
                "Price / Day",
                priceField
        );

        // Status
        statusCombo =
                new JComboBox<>(
                        new String[]{
                                "Available",
                                "Rented",
                                "Maintenance"
                        }
                );

        addFormRow(
                formPanel,
                gbc,
                6,
                "Status",
                statusCombo
        );

        card.add(
                formPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // BUTTON PANEL
        // =================================================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                10,
                                10
                        )
                );

        buttonPanel.setBackground(
                Color.WHITE
        );

        buttonPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        0,
                        0,
                        0
                )
        );

        JButton addButton =
                createButton(
                        "ADD",
                        greenColor
                );

        JButton updateButton =
                createButton(
                        "UPDATE",
                        blueColor
                );

        JButton deleteButton =
                createButton(
                        "DELETE",
                        redColor
                );

        JButton clearButton =
                createButton(
                        "CLEAR",
                        grayColor
                );

        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);

        JPanel bottomPanel =
                new JPanel(new BorderLayout());

        bottomPanel.setBackground(
                Color.WHITE
        );

        bottomPanel.add(
                buttonPanel,
                BorderLayout.CENTER
        );

        JButton backButton =
                createButton(
                        "BACK TO DASHBOARD",
                        headerColor
                );

        JPanel backPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                0,
                                10
                        )
                );

        backPanel.setBackground(
                Color.WHITE
        );

        backPanel.add(backButton);

        bottomPanel.add(
                backPanel,
                BorderLayout.SOUTH
        );

        card.add(
                bottomPanel,
                BorderLayout.SOUTH
        );

        // =================================================
        // ACTIONS
        // =================================================

        addButton.addActionListener(
                e -> addCar()
        );

        updateButton.addActionListener(
                e -> updateCar()
        );

        deleteButton.addActionListener(
                e -> deleteCar()
        );

        clearButton.addActionListener(
                e -> clearFields()
        );

        backButton.addActionListener(
                e -> {

                    AdminDashboardFrame dashboard =
                            new AdminDashboardFrame();

                    dashboard.setVisible(true);

                    dispose();
                }
        );

        return card;
    }

    // =====================================================
    // ADD FORM ROW
    // =====================================================

    private void addFormRow(
            JPanel panel,
            GridBagConstraints gbc,
            int row,
            String labelText,
            java.awt.Component component) {

        gbc.gridy = row;

        gbc.gridx = 0;
        gbc.weightx = 0.35;

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

        gbc.gridx = 1;
        gbc.weightx = 0.65;

        if (component instanceof JTextField) {

            ((JTextField) component)
                    .setPreferredSize(
                            new Dimension(
                                    0,
                                    34
                            )
                    );
        }

        if (component instanceof JComboBox) {

            ((JComboBox<?>) component)
                    .setPreferredSize(
                            new Dimension(
                                    0,
                                    34
                            )
                    );
        }

        panel.add(
                component,
                gbc
        );
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
                                borderColor
                        ),
                        BorderFactory.createEmptyBorder(
                                15,
                                15,
                                15,
                                15
                        )
                )
        );

        // =================================================
        // TITLE + SEARCH
        // =================================================

        JPanel topPanel =
                new JPanel(new BorderLayout(15, 0));

        topPanel.setBackground(
                Color.WHITE
        );

        JLabel title =
                new JLabel(
                        "DRIVEX VEHICLE INVENTORY"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        19
                )
        );

        title.setForeground(
                headerColor
        );

        topPanel.add(
                title,
                BorderLayout.WEST
        );

        JPanel searchPanel =
                new JPanel(
                        new BorderLayout(8, 0)
                );

        searchPanel.setBackground(
                Color.WHITE
        );

        searchField =
                new JTextField();

        searchField.setPreferredSize(
                new Dimension(
                        250,
                        34
                )
        );

        JButton searchButton =
                createButton(
                        "SEARCH",
                        blueColor
                );

        searchButton.setPreferredSize(
                new Dimension(
                        105,
                        34
                )
        );

        searchPanel.add(
                searchField,
                BorderLayout.CENTER
        );

        searchPanel.add(
                searchButton,
                BorderLayout.EAST
        );

        topPanel.add(
                searchPanel,
                BorderLayout.EAST
        );

        card.add(
                topPanel,
                BorderLayout.NORTH
        );

        // =================================================
        // TABLE
        // =================================================

        String[] columns = {
                "ID",
                "Car Number",
                "Brand",
                "Model",
                "Type",
                "Category",
                "Price/Day",
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

        carTable =
                new JTable(tableModel);

        carTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        carTable.setRowHeight(30);

        carTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        carTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_ALL_COLUMNS
        );

        carTable.setShowGrid(true);

        carTable.setGridColor(
                new Color(225, 230, 235)
        );

        carTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                12
                        )
                );

        carTable.getTableHeader()
                .setBackground(
                        headerColor
                );

        carTable.getTableHeader()
                .setForeground(
                        Color.WHITE
                );

        carTable.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                34
                        )
                );

        // Column widths
        carTable.getColumnModel()
                .getColumn(0)
                .setPreferredWidth(45);

        carTable.getColumnModel()
                .getColumn(1)
                .setPreferredWidth(100);

        carTable.getColumnModel()
                .getColumn(2)
                .setPreferredWidth(90);

        carTable.getColumnModel()
                .getColumn(3)
                .setPreferredWidth(90);

        carTable.getColumnModel()
                .getColumn(4)
                .setPreferredWidth(80);

        carTable.getColumnModel()
                .getColumn(5)
                .setPreferredWidth(85);

        carTable.getColumnModel()
                .getColumn(6)
                .setPreferredWidth(90);

        carTable.getColumnModel()
                .getColumn(7)
                .setPreferredWidth(100);

        JScrollPane scrollPane =
                new JScrollPane(carTable);

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        borderColor
                )
        );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        borderColor
                )
        );

        JPanel tablePanel =
                new JPanel(
                        new BorderLayout()
                );

        tablePanel.setBackground(
                Color.WHITE
        );

        tablePanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        0,
                        0,
                        0
                )
        );

        tablePanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        card.add(
                tablePanel,
                BorderLayout.CENTER
        );

        // =================================================
        // SEARCH ACTION
        // =================================================

        searchButton.addActionListener(
                e -> searchCars()
        );

        searchField.addActionListener(
                e -> searchCars()
        );

        // =================================================
        // TABLE SELECTION
        // =================================================

        carTable.getSelectionModel()
                .addListSelectionListener(
                        e -> {

                            if (!e.getValueIsAdjusting()) {

                                loadSelectedCar();
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

        JPanel footerPanel =
                new JPanel(
                        new BorderLayout()
                );

        footerPanel.setBackground(
                backgroundColor
        );

        footerPanel.setPreferredSize(
                new Dimension(
                        0,
                        45
                )
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
                grayColor
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

    // =====================================================
    // ADD CAR
    // =====================================================

    private void addCar() {

        if (!validateFields()) {
            return;
        }

        String carNumber =
                carNumberField.getText()
                        .trim();

        String brand =
                brandField.getText()
                        .trim();

        String model =
                modelField.getText()
                        .trim();

        String type =
                typeCombo.getSelectedItem()
                        .toString();

        String category =
                categoryCombo.getSelectedItem()
                        .toString();

        double price =
                Double.parseDouble(
                        priceField.getText()
                                .trim()
                );

        String status =
                statusCombo.getSelectedItem()
                        .toString();

        // =================================================
        // DUPLICATE CHECK
        // =================================================

        String duplicateQuery =
                "SELECT id FROM cars WHERE car_number=?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(
                                duplicateQuery
                        )
        ) {

            ps.setString(
                    1,
                    carNumber
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Car Already Exists\n\n"
                                    + "A vehicle with car number \""
                                    + carNumber
                                    + "\" already exists in the DriveX system.\n\n"
                                    + "Please enter a different car number.",
                            "DriveX - Duplicate Vehicle",
                            JOptionPane.WARNING_MESSAGE
                    );

                    carNumberField.requestFocus();

                    return;
                }
            }

        } catch (SQLException ex) {

            showDatabaseError();

            return;
        }

        // =================================================
        // INSERT
        // =================================================

        String query =
                "INSERT INTO cars "
                        + "(car_number, brand, model, car_type, "
                        + "price_per_day, status, category) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(query)
        ) {

            ps.setString(1, carNumber);
            ps.setString(2, brand);
            ps.setString(3, model);
            ps.setString(4, type);
            ps.setDouble(5, price);
            ps.setString(6, status);
            ps.setString(7, category);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Vehicle Added Successfully!\n\n"
                            + "Car number: "
                            + carNumber,
                    "DriveX - Vehicle Added",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

            loadCars();

        } catch (SQLException ex) {

            showDatabaseError();
        }
    }

    // =====================================================
    // UPDATE CAR
    // =====================================================

    private void updateCar() {

        if (selectedCarId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a vehicle from the table before updating.",
                    "DriveX - Update Vehicle",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (!validateFields()) {
            return;
        }

        String carNumber =
                carNumberField.getText()
                        .trim();

        String brand =
                brandField.getText()
                        .trim();

        String model =
                modelField.getText()
                        .trim();

        String type =
                typeCombo.getSelectedItem()
                        .toString();

        String category =
                categoryCombo.getSelectedItem()
                        .toString();

        double price =
                Double.parseDouble(
                        priceField.getText()
                                .trim()
                );

        String status =
                statusCombo.getSelectedItem()
                        .toString();

        // =================================================
        // DUPLICATE CHECK
        // =================================================

        String duplicateQuery =
                "SELECT id FROM cars "
                        + "WHERE car_number=? AND id<>?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(
                                duplicateQuery
                        )
        ) {

            ps.setString(
                    1,
                    carNumber
            );

            ps.setInt(
                    2,
                    selectedCarId
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Car Already Exists\n\n"
                                    + "Another vehicle with car number \""
                                    + carNumber
                                    + "\" already exists in the DriveX system.\n\n"
                                    + "Please enter a different car number.",
                            "DriveX - Duplicate Vehicle",
                            JOptionPane.WARNING_MESSAGE
                    );

                    carNumberField.requestFocus();

                    return;
                }
            }

        } catch (SQLException ex) {

            showDatabaseError();

            return;
        }

        // =================================================
        // UPDATE
        // =================================================

        String query =
                "UPDATE cars SET "
                        + "car_number=?, "
                        + "brand=?, "
                        + "model=?, "
                        + "car_type=?, "
                        + "price_per_day=?, "
                        + "status=?, "
                        + "category=? "
                        + "WHERE id=?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(query)
        ) {

            ps.setString(1, carNumber);
            ps.setString(2, brand);
            ps.setString(3, model);
            ps.setString(4, type);
            ps.setDouble(5, price);
            ps.setString(6, status);
            ps.setString(7, category);
            ps.setInt(8, selectedCarId);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Vehicle Updated Successfully.",
                    "DriveX - Vehicle Updated",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearFields();

            loadCars();

        } catch (SQLException ex) {

            showDatabaseError();
        }
    }

    // =====================================================
    // DELETE CAR
    // =====================================================

    private void deleteCar() {

        if (selectedCarId == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a vehicle from the table before deleting.",
                    "DriveX - Delete Vehicle",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        this,
                        "Are you sure you want to delete this vehicle?\n\n"
                                + "Deleting a vehicle with booking history "
                                + "may not be allowed.",
                        "DriveX - Confirm Delete",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.WARNING_MESSAGE
                );

        if (choice != JOptionPane.YES_OPTION) {
            return;
        }

        String query =
                "DELETE FROM cars WHERE id=?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(query)
        ) {

            ps.setInt(
                    1,
                    selectedCarId
            );

            int rows =
                    ps.executeUpdate();

            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Vehicle Deleted Successfully.",
                        "DriveX - Vehicle Deleted",
                        JOptionPane.INFORMATION_MESSAGE
                );

                clearFields();

                loadCars();
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "This vehicle cannot be deleted because it may have "
                            + "existing booking history.\n\n"
                            + "Consider changing its status to "
                            + "\"Maintenance\" instead.",
                    "DriveX - Vehicle Cannot Be Deleted",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }

    // =====================================================
    // LOAD ALL CARS
    // =====================================================

    private void loadCars() {

        tableModel.setRowCount(0);

        String query =
                "SELECT id, car_number, brand, model, "
                        + "car_type, category, price_per_day, status "
                        + "FROM cars ORDER BY id DESC";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(query);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                tableModel.addRow(
                        new Object[]{
                                rs.getInt("id"),
                                rs.getString("car_number"),
                                rs.getString("brand"),
                                rs.getString("model"),
                                rs.getString("car_type"),
                                rs.getString("category"),
                                rs.getDouble("price_per_day"),
                                rs.getString("status")
                        }
                );
            }

        } catch (SQLException ex) {

            showDatabaseError();
        }
    }

    // =====================================================
    // SEARCH CARS
    // =====================================================

    private void searchCars() {

        String search =
                searchField.getText()
                        .trim();

        if (search.isEmpty()) {

            loadCars();

            return;
        }

        tableModel.setRowCount(0);

        String query =
                "SELECT id, car_number, brand, model, "
                        + "car_type, category, price_per_day, status "
                        + "FROM cars "
                        + "WHERE car_number LIKE ? "
                        + "OR brand LIKE ? "
                        + "OR model LIKE ? "
                        + "OR car_type LIKE ? "
                        + "OR category LIKE ? "
                        + "OR status LIKE ? "
                        + "ORDER BY id DESC";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(query)
        ) {

            String value =
                    "%" + search + "%";

            ps.setString(1, value);
            ps.setString(2, value);
            ps.setString(3, value);
            ps.setString(4, value);
            ps.setString(5, value);
            ps.setString(6, value);

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                while (rs.next()) {

                    tableModel.addRow(
                            new Object[]{
                                    rs.getInt("id"),
                                    rs.getString("car_number"),
                                    rs.getString("brand"),
                                    rs.getString("model"),
                                    rs.getString("car_type"),
                                    rs.getString("category"),
                                    rs.getDouble("price_per_day"),
                                    rs.getString("status")
                            }
                    );
                }
            }

        } catch (SQLException ex) {

            showDatabaseError();
        }
    }

    // =====================================================
    // LOAD SELECTED CAR
    // =====================================================

    private void loadSelectedCar() {

        int row =
                carTable.getSelectedRow();

        if (row == -1) {
            return;
        }

        selectedCarId =
                Integer.parseInt(
                        tableModel
                                .getValueAt(row, 0)
                                .toString()
                );

        carNumberField.setText(
                tableModel
                        .getValueAt(row, 1)
                        .toString()
        );

        brandField.setText(
                tableModel
                        .getValueAt(row, 2)
                        .toString()
        );

        modelField.setText(
                tableModel
                        .getValueAt(row, 3)
                        .toString()
        );

        typeCombo.setSelectedItem(
                tableModel
                        .getValueAt(row, 4)
                        .toString()
        );

        categoryCombo.setSelectedItem(
                tableModel
                        .getValueAt(row, 5)
                        .toString()
        );

        priceField.setText(
                tableModel
                        .getValueAt(row, 6)
                        .toString()
        );

        statusCombo.setSelectedItem(
                tableModel
                        .getValueAt(row, 7)
                        .toString()
        );
    }

    // =====================================================
    // VALIDATION
    // =====================================================

    private boolean validateFields() {

        String carNumber =
                carNumberField.getText()
                        .trim();

        String brand =
                brandField.getText()
                        .trim();

        String model =
                modelField.getText()
                        .trim();

        String price =
                priceField.getText()
                        .trim();

        if (carNumber.isEmpty()) {

            showWarning(
                    "Please enter the car number."
            );

            carNumberField.requestFocus();

            return false;
        }

        if (brand.isEmpty()) {

            showWarning(
                    "Please enter the car brand."
            );

            brandField.requestFocus();

            return false;
        }

        if (model.isEmpty()) {

            showWarning(
                    "Please enter the car model."
            );

            modelField.requestFocus();

            return false;
        }

        if (price.isEmpty()) {

            showWarning(
                    "Please enter the price per day."
            );

            priceField.requestFocus();

            return false;
        }

        try {

            double priceValue =
                    Double.parseDouble(price);

            if (priceValue <= 0) {

                showWarning(
                        "Price per day must be greater than zero."
                );

                priceField.requestFocus();

                return false;
            }

        } catch (NumberFormatException ex) {

            showWarning(
                    "Please enter a valid price."
            );

            priceField.requestFocus();

            return false;
        }

        return true;
    }

    // =====================================================
    // CLEAR
    // =====================================================

    private void clearFields() {

        selectedCarId = -1;

        carNumberField.setText("");

        brandField.setText("");

        modelField.setText("");

        priceField.setText("");

        typeCombo.setSelectedIndex(0);

        categoryCombo.setSelectedIndex(0);

        statusCombo.setSelectedIndex(0);

        carTable.clearSelection();

        carNumberField.requestFocus();
    }

    // =====================================================
    // WARNING
    // =====================================================

    private void showWarning(
            String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "DriveX - Validation",
                JOptionPane.WARNING_MESSAGE
        );
    }

    // =====================================================
    // DATABASE ERROR
    // =====================================================

    private void showDatabaseError() {

        JOptionPane.showMessageDialog(
                this,
                "Unable to complete the requested operation.\n\n"
                        + "Please make sure the database is running "
                        + "and try again.",
                "DriveX - Database Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    // =====================================================
    // BUTTON CREATOR
    // =====================================================

    private JButton createButton(
            String text,
            Color color) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        button.setForeground(
                Color.WHITE
        );

        button.setBackground(
                color
        );

        button.setFocusPainted(
                false
        );

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        12,
                        8,
                        12
                )
        );

        button.setCursor(
                new java.awt.Cursor(
                        java.awt.Cursor.HAND_CURSOR
                )
        );

        return button;
    }
}