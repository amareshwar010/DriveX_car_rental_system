package com.car_rental_system;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.*;

public class ProfileFrame extends JFrame {

    private final int userId;
    private String customerName;

    private JTextField nameField;
    private JTextField usernameField;
    private JTextField phoneField;

    private JPasswordField currentPasswordField;
    private JPasswordField newPasswordField;
    private JPasswordField confirmPasswordField;

    private JButton saveProfileButton;
    private JButton changePasswordButton;

    private final Color BG = new Color(235, 245, 255);
    private final Color BLUE = new Color(25, 118, 210);
    private final Color DARK_BLUE = new Color(13, 71, 161);
    private final Color GREEN = new Color(40, 167, 69);
    private final Color ORANGE = new Color(255, 152, 0);
    private final Color GRAY = new Color(108, 117, 125);
    private final Color DARK_TEXT = new Color(45, 45, 65);
    private final Color BORDER = new Color(210, 220, 230);

    public ProfileFrame(
            int userId,
            String customerName) {

        this.userId = userId;
        this.customerName = customerName;

        setTitle("DriveX - My Profile");

        setSize(1050, 780);
        setMinimumSize(
                new Dimension(
                        900,
                        700
                )
        );

        setLocationRelativeTo(null);

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setResizable(true);

        createUI();

        loadProfile();

        setExtendedState(
                JFrame.MAXIMIZED_BOTH
        );
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

        header.setBackground(DARK_BLUE);

        header.setBorder(
                new EmptyBorder(
                        18,
                        35,
                        18,
                        35
                )
        );

        // -----------------------------------------------------
        // LEFT SIDE
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
                new JLabel("DriveX");

        title.setForeground(Color.WHITE);

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        32
                )
        );

        JLabel subtitle =
                new JLabel(
                        "My Profile"
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
                        15
                )
        );

        leftPanel.add(title);

        leftPanel.add(
                Box.createVerticalStrut(3)
        );

        leftPanel.add(subtitle);

        // -----------------------------------------------------
        // RIGHT SIDE
        // -----------------------------------------------------

        JLabel customer =
                new JLabel(
                        "Customer: " +
                        customerName
                );

        customer.setForeground(Color.WHITE);

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
                        new GridBagLayout()
                );

        centerPanel.setBackground(BG);

        centerPanel.setBorder(
                new EmptyBorder(
                        20,
                        25,
                        15,
                        25
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        // -----------------------------------------------------
        // PROFILE CARD
        // -----------------------------------------------------

        JPanel profileCard =
                createProfileCard();

        gbc.gridy = 0;

        gbc.weighty = 0;

        gbc.anchor =
                GridBagConstraints.NORTH;

        centerPanel.add(
                profileCard,
                gbc
        );

        // -----------------------------------------------------
        // GAP
        // -----------------------------------------------------

        gbc.gridy = 1;

        gbc.weighty = 0;

        centerPanel.add(
                Box.createVerticalStrut(18),
                gbc
        );

        // -----------------------------------------------------
        // PASSWORD CARD
        // -----------------------------------------------------

        JPanel passwordCard =
                createPasswordCard();

        gbc.gridy = 2;

        centerPanel.add(
                passwordCard,
                gbc
        );

        // -----------------------------------------------------
        // PUSH CONTENT UP
        // -----------------------------------------------------

        gbc.gridy = 3;

        gbc.weighty = 1;

        centerPanel.add(
                Box.createVerticalGlue(),
                gbc
        );

        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // PROFILE CARD
    // =========================================================

    private JPanel createProfileCard() {

        JPanel card =
                new JPanel(
                        new GridBagLayout()
                );

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                20,
                                25,
                                20,
                                25
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        7,
                        7,
                        7,
                        7
                );

        // -----------------------------------------------------
        // HEADING
        // -----------------------------------------------------

        JLabel heading =
                new JLabel(
                        "Profile Information"
                );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        21
                )
        );

        heading.setForeground(DARK_TEXT);

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.gridwidth = 4;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.WEST;

        card.add(
                heading,
                gbc
        );

        // -----------------------------------------------------
        // FULL NAME
        // -----------------------------------------------------

        JLabel nameLabel =
                createLabel(
                        "Full Name"
                );

        gbc.gridy = 1;

        gbc.gridx = 0;

        gbc.gridwidth = 1;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.NONE;

        gbc.anchor =
                GridBagConstraints.WEST;

        card.add(
                nameLabel,
                gbc
        );

        nameField =
                createTextField();

        gbc.gridx = 1;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        card.add(
                nameField,
                gbc
        );

        // -----------------------------------------------------
        // USERNAME
        // -----------------------------------------------------

        JLabel usernameLabel =
                createLabel(
                        "Username"
                );

        gbc.gridx = 2;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.NONE;

        card.add(
                usernameLabel,
                gbc
        );

        usernameField =
                createTextField();

        gbc.gridx = 3;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        card.add(
                usernameField,
                gbc
        );

        // -----------------------------------------------------
        // PHONE
        // -----------------------------------------------------

        JLabel phoneLabel =
                createLabel(
                        "Phone Number"
                );

        gbc.gridy = 2;

        gbc.gridx = 0;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.NONE;

        card.add(
                phoneLabel,
                gbc
        );

        phoneField =
                createTextField();

        gbc.gridx = 1;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        card.add(
                phoneField,
                gbc
        );

        // Empty space for alignment
        gbc.gridx = 2;

        gbc.weightx = 0;

        card.add(
                Box.createHorizontalStrut(10),
                gbc
        );

        // -----------------------------------------------------
        // NOTE
        // -----------------------------------------------------

        JLabel note =
                new JLabel(
                        "Update your personal information and keep your DriveX account details current."
                );

        note.setFont(
                new Font(
                        "Arial",
                        Font.ITALIC,
                        12
                )
        );

        note.setForeground(GRAY);

        gbc.gridy = 3;

        gbc.gridx = 0;

        gbc.gridwidth = 4;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        card.add(
                note,
                gbc
        );

        // -----------------------------------------------------
        // BUTTON PANEL
        // -----------------------------------------------------

        JPanel buttonPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.LEFT,
                                10,
                                0
                        )
                );

        buttonPanel.setOpaque(false);

        saveProfileButton =
                createButton(
                        "SAVE PROFILE",
                        BLUE
                );

        saveProfileButton.setPreferredSize(
                new Dimension(
                        150,
                        40
                )
        );

        saveProfileButton.addActionListener(
                e -> updateProfile()
        );

        buttonPanel.add(
                saveProfileButton
        );

        JButton resetButton =
                createButton(
                        "RESET",
                        ORANGE
                );

        resetButton.setPreferredSize(
                new Dimension(
                        120,
                        40
                )
        );

        resetButton.addActionListener(
                e -> {

                    loadProfile();

                    clearPasswordFields();
                }
        );

        buttonPanel.add(
                resetButton
        );

        gbc.gridy = 4;

        gbc.gridx = 0;

        gbc.gridwidth = 4;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        card.add(
                buttonPanel,
                gbc
        );

        return card;
    }

    // =========================================================
    // PASSWORD CARD
    // =========================================================

    private JPanel createPasswordCard() {

        JPanel card =
                new JPanel(
                        new GridBagLayout()
                );

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(
                        new LineBorder(
                                BORDER
                        ),
                        new EmptyBorder(
                                20,
                                25,
                                20,
                                25
                        )
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(
                        7,
                        7,
                        7,
                        7
                );

        // -----------------------------------------------------
        // HEADING
        // -----------------------------------------------------

        JLabel heading =
                new JLabel(
                        "Change Password"
                );

        heading.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        heading.setForeground(DARK_TEXT);

        gbc.gridx = 0;

        gbc.gridy = 0;

        gbc.gridwidth = 4;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.WEST;

        card.add(
                heading,
                gbc
        );

        // -----------------------------------------------------
        // CURRENT PASSWORD
        // -----------------------------------------------------

        JLabel currentLabel =
                createLabel(
                        "Current Password"
                );

        gbc.gridy = 1;

        gbc.gridx = 0;

        gbc.gridwidth = 1;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.NONE;

        card.add(
                currentLabel,
                gbc
        );

        currentPasswordField =
                createPasswordField();

        gbc.gridx = 1;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        card.add(
                currentPasswordField,
                gbc
        );

        // -----------------------------------------------------
        // NEW PASSWORD
        // -----------------------------------------------------

        JLabel newLabel =
                createLabel(
                        "New Password"
                );

        gbc.gridx = 2;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.NONE;

        card.add(
                newLabel,
                gbc
        );

        newPasswordField =
                createPasswordField();

        gbc.gridx = 3;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        card.add(
                newPasswordField,
                gbc
        );

        // -----------------------------------------------------
        // CONFIRM PASSWORD
        // -----------------------------------------------------

        JLabel confirmLabel =
                createLabel(
                        "Confirm Password"
                );

        gbc.gridy = 2;

        gbc.gridx = 0;

        gbc.weightx = 0;

        gbc.fill =
                GridBagConstraints.NONE;

        card.add(
                confirmLabel,
                gbc
        );

        confirmPasswordField =
                createPasswordField();

        gbc.gridx = 1;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        card.add(
                confirmPasswordField,
                gbc
        );

        // -----------------------------------------------------
        // CHANGE PASSWORD BUTTON
        // -----------------------------------------------------

        changePasswordButton =
                createButton(
                        "CHANGE PASSWORD",
                        GREEN
                );

        changePasswordButton.setPreferredSize(
                new Dimension(
                        200,
                        40
                )
        );

        changePasswordButton.addActionListener(
                e -> changePassword()
        );

        gbc.gridx = 2;

        gbc.gridwidth = 2;

        gbc.weightx = 1;

        gbc.fill =
                GridBagConstraints.NONE;

        gbc.anchor =
                GridBagConstraints.WEST;

        card.add(
                changePasswordButton,
                gbc
        );

        return card;
    }

    // =========================================================
    // LOAD PROFILE
    // =========================================================

    private void loadProfile() {

        String query =
                "SELECT name, username, phone "
                + "FROM users "
                + "WHERE id=?";

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

            try (
                    ResultSet rs =
                            ps.executeQuery()
            ) {

                if (rs.next()) {

                    nameField.setText(
                            rs.getString("name")
                    );

                    usernameField.setText(
                            rs.getString("username")
                    );

                    phoneField.setText(
                            rs.getString("phone")
                    );

                    customerName =
                            rs.getString("name");
                }
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,

                    "Unable to load your profile.\n\n"
                    + "Please try again.",

                    "Profile Error",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // UPDATE PROFILE
    // =========================================================

    private void updateProfile() {

        String name =
                nameField.getText().trim();

        String username =
                usernameField
                        .getText()
                        .trim();

        String phone =
                phoneField
                        .getText()
                        .trim();

        // -----------------------------------------------------
        // VALIDATION
        // -----------------------------------------------------

        if (name.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please enter your full name.",

                    "Validation",

                    JOptionPane.WARNING_MESSAGE
            );

            nameField.requestFocus();

            return;
        }

        if (username.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please enter a username.",

                    "Validation",

                    JOptionPane.WARNING_MESSAGE
            );

            usernameField.requestFocus();

            return;
        }

        if (phone.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please enter your phone number.",

                    "Validation",

                    JOptionPane.WARNING_MESSAGE
            );

            phoneField.requestFocus();

            return;
        }

        if (!phone.matches("\\d{10}")) {

            JOptionPane.showMessageDialog(
                    this,

                    "Phone Number Invalid\n\n"
                    + "Please enter exactly 10 digits.",

                    "Invalid Phone Number",

                    JOptionPane.WARNING_MESSAGE
            );

            phoneField.requestFocus();

            return;
        }

        String checkUsername =
                "SELECT id FROM users "
                + "WHERE username=? AND id<>?";

        String checkPhone =
                "SELECT id FROM users "
                + "WHERE phone=? AND id<>?";

        String update =
                "UPDATE users "
                + "SET name=?, username=?, phone=? "
                + "WHERE id=?";

        try (
                Connection con =
                        DBConnection.getConnection()
        ) {

            // -------------------------------------------------
            // USERNAME DUPLICATE CHECK
            // -------------------------------------------------

            try (
                    PreparedStatement ps =
                            con.prepareStatement(
                                    checkUsername
                            )
            ) {

                ps.setString(
                        1,
                        username
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

                                "Username Already Registered\n\n"
                                + "This username is already associated "
                                + "with a DriveX account.\n"
                                + "Please choose a different username.",

                                "Username Already Exists",

                                JOptionPane.WARNING_MESSAGE
                        );

                        usernameField.requestFocus();

                        return;
                    }
                }
            }

            // -------------------------------------------------
            // PHONE DUPLICATE CHECK
            // -------------------------------------------------

            try (
                    PreparedStatement ps =
                            con.prepareStatement(
                                    checkPhone
                            )
            ) {

                ps.setString(
                        1,
                        phone
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

                                "Phone Number Already Registered\n\n"
                                + "This phone number is already associated "
                                + "with a DriveX account.\n"
                                + "Please use a different phone number.",

                                "Phone Number Already Exists",

                                JOptionPane.WARNING_MESSAGE
                        );

                        phoneField.requestFocus();

                        return;
                    }
                }
            }

            // -------------------------------------------------
            // UPDATE PROFILE
            // -------------------------------------------------

            try (
                    PreparedStatement ps =
                            con.prepareStatement(
                                    update
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
                        phone
                );

                ps.setInt(
                        4,
                        userId
                );

                int updated =
                        ps.executeUpdate();

                if (updated > 0) {

                    customerName = name;

                    JOptionPane.showMessageDialog(
                            this,

                            "Profile Updated Successfully!\n\n"
                            + "Your DriveX profile information "
                            + "has been updated.",

                            "DriveX Profile",

                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,

                    "Your profile could not be updated.\n\n"
                    + "Please try again.",

                    "Profile Update Error",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // CHANGE PASSWORD
    // =========================================================

    private void changePassword() {

        String currentPassword =
                new String(
                        currentPasswordField
                                .getPassword()
                );

        String newPassword =
                new String(
                        newPasswordField
                                .getPassword()
                );

        String confirmPassword =
                new String(
                        confirmPasswordField
                                .getPassword()
                );

        // -----------------------------------------------------
        // VALIDATION
        // -----------------------------------------------------

        if (currentPassword.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please enter your current password.",

                    "Validation",

                    JOptionPane.WARNING_MESSAGE
            );

            currentPasswordField.requestFocus();

            return;
        }

        if (newPassword.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,

                    "Please enter a new password.",

                    "Validation",

                    JOptionPane.WARNING_MESSAGE
            );

            newPasswordField.requestFocus();

            return;
        }

        if (newPassword.length() < 6) {

            JOptionPane.showMessageDialog(
                    this,

                    "New password must contain at least 6 characters.",

                    "Invalid Password",

                    JOptionPane.WARNING_MESSAGE
            );

            newPasswordField.requestFocus();

            return;
        }

        if (!newPassword.equals(
                confirmPassword)) {

            JOptionPane.showMessageDialog(
                    this,

                    "New password and confirm password "
                    + "do not match.",

                    "Password Mismatch",

                    JOptionPane.WARNING_MESSAGE
            );

            confirmPasswordField.requestFocus();

            return;
        }

        if (currentPassword.equals(
                newPassword)) {

            JOptionPane.showMessageDialog(
                    this,

                    "New password must be different "
                    + "from your current password.",

                    "Invalid Password",

                    JOptionPane.WARNING_MESSAGE
            );

            newPasswordField.requestFocus();

            return;
        }

        String verifyQuery =
                "SELECT id FROM users "
                + "WHERE id=? AND password=?";

        String updateQuery =
                "UPDATE users "
                + "SET password=? "
                + "WHERE id=?";

        try (
                Connection con =
                        DBConnection.getConnection()
        ) {

            // -------------------------------------------------
            // VERIFY CURRENT PASSWORD
            // -------------------------------------------------

            try (
                    PreparedStatement ps =
                            con.prepareStatement(
                                    verifyQuery
                            )
            ) {

                ps.setInt(
                        1,
                        userId
                );

                ps.setString(
                        2,
                        currentPassword
                );

                try (
                        ResultSet rs =
                                ps.executeQuery()
                ) {

                    if (!rs.next()) {

                        JOptionPane.showMessageDialog(
                                this,

                                "Current password is incorrect.\n\n"
                                + "Please enter your existing "
                                + "DriveX password.",

                                "Incorrect Password",

                                JOptionPane.WARNING_MESSAGE
                        );

                        currentPasswordField.requestFocus();

                        return;
                    }
                }
            }

            // -------------------------------------------------
            // UPDATE PASSWORD
            // -------------------------------------------------

            try (
                    PreparedStatement ps =
                            con.prepareStatement(
                                    updateQuery
                            )
            ) {

                ps.setString(
                        1,
                        newPassword
                );

                ps.setInt(
                        2,
                        userId
                );

                int updated =
                        ps.executeUpdate();

                if (updated > 0) {

                    JOptionPane.showMessageDialog(
                            this,

                            "Password Changed Successfully!\n\n"
                            + "Your DriveX password has been updated.",

                            "DriveX Security",

                            JOptionPane.INFORMATION_MESSAGE
                    );

                    clearPasswordFields();
                }
            }

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(
                    this,

                    "Password could not be changed.\n\n"
                    + "Please try again.",

                    "Password Error",

                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // =========================================================
    // CLEAR PASSWORD FIELDS
    // =========================================================

    private void clearPasswordFields() {

        currentPasswordField.setText("");
        newPasswordField.setText("");
        confirmPasswordField.setText("");
    }

    // =========================================================
    // LABEL HELPER
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

        label.setForeground(GRAY);

        return label;
    }

    // =========================================================
    // TEXT FIELD HELPER
    // =========================================================

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
                        200,
                        38
                )
        );

        field.setBorder(
                new LineBorder(
                        new Color(
                                190,
                                200,
                                215
                        ),
                        1
                )
        );

        return field;
    }

    // =========================================================
    // PASSWORD FIELD HELPER
    // =========================================================

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
                        200,
                        38
                )
        );

        field.setBorder(
                new LineBorder(
                        new Color(
                                190,
                                200,
                                215
                        ),
                        1
                )
        );

        return field;
    }

    // =========================================================
    // BUTTON HELPER
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
    // FOOTER
    // =========================================================

    private void createFooter(
            JPanel mainPanel) {

        JLabel footer =
                new JLabel(
                        "DriveX • Drive • Explore • Enjoy"
                );

        footer.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        footer.setForeground(GRAY);

        footer.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        JPanel footerPanel =
                new JPanel(
                        new BorderLayout()
                );

        footerPanel.setBackground(BG);

        footerPanel.setBorder(
                new EmptyBorder(
                        5,
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