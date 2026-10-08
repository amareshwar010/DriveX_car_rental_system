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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class LoginFrame extends JFrame {

    private JTextField usernameField;
    private JPasswordField passwordField;

    private JButton loginButton;
    private JButton clearButton;
    private JButton exitButton;
    private JButton signUpButton;
    private JButton adminButton;
    private JButton forgotPasswordButton;


    public LoginFrame() {

        setTitle("DriveX - Login");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(900, 700);
        setMinimumSize(new Dimension(760, 620));

        setLocationRelativeTo(null);

        setResizable(true);

        getContentPane().setBackground(new Color(242, 247, 255));

        createUI();

        addActions();
    }


    // =========================================================
    // MAIN UI
    // =========================================================

    private void createUI() {

        setLayout(new BorderLayout());


        // =====================================================
        // HEADER
        // =====================================================

        JPanel headerPanel = new JPanel(new GridBagLayout());

        headerPanel.setBackground(new Color(25, 118, 210));

        headerPanel.setPreferredSize(new Dimension(900, 115));


        GridBagConstraints headerGbc = new GridBagConstraints();

        headerGbc.gridx = 0;
        headerGbc.gridy = 0;
        headerGbc.anchor = GridBagConstraints.CENTER;


        JLabel titleLabel = new JLabel("DriveX");

        titleLabel.setForeground(Color.WHITE);

        titleLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 34)
        );

        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);


        headerGbc.insets = new Insets(5, 10, 0, 10);

        headerPanel.add(titleLabel, headerGbc);


        JLabel subtitleLabel = new JLabel(
                "Smart Mobility. Better Journeys."
        );

        subtitleLabel.setForeground(new Color(225, 240, 255));

        subtitleLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 15)
        );

        subtitleLabel.setHorizontalAlignment(SwingConstants.CENTER);


        headerGbc.gridy = 1;

        headerGbc.insets = new Insets(0, 10, 5, 10);

        headerPanel.add(subtitleLabel, headerGbc);


        add(headerPanel, BorderLayout.NORTH);


        // =====================================================
        // CENTER AREA
        // =====================================================

        JPanel centerPanel = new JPanel(new GridBagLayout());

        centerPanel.setBackground(new Color(242, 247, 255));


        JPanel loginCard = createLoginCard();


        GridBagConstraints cardGbc = new GridBagConstraints();

        cardGbc.gridx = 0;
        cardGbc.gridy = 0;

        cardGbc.anchor = GridBagConstraints.CENTER;

        cardGbc.insets = new Insets(25, 25, 25, 25);


        centerPanel.add(loginCard, cardGbc);


        add(centerPanel, BorderLayout.CENTER);


        // =====================================================
        // FOOTER
        // =====================================================

        JPanel footerPanel = new JPanel(new GridBagLayout());

        footerPanel.setBackground(new Color(230, 238, 250));

        footerPanel.setPreferredSize(new Dimension(900, 55));


        JLabel footerLabel = new JLabel(
                "Drive • Explore • Enjoy"
        );

        footerLabel.setForeground(new Color(70, 90, 115));

        footerLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );


        footerPanel.add(footerLabel);


        add(footerPanel, BorderLayout.SOUTH);
    }


    // =========================================================
    // LOGIN CARD
    // =========================================================

    private JPanel createLoginCard() {

        JPanel card = new JPanel(new GridBagLayout());

        card.setBackground(Color.WHITE);

        card.setPreferredSize(new Dimension(520, 470));

        card.setMinimumSize(new Dimension(480, 450));

        card.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(210, 220, 235),
                                1
                        ),

                        BorderFactory.createEmptyBorder(
                                25, 35, 25, 35
                        )
                )
        );


        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1.0;

        gbc.insets =
                new Insets(6, 0, 6, 0);


        // =====================================================
        // TITLE
        // =====================================================

        JLabel titleLabel =
                new JLabel("WELCOME BACK");

        titleLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 24)
        );

        titleLabel.setForeground(
                new Color(35, 55, 80)
        );

        titleLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 2;

        gbc.insets =
                new Insets(0, 0, 4, 0);

        card.add(titleLabel, gbc);


        // =====================================================
        // DESCRIPTION
        // =====================================================

        JLabel descriptionLabel =
                new JLabel(
                        "Login to continue your journey"
                );

        descriptionLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        descriptionLabel.setForeground(
                new Color(110, 120, 135)
        );

        descriptionLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        gbc.gridy = 1;

        gbc.insets =
                new Insets(0, 0, 18, 0);

        card.add(descriptionLabel, gbc);


        // =====================================================
        // USERNAME LABEL
        // =====================================================

        JLabel usernameLabel =
                new JLabel("Username");

        usernameLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        usernameLabel.setForeground(
                new Color(55, 70, 90)
        );


        gbc.gridy = 2;

        gbc.gridwidth = 2;

        gbc.insets =
                new Insets(5, 0, 4, 0);

        card.add(usernameLabel, gbc);


        // =====================================================
        // USERNAME FIELD
        // =====================================================

        usernameField = new JTextField();

        usernameField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        usernameField.setPreferredSize(
                new Dimension(300, 42)
        );

        usernameField.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(190, 205, 225),
                                1
                        ),

                        BorderFactory.createEmptyBorder(
                                5, 10, 5, 10
                        )
                )
        );


        gbc.gridy = 3;

        gbc.insets =
                new Insets(0, 0, 10, 0);

        card.add(usernameField, gbc);


        // =====================================================
        // PASSWORD LABEL
        // =====================================================

        JLabel passwordLabel =
                new JLabel("Password");

        passwordLabel.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        passwordLabel.setForeground(
                new Color(55, 70, 90)
        );


        gbc.gridy = 4;

        gbc.insets =
                new Insets(3, 0, 4, 0);

        card.add(passwordLabel, gbc);


        // =====================================================
        // PASSWORD FIELD
        // =====================================================

        passwordField = new JPasswordField();

        passwordField.setFont(
                new Font("Segoe UI", Font.PLAIN, 14)
        );

        passwordField.setPreferredSize(
                new Dimension(300, 42)
        );

        passwordField.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                new Color(190, 205, 225),
                                1
                        ),

                        BorderFactory.createEmptyBorder(
                                5, 10, 5, 10
                        )
                )
        );


        gbc.gridy = 5;

        gbc.insets =
                new Insets(0, 0, 15, 0);

        card.add(passwordField, gbc);


        // =====================================================
        // MAIN BUTTONS
        // =====================================================

        JPanel mainButtonPanel =
                new JPanel(new GridLayout(1, 3, 10, 0));

        mainButtonPanel.setOpaque(false);


        loginButton = createButton(
                "LOGIN",
                new Color(25, 118, 210),
                Color.WHITE
        );


        clearButton = createButton(
                "CLEAR",
                new Color(245, 166, 35),
                Color.WHITE
        );


        exitButton = createButton(
                "EXIT",
                new Color(220, 53, 69),
                Color.WHITE
        );


        mainButtonPanel.add(loginButton);

        mainButtonPanel.add(clearButton);

        mainButtonPanel.add(exitButton);


        gbc.gridy = 6;

        gbc.insets =
                new Insets(0, 0, 15, 0);

        card.add(mainButtonPanel, gbc);


        // =====================================================
        // ACCOUNT LABEL
        // =====================================================

        JLabel accountLabel =
                new JLabel("Don't have an account?");

        accountLabel.setFont(
                new Font("Segoe UI", Font.PLAIN, 13)
        );

        accountLabel.setForeground(
                new Color(100, 110, 125)
        );

        accountLabel.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        gbc.gridy = 7;

        gbc.insets =
                new Insets(0, 0, 7, 0);

        card.add(accountLabel, gbc);


        // =====================================================
        // SIGN UP + ADMIN LOGIN
        // =====================================================

        JPanel secondaryPanel =
                new JPanel(new GridLayout(1, 2, 10, 0));

        secondaryPanel.setOpaque(false);


        signUpButton = createOutlineButton(
                "SIGN UP",
                new Color(25, 118, 210)
        );


        adminButton = createButton(
                "ADMIN LOGIN",
                new Color(123, 63, 180),
                Color.WHITE
        );


        secondaryPanel.add(signUpButton);

        secondaryPanel.add(adminButton);


        gbc.gridy = 8;

        gbc.insets =
                new Insets(0, 0, 10, 0);

        card.add(secondaryPanel, gbc);


        // =====================================================
        // FORGOT PASSWORD
        // =====================================================

        forgotPasswordButton =
                createOutlineButton(
                        "FORGOT PASSWORD?",
                        new Color(25, 118, 210)
                );


        gbc.gridy = 9;

        gbc.insets =
                new Insets(0, 0, 0, 0);

        card.add(forgotPasswordButton, gbc);


        return card;
    }


    // =========================================================
    // NORMAL BUTTON
    // =========================================================

    private JButton createButton(
            String text,
            Color background,
            Color foreground) {

        JButton button = new JButton(text);

        button.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        button.setForeground(foreground);

        button.setBackground(background);

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setOpaque(true);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        button.setPreferredSize(
                new Dimension(130, 40)
        );

        return button;
    }


    // =========================================================
    // OUTLINE BUTTON
    // =========================================================

    private JButton createOutlineButton(
            String text,
            Color color) {

        JButton button = new JButton(text);

        button.setFont(
                new Font("Segoe UI", Font.BOLD, 13)
        );

        button.setForeground(color);

        button.setBackground(Color.WHITE);

        button.setFocusPainted(false);

        button.setCursor(
                new Cursor(Cursor.HAND_CURSOR)
        );

        button.setBorder(
                BorderFactory.createCompoundBorder(

                        BorderFactory.createLineBorder(
                                color,
                                1
                        ),

                        BorderFactory.createEmptyBorder(
                                8, 12, 8, 12
                        )
                )
        );

        button.setPreferredSize(
                new Dimension(130, 40)
        );

        return button;
    }


    // =========================================================
    // ACTIONS
    // =========================================================

    private void addActions() {


        // LOGIN
        loginButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        loginUser();
                    }
                }
        );


        // CLEAR
        clearButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        usernameField.setText("");

                        passwordField.setText("");

                        usernameField.requestFocus();
                    }
                }
        );


        // EXIT
        exitButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        int choice =
                                javax.swing.JOptionPane.showConfirmDialog(

                                        LoginFrame.this,

                                        "Are you sure you want to exit DriveX?",

                                        "Exit DriveX",

                                        javax.swing.JOptionPane.YES_NO_OPTION,

                                        javax.swing.JOptionPane.QUESTION_MESSAGE
                                );


                        if (choice ==
                                javax.swing.JOptionPane.YES_OPTION) {

                            System.exit(0);
                        }
                    }
                }
        );


        // SIGN UP
        signUpButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        RegistrationFrame registration =
                                new RegistrationFrame();

                        registration.setVisible(true);

                        dispose();
                    }
                }
        );


        // ADMIN LOGIN
        adminButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        AdminLoginFrame adminLogin =
                                new AdminLoginFrame(LoginFrame.this);

                        adminLogin.setVisible(true);

                        setVisible(false);
                    }
                }
        );


        // FORGOT PASSWORD
        forgotPasswordButton.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        ForgotPasswordFrame forgotPassword =
                                new ForgotPasswordFrame(LoginFrame.this);

                        forgotPassword.setVisible(true);

                        setVisible(false);
                    }
                }
        );


        // ENTER KEY
        passwordField.addActionListener(
                new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        loginUser();
                    }
                }
        );
    }


    // =========================================================
    // LOGIN USER
    // =========================================================

    private void loginUser() {

        String username =
                usernameField.getText().trim();

        String password =
                new String(passwordField.getPassword());


        // -----------------------------------------------------
        // VALIDATION
        // -----------------------------------------------------

        if (username.isEmpty()) {

            javax.swing.JOptionPane.showMessageDialog(

                    this,

                    "Please enter your username.",

                    "Login Required",

                    javax.swing.JOptionPane.WARNING_MESSAGE
            );

            usernameField.requestFocus();

            return;
        }


        if (password.isEmpty()) {

            javax.swing.JOptionPane.showMessageDialog(

                    this,

                    "Please enter your password.",

                    "Login Required",

                    javax.swing.JOptionPane.WARNING_MESSAGE
            );

            passwordField.requestFocus();

            return;
        }


        // -----------------------------------------------------
        // DATABASE LOGIN
        // -----------------------------------------------------

        String sql =
                "SELECT id, name FROM users "
                + "WHERE username=? AND password=?";


        try (
                Connection con =
                        DBConnection.getConnection();

                PreparedStatement ps =
                        con.prepareStatement(sql)
        ) {


            ps.setString(1, username);

            ps.setString(2, password);


            try (ResultSet rs = ps.executeQuery()) {


                if (rs.next()) {

                    int userId =
                            rs.getInt("id");

                    String name =
                            rs.getString("name");


                    javax.swing.JOptionPane.showMessageDialog(

                            this,

                            "Welcome to DriveX, "
                                    + name
                                    + "!",

                            "Login Successful",

                            javax.swing.JOptionPane.INFORMATION_MESSAGE
                    );


                    DashboardFrame dashboard =
                            new DashboardFrame(
                                    userId,
                                    name
                            );

                    dashboard.setVisible(true);

                    dispose();

                } else {

                    javax.swing.JOptionPane.showMessageDialog(

                            this,

                            "Invalid username or password.\n"
                                    + "Please check your credentials and try again.",

                            "Login Failed",

                            javax.swing.JOptionPane.ERROR_MESSAGE
                    );


                    passwordField.setText("");

                    passwordField.requestFocus();
                }
            }


        } catch (SQLException ex) {

            javax.swing.JOptionPane.showMessageDialog(

                    this,

                    "Unable to connect to the database.\n"
                            + "Please check your database connection.",

                    "Database Error",

                    javax.swing.JOptionPane.ERROR_MESSAGE
            );

            ex.printStackTrace();
        }
    }


    // =========================================================
    // MAIN METHOD - OPTIONAL TESTING
    // =========================================================

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(
                new Runnable() {

                    @Override
                    public void run() {

                        LoginFrame login =
                                new LoginFrame();

                        login.setVisible(true);
                    }
                }
        );
    }
}