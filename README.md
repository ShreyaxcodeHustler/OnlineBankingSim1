# OnlineBankingSim1
OnlineBankingSim
Java Database Login Application
This is a simple Java application that demonstrates how to connect to a PostgreSQL database and perform user login functionality. The application is built using Java and JavaFX (for the GUI) with PostgreSQL as the database.

Prerequisites
Java Development Kit (JDK) .
PostgreSQL Database installed and running.
A PostgreSQL JDBC driver (usually included in your IDE's dependencies, or you can download it from [PostgreSQL JDBC Driver]
An IDE like IntelliJ IDEA.
Setting up PostgreSQL Database: Open PostgreSQL client tool like pgAdmin
Create a Database: run the following SQL commands to create a new database and user.
CREATE TABLE customers ( customer_id SERIAL PRIMARY KEY, name VARCHAR(100), address VARCHAR(255), contact VARCHAR(50), username VARCHAR(50) UNIQUE NOT NULL, password VARCHAR(255) NOT NULL );

CREATE TABLE accounts ( account_id SERIAL PRIMARY KEY, customer_id INT REFERENCES customers(customer_id), type VARCHAR(50) NOT NULL, balance DECIMAL(10, 2) NOT NULL );

CREATE TABLE transactions ( transaction_id SERIAL PRIMARY KEY, account_id INT REFERENCES accounts(account_id), type VARCHAR(50) NOT NULL, amount DECIMAL(10, 2) NOT NULL, timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP );

CREATE TABLE beneficiaries ( beneficiary_id SERIAL PRIMARY KEY, customer_id INT REFERENCES customers(customer_id), name VARCHAR(100), account_number VARCHAR(50), bank_details VARCHAR(255) );

Code Structure • DatabaseConnection: o Contains the getConnection() method that establishes a connection to the PostgreSQL database using JDBC. • Customer: o Contains the login() method that checks if the user credentials (username and password) match in the database. • LoginScreen: o Contains the GUI logic using JavaFX to capture user input and call the login functionality. • Main: o This is where the application starts (if applicable). It can instantiate the LoginScreen or the main window of your application. Running the Application

Build and Run the Application: Once your setup is complete, you can run the LoginScreen class (or the main class) to launch the application.
Login Screen: o The login screen will prompt you to enter a username and password. o Enter the credentials you have set up in your PostgreSQL database (testuser and testpassword for the sample user). o If the credentials are correct, you will be logged in successfully, and you can proceed with the rest of the application (e.g., go to a main screen). o If the credentials are incorrect, an error message will be shown
