# DriveX_car_rental_system
Java Swing + JDBC + MySQL based Car Rental Management System with customer booking, payments, reviews, and admin management features.
🚗 DriveX
Smart Mobility. Better Journeys.

A Java Swing based Car Rental Management System built with Java, JDBC and MySQL.

DriveX allows customers to browse cars, check date-based availability, make bookings, manage payments, submit reviews, and manage their profiles. Administrators can manage cars, customers, bookings, payments, reviews, maintenance, and reports.


✨ Features
-------------------------------------
👤 Customer Module
🔐 User Registration & Login
🔑 Forgot Password
🚘 Browse Normal Cars
⭐ Browse Premium Cars
📅 Date-Based Car Availability
📝 Book a Car
📋 My Bookings
❌ Cancel Booking
💳 Make Payments
🧾 Payment Receipt
📜 Payment History
⭐ Reviews & Ratings
👤 Profile Management
🔒 Change Password
🛠️ Admin Module
🔐 Admin Login
🚘 Car Management
👥 Customer Management
📋 Booking Management
💳 Payment Management
⭐ Review Management
📊 Reports Dashboard
🔧 Maintenance Management
💰 Revenue Information

🚘 Car Availability
DriveX uses booking dates to determine vehicle availability.


| Technology | Purpose                 |
| ---------- | ----------------------- |
| Java       | Application development |
| Java Swing | Desktop GUI             |
| JDBC       | Database connectivity   |
| MySQL      | Data storage            |
| Git        | Version control         |
| GitHub     | Source code management  |


🗄️ Database

Database:  car_rental_system

Main Tables
---------------
users
admin
cars
bookings
payments
reviews

database/car_rental_system.sql

📂 Project Structure

DriveX
│
├── src
│   └── com.car_rental_system
│       │
│       ├── CarRentalSystem.java
│       ├── WelcomeFrame.java
│       ├── LoginFrame.java
│       ├── RegistrationFrame.java
│       ├── DashboardFrame.java
│       │
│       ├── NormalCarsFrame.java
│       ├── PremiumCarsFrame.java
│       ├── BookingFrame.java
│       ├── MyBookingsFrame.java
│       │
│       ├── PaymentFrame.java
│       ├── PaymentBillFrame.java
│       ├── PaymentHistoryFrame.java
│       ├── ReviewsFrame.java
│       ├── ProfileFrame.java
│       │
│       ├── AdminLoginFrame.java
│       ├── AdminDashboardFrame.java
│       ├── CarManagementFrame.java
│       ├── CustomerManagementFrame.java
│       ├── BookingManagementFrame.java
│       ├── PaymentManagementFrame.java
│       ├── AdminReviewsFrame.java
│       └── ReportsFrame.java
│
├── database
│   └── car_rental_system.sql
│
├── README.md
└── .gitignore


🚀 How to Run
1. Clone the repository
git clone https://github.com/amareshwar010/DriveX-Car-Rental-System.git

2. Open the project

Open the project in:

Eclipse
IntelliJ IDEA
VS Code with Java extensions
3. Configure MySQL

Create the database:
CREATE DATABASE car_rental_system;

4. Add MySQL Connector/J

Add the MySQL JDBC driver to the project.

5. Configure database connection

Update the database configuration in:DBConnection.java

6. Run the application

Run: CarRentalSystem.java

🔄 Application Flow
Welcome
   ↓
Login / Registration
   ↓
Customer Dashboard
   ↓
Browse Cars
   ↓
Select Car
   ↓
Choose Rental Dates
   ↓
Confirm Booking
   ↓
Payment
   ↓
Payment Receipt
   ↓
Review & Rating

Admin flow:

Admin Login
     ↓
Admin Dashboard
     ↓
 ┌───────────────┐
 │ Car Management│
 │ Customer Mgmt │
 │ Booking Mgmt  │
 │ Payment Mgmt  │
 │ Review Mgmt   │
 │ Reports       │
 └───────────────┘

 📸 Screenshots

 🏠 Welcome Screen
 <img width="1919" height="1018" alt="Screenshot 2026-10-08 214256" src="https://github.com/user-attachments/assets/4149110e-135e-4b00-b678-6ec6b6e9a6c8" />
 
 ⭐ Registration
 <img width="1105" height="887" alt="Screenshot 2026-10-08 214514" src="https://github.com/user-attachments/assets/4aaf6bb4-6e3a-4010-bf58-31c3424c794f" />

 
 🔐 Login
 <img width="1103" height="860" alt="Screenshot 2026-10-08 214312" src="https://github.com/user-attachments/assets/d2c3e07c-b422-491f-a61f-897d74a89b81" />

 🚘 Customer Dashboard
 <img width="1919" height="1018" alt="Screenshot 2026-10-08 214338" src="https://github.com/user-attachments/assets/56c6d04d-931f-4b1c-b453-b7b5b12bc72d" />

 🔐 Admin Login
 <img width="1919" height="1018" alt="Screenshot 2026-10-08 214413" src="https://github.com/user-attachments/assets/e486dfa6-2c07-420b-b8b8-9adc13e78fe7" />

 📊 Admin Dashboard
 <img width="1919" height="1018" alt="Screenshot 2026-10-08 214435" src="https://github.com/user-attachments/assets/c215731c-bcc6-4298-b311-1926f79ae5c9" />



