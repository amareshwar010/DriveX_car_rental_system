package com.car_rental_system;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class AdminReviewsFrame extends JFrame {

    private JTextField txtSearch;
    private JComboBox<String> cmbRating;

    private JTable reviewTable;
    private DefaultTableModel tableModel;

    // =====================================================
    // COLORS
    // =====================================================

    private final Color BG_COLOR =
            new Color(235, 245, 255);

    private final Color PURPLE =
            new Color(67, 56, 140);

    private final Color PRIMARY =
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
            new Color(210, 220, 230);

    private final Color DARK_TEXT =
            new Color(45, 45, 45);

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public AdminReviewsFrame() {

        setTitle("DriveX - Reviews & Ratings");

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setResizable(true);

        createUI();
    }

    // =====================================================
    // MAIN UI
    // =====================================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BG_COLOR
        );

        setContentPane(mainPanel);

        createHeader(mainPanel);

        createCenterContent(mainPanel);

        createFooter(mainPanel);
    }

    // =====================================================
    // HEADER
    // =====================================================

    private void createHeader(
            JPanel mainPanel) {

        JPanel headerPanel =
                new JPanel(
                        new GridBagLayout()
                );

        headerPanel.setBackground(
                PURPLE
        );

        headerPanel.setPreferredSize(
                new Dimension(
                        0,
                        105
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.anchor =
                GridBagConstraints.WEST;

        gbc.insets =
                new Insets(
                        10,
                        35,
                        0,
                        10
                );

        JLabel title =
                new JLabel(
                        "DriveX"
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        title.setForeground(
                Color.WHITE
        );

        headerPanel.add(
                title,
                gbc
        );

        // =================================================
        // SUBTITLE
        // =================================================

        gbc.gridy = 1;

        gbc.insets =
                new Insets(
                        0,
                        37,
                        10,
                        10
                );

        JLabel subtitle =
                new JLabel(
                        "Reviews & Ratings"
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        subtitle.setForeground(
                new Color(
                        225,
                        220,
                        255
                )
        );

        headerPanel.add(
                subtitle,
                gbc
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );
    }

    // =====================================================
    // CENTER CONTENT
    // =====================================================

    private void createCenterContent(
            JPanel mainPanel) {

        JPanel centerPanel =
                new JPanel(
                        new BorderLayout(
                                0,
                                12
                        )
                );

        centerPanel.setBackground(
                BG_COLOR
        );

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        25,
                        10,
                        25
                )
        );

        JPanel searchCard =
                createSearchCard();

        centerPanel.add(
                searchCard,
                BorderLayout.NORTH
        );

        JPanel tableCard =
                createTableCard();

        centerPanel.add(
                tableCard,
                BorderLayout.CENTER
        );

        JPanel actionPanel =
                createActionPanel();

        centerPanel.add(
                actionPanel,
                BorderLayout.SOUTH
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
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
                                12,
                                18,
                                12,
                                18
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        // =================================================
        // TITLE
        // =================================================

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 6;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.WEST;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        10,
                        0
                );

        JLabel title =
                new JLabel(
                        "Review Search & Filter"
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

        card.add(
                title,
                gbc
        );

        // =================================================
        // SEARCH LABEL
        // =================================================

        gbc.gridy = 1;

        gbc.gridx = 0;

        gbc.gridwidth = 1;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.NONE;

        gbc.insets =
                new Insets(
                        5,
                        0,
                        5,
                        8
                );

        JLabel searchLabel =
                new JLabel(
                        "Search"
                );

        searchLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        card.add(
                searchLabel,
                gbc
        );

        // =================================================
        // SEARCH FIELD
        // =================================================

        gbc.gridx = 1;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.insets =
                new Insets(
                        5,
                        0,
                        5,
                        15
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
                        250,
                        35
                )
        );

        card.add(
                txtSearch,
                gbc
        );

        // =================================================
        // RATING LABEL
        // =================================================

        gbc.gridx = 2;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.NONE;

        gbc.insets =
                new Insets(
                        5,
                        0,
                        5,
                        8
                );

        JLabel ratingLabel =
                new JLabel(
                        "Rating"
                );

        ratingLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        13
                )
        );

        card.add(
                ratingLabel,
                gbc
        );

        // =================================================
        // RATING COMBO
        // =================================================

        gbc.gridx = 3;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.insets =
                new Insets(
                        5,
                        0,
                        5,
                        15
                );

        cmbRating =
                new JComboBox<>(
                        new String[]{
                                "All Ratings",
                                "5 Stars",
                                "4 Stars",
                                "3 Stars",
                                "2 Stars",
                                "1 Star"
                        }
                );

        cmbRating.setPreferredSize(
                new Dimension(
                        140,
                        35
                )
        );

        card.add(
                cmbRating,
                gbc
        );

        // =================================================
        // SEARCH BUTTON
        // =================================================

        gbc.gridx = 4;

        gbc.insets =
                new Insets(
                        5,
                        0,
                        5,
                        8
                );

        JButton searchButton =
                createButton(
                        "SEARCH",
                        PRIMARY
                );

        searchButton.setPreferredSize(
                new Dimension(
                        110,
                        35
                )
        );

        card.add(
                searchButton,
                gbc
        );

        // =================================================
        // SHOW ALL BUTTON
        // =================================================

        gbc.gridx = 5;

        gbc.insets =
                new Insets(
                        5,
                        0,
                        5,
                        0
                );

        JButton showAllButton =
                createButton(
                        "SHOW ALL",
                        PURPLE
                );

        showAllButton.setPreferredSize(
                new Dimension(
                        110,
                        35
                )
        );

        card.add(
                showAllButton,
                gbc
        );

        // =================================================
        // ACTIONS
        // =================================================

        searchButton.addActionListener(
                e -> searchReviews()
        );

        showAllButton.addActionListener(
                e -> {

                    txtSearch.setText("");

                    cmbRating.setSelectedIndex(0);

                    loadReviews();
                }
        );

        txtSearch.addActionListener(
                e -> searchReviews()
        );

        cmbRating.addActionListener(
                e -> searchReviews()
        );

        return card;
    }

    // =====================================================
    // TABLE CARD
    // =====================================================

    private JPanel createTableCard() {

        JPanel card =
                new JPanel(
                        new BorderLayout(
                                0,
                                8
                        )
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
                                15,
                                15
                        )
                )
        );

        JLabel title =
                new JLabel(
                        "Customer Reviews"
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

        card.add(
                title,
                BorderLayout.NORTH
        );

        // =================================================
        // TABLE
        // =================================================

        String[] columns = {

                "Review ID",
                "Booking ID",
                "Customer",
                "Car",
                "Category",
                "Rating",
                "Review",
                "Review Date"
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

        reviewTable =
                new JTable(
                        tableModel
                );

        reviewTable.setRowHeight(
                32
        );

        reviewTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        reviewTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        reviewTable.setAutoResizeMode(
                JTable.AUTO_RESIZE_LAST_COLUMN
        );

        reviewTable.setGridColor(
                BORDER
        );

        reviewTable.setShowGrid(
                true
        );

        // =================================================
        // TABLE HEADER
        // =================================================

        reviewTable
                .getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                12
                        )
                );

        reviewTable
                .getTableHeader()
                .setBackground(
                        PURPLE
                );

        reviewTable
                .getTableHeader()
                .setForeground(
                        Color.WHITE
                );

        reviewTable
                .getTableHeader()
                .setPreferredSize(
                        new Dimension(
                                0,
                                35
                        )
                );

        // =================================================
        // COLUMN WIDTHS
        // =================================================

        reviewTable
                .getColumnModel()
                .getColumn(0)
                .setPreferredWidth(75);

        reviewTable
                .getColumnModel()
                .getColumn(1)
                .setPreferredWidth(85);

        reviewTable
                .getColumnModel()
                .getColumn(2)
                .setPreferredWidth(150);

        reviewTable
                .getColumnModel()
                .getColumn(3)
                .setPreferredWidth(190);

        reviewTable
                .getColumnModel()
                .getColumn(4)
                .setPreferredWidth(90);

        reviewTable
                .getColumnModel()
                .getColumn(5)
                .setPreferredWidth(80);

        reviewTable
                .getColumnModel()
                .getColumn(6)
                .setPreferredWidth(300);

        reviewTable
                .getColumnModel()
                .getColumn(7)
                .setPreferredWidth(110);

        JScrollPane scrollPane =
                new JScrollPane(
                        reviewTable
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

        return card;
    }

    // =====================================================
    // ACTION PANEL
    // =====================================================

    private JPanel createActionPanel() {

        JPanel actionPanel =
                new JPanel(
                        new GridBagLayout()
                );

        actionPanel.setOpaque(false);

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridy = 0;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.insets =
                new Insets(
                        0,
                        5,
                        0,
                        5
                );

        // =================================================
        // VIEW
        // =================================================

        JButton viewButton =
                createButton(
                        "VIEW REVIEW",
                        GREEN
                );

        viewButton.setPreferredSize(
                new Dimension(
                        140,
                        40
                )
        );

        gbc.gridx = 0;

        actionPanel.add(
                viewButton,
                gbc
        );

        // =================================================
        // DELETE
        // =================================================

        JButton deleteButton =
                createButton(
                        "DELETE REVIEW",
                        RED
                );

        deleteButton.setPreferredSize(
                new Dimension(
                        140,
                        40
                )
        );

        gbc.gridx = 1;

        actionPanel.add(
                deleteButton,
                gbc
        );

        // =================================================
        // REFRESH
        // =================================================

        JButton refreshButton =
                createButton(
                        "REFRESH",
                        PRIMARY
                );

        refreshButton.setPreferredSize(
                new Dimension(
                        120,
                        40
                )
        );

        gbc.gridx = 2;

        actionPanel.add(
                refreshButton,
                gbc
        );

        // =================================================
        // SPACER
        // =================================================

        gbc.gridx = 3;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        actionPanel.add(
                Box.createHorizontalGlue(),
                gbc
        );

        // =================================================
        // BACK
        // =================================================

        JButton backButton =
                createButton(
                        "BACK TO DASHBOARD",
                        GRAY
                );

        backButton.setPreferredSize(
                new Dimension(
                        180,
                        40
                )
        );

        gbc.gridx = 4;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        actionPanel.add(
                backButton,
                gbc
        );

        // =================================================
        // ACTIONS
        // =================================================

        viewButton.addActionListener(
                e -> viewReview()
        );

        deleteButton.addActionListener(
                e -> deleteReview()
        );

        refreshButton.addActionListener(
                e -> loadReviews()
        );

        backButton.addActionListener(
                e -> {

                    new AdminDashboardFrame()
                            .setVisible(true);

                    dispose();
                }
        );

        return actionPanel;
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
                BG_COLOR
        );

        footerPanel.setPreferredSize(
                new Dimension(
                        0,
                        45
                )
        );

        JLabel footer =
                new JLabel(
                        "Drive • Explore • Enjoy"
                );

        footer.setFont(
                new Font(
                        "Arial",
                        Font.ITALIC,
                        12
                )
        );

        footer.setForeground(
                GRAY
        );

        footer.setHorizontalAlignment(
                SwingConstants.CENTER
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
    // LOAD REVIEWS
    // =====================================================

    private void loadReviews() {

        tableModel.setRowCount(0);

        String sql =
                "SELECT r.id AS review_id, "
                        + "r.booking_id, "
                        + "u.name AS customer, "
                        + "CONCAT(c.brand, ' ', c.model, "
                        + " ' (', c.car_number, ')') AS car, "
                        + "c.category, "
                        + "r.rating, "
                        + "r.review_text, "
                        + "r.review_date "
                        + "FROM reviews r "
                        + "JOIN users u "
                        + "ON r.user_id = u.id "
                        + "JOIN bookings b "
                        + "ON r.booking_id = b.id "
                        + "JOIN cars c "
                        + "ON b.car_id = c.id "
                        + "ORDER BY r.id DESC";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql);

                ResultSet rs =
                        ps.executeQuery()
        ) {

            while (rs.next()) {

                addReviewRow(rs);
            }

        } catch (SQLException ex) {

            showDatabaseError();
        }
    }

    // =====================================================
    // SEARCH REVIEWS
    // =====================================================

    private void searchReviews() {

        String search =
                txtSearch.getText().trim();

        String selectedRating =
                cmbRating
                        .getSelectedItem()
                        .toString();

        tableModel.setRowCount(0);

        StringBuilder sql =
                new StringBuilder(

                        "SELECT r.id AS review_id, "
                                + "r.booking_id, "
                                + "u.name AS customer, "
                                + "CONCAT(c.brand, ' ', c.model, "
                                + " ' (', c.car_number, ')') AS car, "
                                + "c.category, "
                                + "r.rating, "
                                + "r.review_text, "
                                + "r.review_date "
                                + "FROM reviews r "
                                + "JOIN users u "
                                + "ON r.user_id = u.id "
                                + "JOIN bookings b "
                                + "ON r.booking_id = b.id "
                                + "JOIN cars c "
                                + "ON b.car_id = c.id "
                                + "WHERE 1=1 "
                );

        if (!search.isEmpty()) {

            sql.append(
                    "AND ("
                            + "u.name LIKE ? "
                            + "OR c.brand LIKE ? "
                            + "OR c.model LIKE ? "
                            + "OR c.car_number LIKE ? "
                            + "OR r.review_text LIKE ? "
                            + "OR CAST(r.booking_id AS CHAR) LIKE ?"
                            + ") "
            );
        }

        if (!selectedRating.equals(
                "All Ratings")) {

            sql.append(
                    "AND r.rating=? "
            );
        }

        sql.append(
                "ORDER BY r.id DESC"
        );

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(
                                sql.toString()
                        )
        ) {

            int index = 1;

            if (!search.isEmpty()) {

                String value =
                        "%" + search + "%";

                for (int i = 0; i < 6; i++) {

                    ps.setString(
                            index++,
                            value
                    );
                }
            }

            if (!selectedRating.equals(
                    "All Ratings")) {

                int rating =
                        Integer.parseInt(
                                selectedRating
                                        .substring(
                                                0,
                                                1
                                        )
                        );

                ps.setInt(
                        index,
                        rating
                );
            }

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                while (rs.next()) {

                    addReviewRow(rs);
                }
            }

        } catch (SQLException ex) {

            showDatabaseError();
        }
    }

    // =====================================================
    // ADD TABLE ROW
    // =====================================================

    private void addReviewRow(
            ResultSet rs)
            throws SQLException {

        int rating =
                rs.getInt(
                        "rating"
                );

        String stars =
                getStars(
                        rating
                );

        tableModel.addRow(
                new Object[]{
                        rs.getInt(
                                "review_id"
                        ),

                        rs.getInt(
                                "booking_id"
                        ),

                        rs.getString(
                                "customer"
                        ),

                        rs.getString(
                                "car"
                        ),

                        rs.getString(
                                "category"
                        ),

                        stars,

                        rs.getString(
                                "review_text"
                        ),

                        rs.getDate(
                                "review_date"
                        )
                }
        );
    }

    // =====================================================
    // STAR DISPLAY
    // =====================================================

    private String getStars(
            int rating) {

        StringBuilder stars =
                new StringBuilder();

        for (int i = 1; i <= 5; i++) {

            if (i <= rating) {

                stars.append("★");

            } else {

                stars.append("☆");
            }
        }

        return stars.toString();
    }

    // =====================================================
    // VIEW REVIEW
    // =====================================================

    private void viewReview() {

        int row =
                reviewTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a review first.",
                    "Review Not Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String reviewId =
                tableModel
                        .getValueAt(
                                row,
                                0
                        )
                        .toString();

        String bookingId =
                tableModel
                        .getValueAt(
                                row,
                                1
                        )
                        .toString();

        String customer =
                tableModel
                        .getValueAt(
                                row,
                                2
                        )
                        .toString();

        String car =
                tableModel
                        .getValueAt(
                                row,
                                3
                        )
                        .toString();

        String category =
                tableModel
                        .getValueAt(
                                row,
                                4
                        )
                        .toString();

        String rating =
                tableModel
                        .getValueAt(
                                row,
                                5
                        )
                        .toString();

        String review =
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

        String message =
                "DRIVEX REVIEW DETAILS\n\n"
                        + "Review ID : "
                        + reviewId
                        + "\n"
                        + "Booking ID: "
                        + bookingId
                        + "\n\n"
                        + "Customer  : "
                        + customer
                        + "\n"
                        + "Vehicle   : "
                        + car
                        + "\n"
                        + "Category  : "
                        + category
                        + "\n"
                        + "Rating    : "
                        + rating
                        + "\n"
                        + "Date      : "
                        + date
                        + "\n\n"
                        + "Customer Review:\n"
                        + review;

        JOptionPane.showMessageDialog(
                this,
                message,
                "DriveX - Review Details",
                JOptionPane.INFORMATION_MESSAGE
        );
    }

    // =====================================================
    // DELETE REVIEW
    // =====================================================

    private void deleteReview() {

        int row =
                reviewTable.getSelectedRow();

        if (row == -1) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please select a review first.",
                    "Review Not Selected",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        int reviewId =
                Integer.parseInt(
                        tableModel
                                .getValueAt(
                                        row,
                                        0
                                )
                                .toString()
                );

        String customer =
                tableModel
                        .getValueAt(
                                row,
                                2
                        )
                        .toString();

        String review =
                tableModel
                        .getValueAt(
                                row,
                                6
                        )
                        .toString();

        int choice =
                JOptionPane.showConfirmDialog(
                        this,

                        "Are you sure you want to delete "
                                + "this review?\n\n"
                                + "Customer: "
                                + customer
                                + "\n\n"
                                + "Review: "
                                + review
                                + "\n\n"
                                + "This action cannot be undone.",

                        "Confirm Review Deletion",

                        JOptionPane.YES_NO_OPTION,

                        JOptionPane.WARNING_MESSAGE
                );

        if (choice !=
                JOptionPane.YES_OPTION) {

            return;
        }

        String sql =
                "DELETE FROM reviews WHERE id=?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    reviewId
            );

            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Review deleted successfully.",
                    "Review Deleted",
                    JOptionPane.INFORMATION_MESSAGE
            );

            loadReviews();

        } catch (SQLException ex) {

            showDatabaseError();
        }
    }

    // =====================================================
    // DATABASE ERROR
    // =====================================================

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

    // =====================================================
    // BUTTON CREATOR
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