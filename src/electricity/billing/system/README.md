# Electricity Billing System

A simple Java console application that calculates electricity bills based on units consumed and saves the bill data in a MySQL database.

## Setup

1. Create a MySQL database named `bill_system`.
2. Create table `bills`:

```sql
CREATE TABLE bills (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    units_consumed INT NOT NULL,
    amount DOUBLE NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

3. Update `Database.java` with your MySQL username and password.

4. Compile and run the application.

```
javac src/electricity/billing/system/*.java
java -cp src electricity.billing.system.Main
```

---

## Features

- Simple tariff calculation
- Saves bill data to database