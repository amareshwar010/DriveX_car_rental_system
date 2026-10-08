package com.car_rental_system;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;

public class MyBookingsFrame extends JFrame {

    private final int userId;
    private final String customerName;

    private JTable bookingTable;
    private DefaultTableModel tableModel;

    private final Color BG = new Color(235, 245, 255);
    private final Color BLUE = new Color(25, 118, 210);
    private final Color DARK_BLUE = new Color(13, 71, 161);
    private final Color GREEN = new Color(40, 167, 69);
    private final Color RED = new Color(220, 53, 69);
    private final Color GRAY = new Color(108, 117, 125);
    private final Color TEXT = new Color(35, 45, 65);
    private final Color BORDER = new Color(210, 220, 230);

    public MyBookingsFrame(int userId, String customerName) {

        this.userId = userId;
        this.customerName = customerName;

        setTitle("DriveX - My Bookings");

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setSize(1250, 780);
        setMinimumSize(new Dimension(1000, 680));

        setLocationRelativeTo(null);

        setResizable(true);

        createUI();

        loadBookings();
    }

    // =========================================================
    // CREATE UI
    // =========================================================

    private void createUI() {

        JPanel mainPanel = new JPanel(new BorderLayout());

        mainPanel.setBackground(BG);

        setContentPane(mainPanel);

        createHeader(mainPanel);

        createCenterContent(mainPanel);

        createFooter(mainPanel);
    }

    // =========================================================
    // HEADER
    // =========================================================

    private void createHeader(JPanel mainPanel) {

        JPanel header = new JPanel(new BorderLayout());

        header.setBackground(DARK_BLUE);

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 30, 15, 30
                )
        );

        header.setPreferredSize(
                new Dimension(0, 105)
        );


        // -----------------------------------------------------
        // LEFT SIDE
        // -----------------------------------------------------

        JPanel leftPanel = new JPanel();

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
                new JLabel("My Bookings");

        subtitle.setForeground(
                new Color(220, 235, 255)
        );

        subtitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        14
                )
        );

        subtitle.setBorder(
                BorderFactory.createEmptyBorder(
                        3, 2, 0, 0
                )
        );


        leftPanel.add(title);
        leftPanel.add(subtitle);


        // -----------------------------------------------------
        // RIGHT SIDE
        // -----------------------------------------------------

        JLabel customer =
                new JLabel(
                        "Customer: " + customerName
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

        JPanel centerWrapper =
                new JPanel(new BorderLayout());

        centerWrapper.setBackground(BG);

        centerWrapper.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 25, 15, 25
                )
        );


        // =====================================================
        // TABLE CARD
        // =====================================================

        JPanel tableCard =
                new JPanel(new BorderLayout(0, 10));

        tableCard.setBackground(Color.WHITE);

        tableCard.setBorder(
                BorderFactory.createCompoundBorder(

                        new LineBorder(
                                BORDER,
                                1
                        ),

                        BorderFactory.createEmptyBorder(
                                15, 15, 15, 15
                        )
                )
        );


        // -----------------------------------------------------
        // CARD HEADING
        // -----------------------------------------------------

        JPanel headingPanel =
                new JPanel();

        headingPanel.setLayout(
                new BoxLayout(
                        headingPanel,
                        BoxLayout.Y_AXIS
                )
        );

        headingPanel.setOpaque(false);


        JLabel heading =
                new JLabel(
                        "Your Booking History"
                );

        heading.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        20
                )
        );

        heading.setForeground(TEXT);


        JLabel description =
                new JLabel(
                        "View, manage and track your DriveX bookings."
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
                        3, 0, 0, 0
                )
        );


        headingPanel.add(heading);
        headingPanel.add(description);


        tableCard.add(
                headingPanel,
                BorderLayout.NORTH
        );


        // =====================================================
        // TABLE
        // =====================================================

        createBookingTable();


        JScrollPane scrollPane =
                new JScrollPane(bookingTable);

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
        // BUTTON PANEL
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
    // CREATE TABLE
    // =========================================================

    private void createBookingTable() {

        String[] columns = {

                "Booking ID",
                "Car Number",
                "Brand",
                "Model",
                "Type",
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
                new JTable(tableModel);


        bookingTable.setRowHeight(32);

        bookingTable.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        12
                )
        );

        bookingTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        bookingTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_OFF
        );

        bookingTable.setGridColor(
                new Color(225, 230, 238)
        );

        bookingTable.setShowVerticalLines(false);


        // -----------------------------------------------------
        // HEADER
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
                                38
                        )
                );


        // -----------------------------------------------------
        // COLUMN WIDTHS
        // -----------------------------------------------------

        int[] widths = {

                85,
                110,
                110,
                110,
                100,
                100,
                115,
                115,
                60,
                115,
                110
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


        bookingTable.setDefaultRenderer(
                Object.class,
                new BookingStatusRenderer()
        );
    }

    // =========================================================
    // ACTION PANEL
    // =========================================================

    private JPanel createActionPanel() {

        JPanel wrapper =
                new JPanel(new GridBagLayout());

        wrapper.setOpaque(false);

        wrapper.setBorder(
                BorderFactory.createEmptyBorder(
                        15, 0, 0, 0
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
                        700,
                        45
                )
        );


        JButton view =
                createButton(
                        "VIEW DETAILS",
                        BLUE
                );

        view.addActionListener(
                e -> viewBooking()
        );


        JButton cancel =
                createButton(
                        "CANCEL BOOKING",
                        RED
                );

        cancel.addActionListener(
                e -> cancelBooking()
        );


        JButton refresh =
                createButton(
                        "REFRESH",
                        GREEN
                );

        refresh.addActionListener(
                e -> loadBookings()
        );


        JButton back =
                createButton(
                        "BACK",
                        GRAY
                );

        back.addActionListener(
                e -> dispose()
        );


        buttons.add(view);
        buttons.add(cancel);
        buttons.add(refresh);
        buttons.add(back);


        wrapper.add(buttons);

        return wrapper;
    }

    // =========================================================
    // LOAD BOOKINGS
    // =========================================================

    private void loadBookings() {

        if (tableModel == null) {
            return;
        }

        tableModel.setRowCount(0);


        String sql =
                "SELECT b.id, "
                + "c.car_number, "
                + "c.brand, "
                + "c.model, "
                + "c.car_type, "
                + "c.category, "
                + "b.start_date, "
                + "b.end_date, "
                + "b.total_days, "
                + "b.total_amount, "
                + "b.status "
                + "FROM bookings b "
                + "JOIN cars c "
                + "ON b.car_id = c.id "
                + "WHERE b.user_id = ? "
                + "ORDER BY b.id DESC";


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

                    tableModel.addRow(
                            new Object[]{

                                    rs.getInt("id"),

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
                            }
                    );
                }
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to load your bookings.\n"
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


        String bookingId =
                tableModel.getValueAt(
                        row,
                        0
                ).toString();

        String carNumber =
                tableModel.getValueAt(
                        row,
                        1
                ).toString();

        String brand =
                tableModel.getValueAt(
                        row,
                        2
                ).toString();

        String model =
                tableModel.getValueAt(
                        row,
                        3
                ).toString();

        String type =
                tableModel.getValueAt(
                        row,
                        4
                ).toString();

        String category =
                tableModel.getValueAt(
                        row,
                        5
                ).toString();

        String startDate =
                tableModel.getValueAt(
                        row,
                        6
                ).toString();

        String endDate =
                tableModel.getValueAt(
                        row,
                        7
                ).toString();

        String days =
                tableModel.getValueAt(
                        row,
                        8
                ).toString();

        String amount =
                tableModel.getValueAt(
                        row,
                        9
                ).toString();

        String status =
                tableModel.getValueAt(
                        row,
                        10
                ).toString();


        String message =
                "Booking ID : " + bookingId

                + "\n\nVehicle Details"

                + "\n-----------------------------"

                + "\nCar Number : " + carNumber

                + "\nBrand      : " + brand

                + "\nModel      : " + model

                + "\nType       : " + type

                + "\nCategory   : " + category

                + "\n\nRental Details"

                + "\n-----------------------------"

                + "\nStart Date : " + startDate

                + "\nReturn Date: " + endDate

                + "\nTotal Days : " + days

                + "\nAmount     : " + amount

                + "\nStatus     : " + status;


        JOptionPane.showMessageDialog(
                this,

                message,

                "DriveX - Booking Details",

                JOptionPane.INFORMATION_MESSAGE
        );
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


        int bookingId =
                Integer.parseInt(
                        tableModel.getValueAt(
                                row,
                                0
                        ).toString()
                );


        String status =
                tableModel.getValueAt(
                        row,
                        10
                ).toString();


        if ("Completed".equalsIgnoreCase(
                status)) {

            JOptionPane.showMessageDialog(
                    this,

                    "Completed bookings cannot be cancelled.",

                    "DriveX",

                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }


        if ("Cancelled".equalsIgnoreCase(
                status)) {

            JOptionPane.showMessageDialog(
                    this,

                    "This booking has already been cancelled.",

                    "DriveX",

                    JOptionPane.INFORMATION_MESSAGE
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


        if (choice != JOptionPane.YES_OPTION) {
            return;
        }


        Connection con = null;


        try {

            con =
                    DBConnection.getConnection();

            con.setAutoCommit(false);


            String updateSql =
                    "UPDATE bookings "
                    + "SET status = 'Cancelled' "
                    + "WHERE id = ? "
                    + "AND user_id = ? "
                    + "AND status IN ('Confirmed', 'Booked')";


            int updated;


            try (
                    PreparedStatement ps =
                            con.prepareStatement(
                                    updateSql
                            )
            ) {

                ps.setInt(
                        1,
                        bookingId
                );

                ps.setInt(
                        2,
                        userId
                );


                updated =
                        ps.executeUpdate();
            }


            if (updated == 0) {

                con.rollback();


                JOptionPane.showMessageDialog(
                        this,

                        "This booking could not be cancelled.\n"
                        + "It may have already been updated.",

                        "DriveX",

                        JOptionPane.WARNING_MESSAGE
                );

                return;
            }


            /*
             * Do not change cars.status here.
             *
             * DriveX uses booking dates and active
             * booking records to determine availability.
             */


            con.commit();


            JOptionPane.showMessageDialog(
                    this,

                    "Booking ID "
                    + bookingId
                    + " has been cancelled successfully.",

                    "DriveX - Booking Cancelled",

                    JOptionPane.INFORMATION_MESSAGE
            );


            loadBookings();


        } catch (SQLException e) {

            try {

                if (con != null) {
                    con.rollback();
                }

            } catch (SQLException ignored) {
            }


            JOptionPane.showMessageDialog(
                    this,

                    "Unable to cancel the booking.\n"
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
    // BUTTON CREATOR
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

        button.setPreferredSize(
                new Dimension(
                        160,
                        45
                )
        );

        return button;
    }

    // =========================================================
    // TABLE STATUS RENDERER
    // =========================================================

    private static class BookingStatusRenderer
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


            if (!isSelected) {

                component.setBackground(
                        Color.WHITE
                );

                component.setForeground(
                        Color.DARK_GRAY
                );
            }


            if (column == 10 &&
                    value != null) {

                String status =
                        value.toString();


                if (status.equalsIgnoreCase(
                        "Confirmed")
                        ||
                        status.equalsIgnoreCase(
                                "Booked")) {

                    component.setForeground(
                            new Color(
                                    40,
                                    167,
                                    69
                            )
                    );

                } else if (
                        status.equalsIgnoreCase(
                                "Completed")) {

                    component.setForeground(
                            new Color(
                                    25,
                                    118,
                                    210
                            )
                    );

                } else if (
                        status.equalsIgnoreCase(
                                "Cancelled")) {

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
                                "Segoe UI",
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

        JPanel footer =
                new JPanel(new GridBagLayout());

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

        footerLabel.setForeground(GRAY);


        footer.add(footerLabel);


        mainPanel.add(
                footer,
                BorderLayout.SOUTH
        );
    }
}