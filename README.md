# ElectricityBillingSystem

This is a Java Swing based GUI application for managing electricity billing operations. It supports user login, customer management, bill calculation, payment, and report generation.

## Features

- User login with secure authentication.
- Add and manage customers.
- Calculate electricity bills based on consumption.
- Pay bills and update payment status.
- Generate and display bill details.
- Connects with MySQL database using JDBC.

## Project Structure

- `SplashScreen.java` — Initial loading screen.
- `LoginScreen.java` — User login interface.
- `MainSystem.java` — Main dashboard after login.
- `AddCustomer.java` — Add and manage customers.
- `PayBill.java` — Bill payment processing.
- `GenerateBill.java` — Generate bill receipts.
- `ShowDetails.java` — Display customer and bill details.
- `LastBill.java` — View last payment details.
- `DbConnection.java` — Manages database connectivity.

## Database

This application uses MySQL with four tables:

- **Login**: Stores user credentials.
- **Bill**: Stores bill details per meter number.
- **Emp**: Stores customer details.
- **Tax**: Stores tax and tariff related info.

## Setup Instructions

1. Install MySQL and create a database named `electricitybilling`.
2. Create tables as per the project schema.
3. Update MySQL credentials in `DbConnection.java`.
4. Add MySQL Connector/J JAR to your project classpath.
5. Compile and run the project from the `src` folder.

## Dependencies

- Java SE Development Kit (JDK) 8 or above.
- MySQL database server.
- MySQL Connector/J JDBC driver.

## How to Run

Compile all `.java` files and run `electricity.billing.system.SplashScreen`.

```bash
javac -cp "libs/mysql-connector-java-8.0.xx.jar;src" src/electricity/billing/system/*.java
java -cp "libs/mysql-connector-java-8.0.xx.jar;src" electricity.billing.system.SplashScreen

