package com.car_rental_system;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.sql.*;

public class ReviewsFrame extends JFrame {

    private final int userId;
    private final String customerName;

    private JTable bookingTable;
    private DefaultTableModel tableModel;

    private JComboBox<String> ratingCombo;
    private JTextArea reviewArea;

    // =====================================================
    // COLORS
    // =====================================================

    private final Color BG =
            new Color(235, 245, 255);

    private final Color BLUE =
            new Color(25, 118, 210);

    private final Color DARK_BLUE =
            new Color(13, 71, 161);

    private final Color GREEN =
            new Color(40, 167, 69);

    private final Color ORANGE =
            new Color(255, 152, 0);

    private final Color RED =
            new Color(220, 53, 69);

    private final Color GRAY =
            new Color(108, 117, 125);

    private final Color DARK_TEXT =
            new Color(45, 45, 65);

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public ReviewsFrame(
            int userId,
            String customerName) {

        this.userId = userId;
        this.customerName = customerName;

        setTitle("DriveX - Reviews & Rating");

        setSize(1200, 800);

        setMinimumSize(
                new Dimension(
                        1000,
                        700
                )
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setResizable(true);

        createUI();

        loadCompletedBookings();
    }

    // =====================================================
    // CREATE UI
    // =====================================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(BG);

        setContentPane(mainPanel);

        createHeader(mainPanel);

        createMainContent(mainPanel);

        createFooter(mainPanel);
    }

    // =====================================================
    // HEADER
    // =====================================================

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
                BorderFactory.createEmptyBorder(
                        15,
                        35,
                        15,
                        35
                )
        );

        header.setPreferredSize(
                new Dimension(
                        0,
                        100
                )
        );

        // =================================================
        // LEFT HEADER
        // =================================================

        JPanel leftPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        leftPanel.setOpaque(false);

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

        JLabel subtitle =
                new JLabel(
                        "Reviews & Rating"
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
                        16
                )
        );

        leftPanel.add(title);

        leftPanel.add(subtitle);

        header.add(
                leftPanel,
                BorderLayout.WEST
        );

        // =================================================
        // CUSTOMER NAME
        // =================================================

        JLabel customer =
                new JLabel(
                        "Customer: " +
                        customerName
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

        header.add(
                customer,
                BorderLayout.EAST
        );

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );
    }

    // =====================================================
    // MAIN CONTENT
    // =====================================================

    private void createMainContent(
            JPanel mainPanel) {

        JPanel contentPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                15
                        )
                );

        contentPanel.setOpaque(false);

        contentPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        30,
                        15,
                        30
                )
        );

        // =================================================
        // BOOKING CARD
        // =================================================

        JPanel bookingCard =
                createBookingCard();

        contentPanel.add(
                bookingCard,
                BorderLayout.CENTER
        );

        // =================================================
        // REVIEW CARD
        // =================================================

        JPanel reviewCard =
                createReviewCard();

        contentPanel.add(
                reviewCard,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                contentPanel,
                BorderLayout.CENTER
        );
    }

    // =====================================================
    // BOOKING CARD
    // =====================================================

    private JPanel createBookingCard() {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                0,
                                10
                        )
                );

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(
                                        210,
                                        220,
                                        230
                                ),
                                1
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
        // CARD HEADER
        // =================================================

        JPanel headingPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        headingPanel.setOpaque(false);

        JLabel heading =
                new JLabel(
                        "Completed Rentals"
                );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        21
                )
        );

        heading.setForeground(
                DARK_TEXT
        );

        JLabel info =
                new JLabel(
                        "You can review a rental only after the vehicle has been returned."
                );

        info.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        info.setForeground(
                GRAY
        );

        headingPanel.add(heading);

        headingPanel.add(info);

        card.add(
                headingPanel,
                BorderLayout.NORTH
        );

        // =================================================
        // TABLE
        // =================================================

        String[] columns = {

                "Booking ID",
                "Car Number",
                "Brand",
                "Model",
                "Category",
                "Start Date",
                "Return Date",
                "Amount",
                "Review"
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
                        "Arial",
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

        bookingTable.setGridColor(
                new Color(
                        220,
                        225,
                        235
                )
        );

        bookingTable.setSelectionBackground(
                new Color(
                        220,
                        235,
                        255
                )
        );

        bookingTable.setSelectionForeground(
                Color.BLACK
        );

        bookingTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                12
                        )
                );

        bookingTable.getTableHeader()
                .setBackground(BLUE);

        bookingTable.getTableHeader()
                .setForeground(Color.WHITE);

        // =================================================
        // COLUMN WIDTHS
        // =================================================

        int[] widths = {

                90,
                110,
                110,
                110,
                100,
                110,
                110,
                110,
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

        // =================================================
        // CENTER TABLE VALUES
        // =================================================

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

        JScrollPane scrollPane =
                new JScrollPane(
                        bookingTable
                );

        scrollPane.setBorder(
                BorderFactory.createLineBorder(
                        new Color(
                                205,
                                215,
                                230
                        )
                )
        );

        card.add(
                scrollPane,
                BorderLayout.CENTER
        );

        return card;
    }

    // =====================================================
    // REVIEW CARD
    // =====================================================

    private JPanel createReviewCard() {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                15,
                                10
                        )
                );

        card.setBackground(
                Color.WHITE
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                new Color(
                                        210,
                                        220,
                                        230
                                ),
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                15,
                                18,
                                15,
                                18
                        )
                )
        );

        card.setPreferredSize(
                new Dimension(
                        0,
                        190
                )
        );

        // =================================================
        // LEFT - RATING
        // =================================================

        JPanel ratingPanel =
                new JPanel(
                        new GridBagLayout()
                );

        ratingPanel.setOpaque(false);

        ratingPanel.setPreferredSize(
                new Dimension(
                        200,
                        0
                )
        );

        GridBagConstraints ratingGbc =
                new GridBagConstraints();

        ratingGbc.gridx = 0;
        ratingGbc.gridy = 0;

        ratingGbc.weightx = 1;

        ratingGbc.fill =
                GridBagConstraints.HORIZONTAL;

        ratingGbc.anchor =
                GridBagConstraints.WEST;

        JLabel ratingLabel =
                new JLabel("Rating");

        ratingLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        ratingLabel.setForeground(
                GRAY
        );

        ratingPanel.add(
                ratingLabel,
                ratingGbc
        );

        ratingCombo =
                new JComboBox<>(
                        new String[]{
                                "5 - Excellent",
                                "4 - Very Good",
                                "3 - Good",
                                "2 - Average",
                                "1 - Poor"
                        }
                );

        ratingCombo.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        ratingGbc.gridy = 1;

        ratingGbc.insets =
                new Insets(
                        8,
                        0,
                        0,
                        0
                );

        ratingPanel.add(
                ratingCombo,
                ratingGbc
        );

        // =================================================
        // REVIEW TEXT AREA
        // =================================================

        JPanel textPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                8
                        )
                );

        textPanel.setOpaque(false);

        JLabel reviewLabel =
                new JLabel(
                        "Your Review"
                );

        reviewLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        reviewLabel.setForeground(
                GRAY
        );

        textPanel.add(
                reviewLabel,
                BorderLayout.NORTH
        );

        reviewArea =
                new JTextArea();

        reviewArea.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        reviewArea.setLineWrap(true);

        reviewArea.setWrapStyleWord(true);

        reviewArea.setMargin(
                new Insets(
                        8,
                        8,
                        8,
                        8
                )
        );

        reviewArea.setBorder(
                BorderFactory.createEmptyBorder()
        );

        JScrollPane reviewScroll =
                new JScrollPane(
                        reviewArea
                );

        reviewScroll.setBorder(
                new LineBorder(
                        new Color(
                                190,
                                200,
                                215
                        ),
                        1
                )
        );

        textPanel.add(
                reviewScroll,
                BorderLayout.CENTER
        );

        // =================================================
        // RIGHT ACTION PANEL
        // =================================================

        JPanel actionPanel =
                new JPanel(
                        new GridBagLayout()
                );

        actionPanel.setOpaque(false);

        actionPanel.setPreferredSize(
                new Dimension(
                        190,
                        0
                )
        );

        GridBagConstraints actionGbc =
                new GridBagConstraints();

        actionGbc.gridx = 0;

        actionGbc.weightx = 1;

        actionGbc.fill =
                GridBagConstraints.HORIZONTAL;

        actionGbc.insets =
                new Insets(
                        4,
                        0,
                        4,
                        0
                );

        JLabel limit =
                new JLabel(
                        "Maximum 500 characters"
                );

        limit.setFont(
                new Font(
                        "Arial",
                        Font.ITALIC,
                        11
                )
        );

        limit.setForeground(
                GRAY
        );

        actionGbc.gridy = 0;

        actionPanel.add(
                limit,
                actionGbc
        );

        JButton submitButton =
                createButton(
                        "SUBMIT REVIEW",
                        GREEN
                );

        submitButton.addActionListener(
                e -> submitReview()
        );

        actionGbc.gridy = 1;

        actionPanel.add(
                submitButton,
                actionGbc
        );

        JButton refreshButton =
                createButton(
                        "REFRESH",
                        BLUE
                );

        refreshButton.addActionListener(
                e -> loadCompletedBookings()
        );

        actionGbc.gridy = 2;

        actionPanel.add(
                refreshButton,
                actionGbc
        );

        JButton clearButton =
                createButton(
                        "CLEAR",
                        ORANGE
                );

        clearButton.addActionListener(
                e -> clearReview()
        );

        actionGbc.gridy = 3;

        actionPanel.add(
                clearButton,
                actionGbc
        );

        JButton backButton =
                createButton(
                        "BACK",
                        GRAY
                );

        backButton.addActionListener(
                e -> dispose()
        );

        actionGbc.gridy = 4;

        actionPanel.add(
                backButton,
                actionGbc
        );

        // =================================================
        // ADD PANELS
        // =================================================

        card.add(
                ratingPanel,
                BorderLayout.WEST
        );

        card.add(
                textPanel,
                BorderLayout.CENTER
        );

        card.add(
                actionPanel,
                BorderLayout.EAST
        );

        return card;
    }

    // =====================================================
    // LOAD COMPLETED BOOKINGS
    // =====================================================

    private void loadCompletedBookings() {

        if (tableModel == null) {
            return;
        }

        tableModel.setRowCount(0);

        String query =
                "SELECT b.id, c.car_number, c.brand, " +
                "c.model, c.category, b.start_date, " +
                "b.end_date, b.total_amount, " +
                "CASE " +
                "WHEN r.id IS NULL THEN 'Not Reviewed' " +
                "ELSE 'Reviewed' " +
                "END AS review_status " +
                "FROM bookings b " +
                "JOIN cars c ON b.car_id=c.id " +
                "LEFT JOIN reviews r " +
                "ON b.id=r.booking_id " +
                "AND r.user_id=? " +
                "WHERE b.user_id=? " +
                "AND b.status='Completed' " +
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

            ps.setInt(
                    2,
                    userId
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                while (rs.next()) {

                    Object[] row = {

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
                                    "category"
                            ),

                            rs.getDate(
                                    "start_date"
                            ),

                            rs.getDate(
                                    "end_date"
                            ),

                            String.format(
                                    "₹ %.2f",
                                    rs.getDouble(
                                            "total_amount"
                                    )
                            ),

                            rs.getString(
                                    "review_status"
                            )
                    };

                    tableModel.addRow(row);
                }
            }

        } catch (SQLException ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to load completed rentals.\n\n"
                            + "Please try again.",
                    "DriveX - Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // SUBMIT REVIEW
    // =====================================================

    private void submitReview() {

        int row =
                bookingTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a completed rental first.",
                    "DriveX - Select Rental",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // Convert view row to model row
        int modelRow =
                bookingTable.convertRowIndexToModel(
                        row
                );

        String reviewStatus =
                tableModel
                        .getValueAt(
                                modelRow,
                                8
                        )
                        .toString();

        if ("Reviewed".equalsIgnoreCase(
                reviewStatus)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Review Already Submitted\n\n"
                            + "You have already submitted a review "
                            + "for this rental.",
                    "DriveX - Duplicate Review",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int bookingId =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        modelRow,
                                        0
                                )
                                .toString()
                );

        String reviewText =
                reviewArea
                        .getText()
                        .trim();

        if (reviewText.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please write your review before submitting.",
                    "DriveX - Review Required",
                    JOptionPane.WARNING_MESSAGE
            );

            reviewArea.requestFocus();

            return;
        }

        if (reviewText.length() > 500) {

            JOptionPane.showMessageDialog(
                    this,
                    "Your review cannot exceed 500 characters.",
                    "DriveX - Review Too Long",
                    JOptionPane.WARNING_MESSAGE
            );

            reviewArea.requestFocus();

            return;
        }

        String selectedRating =
                ratingCombo
                        .getSelectedItem()
                        .toString();

        int rating =
                Integer.parseInt(
                        selectedRating.substring(
                                0,
                                1
                        )
                );

        String checkQuery =
                "SELECT id FROM reviews " +
                "WHERE booking_id=? " +
                "AND user_id=? " +
                "LIMIT 1";

        String insertQuery =
                "INSERT INTO reviews " +
                "(booking_id, user_id, rating, " +
                "review_text, review_date) " +
                "VALUES (?, ?, ?, ?, CURDATE())";

        try (
                Connection con =
                        DBConnection.getConnection()
        ) {

            // =================================================
            // DUPLICATE REVIEW CHECK
            // =================================================

            try (
                    PreparedStatement ps =
                            con.prepareStatement(
                                    checkQuery
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

                try (
                        ResultSet rs =
                                ps.executeQuery()
                ) {

                    if (rs.next()) {

                        JOptionPane.showMessageDialog(
                                this,
                                "Review Already Submitted\n\n"
                                        + "A review already exists "
                                        + "for this rental.",
                                "DriveX - Duplicate Review",
                                JOptionPane.WARNING_MESSAGE
                        );

                        loadCompletedBookings();

                        return;
                    }
                }
            }

            // =================================================
            // INSERT REVIEW
            // =================================================

            try (
                    PreparedStatement ps =
                            con.prepareStatement(
                                    insertQuery
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

                ps.setInt(
                        3,
                        rating
                );

                ps.setString(
                        4,
                        reviewText
                );

                ps.executeUpdate();
            }

            JOptionPane.showMessageDialog(
                    this,
                    "Review Submitted Successfully!\n\n"
                            + "Thank you for sharing your "
                            + "DriveX experience.",
                    "DriveX - Review Submitted",
                    JOptionPane.INFORMATION_MESSAGE
            );

            clearReview();

            loadCompletedBookings();

        } catch (SQLException ex) {

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Your review could not be submitted.\n\n"
                            + "Please try again.",
                    "DriveX - Review Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // CLEAR REVIEW
    // =====================================================

    private void clearReview() {

        if (ratingCombo != null) {

            ratingCombo.setSelectedIndex(0);
        }

        if (reviewArea != null) {

            reviewArea.setText("");
        }

        if (bookingTable != null) {

            bookingTable.clearSelection();
        }
    }

    // =====================================================
    // BUTTON HELPER
    // =====================================================

    private JButton createButton(
            String text,
            Color color) {

        JButton button =
                new JButton(text);

        button.setPreferredSize(
                new Dimension(
                        170,
                        36
                )
        );

        button.setMinimumSize(
                new Dimension(
                        150,
                        36
                )
        );

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

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
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

        footerPanel.setBackground(BG);

        footerPanel.setPreferredSize(
                new Dimension(
                        0,
                        45
                )
        );

        JLabel footer =
                new JLabel(
                        "DriveX • Drive • Explore • Enjoy",
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