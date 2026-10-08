package com.car_rental_system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class AdminLoginFrame extends JFrame {

    private LoginFrame loginFrame;

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton clearButton;
    private JButton backButton;

    // =====================================================
    // COLORS
    // =====================================================

    private final Color ADMIN_PURPLE =
            new Color(108, 76, 180);

    private final Color DARK_PURPLE =
            new Color(67, 56, 140);

    private final Color BACKGROUND_COLOR =
            new Color(235, 245, 255);

    private final Color GRAY =
            new Color(108, 117, 125);

    private final Color RED =
            new Color(220, 53, 69);

    private final Color DARK_TEXT =
            new Color(45, 45, 45);

    private final Color GRAY_TEXT =
            new Color(100, 100, 100);

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    public AdminLoginFrame(LoginFrame loginFrame) {

        this.loginFrame = loginFrame;

        setTitle("DriveX - Admin Login");

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
    // CREATE UI
    // =====================================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                BACKGROUND_COLOR
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
                DARK_PURPLE
        );

        headerPanel.setPreferredSize(
                new Dimension(
                        0,
                        125
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.anchor =
                GridBagConstraints.CENTER;

        gbc.insets =
                new Insets(
                        12,
                        10,
                        0,
                        10
                );

        JLabel titleLabel =
                new JLabel("DriveX");

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        36
                )
        );

        titleLabel.setForeground(
                Color.WHITE
        );

        headerPanel.add(
                titleLabel,
                gbc
        );

        // =================================================
        // SUBTITLE
        // =================================================

        gbc.gridy = 1;

        gbc.insets =
                new Insets(
                        0,
                        10,
                        12,
                        10
                );

        JLabel subtitleLabel =
                new JLabel(
                        "Smart Mobility. Better Journeys."
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

        headerPanel.add(
                subtitleLabel,
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
                        new GridBagLayout()
                );

        centerPanel.setBackground(
                BACKGROUND_COLOR
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.weightx = 1.0;
        gbc.weighty = 1.0;

        gbc.anchor =
                GridBagConstraints.CENTER;

        gbc.fill =
                GridBagConstraints.NONE;

        gbc.insets =
                new Insets(
                        20,
                        20,
                        20,
                        20
                );

        JPanel card =
                createLoginCard();

        centerPanel.add(
                card,
                gbc
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );
    }

    // =====================================================
    // LOGIN CARD
    // =====================================================

    private JPanel createLoginCard() {

        JPanel card =
                new JPanel(
                        new GridBagLayout()
                );

        card.setBackground(
                Color.WHITE
        );

        card.setPreferredSize(
                new Dimension(
                        540,
                        400
                )
        );

        card.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(
                                        210,
                                        220,
                                        230
                                ),
                                1
                        ),

                        BorderFactory.createEmptyBorder(
                                25,
                                35,
                                25,
                                35
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1.0;

        // =================================================
        // CARD TITLE
        // =================================================

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 3;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        5,
                        0
                );

        JLabel cardTitle =
                new JLabel(
                        "ADMIN LOGIN"
                );

        cardTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        cardTitle.setForeground(
                ADMIN_PURPLE
        );

        cardTitle.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        card.add(
                cardTitle,
                gbc
        );

        // =================================================
        // DESCRIPTION
        // =================================================

        gbc.gridy = 1;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        25,
                        0
                );

        JLabel description =
                new JLabel(
                        "Authorized access to DriveX administration"
                );

        description.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        description.setForeground(
                GRAY_TEXT
        );

        description.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        card.add(
                description,
                gbc
        );

        // =================================================
        // USERNAME LABEL
        // =================================================

        gbc.gridwidth = 1;

        gbc.gridx = 0;
        gbc.gridy = 2;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.NONE;

        gbc.anchor =
                GridBagConstraints.WEST;

        gbc.insets =
                new Insets(
                        8,
                        0,
                        8,
                        15
                );

        JLabel usernameLabel =
                new JLabel(
                        "Username"
                );

        usernameLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        usernameLabel.setForeground(
                DARK_TEXT
        );

        card.add(
                usernameLabel,
                gbc
        );

        // =================================================
        // USERNAME FIELD
        // =================================================

        gbc.gridx = 1;

        gbc.gridwidth = 2;

        gbc.weightx = 1.0;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.insets =
                new Insets(
                        8,
                        0,
                        8,
                        0
                );

        usernameField =
                new JTextField();

        usernameField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        usernameField.setPreferredSize(
                new Dimension(
                        0,
                        38
                )
        );

        card.add(
                usernameField,
                gbc
        );

        // =================================================
        // PASSWORD LABEL
        // =================================================

        gbc.gridx = 0;
        gbc.gridy = 3;

        gbc.gridwidth = 1;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.NONE;

        gbc.anchor =
                GridBagConstraints.WEST;

        gbc.insets =
                new Insets(
                        8,
                        0,
                        8,
                        15
                );

        JLabel passwordLabel =
                new JLabel(
                        "Password"
                );

        passwordLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        passwordLabel.setForeground(
                DARK_TEXT
        );

        card.add(
                passwordLabel,
                gbc
        );

        // =================================================
        // PASSWORD FIELD
        // =================================================

        gbc.gridx = 1;

        gbc.gridwidth = 2;

        gbc.weightx = 1.0;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.insets =
                new Insets(
                        8,
                        0,
                        8,
                        0
                );

        passwordField =
                new JPasswordField();

        passwordField.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        passwordField.setPreferredSize(
                new Dimension(
                        0,
                        38
                )
        );

        card.add(
                passwordField,
                gbc
        );

        // =================================================
        // BUTTON PANEL
        // =================================================

        JPanel buttonPanel =
                new JPanel(
                        new GridBagLayout()
                );

        buttonPanel.setOpaque(false);

        GridBagConstraints buttonGbc =
                new GridBagConstraints();

        buttonGbc.gridy = 0;

        buttonGbc.fill =
                GridBagConstraints.HORIZONTAL;

        buttonGbc.weightx = 1.0;

        buttonGbc.insets =
                new Insets(
                        15,
                        5,
                        10,
                        5
                );

        // =================================================
        // LOGIN BUTTON
        // =================================================

        loginButton =
                createButton(
                        "ADMIN LOGIN",
                        ADMIN_PURPLE
                );

        loginButton.setPreferredSize(
                new Dimension(
                        170,
                        45
                )
        );

        buttonGbc.gridx = 0;

        buttonPanel.add(
                loginButton,
                buttonGbc
        );

        // =================================================
        // CLEAR BUTTON
        // =================================================

        clearButton =
                createButton(
                        "CLEAR",
                        GRAY
                );

        clearButton.setPreferredSize(
                new Dimension(
                        110,
                        45
                )
        );

        buttonGbc.gridx = 1;

        buttonPanel.add(
                clearButton,
                buttonGbc
        );

        // =================================================
        // BACK BUTTON
        // =================================================

        backButton =
                createButton(
                        "BACK",
                        RED
                );

        backButton.setPreferredSize(
                new Dimension(
                        110,
                        45
                )
        );

        buttonGbc.gridx = 2;

        buttonPanel.add(
                backButton,
                buttonGbc
        );

        // =================================================
        // ADD BUTTON PANEL
        // =================================================

        gbc.gridx = 0;
        gbc.gridy = 4;

        gbc.gridwidth = 3;

        gbc.weightx = 1.0;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.insets =
                new Insets(
                        5,
                        0,
                        10,
                        0
                );

        card.add(
                buttonPanel,
                gbc
        );

        // =================================================
        // SECURITY MESSAGE
        // =================================================

        gbc.gridy = 5;

        gbc.insets =
                new Insets(
                        8,
                        0,
                        0,
                        0
                );

        JLabel securityLabel =
                new JLabel(
                        "Authorized administrators only"
                );

        securityLabel.setFont(
                new Font(
                        "Arial",
                        Font.ITALIC,
                        12
                )
        );

        securityLabel.setForeground(
                GRAY_TEXT
        );

        securityLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        card.add(
                securityLabel,
                gbc
        );

        // =================================================
        // ACTIONS
        // =================================================

        addActions();

        return card;
    }

    // =====================================================
    // ACTION LISTENERS
    // =====================================================

    private void addActions() {

        // ADMIN LOGIN

        loginButton.addActionListener(
                e -> adminLogin()
        );

        // CLEAR

        clearButton.addActionListener(
                e -> {

                    usernameField.setText("");

                    passwordField.setText("");

                    usernameField.requestFocus();
                }
        );

        // BACK

        backButton.addActionListener(
                e -> goBack()
        );

        // ENTER FROM USERNAME

        usernameField.addActionListener(
                e -> passwordField.requestFocus()
        );

        // ENTER FROM PASSWORD

        passwordField.addActionListener(
                e -> adminLogin()
        );
    }

    // =====================================================
    // ADMIN LOGIN
    // =====================================================

    private void adminLogin() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(
                        passwordField.getPassword()
                ).trim();

        if (username.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the admin username.",
                    "DriveX - Admin Login",
                    JOptionPane.WARNING_MESSAGE
            );

            usernameField.requestFocus();

            return;
        }

        if (password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter the admin password.",
                    "DriveX - Admin Login",
                    JOptionPane.WARNING_MESSAGE
            );

            passwordField.requestFocus();

            return;
        }

        String query =
                "SELECT id FROM admin "
                        + "WHERE username=? AND password=?";

        try (
                java.sql.Connection con =
                        DBConnection.getConnection();

                java.sql.PreparedStatement ps =
                        con.prepareStatement(query)
        ) {

            ps.setString(
                    1,
                    username
            );

            ps.setString(
                    2,
                    password
            );

            try (
                    java.sql.ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    JOptionPane.showMessageDialog(
                            this,
                            "Welcome to the DriveX Admin Panel.",
                            "DriveX - Login Successful",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                    AdminDashboardFrame adminDashboard =
                            new AdminDashboardFrame();

                    adminDashboard.setVisible(true);

                    dispose();

                } else {

                    JOptionPane.showMessageDialog(
                            this,
                            "Invalid admin username or password.\n\n"
                                    + "Please verify your credentials "
                                    + "and try again.",
                            "DriveX - Invalid Credentials",
                            JOptionPane.ERROR_MESSAGE
                    );

                    passwordField.setText("");

                    passwordField.requestFocus();
                }
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to connect to the DriveX database.\n\n"
                            + "Please make sure MySQL is running "
                            + "and try again.",
                    "DriveX - Database Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // BACK TO USER LOGIN
    // =====================================================

    private void goBack() {

        if (loginFrame != null) {

            loginFrame.setVisible(true);

        } else {

            LoginFrame login =
                    new LoginFrame();

            login.setVisible(true);
        }

        dispose();
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
                BACKGROUND_COLOR
        );

        footerPanel.setPreferredSize(
                new Dimension(
                        0,
                        55
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
                        13
                )
        );

        footer.setForeground(
                GRAY_TEXT
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
                        13
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
                BorderFactory.createEmptyBorder()
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }
}