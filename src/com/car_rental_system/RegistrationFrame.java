package com.car_rental_system;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class RegistrationFrame extends JFrame {

    JTextField nameField;
    JTextField usernameField;
    JTextField phoneField;

    JPasswordField passwordField;
    JPasswordField confirmPasswordField;

    JButton registerButton;
    JButton clearButton;
    JButton backButton;

    // =====================================================
    // COLORS
    // =====================================================

    private final Color BLUE =
            new Color(25, 118, 210);

    private final Color LIGHT_BLUE =
            new Color(235, 245, 255);

    private final Color BORDER_COLOR =
            new Color(210, 220, 230);

    private final Color DARK_TEXT =
            new Color(45, 45, 45);

    private final Color GRAY_TEXT =
            new Color(100, 100, 100);

    // =====================================================
    // CONSTRUCTOR
    // =====================================================

    RegistrationFrame() {

        setTitle("DriveX - Create Account");

        setSize(900, 720);

        setMinimumSize(
                new Dimension(780, 650)
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(true);

        createUI();

        addWindowListener(
                new WindowAdapter() {

                    @Override
                    public void windowClosing(
                            WindowEvent e) {

                        LoginFrame login =
                                new LoginFrame();

                        login.setVisible(true);
                    }
                }
        );
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
                LIGHT_BLUE
        );

        // =================================================
        // HEADER
        // =================================================

        JPanel headerPanel =
                new JPanel(
                        new GridBagLayout()
                );

        headerPanel.setBackground(
                BLUE
        );

        headerPanel.setPreferredSize(
                new Dimension(0, 115)
        );

        GridBagConstraints headerGbc =
                new GridBagConstraints();

        headerGbc.gridx = 0;
        headerGbc.gridy = 0;
        headerGbc.weightx = 1;
        headerGbc.anchor =
                GridBagConstraints.CENTER;

        JLabel title =
                new JLabel("DriveX");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        36
                )
        );

        title.setForeground(
                Color.WHITE
        );

        headerPanel.add(
                title,
                headerGbc
        );

        JLabel subtitle =
                new JLabel(
                        "Smart Mobility. Better Journeys."
                );

        subtitle.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        17
                )
        );

        subtitle.setForeground(
                Color.WHITE
        );

        headerGbc.gridy = 1;

        headerGbc.insets =
                new Insets(
                        4,
                        0,
                        0,
                        0
                );

        headerPanel.add(
                subtitle,
                headerGbc
        );

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =================================================
        // CENTER PANEL
        // =================================================

        JPanel centerPanel =
                new JPanel(
                        new GridBagLayout()
                );

        centerPanel.setOpaque(false);

        GridBagConstraints centerGbc =
                new GridBagConstraints();

        centerGbc.gridx = 0;
        centerGbc.gridy = 0;

        centerGbc.weightx = 1;
        centerGbc.weighty = 1;

        centerGbc.anchor =
                GridBagConstraints.CENTER;

        // =================================================
        // REGISTRATION CARD
        // =================================================

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
                                BORDER_COLOR,
                                1
                        ),
                        BorderFactory.createEmptyBorder(
                                22,
                                35,
                                22,
                                35
                        )
                )
        );

        card.setPreferredSize(
                new Dimension(600, 500)
        );

        // =================================================
        // CARD CONSTRAINTS
        // =================================================

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.insets =
                new Insets(
                        6,
                        8,
                        6,
                        8
                );

        // =================================================
        // CARD TITLE
        // =================================================

        JLabel cardTitle =
                new JLabel(
                        "CREATE YOUR ACCOUNT",
                        SwingConstants.CENTER
                );

        cardTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        cardTitle.setForeground(
                BLUE
        );

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 2;

        gbc.weightx = 1;

        gbc.insets =
                new Insets(
                        0,
                        8,
                        4,
                        8
                );

        card.add(
                cardTitle,
                gbc
        );

        // =================================================
        // DESCRIPTION
        // =================================================

        JLabel description =
                new JLabel(
                        "Join DriveX and start your journey",
                        SwingConstants.CENTER
                );

        description.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        description.setForeground(
                GRAY_TEXT
        );

        gbc.gridy = 1;

        gbc.insets =
                new Insets(
                        0,
                        8,
                        18,
                        8
                );

        card.add(
                description,
                gbc
        );

        // Reset grid width

        gbc.gridwidth = 1;

        gbc.insets =
                new Insets(
                        7,
                        8,
                        7,
                        8
                );

        // =================================================
        // FULL NAME
        // =================================================

        JLabel nameLabel =
                createLabel(
                        "Full Name"
                );

        gbc.gridx = 0;
        gbc.gridy = 2;

        gbc.weightx = 0.25;

        card.add(
                nameLabel,
                gbc
        );

        nameField =
                createTextField();

        gbc.gridx = 1;

        gbc.weightx = 0.75;

        card.add(
                nameField,
                gbc
        );

        // =================================================
        // USERNAME
        // =================================================

        JLabel usernameLabel =
                createLabel(
                        "Username"
                );

        gbc.gridx = 0;
        gbc.gridy = 3;

        card.add(
                usernameLabel,
                gbc
        );

        usernameField =
                createTextField();

        gbc.gridx = 1;

        card.add(
                usernameField,
                gbc
        );

        // =================================================
        // PASSWORD
        // =================================================

        JLabel passwordLabel =
                createLabel(
                        "Password"
                );

        gbc.gridx = 0;
        gbc.gridy = 4;

        card.add(
                passwordLabel,
                gbc
        );

        passwordField =
                createPasswordField();

        gbc.gridx = 1;

        card.add(
                passwordField,
                gbc
        );

        // =================================================
        // CONFIRM PASSWORD
        // =================================================

        JLabel confirmLabel =
                createLabel(
                        "Confirm Password"
                );

        gbc.gridx = 0;
        gbc.gridy = 5;

        card.add(
                confirmLabel,
                gbc
        );

        confirmPasswordField =
                createPasswordField();

        gbc.gridx = 1;

        card.add(
                confirmPasswordField,
                gbc
        );

        // =================================================
        // PHONE
        // =================================================

        JLabel phoneLabel =
                createLabel(
                        "Phone Number"
                );

        gbc.gridx = 0;
        gbc.gridy = 6;

        card.add(
                phoneLabel,
                gbc
        );

        phoneField =
                createTextField();

        gbc.gridx = 1;

        card.add(
                phoneField,
                gbc
        );

        // =================================================
        // BUTTON PANEL
        // =================================================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                3,
                                12,
                                0
                        )
                );

        buttonPanel.setOpaque(false);

        buttonPanel.setPreferredSize(
                new Dimension(
                        0,
                        45
                )
        );

        registerButton =
                createButton(
                        "CREATE ACCOUNT",
                        BLUE
                );

        clearButton =
                createButton(
                        "CLEAR",
                        new Color(
                                108,
                                117,
                                125
                        )
                );

        backButton =
                createButton(
                        "BACK TO LOGIN",
                        new Color(
                                220,
                                53,
                                69
                        )
                );

        buttonPanel.add(
                registerButton
        );

        buttonPanel.add(
                clearButton
        );

        buttonPanel.add(
                backButton
        );

        gbc.gridx = 0;
        gbc.gridy = 7;

        gbc.gridwidth = 2;

        gbc.weightx = 1;

        gbc.insets =
                new Insets(
                        18,
                        8,
                        8,
                        8
                );

        card.add(
                buttonPanel,
                gbc
        );

        // =================================================
        // INFORMATION
        // =================================================

        JLabel information =
                new JLabel(
                        "Create your DriveX account using your registered details.",
                        SwingConstants.CENTER
                );

        information.setFont(
                new Font(
                        "Arial",
                        Font.ITALIC,
                        12
                )
        );

        information.setForeground(
                new Color(
                        110,
                        110,
                        110
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 8;

        gbc.gridwidth = 2;

        gbc.insets =
                new Insets(
                        8,
                        8,
                        0,
                        8
                );

        card.add(
                information,
                gbc
        );

        // =================================================
        // ADD CARD
        // =================================================

        centerPanel.add(
                card,
                centerGbc
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =================================================
        // FOOTER
        // =================================================

        JPanel footerPanel =
                new JPanel(
                        new BorderLayout()
                );

        footerPanel.setBackground(
                LIGHT_BLUE
        );

        footerPanel.setPreferredSize(
                new Dimension(
                        0,
                        55
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
                new Color(
                        100,
                        100,
                        100
                )
        );

        footerPanel.add(
                footer,
                BorderLayout.CENTER
        );

        mainPanel.add(
                footerPanel,
                BorderLayout.SOUTH
        );

        // =================================================
        // ADD MAIN PANEL
        // =================================================

        add(mainPanel);

        // =================================================
        // BUTTON ACTIONS
        // =================================================

        registerButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        registerUser();
                    }
                }
        );

        clearButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        clearFields();
                    }
                }
        );

        backButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        goBack();
                    }
                }
        );

        // =================================================
        // ENTER KEY NAVIGATION
        // =================================================

        nameField.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        usernameField.requestFocus();
                    }
                }
        );

        usernameField.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        passwordField.requestFocus();
                    }
                }
        );

        passwordField.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        confirmPasswordField.requestFocus();
                    }
                }
        );

        confirmPasswordField.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        phoneField.requestFocus();
                    }
                }
        );

        phoneField.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        registerUser();
                    }
                }
        );
    }

    // =====================================================
    // CREATE LABEL
    // =====================================================

    private JLabel createLabel(
            String text) {

        JLabel label =
                new JLabel(text);

        label.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        label.setForeground(
                DARK_TEXT
        );

        return label;
    }

    // =====================================================
    // CREATE TEXT FIELD
    // =====================================================

    private JTextField createTextField() {

        JTextField field =
                new JTextField();

        field.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        field.setPreferredSize(
                new Dimension(
                        0,
                        36
                )
        );

        return field;
    }

    // =====================================================
    // CREATE PASSWORD FIELD
    // =====================================================

    private JPasswordField createPasswordField() {

        JPasswordField field =
                new JPasswordField();

        field.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        field.setPreferredSize(
                new Dimension(
                        0,
                        36
                )
        );

        return field;
    }

    // =====================================================
    // CREATE BUTTON
    // =====================================================

    private JButton createButton(
            String text,
            Color background) {

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
                background
        );

        button.setFocusPainted(false);

        button.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        10,
                        8,
                        10
                )
        );

        button.setCursor(
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        return button;
    }

    // =====================================================
    // REGISTER USER
    // =====================================================

    private void registerUser() {

        String name =
                nameField
                        .getText()
                        .trim();

        String username =
                usernameField
                        .getText()
                        .trim();

        String password =
                new String(
                        passwordField
                                .getPassword()
                ).trim();

        String confirmPassword =
                new String(
                        confirmPasswordField
                                .getPassword()
                ).trim();

        String phone =
                phoneField
                        .getText()
                        .trim();

        // =================================================
        // NAME VALIDATION
        // =================================================

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your full name.",
                    "DriveX - Registration",
                    JOptionPane.WARNING_MESSAGE
            );

            nameField.requestFocus();

            return;
        }

        // =================================================
        // USERNAME VALIDATION
        // =================================================

        if (username.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a username.",
                    "DriveX - Registration",
                    JOptionPane.WARNING_MESSAGE
            );

            usernameField.requestFocus();

            return;
        }

        // =================================================
        // PASSWORD VALIDATION
        // =================================================

        if (password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a password.",
                    "DriveX - Registration",
                    JOptionPane.WARNING_MESSAGE
            );

            passwordField.requestFocus();

            return;
        }

        if (password.length() < 6) {

            JOptionPane.showMessageDialog(
                    this,
                    "Password must contain at least 6 characters.",
                    "DriveX - Registration",
                    JOptionPane.WARNING_MESSAGE
            );

            passwordField.requestFocus();

            return;
        }

        // =================================================
        // CONFIRM PASSWORD
        // =================================================

        if (confirmPassword.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please confirm your password.",
                    "DriveX - Registration",
                    JOptionPane.WARNING_MESSAGE
            );

            confirmPasswordField.requestFocus();

            return;
        }

        if (!password.equals(confirmPassword)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Password and confirm password do not match.",
                    "DriveX - Registration",
                    JOptionPane.WARNING_MESSAGE
            );

            confirmPasswordField.requestFocus();

            return;
        }

        // =================================================
        // PHONE VALIDATION
        // =================================================

        if (phone.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your phone number.",
                    "DriveX - Registration",
                    JOptionPane.WARNING_MESSAGE
            );

            phoneField.requestFocus();

            return;
        }

        if (!phone.matches("\\d{10}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Phone number must contain exactly 10 digits.",
                    "DriveX - Registration",
                    JOptionPane.WARNING_MESSAGE
            );

            phoneField.requestFocus();

            return;
        }

        // =================================================
        // CHECK DUPLICATE USERNAME
        // =================================================

        String usernameQuery =
                "SELECT id FROM users WHERE username=?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(
                                usernameQuery
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
                                    + "\" is already associated "
                                    + "with a DriveX account.\n\n"
                                    + "Please choose a different username.",
                            "DriveX - Account Already Exists",
                            JOptionPane.WARNING_MESSAGE
                    );

                    usernameField.requestFocus();

                    return;
                }
            }

        } catch (Exception ex) {

            // Print the actual error in Eclipse console
            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to verify the username at this time.\n\n"
                            + "Please check your database connection "
                            + "and try again.",
                    "DriveX - Registration",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // =================================================
        // CHECK DUPLICATE PHONE
        // =================================================

        String phoneQuery =
                "SELECT id FROM users WHERE phone=?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(
                                phoneQuery
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
                                    + "The phone number you entered "
                                    + "is already associated with another "
                                    + "DriveX account.\n\n"
                                    + "Please use a different phone number.",
                            "DriveX - Account Already Exists",
                            JOptionPane.WARNING_MESSAGE
                    );

                    phoneField.requestFocus();

                    return;
                }
            }

        } catch (Exception ex) {

            // Print actual error in Eclipse console
            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "Unable to verify the phone number at this time.\n\n"
                            + "Please check your database connection "
                            + "and try again.",
                    "DriveX - Registration",
                    JOptionPane.ERROR_MESSAGE
            );

            return;
        }

        // =================================================
        // INSERT USER
        // =================================================

        String insertQuery =
                "INSERT INTO users "
                        + "(name, username, password, phone) "
                        + "VALUES (?, ?, ?, ?)";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(
                                insertQuery
                        )
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

            int rows =
                    ps.executeUpdate();

            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Account Created Successfully!\n\n"
                                + "Welcome to DriveX, "
                                + name
                                + ".\n\n"
                                + "You can now login using your "
                                + "new account credentials.",
                        "DriveX - Registration Successful",
                        JOptionPane.INFORMATION_MESSAGE
                );

                LoginFrame login =
                        new LoginFrame();

                login.setVisible(true);

                dispose();
            }

        } catch (Exception ex) {

            // =================================================
            // IMPORTANT:
            // SHOW ACTUAL ERROR IN ECLIPSE CONSOLE
            // =================================================

            ex.printStackTrace();

            JOptionPane.showMessageDialog(
                    this,
                    "We couldn't create your account right now.\n\n"
                            + "Please check your database connection "
                            + "and try again.",
                    "DriveX - Registration",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =====================================================
    // CLEAR FIELDS
    // =====================================================

    private void clearFields() {

        nameField.setText("");

        usernameField.setText("");

        passwordField.setText("");

        confirmPasswordField.setText("");

        phoneField.setText("");

        nameField.requestFocus();
    }

    // =====================================================
    // BACK TO LOGIN
    // =====================================================

    private void goBack() {

        LoginFrame login =
                new LoginFrame();

        login.setVisible(true);

        dispose();
    }
}