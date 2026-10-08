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
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

public class ForgotPasswordFrame extends JFrame {

    private LoginFrame loginFrame;

    private JTextField usernameField;
    private JTextField phoneField;

    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;

    private JButton verifyButton;
    private JButton resetButton;
    private JButton backButton;

    private JLabel statusLabel;

    private boolean userVerified = false;

    // =========================================================
    // COLORS
    // =========================================================

    private final Color PRIMARY_BLUE =
            new Color(25, 118, 210);

    private final Color LIGHT_BLUE =
            new Color(235, 245, 255);

    private final Color GREEN =
            new Color(40, 167, 69);

    private final Color RED =
            new Color(220, 53, 69);

    private final Color GRAY =
            new Color(108, 117, 125);

    private final Color TEXT_GRAY =
            new Color(100, 100, 100);

    // =========================================================
    // CONSTRUCTOR
    // =========================================================

    ForgotPasswordFrame(LoginFrame loginFrame) {

        this.loginFrame = loginFrame;

        setTitle("DriveX - Reset Password");

        setSize(900, 700);

        setMinimumSize(
                new Dimension(750, 620)
        );

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setLocationRelativeTo(null);

        setResizable(true);

        // =====================================================
        // SHOW LOGIN WHEN WINDOW IS CLOSED
        // =====================================================

        addWindowListener(
                new WindowAdapter() {

                    @Override
                    public void windowClosing(
                            WindowEvent e) {

                        loginFrame.setVisible(true);
                    }
                }
        );

        createUI();
    }

    // =========================================================
    // CREATE UI
    // =========================================================

    private void createUI() {

        JPanel mainPanel =
                new JPanel(
                        new BorderLayout()
                );

        mainPanel.setBackground(
                LIGHT_BLUE
        );

        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel =
                createHeader();

        mainPanel.add(
                headerPanel,
                BorderLayout.NORTH
        );

        // =====================================================
        // CENTER AREA
        // =====================================================

        JPanel centerPanel =
                new JPanel(
                        new GridBagLayout()
                );

        centerPanel.setBackground(
                LIGHT_BLUE
        );

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        20,
                        25,
                        20
                )
        );

        // =====================================================
        // RESET CARD
        // =====================================================

        JPanel card =
                createResetCard();

        GridBagConstraints cardGbc =
                new GridBagConstraints();

        cardGbc.gridx = 0;
        cardGbc.gridy = 0;

        cardGbc.weightx = 1;
        cardGbc.weighty = 1;

        cardGbc.fill =
                GridBagConstraints.NONE;

        centerPanel.add(
                card,
                cardGbc
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );

        // =====================================================
        // FOOTER
        // =====================================================

        JPanel footerPanel =
                createFooter();

        mainPanel.add(
                footerPanel,
                BorderLayout.SOUTH
        );

        add(mainPanel);

        // =====================================================
        // VERIFY ACTION
        // =====================================================

        verifyButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        verifyUser();
                    }
                }
        );

        // =====================================================
        // RESET ACTION
        // =====================================================

        resetButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        resetPassword();
                    }
                }
        );

        // =====================================================
        // BACK ACTION
        // =====================================================

        backButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        goBack();
                    }
                }
        );

        // =====================================================
        // ENTER KEY - USERNAME
        // =====================================================

        usernameField.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        phoneField.requestFocus();
                    }
                }
        );

        // =====================================================
        // ENTER KEY - PHONE
        // =====================================================

        phoneField.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(
                            ActionEvent e) {

                        verifyUser();
                    }
                }
        );
    }

    // =========================================================
    // HEADER
    // =========================================================

    private JPanel createHeader() {

        JPanel headerPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                1
                        )
                );

        headerPanel.setBackground(
                PRIMARY_BLUE
        );

        headerPanel.setPreferredSize(
                new Dimension(
                        0,
                        120
                )
        );

        // =====================================================
        // TITLE
        // =====================================================

        JLabel title =
                new JLabel(
                        "DriveX",
                        SwingConstants.CENTER
                );

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

        // =====================================================
        // SUBTITLE
        // =====================================================

        JLabel subtitle =
                new JLabel(
                        "Smart Mobility. Better Journeys.",
                        SwingConstants.CENTER
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

        headerPanel.add(title);
        headerPanel.add(subtitle);

        return headerPanel;
    }

    // =========================================================
    // RESET CARD
    // =========================================================

    private JPanel createResetCard() {

        JPanel card =
                new JPanel(
                        new GridBagLayout()
                );

        card.setBackground(
                Color.WHITE
        );

        card.setPreferredSize(
                new Dimension(
                        560,
                        500
                )
        );

        card.setMaximumSize(
                new Dimension(
                        650,
                        520
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
                                20,
                                35,
                                20,
                                35
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.insets =
                new Insets(
                        6,
                        6,
                        6,
                        6
                );

        gbc.weightx = 1;

        // =====================================================
        // CARD TITLE
        // =====================================================

        JLabel cardTitle =
                new JLabel(
                        "RESET PASSWORD",
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
                PRIMARY_BLUE
        );

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 2;

        gbc.insets =
                new Insets(
                        0,
                        5,
                        4,
                        5
                );

        card.add(
                cardTitle,
                gbc
        );

        // =====================================================
        // DESCRIPTION
        // =====================================================

        JLabel description =
                new JLabel(
                        "Verify your account to create a new password",
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
                TEXT_GRAY
        );

        gbc.gridy = 1;

        gbc.insets =
                new Insets(
                        0,
                        5,
                        18,
                        5
                );

        card.add(
                description,
                gbc
        );

        // =====================================================
        // USERNAME
        // =====================================================

        JLabel usernameLabel =
                createLabel("Username");

        usernameField =
                new JTextField();

        styleTextField(
                usernameField
        );

        addFormRow(
                card,
                gbc,
                usernameLabel,
                usernameField,
                2
        );

        // =====================================================
        // PHONE
        // =====================================================

        JLabel phoneLabel =
                createLabel(
                        "Registered Phone"
                );

        phoneField =
                new JTextField();

        styleTextField(
                phoneField
        );

        addFormRow(
                card,
                gbc,
                phoneLabel,
                phoneField,
                3
        );

        // =====================================================
        // VERIFY BUTTON
        // =====================================================

        verifyButton =
                createButton(
                        "VERIFY",
                        PRIMARY_BLUE
                );

        gbc.gridx = 1;
        gbc.gridy = 4;

        gbc.gridwidth = 1;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.NONE;

        gbc.anchor =
                GridBagConstraints.EAST;

        gbc.insets =
                new Insets(
                        4,
                        6,
                        4,
                        6
                );

        card.add(
                verifyButton,
                gbc
        );

        // =====================================================
        // STATUS
        // =====================================================

        statusLabel =
                new JLabel(
                        "",
                        SwingConstants.CENTER
                );

        statusLabel.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        statusLabel.setPreferredSize(
                new Dimension(
                        0,
                        25
                )
        );

        gbc.gridx = 0;
        gbc.gridy = 5;

        gbc.gridwidth = 2;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.CENTER;

        card.add(
                statusLabel,
                gbc
        );

        // =====================================================
        // NEW PASSWORD
        // =====================================================

        JLabel newPasswordLabel =
                createLabel(
                        "New Password"
                );

        newPasswordField =
                new JPasswordField();

        stylePasswordField(
                newPasswordField
        );

        newPasswordField.setEnabled(false);

        addFormRow(
                card,
                gbc,
                newPasswordLabel,
                newPasswordField,
                6
        );

        // =====================================================
        // CONFIRM PASSWORD
        // =====================================================

        JLabel confirmPasswordLabel =
                createLabel(
                        "Confirm Password"
                );

        confirmPasswordField =
                new JPasswordField();

        stylePasswordField(
                confirmPasswordField
        );

        confirmPasswordField.setEnabled(false);

        addFormRow(
                card,
                gbc,
                confirmPasswordLabel,
                confirmPasswordField,
                7
        );

        // =====================================================
        // BUTTON PANEL
        // =====================================================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                12,
                                0
                        )
                );

        buttonPanel.setOpaque(false);

        // =====================================================
        // RESET BUTTON
        // =====================================================

        resetButton =
                createButton(
                        "RESET PASSWORD",
                        GREEN
                );

        resetButton.setEnabled(false);

        // =====================================================
        // BACK BUTTON
        // =====================================================

        backButton =
                createButton(
                        "BACK TO LOGIN",
                        GRAY
                );

        buttonPanel.add(
                resetButton
        );

        buttonPanel.add(
                backButton
        );

        gbc.gridx = 0;
        gbc.gridy = 8;

        gbc.gridwidth = 2;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        gbc.insets =
                new Insets(
                        18,
                        6,
                        8,
                        6
                );

        card.add(
                buttonPanel,
                gbc
        );

        // =====================================================
        // INFORMATION
        // =====================================================

        JLabel information =
                new JLabel(
                        "Use your registered username and phone number.",
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
        gbc.gridy = 9;

        gbc.gridwidth = 2;

        gbc.insets =
                new Insets(
                        8,
                        5,
                        0,
                        5
                );

        card.add(
                information,
                gbc
        );

        return card;
    }

    // =========================================================
    // FORM ROW
    // =========================================================

    private void addFormRow(
            JPanel panel,
            GridBagConstraints gbc,
            JLabel label,
            JTextField field,
            int row) {

        // =====================================================
        // LABEL
        // =====================================================

        gbc.gridy = row;

        gbc.gridwidth = 1;

        gbc.gridx = 0;

        gbc.weightx = 0.25;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.WEST;

        gbc.insets =
                new Insets(
                        6,
                        6,
                        6,
                        12
                );

        panel.add(
                label,
                gbc
        );

        // =====================================================
        // FIELD
        // =====================================================

        gbc.gridx = 1;

        gbc.weightx = 0.75;

        gbc.insets =
                new Insets(
                        6,
                        0,
                        6,
                        6
                );

        panel.add(
                field,
                gbc
        );
    }

    // =========================================================
    // CREATE LABEL
    // =========================================================

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
                new Color(
                        45,
                        45,
                        45
                )
        );

        return label;
    }

    // =========================================================
    // STYLE TEXT FIELD
    // =========================================================

    private void styleTextField(
            JTextField field) {

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

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        190,
                                        200,
                                        210
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                5,
                                8,
                                5,
                                8
                        )
                )
        );
    }

    // =========================================================
    // STYLE PASSWORD FIELD
    // =========================================================

    private void stylePasswordField(
            JPasswordField field) {

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

        // IMPORTANT:
        // Comma added between createLineBorder()
        // and createEmptyBorder()

        field.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        190,
                                        200,
                                        210
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                5,
                                8,
                                5,
                                8
                        )
                )
        );
    }

    // =========================================================
    // CREATE BUTTON
    // =========================================================

    private JButton createButton(
            String text,
            Color background) {

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
                background
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
                new Cursor(
                        Cursor.HAND_CURSOR
                )
        );

        button.setPreferredSize(
                new Dimension(
                        170,
                        40
                )
        );

        return button;
    }

    // =========================================================
    // FOOTER
    // =========================================================

    private JPanel createFooter() {

        JPanel footerPanel =
                new JPanel(
                        new java.awt.FlowLayout(
                                java.awt.FlowLayout.CENTER
                        )
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
                TEXT_GRAY
        );

        footerPanel.add(
                footer
        );

        return footerPanel;
    }

    // =========================================================
    // VERIFY USER
    // =========================================================

    private void verifyUser() {

        String username =
                usernameField
                        .getText()
                        .trim();

        String phone =
                phoneField
                        .getText()
                        .trim();

        // =====================================================
        // USERNAME VALIDATION
        // =====================================================

        if (username.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your username.",
                    "DriveX",
                    JOptionPane.WARNING_MESSAGE
            );

            usernameField.requestFocus();

            return;
        }

        // =====================================================
        // PHONE VALIDATION
        // =====================================================

        if (phone.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter your registered phone number.",
                    "DriveX",
                    JOptionPane.WARNING_MESSAGE
            );

            phoneField.requestFocus();

            return;
        }

        if (!phone.matches("\\d{10}")) {

            JOptionPane.showMessageDialog(
                    this,
                    "Phone number must contain exactly 10 digits.",
                    "DriveX",
                    JOptionPane.WARNING_MESSAGE
            );

            phoneField.requestFocus();

            return;
        }

        // =====================================================
        // DATABASE QUERY
        // =====================================================

        String query =
                "SELECT id FROM users "
                        + "WHERE username=? AND phone=?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(query)
        ) {

            ps.setString(
                    1,
                    username
            );

            ps.setString(
                    2,
                    phone
            );

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    userVerified = true;

                    statusLabel.setText(
                            "Account verified successfully."
                    );

                    statusLabel.setForeground(
                            GREEN
                    );

                    usernameField.setEnabled(false);

                    phoneField.setEnabled(false);

                    verifyButton.setEnabled(false);

                    newPasswordField.setEnabled(true);

                    confirmPasswordField.setEnabled(true);

                    resetButton.setEnabled(true);

                    newPasswordField.requestFocus();

                    JOptionPane.showMessageDialog(
                            this,
                            "Account verified successfully!\n"
                                    + "You can now create a new password.",
                            "DriveX",
                            JOptionPane.INFORMATION_MESSAGE
                    );

                } else {

                    userVerified = false;

                    statusLabel.setText(
                            "Username and phone number do not match."
                    );

                    statusLabel.setForeground(
                            RED
                    );

                    JOptionPane.showMessageDialog(
                            this,
                            "Username and registered phone number do not match.",
                            "Verification Failed",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database Error:\n"
                            + ex.getMessage(),
                    "DriveX",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // RESET PASSWORD
    // =========================================================

    private void resetPassword() {

        if (!userVerified) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please verify your account first.",
                    "DriveX",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        String newPassword =
                new String(
                        newPasswordField.getPassword()
                ).trim();

        String confirmPassword =
                new String(
                        confirmPasswordField.getPassword()
                ).trim();

        // =====================================================
        // NEW PASSWORD
        // =====================================================

        if (newPassword.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a new password.",
                    "DriveX",
                    JOptionPane.WARNING_MESSAGE
            );

            newPasswordField.requestFocus();

            return;
        }

        // =====================================================
        // PASSWORD LENGTH
        // =====================================================

        if (newPassword.length() < 6) {

            JOptionPane.showMessageDialog(
                    this,
                    "Password must contain at least 6 characters.",
                    "DriveX",
                    JOptionPane.WARNING_MESSAGE
            );

            newPasswordField.requestFocus();

            return;
        }

        // =====================================================
        // CONFIRM PASSWORD
        // =====================================================

        if (confirmPassword.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please confirm your new password.",
                    "DriveX",
                    JOptionPane.WARNING_MESSAGE
            );

            confirmPasswordField.requestFocus();

            return;
        }

        // =====================================================
        // PASSWORD MATCH
        // =====================================================

        if (!newPassword.equals(confirmPassword)) {

            JOptionPane.showMessageDialog(
                    this,
                    "Passwords do not match.",
                    "DriveX",
                    JOptionPane.WARNING_MESSAGE
            );

            confirmPasswordField.requestFocus();

            return;
        }

        String username =
                usernameField
                        .getText()
                        .trim();

        String phone =
                phoneField
                        .getText()
                        .trim();

        // =====================================================
        // UPDATE PASSWORD
        // =====================================================

        String query =
                "UPDATE users "
                        + "SET password=? "
                        + "WHERE username=? AND phone=?";

        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(query)
        ) {

            ps.setString(
                    1,
                    newPassword
            );

            ps.setString(
                    2,
                    username
            );

            ps.setString(
                    3,
                    phone
            );

            int rows =
                    ps.executeUpdate();

            if (rows > 0) {

                JOptionPane.showMessageDialog(
                        this,
                        "Password reset successfully!\n\n"
                                + "You can now login with your new password.",
                        "DriveX",
                        JOptionPane.INFORMATION_MESSAGE
                );

                loginFrame.setVisible(true);

                dispose();

            } else {

                JOptionPane.showMessageDialog(
                        this,
                        "Password could not be updated.",
                        "DriveX",
                        JOptionPane.ERROR_MESSAGE
                );
            }

        } catch (Exception ex) {

            JOptionPane.showMessageDialog(
                    this,
                    "Database Error:\n"
                            + ex.getMessage(),
                    "DriveX",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // BACK TO LOGIN
    // =========================================================

    private void goBack() {

        loginFrame.setVisible(true);

        dispose();
    }
}