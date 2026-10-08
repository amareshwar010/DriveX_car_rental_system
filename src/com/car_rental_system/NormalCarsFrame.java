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

public class NormalCarsFrame extends JFrame {

    private final int userId;
    private final String customerName;

    private JTable carTable;
    private DefaultTableModel tableModel;
    private JTextField searchField;

    private final Color BG = new Color(235, 245, 255);
    private final Color BLUE = new Color(25, 118, 210);
    private final Color DARK_BLUE = new Color(13, 71, 161);

    private final Color GREEN = new Color(40, 167, 69);
    private final Color ORANGE = new Color(255, 152, 0);
    private final Color RED = new Color(220, 53, 69);
    private final Color GRAY = new Color(108, 117, 125);

    private final Color TEXT = new Color(35, 45, 65);
    private final Color BORDER = new Color(210, 220, 230);

    public NormalCarsFrame(
            int userId,
            String customerName) {

        this.userId = userId;
        this.customerName = customerName;

        setTitle("DriveX - Normal Cars");

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setSize(1250, 780);

        setMinimumSize(
                new Dimension(
                        1050,
                        680
                )
        );

        setLocationRelativeTo(null);

        setResizable(true);

        createUI();

        loadCars();

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );
    }

    // =========================================================
    // UI
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
                DARK_BLUE
        );

        header.setBorder(
                new EmptyBorder(
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

        // LEFT SIDE

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
                new JLabel(
                        "DriveX"
                );

        title.setForeground(
                Color.WHITE
        );

        title.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        32
                )
        );

        JLabel subtitle =
                new JLabel(
                        "Normal Cars"
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
                        14
                )
        );

        subtitle.setBorder(
                new EmptyBorder(
                        3,
                        2,
                        0,
                        0
                )
        );

        leftPanel.add(title);

        leftPanel.add(subtitle);

        // CUSTOMER

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

        JPanel centerWrapper =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        centerWrapper.setBackground(BG);

        centerWrapper.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        15,
                        25
                )
        );

        // =====================================================
        // SEARCH CARD
        // =====================================================

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

        gbc.gridy = 0;

        gbc.insets =
                new Insets(
                        5,
                        5,
                        5,
                        8
                );

        gbc.anchor =
                GridBagConstraints.CENTER;

        // SEARCH LABEL

        JLabel searchLabel =
                new JLabel(
                        "Search"
                );

        searchLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        13
                )
        );

        searchLabel.setForeground(
                TEXT
        );

        gbc.gridx = 0;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.NONE;

        searchCard.add(
                searchLabel,
                gbc
        );

        // SEARCH FIELD

        searchField =
                new JTextField();

        searchField.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        searchField.setPreferredSize(
                new Dimension(
                        350,
                        38
                )
        );

        searchField.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        190,
                                        205,
                                        225
                                )
                        ),

                        BorderFactory.createEmptyBorder(
                                5,
                                10,
                                5,
                                10
                        )
                )
        );

        gbc.gridx = 1;

        gbc.weightx = 1.0;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        searchCard.add(
                searchField,
                gbc
        );

        // SEARCH BUTTON

        JButton search =
                createButton(
                        "SEARCH",
                        BLUE
                );

        search.addActionListener(
                e -> loadCars()
        );

        gbc.gridx = 2;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.NONE;

        gbc.insets =
                new Insets(
                        5,
                        5,
                        5,
                        5
                );

        searchCard.add(
                search,
                gbc
        );

        // CLEAR BUTTON

        JButton clear =
                createButton(
                        "CLEAR",
                        GRAY
                );

        clear.addActionListener(
                e -> {

                    searchField.setText("");

                    loadCars();

                    searchField.requestFocus();
                }
        );

        gbc.gridx = 3;

        searchCard.add(
                clear,
                gbc
        );

        // INFO

        JLabel info =
                new JLabel(
                        "Normal Category • Date-based availability"
                );

        info.setForeground(
                new Color(
                        70,
                        90,
                        110
                )
        );

        info.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        gbc.gridx = 4;

        gbc.insets =
                new Insets(
                        5,
                        15,
                        5,
                        5
                );

        searchCard.add(
                info,
                gbc
        );

        centerWrapper.add(
                searchCard,
                BorderLayout.NORTH
        );

        // =====================================================
        // TABLE CARD
        // =====================================================

        JPanel tableCard =
                new JPanel(
                        new BorderLayout()
                );

        tableCard.setBackground(
                Color.WHITE
        );

        tableCard.setBorder(
                BorderFactory.createCompoundBorder(

                        new LineBorder(
                                BORDER,
                                1
                        ),

                        new EmptyBorder(
                                12,
                                12,
                                12,
                                12
                        )
                )
        );

        createTable();

        JScrollPane scrollPane =
                new JScrollPane(
                        carTable
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        BORDER
                )
        );

        scrollPane.getVerticalScrollBar()
                .setUnitIncrement(16);

        tableCard.add(
                scrollPane,
                BorderLayout.CENTER
        );

        centerWrapper.add(
                tableCard,
                BorderLayout.CENTER
        );

        // =====================================================
        // ACTION BUTTONS
        // =====================================================

        JPanel actionPanel =
                createActionPanel();

        centerWrapper.add(
                actionPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                centerWrapper,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // TABLE
    // =========================================================

    private void createTable() {

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
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        carTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        carTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_OFF
        );

        carTable.setGridColor(
                new Color(
                        225,
                        230,
                        238
                )
        );

        carTable.setShowVerticalLines(false);

        // TABLE HEADER

        carTable.getTableHeader()
                .setFont(
                        new Font(
                                "Segoe UI",
                                Font.BOLD,
                                12
                        )
                );

        carTable.getTableHeader()
                .setBackground(
                        BLUE
                );

        carTable.getTableHeader()
                .setForeground(
                        Color.WHITE
                );

        carTable.getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                38
                        )
                );

        // COLUMN WIDTHS

        int[] widths = {

                65,
                150,
                140,
                140,
                130,
                120,
                130,
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
    }

    // =========================================================
    // ACTION PANEL
    // =========================================================

    private JPanel createActionPanel() {

        JPanel wrapper =
                new JPanel(
                        new GridBagLayout()
                );

        wrapper.setOpaque(false);

        wrapper.setBorder(
                new EmptyBorder(
                        15,
                        0,
                        0,
                        0
                )
        );

        JPanel buttons =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                12,
                                0
                        )
                );

        buttons.setOpaque(false);

        buttons.setPreferredSize(
                new Dimension(
                        800,
                        45
                )
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

        // DETAILS

        JButton details =
                createButton(
                        "CAR DETAILS",
                        BLUE
                );

        details.addActionListener(
                e -> showCarDetails()
        );

        // REFRESH

        JButton refresh =
                createButton(
                        "REFRESH",
                        ORANGE
                );

        refresh.addActionListener(
                e -> loadCars()
        );

        // BACK

        JButton back =
                createButton(
                        "BACK",
                        GRAY
                );

        back.addActionListener(
                e -> dispose()
        );

        buttons.add(book);

        buttons.add(details);

        buttons.add(refresh);

        buttons.add(back);

        wrapper.add(buttons);

        return wrapper;
    }

    // =========================================================
    // LOAD NORMAL CARS
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
         * cars.status is used only for
         * permanent vehicle conditions such
         * as Maintenance.
         *
         * Rented / Available are calculated
         * from the bookings table.
         */

        String sql =
                "SELECT "
                + "id, "
                + "car_number, "
                + "brand, "
                + "model, "
                + "car_type, "
                + "category, "
                + "price_per_day, "
                + "status "
                + "FROM cars "
                + "WHERE category = 'Normal' "
                + "AND ("
                + "car_number LIKE ? "
                + "OR brand LIKE ? "
                + "OR model LIKE ? "
                + "OR car_type LIKE ?"
                + ") "
                + "ORDER BY id DESC";

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
                                    "status"
                            );

                    /*
                     * =================================================
                     * MAINTENANCE
                     * =================================================
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
                     * =================================================
                     * CHECK CURRENT BOOKING
                     * =================================================
                     */

                    BookingInfo currentBooking =
                            getCurrentBooking(
                                    con,
                                    carId
                            );

                    /*
                     * =================================================
                     * CURRENTLY RENTED
                     * =================================================
                     *
                     * Example:
                     *
                     * Today      : Oct 8
                     * Start Date : Oct 8
                     * End Date   : Oct 10
                     *
                     * Result:
                     *
                     * Status        = Rented
                     * Available From = Oct 11
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
                         * =================================================
                         * AVAILABLE TODAY
                         * =================================================
                         *
                         * Example:
                         *
                         * Today      : Oct 8
                         * Future     : Oct 20 - Oct 30
                         *
                         * Result:
                         *
                         * Status        = Available
                         * Available From = Oct 8
                         *
                         * The future booking does not make the
                         * car rented today.
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

                    "Unable to load normal cars.\n"
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
            int carId)
            throws SQLException {

        /*
         * A booking is considered CURRENT when:
         *
         * start_date <= today
         * AND
         * end_date >= today
         *
         * Only Confirmed and Booked are active.
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
         * Maintenance cars cannot be booked.
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
         * BookingFrame performs the final
         * date-overlap validation.
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

                + "\n\nBrand : "
                + tableModel.getValueAt(
                        modelRow,
                        2
                )

                + "\nModel : "
                + tableModel.getValueAt(
                        modelRow,
                        3
                )

                + "\nType : "
                + tableModel.getValueAt(
                        modelRow,
                        4
                )

                + "\nCategory : "
                + tableModel.getValueAt(
                        modelRow,
                        5
                )

                + "\nPrice Per Day : "
                + tableModel.getValueAt(
                        modelRow,
                        6
                )

                + "\nCurrent Status : "
                + tableModel.getValueAt(
                        modelRow,
                        7
                )

                + "\nAvailable From : "
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

        button.setBackground(
                color
        );

        button.setForeground(
                Color.WHITE
        );

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

        button.setPreferredSize(
                new Dimension(
                        175,
                        45
                )
        );

        return button;
    }

    // =========================================================
    // STATUS RENDERER
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
                                210,
                                230,
                                255
                        )
                );

                component.setForeground(
                        DARK_BLUE
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
                                "Segoe UI",
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
                                "Segoe UI",
                                Font.BOLD,
                                12
                        )
                );

                if (!selected) {

                    component.setForeground(
                            DARK_BLUE
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
                        48
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

        footerLabel.setForeground(
                GRAY
        );

        footer.add(
                footerLabel
        );

        mainPanel.add(
                footer,
                BorderLayout.SOUTH
        );
    }
}