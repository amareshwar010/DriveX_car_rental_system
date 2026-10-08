package com.car_rental_system;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;

public class BookingManagementFrame extends JFrame {

    private JTable bookingTable;
    private DefaultTableModel tableModel;

    private JTextField searchField;
    private JComboBox<String> statusCombo;

    // Existing Admin Dashboard reference
    private final AdminDashboardFrame adminDashboard;

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

    private final Color RED =
            new Color(220, 53, 69);

    private final Color GRAY =
            new Color(108, 117, 125);

    private final Color BORDER =
            new Color(215, 210, 230);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    public BookingManagementFrame(
            AdminDashboardFrame adminDashboard) {

        this.adminDashboard = adminDashboard;

        setTitle(
                "DriveX - Booking Management"
        );

        setSize(
                1250,
                750
        );

        setMinimumSize(
                new Dimension(
                        950,
                        650
                )
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setResizable(true);

        createUI();

        loadBookings();
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

        createCenterArea(mainPanel);

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

        JPanel leftPanel =
                new JPanel();

        leftPanel.setOpaque(false);

        leftPanel.setLayout(
                new BoxLayout(
                        leftPanel,
                        BoxLayout.Y_AXIS
                )
        );

        JLabel title =
                new JLabel(
                        "DriveX"
                );

        title.setForeground(
                Color.WHITE
        );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        34
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Booking Management"
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

        leftPanel.add(title);

        leftPanel.add(
                Box.createVerticalStrut(2)
        );

        leftPanel.add(subtitle);

        header.add(
                leftPanel,
                BorderLayout.WEST
        );

        // -----------------------------------------------------
        // RIGHT HEADER
        // -----------------------------------------------------

        JLabel info =
                new JLabel(
                        "Manage • Monitor • Maintain"
                );

        info.setForeground(
                new Color(
                        235,
                        230,
                        255
                )
        );

        info.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        header.add(
                info,
                BorderLayout.EAST
        );

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );
    }

    // =========================================================
    // CENTER AREA
    // =========================================================

    private void createCenterArea(
            JPanel mainPanel) {

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                12
                        )
                );

        centerPanel.setBackground(BG);

        centerPanel.setBorder(
                new EmptyBorder(
                        15,
                        20,
                        10,
                        20
                )
        );

        createSearchPanel(centerPanel);

        createTable(centerPanel);

        createButtons(centerPanel);

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // SEARCH PANEL
    // =========================================================

    private void createSearchPanel(
            JPanel parent) {

        JPanel searchCard =
                new JPanel(
                        new GridBagLayout()
                );

        searchCard.setBackground(
                Color.WHITE
        );

        searchCard.setBorder(
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

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        5,
                        7,
                        5,
                        7
                );

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // -----------------------------------------------------
        // SEARCH LABEL
        // -----------------------------------------------------

        JLabel searchLabel =
                createLabel(
                        "Search"
                );

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;

        searchCard.add(
                searchLabel,
                gbc
        );

        // -----------------------------------------------------
        // SEARCH FIELD
        // -----------------------------------------------------

        searchField =
                new JTextField();

        searchField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        searchField.setPreferredSize(
                new Dimension(
                        0,
                        38
                )
        );

        searchField.setBorder(
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

        gbc.gridx = 1;
        gbc.weightx = 1.0;

        searchCard.add(
                searchField,
                gbc
        );

        // -----------------------------------------------------
        // SEARCH BUTTON
        // -----------------------------------------------------

        JButton searchButton =
                createButton(
                        "SEARCH",
                        PURPLE
                );

        searchButton.setPreferredSize(
                new Dimension(
                        110,
                        38
                )
        );

        searchButton.addActionListener(
                e -> loadBookings()
        );

        gbc.gridx = 2;
        gbc.weightx = 0;

        searchCard.add(
                searchButton,
                gbc
        );

        // -----------------------------------------------------
        // STATUS LABEL
        // -----------------------------------------------------

        JLabel statusLabel =
                createLabel(
                        "Status"
                );

        gbc.gridx = 3;

        searchCard.add(
                statusLabel,
                gbc
        );

        // -----------------------------------------------------
        // STATUS COMBO
        // -----------------------------------------------------

        statusCombo =
                new JComboBox<>(
                        new String[]{
                                "All",
                                "Confirmed",
                                "Booked",
                                "Completed",
                                "Cancelled"
                        }
                );

        statusCombo.setPreferredSize(
                new Dimension(
                        155,
                        38
                )
        );

        statusCombo.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        statusCombo.addActionListener(
                e -> loadBookings()
        );

        gbc.gridx = 4;

        searchCard.add(
                statusCombo,
                gbc
        );

        // -----------------------------------------------------
        // CLEAR
        // -----------------------------------------------------

        JButton clear =
                createButton(
                        "CLEAR",
                        GRAY
                );

        clear.setPreferredSize(
                new Dimension(
                        100,
                        38
                )
        );

        clear.addActionListener(
                e -> {

                    searchField.setText("");

                    statusCombo.setSelectedIndex(
                            0
                    );

                    loadBookings();
                }
        );

        gbc.gridx = 5;

        searchCard.add(
                clear,
                gbc
        );

        parent.add(
                searchCard,
                BorderLayout.NORTH
        );
    }

    // =========================================================
    // TABLE
    // =========================================================

    private void createTable(
            JPanel parent) {

        String[] columns = {

                "Booking ID",
                "Customer",
                "Phone",
                "Car Number",
                "Brand",
                "Model",
                "Category",
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
                new JTable(
                        tableModel
                );

        bookingTable.setRowHeight(
                31
        );

        bookingTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        bookingTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        bookingTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_ALL_COLUMNS
        );

        bookingTable.setGridColor(
                new Color(
                        225,
                        225,
                        235
                )
        );

        bookingTable.setShowGrid(true);

        bookingTable.setSelectionBackground(
                new Color(
                        225,
                        215,
                        255
                )
        );

        bookingTable.setSelectionForeground(
                Color.BLACK
        );

        // -----------------------------------------------------
        // TABLE HEADER
        // -----------------------------------------------------

        bookingTable
                .getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                12
                        )
                );

        bookingTable
                .getTableHeader()
                .setBackground(
                        PURPLE
                );

        bookingTable
                .getTableHeader()
                .setForeground(
                        Color.WHITE
                );

        bookingTable
                .getTableHeader()
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

                80,
                120,
                100,
                100,
                100,
                100,
                90,
                105,
                105,
                55,
                100,
                100
        };

        for (
                int i = 0;
                i < widths.length;
                i++
        ) {

            bookingTable
                    .getColumnModel()
                    .getColumn(i)
                    .setPreferredWidth(
                            widths[i]
                    );
        }

        bookingTable.setDefaultRenderer(
                Object.class,
                new StatusRenderer()
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        bookingTable
                );

        scrollPane.setBorder(
                new LineBorder(
                        BORDER,
                        1
                )
        );

        scrollPane.getViewport()
                .setBackground(
                        Color.WHITE
                );

        parent.add(
                scrollPane,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // LOAD BOOKINGS
    // =========================================================

    private void loadBookings() {

        if (tableModel == null) {
            return;
        }

        tableModel.setRowCount(0);

        String search =
                searchField == null
                        ? ""
                        : searchField
                        .getText()
                        .trim();

        String selectedStatus =
                statusCombo == null
                        ? "All"
                        : statusCombo
                        .getSelectedItem()
                        .toString();

        String sql =
                "SELECT b.id, " +
                "u.name, " +
                "u.phone, " +
                "c.car_number, " +
                "c.brand, " +
                "c.model, " +
                "c.category, " +
                "b.start_date, " +
                "b.end_date, " +
                "b.total_days, " +
                "b.total_amount, " +
                "b.status " +
                "FROM bookings b " +
                "JOIN users u ON b.user_id = u.id " +
                "JOIN cars c ON b.car_id = c.id " +
                "WHERE (" +
                "CAST(b.id AS CHAR) LIKE ? " +
                "OR u.name LIKE ? " +
                "OR u.phone LIKE ? " +
                "OR c.car_number LIKE ? " +
                "OR c.brand LIKE ? " +
                "OR c.model LIKE ?" +
                ") ";

        if (!"All".equals(
                selectedStatus
        )) {

            sql +=
                    "AND b.status = ? ";
        }

        sql +=
                "ORDER BY b.id DESC";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            String keyword =
                    "%" + search + "%";

            int index = 1;

            ps.setString(
                    index++,
                    keyword
            );

            ps.setString(
                    index++,
                    keyword
            );

            ps.setString(
                    index++,
                    keyword
            );

            ps.setString(
                    index++,
                    keyword
            );

            ps.setString(
                    index++,
                    keyword
            );

            ps.setString(
                    index++,
                    keyword
            );

            if (!"All".equals(
                    selectedStatus
            )) {

                ps.setString(
                        index,
                        selectedStatus
                );
            }

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                while (rs.next()) {

                    tableModel.addRow(
                            new Object[]{

                                    rs.getInt("id"),

                                    rs.getString("name"),

                                    rs.getString("phone"),

                                    rs.getString("car_number"),

                                    rs.getString("brand"),

                                    rs.getString("model"),

                                    rs.getString("category"),

                                    rs.getDate("start_date"),

                                    rs.getDate("end_date"),

                                    rs.getInt("total_days"),

                                    String.format(
                                            "₹ %.2f",
                                            rs.getDouble(
                                                    "total_amount"
                                            )
                                    ),

                                    rs.getString("status")
                            }
                    );
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to load booking records.\n"
                            + "Please try again.",

                    "DriveX",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // BUTTON PANEL
    // =========================================================

    private void createButtons(
            JPanel parent) {

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                12,
                                5
                        )
                );

        buttonPanel.setOpaque(false);

        // -----------------------------------------------------
        // UPDATE
        // -----------------------------------------------------

        JButton update =
                createButton(
                        "UPDATE STATUS",
                        PURPLE
                );

        update.setPreferredSize(
                new Dimension(
                        160,
                        45
                )
        );

        update.addActionListener(
                e -> updateStatus()
        );

        buttonPanel.add(update);

        // -----------------------------------------------------
        // CANCEL
        // -----------------------------------------------------

        JButton cancel =
                createButton(
                        "CANCEL BOOKING",
                        RED
                );

        cancel.setPreferredSize(
                new Dimension(
                        165,
                        45
                )
        );

        cancel.addActionListener(
                e -> cancelBooking()
        );

        buttonPanel.add(cancel);

        // -----------------------------------------------------
        // VIEW
        // -----------------------------------------------------

        JButton view =
                createButton(
                        "VIEW",
                        BLUE
                );

        view.setPreferredSize(
                new Dimension(
                        120,
                        45
                )
        );

        view.addActionListener(
                e -> viewBooking()
        );

        buttonPanel.add(view);

        // -----------------------------------------------------
        // CLEAR
        // -----------------------------------------------------

        JButton clear =
                createButton(
                        "CLEAR",
                        GRAY
                );

        clear.setPreferredSize(
                new Dimension(
                        120,
                        45
                )
        );

        clear.addActionListener(
                e -> {

                    searchField.setText("");

                    statusCombo.setSelectedIndex(
                            0
                    );

                    bookingTable.clearSelection();

                    loadBookings();
                }
        );

        buttonPanel.add(clear);

        // -----------------------------------------------------
        // BACK
        // -----------------------------------------------------

        JButton back =
                createButton(
                        "BACK",
                        new Color(
                                80,
                                80,
                                80
                        )
                );

        back.setPreferredSize(
                new Dimension(
                        120,
                        45
                )
        );

        /*
         * IMPORTANT:
         * Do not simply call dispose().
         *
         * The Admin Dashboard was hidden when this frame
         * was opened. Show that same dashboard again.
         */

        back.addActionListener(
                e -> {

                    if (adminDashboard != null) {

                        adminDashboard.setVisible(true);

                    } else {

                        // Safety fallback
                        new AdminDashboardFrame()
                                .setVisible(true);
                    }

                    dispose();
                }
        );

        buttonPanel.add(back);

        parent.add(
                buttonPanel,
                BorderLayout.SOUTH
        );
    }

    // =========================================================
    // UPDATE STATUS
    // =========================================================

    private void updateStatus() {

        int row =
                bookingTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please select a booking first.",

                    "No Booking Selected",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int modelRow =
                bookingTable.convertRowIndexToModel(
                        row
                );

        int bookingId =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        modelRow,
                                        0
                                )
                                .toString()
                );

        String currentStatus =
                tableModel
                        .getValueAt(
                                modelRow,
                                11
                        )
                        .toString();

        String[] options = {

                "Confirmed",
                "Booked",
                "Completed",
                "Cancelled"
        };

        String newStatus =
                (String)
                        JOptionPane.showInputDialog(
                                this,

                                "Select the new booking status:",

                                "Update Booking Status",

                                JOptionPane.PLAIN_MESSAGE,

                                null,

                                options,

                                currentStatus
                        );

        if (newStatus == null) {
            return;
        }

        // -----------------------------------------------------
        // CANCELLED CANNOT BE REACTIVATED
        // -----------------------------------------------------

        if (
                currentStatus.equalsIgnoreCase(
                        "Cancelled"
                )
                        &&
                        !newStatus.equalsIgnoreCase(
                                "Cancelled"
                        )
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "A cancelled booking cannot be reactivated.",

                    "DriveX",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // -----------------------------------------------------
        // COMPLETED CANNOT BE CHANGED
        // -----------------------------------------------------

        if (
                currentStatus.equalsIgnoreCase(
                        "Completed"
                )
                        &&
                        !newStatus.equalsIgnoreCase(
                                "Completed"
                        )
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "A completed booking cannot be changed.",

                    "DriveX",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        if (
                newStatus.equalsIgnoreCase(
                        currentStatus
                )
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "The booking already has this status.",

                    "DriveX",

                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        // -----------------------------------------------------
        // CHECK DATE CONFLICT
        // -----------------------------------------------------

        if (
                newStatus.equalsIgnoreCase(
                        "Confirmed"
                )
                        ||
                        newStatus.equalsIgnoreCase(
                                "Booked"
                        )
        ) {

            if (!checkBookingOverlap(
                    bookingId
            )) {

                return;
            }
        }

        updateBookingStatus(
                bookingId,
                newStatus
        );
    }

    // =========================================================
    // CHECK DATE OVERLAP
    // =========================================================

    private boolean checkBookingOverlap(
            int bookingId) {

        String sql =
                "SELECT b.car_id, " +
                "b.start_date, " +
                "b.end_date, " +
                "c.status " +
                "FROM bookings b " +
                "JOIN cars c ON b.car_id = c.id " +
                "WHERE b.id = ?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    bookingId
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (!rs.next()) {

                    JOptionPane.showMessageDialog(
                            this,

                            "Booking record not found.",

                            "DriveX",

                            JOptionPane.WARNING_MESSAGE
                    );

                    return false;
                }

                int carId =
                        rs.getInt(
                                "car_id"
                        );

                Date startDate =
                        rs.getDate(
                                "start_date"
                        );

                Date endDate =
                        rs.getDate(
                                "end_date"
                        );

                String carStatus =
                        rs.getString(
                                "status"
                        );

                // -------------------------------------------------
                // MAINTENANCE CHECK
                // -------------------------------------------------

                if (
                        "Maintenance".equalsIgnoreCase(
                                carStatus
                        )
                ) {

                    JOptionPane.showMessageDialog(
                            this,

                            "This vehicle is currently under maintenance.\n"
                                    + "The booking cannot be activated.",

                            "DriveX",

                            JOptionPane.WARNING_MESSAGE
                    );

                    return false;
                }

                // -------------------------------------------------
                // OVERLAP QUERY
                // -------------------------------------------------

                String overlapSql =
                        "SELECT id " +
                        "FROM bookings " +
                        "WHERE car_id = ? " +
                        "AND id <> ? " +
                        "AND status IN ('Confirmed','Booked') " +
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
                            carId
                    );

                    overlapPs.setInt(
                            2,
                            bookingId
                    );

                    overlapPs.setDate(
                            3,
                            endDate
                    );

                    overlapPs.setDate(
                            4,
                            startDate
                    );

                    try (
                            ResultSet overlapRs =
                                    overlapPs.executeQuery()
                    ) {

                        if (overlapRs.next()) {

                            JOptionPane.showMessageDialog(
                                    this,

                                    "This vehicle already has another active booking "
                                            + "during the selected rental dates.",

                                    "Booking Date Conflict",

                                    JOptionPane.WARNING_MESSAGE
                            );

                            return false;
                        }
                    }
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to verify booking availability.",

                    "DriveX",

                    JOptionPane.ERROR_MESSAGE
            );

            return false;
        }

        return true;
    }

    // =========================================================
    // UPDATE DATABASE
    // =========================================================

    private void updateBookingStatus(
            int bookingId,
            String newStatus) {

        String sql =
                "UPDATE bookings " +
                "SET status = ? " +
                "WHERE id = ?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setString(
                    1,
                    newStatus
            );

            ps.setInt(
                    2,
                    bookingId
            );

            int updated =
                    ps.executeUpdate();

            if (updated > 0) {

                JOptionPane.showMessageDialog(
                        this,

                        "Booking ID "
                                + bookingId
                                + " status updated to "
                                + newStatus
                                + ".",

                        "DriveX",

                        JOptionPane.INFORMATION_MESSAGE
                );

                loadBookings();
            }

        } catch (SQLException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to update booking status.\n"
                            + "Please try again.",

                    "DriveX",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // CANCEL BOOKING
    // =========================================================

    private void cancelBooking() {

        int row =
                bookingTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please select a booking first.",

                    "No Booking Selected",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int modelRow =
                bookingTable.convertRowIndexToModel(
                        row
                );

        int bookingId =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        modelRow,
                                        0
                                )
                                .toString()
                );

        String status =
                tableModel
                        .getValueAt(
                                modelRow,
                                11
                        )
                        .toString();

        if (
                "Cancelled".equalsIgnoreCase(
                        status
                )
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "This booking is already cancelled.",

                    "DriveX",

                    JOptionPane.INFORMATION_MESSAGE
            );

            return;
        }

        if (
                "Completed".equalsIgnoreCase(
                        status
                )
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "Completed bookings cannot be cancelled.",

                    "DriveX",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int choice =
                JOptionPane.showConfirmDialog(
                        this,

                        "Are you sure you want to cancel Booking ID "
                                + bookingId
                                + "?",

                        "Cancel Booking",

                        JOptionPane.YES_NO_OPTION,

                        JOptionPane.WARNING_MESSAGE
                );

        if (
                choice !=
                        JOptionPane.YES_OPTION
        ) {

            return;
        }

        String sql =
                "UPDATE bookings " +
                "SET status = 'Cancelled' " +
                "WHERE id = ? " +
                "AND status IN ('Confirmed','Booked')";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    bookingId
            );

            int updated =
                    ps.executeUpdate();

            if (updated > 0) {

                JOptionPane.showMessageDialog(
                        this,

                        "Booking ID "
                                + bookingId
                                + " has been cancelled successfully.",

                        "DriveX",

                        JOptionPane.INFORMATION_MESSAGE
                );

                loadBookings();
            }

        } catch (SQLException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to cancel the booking.\n"
                            + "Please try again.",

                    "DriveX",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // VIEW BOOKING
    // =========================================================

    private void viewBooking() {

        int row =
                bookingTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please select a booking first.",

                    "No Booking Selected",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int modelRow =
                bookingTable.convertRowIndexToModel(
                        row
                );

        String details =
                "Booking ID : "
                        + tableModel.getValueAt(
                        modelRow,
                        0
                )

                        + "\n\nCustomer : "
                        + tableModel.getValueAt(
                        modelRow,
                        1
                )

                        + "\nPhone : "
                        + tableModel.getValueAt(
                        modelRow,
                        2
                )

                        + "\n\nVehicle"
                        + "\n--------------------------"

                        + "\nCar Number : "
                        + tableModel.getValueAt(
                        modelRow,
                        3
                )

                        + "\nBrand : "
                        + tableModel.getValueAt(
                        modelRow,
                        4
                )

                        + "\nModel : "
                        + tableModel.getValueAt(
                        modelRow,
                        5
                )

                        + "\nCategory : "
                        + tableModel.getValueAt(
                        modelRow,
                        6
                )

                        + "\n\nRental"
                        + "\n--------------------------"

                        + "\nStart Date : "
                        + tableModel.getValueAt(
                        modelRow,
                        7
                )

                        + "\nReturn Date : "
                        + tableModel.getValueAt(
                        modelRow,
                        8
                )

                        + "\nDays : "
                        + tableModel.getValueAt(
                        modelRow,
                        9
                )

                        + "\nAmount : "
                        + tableModel.getValueAt(
                        modelRow,
                        10
                )

                        + "\nStatus : "
                        + tableModel.getValueAt(
                        modelRow,
                        11
                );

        JOptionPane.showMessageDialog(
                this,

                details,

                "DriveX - Booking Details",

                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =========================================================
    // LABEL
    // =========================================================

    private JLabel createLabel(
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

        label.setForeground(
                new Color(
                        50,
                        50,
                        60
                )
        );

        return label;
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
    // STATUS RENDERER
    // =========================================================

    private static class StatusRenderer
            extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(
                JTable table,
                Object value,
                boolean selected,
                boolean focus,
                int row,
                int column) {

            Component component =
                    super.getTableCellRendererComponent(
                            table,
                            value,
                            selected,
                            focus,
                            row,
                            column
                    );

            if (!selected) {

                component.setBackground(
                        Color.WHITE
                );

                component.setForeground(
                        Color.DARK_GRAY
                );
            }

            if (
                    column == 11
                            &&
                            value != null
            ) {

                String status =
                        value.toString();

                if (
                        status.equalsIgnoreCase(
                                "Confirmed"
                        )
                                ||
                                status.equalsIgnoreCase(
                                        "Booked"
                                )
                ) {

                    component.setForeground(
                            new Color(
                                    40,
                                    167,
                                    69
                            )
                    );

                } else if (
                        status.equalsIgnoreCase(
                                "Completed"
                        )
                ) {

                    component.setForeground(
                            new Color(
                                    25,
                                    118,
                                    210
                            )
                    );

                } else if (
                        status.equalsIgnoreCase(
                                "Cancelled"
                        )
                ) {

                    component.setForeground(
                            new Color(
                                    220,
                                    53,
                                    69
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
                        4,
                        10,
                        8,
                        10
                )
        );

        JLabel footer =
                new JLabel(
                        "DriveX • Drive • Explore • Enjoy"
                );

        footer.setHorizontalAlignment(
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