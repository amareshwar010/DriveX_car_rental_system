package com.car_rental_system;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PaymentBillFrame extends JFrame {

    private final Color BG = new Color(235, 245, 255);
    private final Color BLUE = new Color(25, 118, 210);
    private final Color DARK_BLUE = new Color(13, 71, 161);
    private final Color GREEN = new Color(40, 167, 69);
    private final Color GRAY = new Color(108, 117, 125);
    private final Color TEXT = new Color(40, 40, 50);
    private final Color BORDER = new Color(210, 220, 230);

    public PaymentBillFrame(
            String transactionId,
            int bookingId,
            String customerName,
            String carNumber,
            String brand,
            String model,
            String startDate,
            String returnDate,
            int totalDays,
            double amount,
            String paymentMethod,
            String paymentDate) {

        setTitle("DriveX - Payment Receipt");

        setDefaultCloseOperation(
                JFrame.DISPOSE_ON_CLOSE
        );

        setSize(700, 800);

        setMinimumSize(
                new Dimension(620, 700)
        );

        setLocationRelativeTo(null);

        setResizable(true);

        createUI(
                transactionId,
                bookingId,
                customerName,
                carNumber,
                brand,
                model,
                startDate,
                returnDate,
                totalDays,
                amount,
                paymentMethod,
                paymentDate
        );
    }

    // =========================================================
    // CREATE UI
    // =========================================================

    private void createUI(
            String transactionId,
            int bookingId,
            String customerName,
            String carNumber,
            String brand,
            String model,
            String startDate,
            String returnDate,
            int totalDays,
            double amount,
            String paymentMethod,
            String paymentDate) {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(BG);

        setContentPane(mainPanel);


        // =====================================================
        // HEADER
        // =====================================================

        createHeader(mainPanel);


        // =====================================================
        // CENTER
        // =====================================================

        JPanel centerPanel =
                new JPanel(new GridBagLayout());

        centerPanel.setBackground(BG);

        centerPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        20,
                        15,
                        20
                )
        );


        JPanel receiptCard =
                createReceiptCard(
                        transactionId,
                        bookingId,
                        customerName,
                        carNumber,
                        brand,
                        model,
                        startDate,
                        returnDate,
                        totalDays,
                        amount,
                        paymentMethod,
                        paymentDate
                );


        GridBagConstraints cardGbc =
                new GridBagConstraints();

        cardGbc.gridx = 0;
        cardGbc.gridy = 0;

        cardGbc.weightx = 1.0;
        cardGbc.weighty = 1.0;

        cardGbc.fill =
                GridBagConstraints.BOTH;

        cardGbc.anchor =
                GridBagConstraints.CENTER;


        centerPanel.add(
                receiptCard,
                cardGbc
        );


        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );


        // =====================================================
        // FOOTER
        // =====================================================

        createFooter(mainPanel);
    }

    // =========================================================
    // HEADER
    // =========================================================

    private void createHeader(
            JPanel mainPanel) {

        JPanel header =
                new JPanel(new BorderLayout());

        header.setBackground(DARK_BLUE);

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        15,
                        30,
                        15,
                        30
                )
        );

        header.setPreferredSize(
                new Dimension(
                        0,
                        100
                )
        );


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
                new JLabel("Payment Receipt");

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
                        15
                )
        );

        subtitle.setBorder(
                BorderFactory.createEmptyBorder(
                        3,
                        2,
                        0,
                        0
                )
        );


        leftPanel.add(title);

        leftPanel.add(subtitle);


        header.add(
                leftPanel,
                BorderLayout.WEST
        );


        mainPanel.add(
                header,
                BorderLayout.NORTH
        );
    }

    // =========================================================
    // RECEIPT CARD
    // =========================================================

    private JPanel createReceiptCard(
            String transactionId,
            int bookingId,
            String customerName,
            String carNumber,
            String brand,
            String model,
            String startDate,
            String returnDate,
            int totalDays,
            double amount,
            String paymentMethod,
            String paymentDate) {

        JPanel card =
                new JPanel(new GridBagLayout());

        card.setBackground(Color.WHITE);

        card.setBorder(
                BorderFactory.createCompoundBorder(

                        new LineBorder(
                                BORDER,
                                1
                        ),

                        BorderFactory.createEmptyBorder(
                                20,
                                30,
                                20,
                                30
                        )
                )
        );


        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;

        gbc.weightx = 1.0;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.anchor =
                GridBagConstraints.CENTER;


        // =====================================================
        // SUCCESS TITLE
        // =====================================================

        JLabel receiptTitle =
                new JLabel(
                        "PAYMENT SUCCESSFUL"
                );

        receiptTitle.setForeground(GREEN);

        receiptTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        22
                )
        );

        receiptTitle.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        gbc.gridy = 0;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        5,
                        0
                );

        card.add(
                receiptTitle,
                gbc
        );


        // =====================================================
        // MESSAGE
        // =====================================================

        JLabel message =
                new JLabel(
                        "Thank you for choosing DriveX."
                );

        message.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        message.setForeground(GRAY);

        message.setHorizontalAlignment(
                SwingConstants.CENTER
        );


        gbc.gridy = 1;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        18,
                        0
                );

        card.add(
                message,
                gbc
        );


        // =====================================================
        // TRANSACTION DETAILS
        // =====================================================

        addSectionTitle(
                card,
                gbc,
                "Transaction Details",
                2
        );


        addRow(
                card,
                gbc,
                "Transaction ID",
                transactionId,
                3
        );

        addRow(
                card,
                gbc,
                "Booking ID",
                String.valueOf(bookingId),
                4
        );

        addRow(
                card,
                gbc,
                "Payment Date",
                paymentDate,
                5
        );

        addRow(
                card,
                gbc,
                "Payment Method",
                paymentMethod,
                6
        );


        // =====================================================
        // CUSTOMER DETAILS
        // =====================================================

        addSectionTitle(
                card,
                gbc,
                "Customer Details",
                7
        );


        addRow(
                card,
                gbc,
                "Customer Name",
                customerName,
                8
        );


        // =====================================================
        // VEHICLE DETAILS
        // =====================================================

        addSectionTitle(
                card,
                gbc,
                "Vehicle Details",
                9
        );


        addRow(
                card,
                gbc,
                "Car Number",
                carNumber,
                10
        );

        addRow(
                card,
                gbc,
                "Vehicle",
                brand + " " + model,
                11
        );

        addRow(
                card,
                gbc,
                "Rental Period",
                startDate + "  to  " + returnDate,
                12
        );

        addRow(
                card,
                gbc,
                "Total Days",
                String.valueOf(totalDays),
                13
        );


        // =====================================================
        // SEPARATOR
        // =====================================================

        JSeparator separator =
                new JSeparator();

        separator.setForeground(
                new Color(
                        220,
                        225,
                        235
                )
        );


        gbc.gridy = 14;

        gbc.insets =
                new Insets(
                        12,
                        0,
                        12,
                        0
                );

        card.add(
                separator,
                gbc
        );


        // =====================================================
        // TOTAL PAID
        // =====================================================

        JPanel amountPanel =
                new JPanel(new BorderLayout());

        amountPanel.setOpaque(false);

        amountPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        5,
                        0,
                        5,
                        0
                )
        );


        JLabel amountLabel =
                new JLabel("TOTAL PAID");

        amountLabel.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        16
                )
        );

        amountLabel.setForeground(DARK_BLUE);


        JLabel amountValue =
                new JLabel(
                        "₹ "
                        + String.format(
                                "%.2f",
                                amount
                        )
                );

        amountValue.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        23
                )
        );

        amountValue.setForeground(GREEN);

        amountValue.setHorizontalAlignment(
                SwingConstants.RIGHT
        );


        amountPanel.add(
                amountLabel,
                BorderLayout.WEST
        );

        amountPanel.add(
                amountValue,
                BorderLayout.EAST
        );


        gbc.gridy = 15;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        15,
                        0
                );

        card.add(
                amountPanel,
                gbc
        );


        // =====================================================
        // CLOSE BUTTON
        // =====================================================

        JButton closeButton =
                createButton(
                        "CLOSE",
                        BLUE
                );


        closeButton.addActionListener(
                e -> dispose()
        );


        JPanel buttonPanel =
                new JPanel(new FlowLayout(
                        FlowLayout.CENTER,
                        0,
                        0
                ));

        buttonPanel.setOpaque(false);

        buttonPanel.add(closeButton);


        gbc.gridy = 16;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        0,
                        0
                );

        card.add(
                buttonPanel,
                gbc
        );


        return card;
    }

    // =========================================================
    // SECTION TITLE
    // =========================================================

    private void addSectionTitle(
            JPanel panel,
            GridBagConstraints baseGbc,
            String text,
            int row) {

        JLabel sectionTitle =
                new JLabel(text);

        sectionTitle.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        15
                )
        );

        sectionTitle.setForeground(
                DARK_BLUE
        );


        GridBagConstraints gbc =
                (GridBagConstraints)
                        baseGbc.clone();

        gbc.gridx = 0;

        gbc.gridy = row;

        gbc.insets =
                new Insets(
                        8,
                        0,
                        5,
                        0
                );


        panel.add(
                sectionTitle,
                gbc
        );
    }

    // =========================================================
    // RECEIPT ROW
    // =========================================================

    private void addRow(
            JPanel panel,
            GridBagConstraints baseGbc,
            String label,
            String value,
            int row) {

        JPanel rowPanel =
                new JPanel(new BorderLayout());

        rowPanel.setOpaque(false);

        rowPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        3,
                        0,
                        3,
                        0
                )
        );


        JLabel labelText =
                new JLabel(label);

        labelText.setFont(
                new Font(
                        "Segoe UI",
                        Font.BOLD,
                        12
                )
        );

        labelText.setForeground(GRAY);


        JLabel valueText =
                new JLabel(
                        escapeLongText(value)
                );

        valueText.setFont(
                new Font(
                        "Segoe UI",
                        Font.PLAIN,
                        13
                )
        );

        valueText.setForeground(TEXT);

        valueText.setHorizontalAlignment(
                SwingConstants.RIGHT
        );


        rowPanel.add(
                labelText,
                BorderLayout.WEST
        );

        rowPanel.add(
                valueText,
                BorderLayout.CENTER
        );


        GridBagConstraints gbc =
                (GridBagConstraints)
                        baseGbc.clone();

        gbc.gridx = 0;

        gbc.gridy = row;

        gbc.weightx = 1.0;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.insets =
                new Insets(
                        0,
                        0,
                        0,
                        0
                );


        panel.add(
                rowPanel,
                gbc
        );
    }

    // =========================================================
    // LONG TEXT HANDLING
    // =========================================================

    private String escapeLongText(
            String value) {

        if (value == null) {
            return "";
        }

        return "<html><div style='text-align:right;'>"
                + value
                + "</div></html>";
    }

    // =========================================================
    // BUTTON
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
                        140,
                        40
                )
        );


        return button;
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