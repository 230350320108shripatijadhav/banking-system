💳 ✨ Banking System (Spring Boot + Angular + Spring Security)
🚀 Overview

A full-stack Banking System application built using Spring Boot (Backend) and Angular (Frontend) with Spring Security authentication.

This project simulates real-world banking operations like account management, secure transactions, and transaction history tracking.

🎯 Key Features
🔐 Secure Login & Registration (Spring Security + JWT)
🏦 Account Creation & Management
💸 Money Transfer between accounts
📊 Transaction History tracking
👤 User Profile Management
⚡ Responsive UI (Angular + Bootstrap)
🛠️ Tech Stack
🔙 Backend
Java 17
Spring Boot
Spring Security
JWT Authentication
JPA / Hibernate
MySQL
🎨 Frontend
Angular 9
TypeScript
Bootstrap
HTML/CSS
⚙️ Tools
Maven
Node.js
npm
Git & GitHub
📁 Project Structure
BankingSystem/
 ├── BankingSystemBackend/
 ├── BankingSystemFrontend/
 ├── screenshots/
 └── README.md
⚙️ Prerequisites

Before running this project, make sure you have installed:

Java 17+
Maven 3+
Node.js (v16 recommended)
npm
MySQL
🔧 Backend Setup
1️⃣ Clone Repository
git clone https://github.com/230350320108shripatijadhav/BankingSystem.git
2️⃣ Navigate to Backend
cd BankingSystem/BankingSystemBackend
3️⃣ Build Project
mvn clean install
4️⃣ Run Application
mvn spring-boot:run

OR

java -jar target/backend-0.0.1-SNAPSHOT.jar
📡 REST APIs
Method	Endpoint	Description
POST	/api/signup	Register User
POST	/api/user	Create User
GET	/api/user	Get User
PUT	/api/user	Update User
GET	/api/account	Get Account
POST	/api/transfer	Transfer Money
GET	/api/transaction	Transaction History
🎨 Frontend Setup
1️⃣ Navigate to Frontend
cd BankingSystem/BankingSystemFrontend
2️⃣ Install Dependencies
npm install
3️⃣ Run Application
npm start
🌐 Open in Browser
http://localhost:4200/
📸 Screenshots
🔐 Login Page

🏠 Home Page

👤 Profile Page

💸 Transfer Money

📊 Transaction History

🔮 Future Improvements
📱 Mobile responsive UI improvements
📧 Email notifications for transactions
📊 Dashboard analytics
☁️ Cloud deployment (AWS / Azure)
🧾 PDF account statement generation
👨‍💻 Author

Shripati Jadhav
💼 Full Stack Developer (Java + Angular)

🤝 Connect
GitHub: https://github.com/230350320108shripatijadhav
⭐ Project Highlights

✔ Real-world banking workflow
✔ Secure authentication (JWT)
✔ Clean REST API design
✔ Full-stack integration

🔥 Final Result

👉 This project demonstrates strong understanding of:

Backend Development
Frontend Integration
Security (Spring Security)
Full-stack Architecture
