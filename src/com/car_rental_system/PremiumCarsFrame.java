package com.car_rental_system;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class PremiumCarsFrame extends JFrame {

    private final int userId;
    private final String customerName;

    private JTable carTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    private final Color BG = new Color(245, 240, 255);
    private final Color PURPLE = new Color(108, 76, 180);
    private final Color DARK_PURPLE = new Color(67, 56, 140);
    private final Color LIGHT_PURPLE = new Color(235, 225, 255);

    private final Color GREEN = new Color(40, 167, 69);
    private final Color ORANGE = new Color(255, 152, 0);
    private final Color RED = new Color(220, 53, 69);
    private final Color GRAY = new Color(108, 117, 125);
    private final Color DARK_TEXT = new Color(45, 45, 55);

    public PremiumCarsFrame(int userId, String customerName) {

        this.userId = userId;
        this.customerName = customerName;

        setTitle("DriveX - Premium Cars");

        setSize(1250, 780);

        setMinimumSize(
                new Dimension(
                        1050,
                        680
                )
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setResizable(true);

        createUI();

        loadCars();
    }

    // =========================================================
    // MAIN UI
    // =========================================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(BG);

        setContentPane(mainPanel);

        createHeader(mainPanel);

        createCenterContent(mainPanel);

        createFooter(mainPanel);

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );
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
                new JLabel("DriveX");

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
                        "Premium Cars"
                );

        subtitle.setForeground(
                LIGHT_PURPLE
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
                Box.createVerticalStrut(3)
        );

        leftPanel.add(subtitle);

        JLabel customer =
                new JLabel(
                        "Welcome, "
                        + customerName
                );

        customer.setForeground(
                Color.WHITE
        );

        customer.setFont(
                new Font(
                        "Arial",
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

        header.setPreferredSize(
                new Dimension(
                        0,
                        105
                )
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
                                15
                        )
                );

        centerPanel.setBackground(BG);

        centerPanel.setBorder(
                new EmptyBorder(
                        18,
                        25,
                        10,
                        25
                )
        );

        createSearchCard(
                centerPanel
        );

        createTableCard(
                centerPanel
        );

        createButtons(
                centerPanel
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
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

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(
                                        215,
                                        205,
                                        230
                                )
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
                        5,
                        6,
                        5,
                        6
                );

        gbc.gridy = 0;

        // SEARCH LABEL

        gbc.gridx = 0;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.NONE;

        JLabel label =
                new JLabel(
                        "Search"
                );

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        label.setForeground(
                DARK_TEXT
        );

        card.add(
                label,
                gbc
        );

        // SEARCH FIELD

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
                        300,
                        38
                )
        );

        gbc.gridx = 1;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        card.add(
                searchField,
                gbc
        );

        // SEARCH BUTTON

        JButton search =
                createButton(
                        "SEARCH",
                        PURPLE
                );

        search.setPreferredSize(
                new Dimension(
                        110,
                        38
                )
        );

        search.addActionListener(
                e -> loadCars()
        );

        gbc.gridx = 2;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.NONE;

        card.add(
                search,
                gbc
        );

        // CLEAR BUTTON

        JButton clear =
                createButton(
                        "CLEAR",
                        GRAY
                );

        clear.setPreferredSize(
                new Dimension(
                        110,
                        38
                )
        );

        clear.addActionListener(
                e -> {

                    searchField.setText("");

                    loadCars();
                }
        );

        gbc.gridx = 3;

        card.add(
                clear,
                gbc
        );

        // INFO

        JLabel info =
                new JLabel(
                        "Premium category • Date-based availability"
                );

        info.setForeground(
                new Color(
                        90,
                        70,
                        120
                )
        );

        info.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        12
                )
        );

        gbc.gridx = 4;

        card.add(
                info,
                gbc
        );

        parent.add(
                card,
                BorderLayout.NORTH
        );
    }

    // =========================================================
    // TABLE CARD
    // =========================================================

    private void createTableCard(
            JPanel parent) {

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
                                        205,
                                        230
                                )
                        ),
                        new EmptyBorder(
                                10,
                                10,
                                10,
                                10
                        )
                )
        );

        JLabel title =
                new JLabel(
                        "Premium Cars & Availability"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        title.setForeground(
                DARK_PURPLE
        );

        title.setBorder(
                new EmptyBorder(
                        0,
                        5,
                        10,
                        5
                )
        );

        card.add(
                title,
                BorderLayout.NORTH
        );

        String[] columns = {

                "ID",
                "Car Number",
                "Brand",
                "Model",
                "Type",
                "Category",
                "Price/Day",
                "Status",
                "Available From"
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
                new JTable(
                        tableModel
                );

        carTable.setRowHeight(34);

        carTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        carTable.setForeground(
                DARK_TEXT
        );

        carTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        carTable.setShowGrid(true);

        carTable.setGridColor(
                new Color(
                        225,
                        225,
                        230
                )
        );

        carTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_OFF
        );

        // TABLE HEADER

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
                        PURPLE
                );

        carTable.getTableHeader()
                .setForeground(
                        Color.WHITE
                );

        carTable.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                36
                        )
                );

        // COLUMN WIDTHS

        int[] widths = {

                60,
                140,
                130,
                130,
                120,
                110,
                125,
                120,
                150
        };

        for (
                int i = 0;
                i < widths.length;
                i++
        ) {

            carTable
                    .getColumnModel()
                    .getColumn(i)
                    .setPreferredWidth(
                            widths[i]
                    );
        }

        carTable.setDefaultRenderer(
                Object.class,
                new CarStatusRenderer()
        );

        JScrollPane scrollPane =
                new JScrollPane(
                        carTable
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                225,
                                225,
                                230
                        )
                )
        );

        card.add(
                scrollPane,
                BorderLayout.CENTER
        );

        parent.add(
                card,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // BUTTONS
    // =========================================================

    private void createButtons(
            JPanel parent) {

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                15,
                                0
                        )
                );

        buttonPanel.setBackground(
                BG
        );

        // BOOK

        JButton book =
                createButton(
                        "BOOK SELECTED CAR",
                        GREEN
                );

        book.addActionListener(
                e -> bookSelectedCar()
        );

        buttonPanel.add(book);

        // DETAILS

        JButton details =
                createButton(
                        "CAR DETAILS",
                        PURPLE
                );

        details.addActionListener(
                e -> showCarDetails()
        );

        buttonPanel.add(details);

        // REFRESH

        JButton refresh =
                createButton(
                        "REFRESH",
                        ORANGE
                );

        refresh.addActionListener(
                e -> loadCars()
        );

        buttonPanel.add(refresh);

        // BACK

        JButton back =
                createButton(
                        "BACK",
                        GRAY
                );

        back.addActionListener(
                e -> dispose()
        );

        buttonPanel.add(back);

        buttonPanel.setPreferredSize(
                new Dimension(
                        0,
                        48
                )
        );

        parent.add(
                buttonPanel,
                BorderLayout.SOUTH
        );
    }

    // =========================================================
    // LOAD CARS
    // =========================================================

    private void loadCars() {

        if (
                tableModel == null
                || searchField == null
        ) {
            return;
        }

        tableModel.setRowCount(0);

        String search =
                searchField
                        .getText()
                        .trim();

        /*
         * Only Maintenance comes from
         * cars.status.
         *
         * Rented / Available are calculated
         * from bookings according to TODAY.
         */

        String sql =
                "SELECT "
                + "c.id, "
                + "c.car_number, "
                + "c.brand, "
                + "c.model, "
                + "c.car_type, "
                + "c.category, "
                + "c.price_per_day, "
                + "c.status AS car_status "
                + "FROM cars c "
                + "WHERE c.category = 'Premium' "
                + "AND ("
                + "c.car_number LIKE ? "
                + "OR c.brand LIKE ? "
                + "OR c.model LIKE ? "
                + "OR c.car_type LIKE ?"
                + ") "
                + "ORDER BY c.id DESC";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            String keyword =
                    "%" + search + "%";

            ps.setString(
                    1,
                    keyword
            );

            ps.setString(
                    2,
                    keyword
            );

            ps.setString(
                    3,
                    keyword
            );

            ps.setString(
                    4,
                    keyword
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                while (rs.next()) {

                    int carId =
                            rs.getInt(
                                    "id"
                            );

                    String carStatus =
                            rs.getString(
                                    "car_status"
                            );

                    /*
                     * Maintenance has highest priority.
                     */

                    if (
                            "Maintenance"
                                    .equalsIgnoreCase(
                                            carStatus
                                    )
                    ) {

                        tableModel.addRow(
                                new Object[]{

                                        carId,

                                        rs.getString(
                                                "car_number"
                                        ),

                                        rs.getString(
                                                "brand"
                                        ),

                                        rs.getString(
                                                "model"
                                        ),

                                        rs.getString(
                                                "car_type"
                                        ),

                                        rs.getString(
                                                "category"
                                        ),

                                        String.format(
                                                "₹ %.2f",
                                                rs.getDouble(
                                                        "price_per_day"
                                                )
                                        ),

                                        "Maintenance",

                                        "Not Available"
                                }
                        );

                        continue;
                    }

                    /*
                     * Get today's active booking.
                     */

                    BookingInfo currentBooking =
                            getCurrentBooking(
                                    con,
                                    carId
                            );

                    /*
                     * CASE 1
                     *
                     * Car is rented TODAY.
                     */

                    if (
                            currentBooking != null
                    ) {

                        LocalDate availableDate =
                                currentBooking.endDate
                                        .plusDays(1);

                        tableModel.addRow(
                                new Object[]{

                                        carId,

                                        rs.getString(
                                                "car_number"
                                        ),

                                        rs.getString(
                                                "brand"
                                        ),

                                        rs.getString(
                                                "model"
                                        ),

                                        rs.getString(
                                                "car_type"
                                        ),

                                        rs.getString(
                                                "category"
                                        ),

                                        String.format(
                                                "₹ %.2f",
                                                rs.getDouble(
                                                        "price_per_day"
                                                )
                                        ),

                                        "Rented",

                                        availableDate.toString()
                                }
                        );

                    } else {

                        /*
                         * CASE 2
                         *
                         * No booking is active TODAY.
                         *
                         * Even if there is a future booking,
                         * the car is available today.
                         */

                        tableModel.addRow(
                                new Object[]{

                                        carId,

                                        rs.getString(
                                                "car_number"
                                        ),

                                        rs.getString(
                                                "brand"
                                        ),

                                        rs.getString(
                                                "model"
                                        ),

                                        rs.getString(
                                                "car_type"
                                        ),

                                        rs.getString(
                                                "category"
                                        ),

                                        String.format(
                                                "₹ %.2f",
                                                rs.getDouble(
                                                        "price_per_day"
                                                )
                                        ),

                                        "Available",

                                        LocalDate.now()
                                                .toString()
                                }
                        );
                    }
                }
            }

        } catch (SQLException e) {

            e.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to load premium cars.\n"
                    + "Please try again.",

                    "DriveX",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // GET CURRENT BOOKING
    // =========================================================

    private BookingInfo getCurrentBooking(
            Connection con,
            int carId) throws SQLException {

        /*
         * A booking is CURRENT only when:
         *
         * start_date <= today
         * AND
         * end_date >= today
         *
         * Only Confirmed / Booked bookings
         * are considered.
         */

        String sql =
                "SELECT "
                + "start_date, "
                + "end_date "
                + "FROM bookings "
                + "WHERE car_id = ? "
                + "AND status IN ('Confirmed','Booked') "
                + "AND start_date <= CURDATE() "
                + "AND end_date >= CURDATE() "
                + "ORDER BY end_date DESC "
                + "LIMIT 1";

        try (
                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    carId
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    java.sql.Date startDate =
                            rs.getDate(
                                    "start_date"
                            );

                    java.sql.Date endDate =
                            rs.getDate(
                                    "end_date"
                            );

                    return new BookingInfo(
                            startDate.toLocalDate(),
                            endDate.toLocalDate()
                    );
                }
            }
        }

        return null;
    }

    // =========================================================
    // BOOKING INFORMATION
    // =========================================================

    private static class BookingInfo {

        private final LocalDate startDate;
        private final LocalDate endDate;

        private BookingInfo(
                LocalDate startDate,
                LocalDate endDate) {

            this.startDate = startDate;

            this.endDate = endDate;
        }
    }

    // =========================================================
    // BOOK SELECTED CAR
    // =========================================================

    private void bookSelectedCar() {

        int row =
                carTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please select a car first.",

                    "No Car Selected",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int modelRow =
                carTable.convertRowIndexToModel(
                        row
                );

        int carId =
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
                                7
                        )
                        .toString();

        /*
         * Maintenance cannot be booked.
         */

        if (
                "Maintenance"
                        .equalsIgnoreCase(
                                status
                        )
        ) {

            JOptionPane.showMessageDialog(
                    this,

                    "This vehicle is currently under "
                    + "maintenance and cannot be booked.",

                    "DriveX",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        /*
         * Rented means the car is occupied TODAY.
         *
         * However, BookingFrame will still check
         * the customer's selected dates.
         *
         * Therefore a future date after the current
         * rental can still be booked if there is
         * no overlap.
         */

        BookingFrame bookingFrame =
                new BookingFrame(
                        userId,
                        customerName,
                        carId
                );

        bookingFrame.setVisible(true);
    }

    // =========================================================
    // CAR DETAILS
    // =========================================================

    private void showCarDetails() {

        int row =
                carTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please select a car first.",

                    "No Car Selected",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int modelRow =
                carTable.convertRowIndexToModel(
                        row
                );

        String details =
                "Car Number : "
                + tableModel.getValueAt(
                        modelRow,
                        1
                )
                + "\n\n"

                + "Brand : "
                + tableModel.getValueAt(
                        modelRow,
                        2
                )
                + "\n"

                + "Model : "
                + tableModel.getValueAt(
                        modelRow,
                        3
                )
                + "\n"

                + "Type : "
                + tableModel.getValueAt(
                        modelRow,
                        4
                )
                + "\n"

                + "Category : "
                + tableModel.getValueAt(
                        modelRow,
                        5
                )
                + "\n"

                + "Price Per Day : "
                + tableModel.getValueAt(
                        modelRow,
                        6
                )
                + "\n"

                + "Current Status : "
                + tableModel.getValueAt(
                        modelRow,
                        7
                )
                + "\n"

                + "Available From : "
                + tableModel.getValueAt(
                        modelRow,
                        8
                );

        JOptionPane.showMessageDialog(
                this,

                details,

                "DriveX - Car Details",

                JOptionPane.INFORMATION_MESSAGE
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

        button.setOpaque(true);

        return button;
    }

    // =========================================================
    // TABLE RENDERER
    // =========================================================

    private class CarStatusRenderer
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

            setHorizontalAlignment(
                    SwingConstants.CENTER
            );

            if (selected) {

                component.setBackground(
                        new Color(
                                225,
                                215,
                                250
                        )
                );

                component.setForeground(
                        DARK_PURPLE
                );

            } else {

                component.setBackground(
                        Color.WHITE
                );

                component.setForeground(
                        Color.DARK_GRAY
                );
            }

            /*
             * STATUS COLUMN = 7
             */

            if (
                    column == 7
                    && value != null
            ) {

                String status =
                        value.toString();

                component.setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                12
                        )
                );

                if (!selected) {

                    if (
                            status.equalsIgnoreCase(
                                    "Available"
                            )
                    ) {

                        component.setForeground(
                                GREEN
                        );

                    } else if (
                            status.equalsIgnoreCase(
                                    "Rented"
                            )
                    ) {

                        component.setForeground(
                                ORANGE
                        );

                    } else if (
                            status.equalsIgnoreCase(
                                    "Maintenance"
                            )
                    ) {

                        component.setForeground(
                                RED
                        );
                    }
                }
            }

            /*
             * AVAILABLE FROM COLUMN = 8
             */

            if (
                    column == 8
                    && value != null
            ) {

                component.setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                12
                        )
                );

                if (!selected) {

                    component.setForeground(
                            DARK_PURPLE
                    );
                }
            }

            return component;
        }
    }

    // =========================================================
    // FOOTER
    // =========================================================

    private void createFooter(
            JPanel mainPanel) {

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

        footer.setForeground(
                GRAY
        );

        JPanel footerPanel =
                new JPanel(
                        new BorderLayout()
                );

        footerPanel.setBackground(
                BG
        );

        footerPanel.setBorder(
                new EmptyBorder(
                        4,
                        10,
                        8,
                        10
                )
        );

        footerPanel.add(
                footer,
                BorderLayout.CENTER
        );

        footerPanel.setPreferredSize(
                new Dimension(
                        0,
                        35
                )
        );

        mainPanel.add(
                footerPanel,
                BorderLayout.SOUTH
        );
    }
}