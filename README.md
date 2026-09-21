# 📚 Library Management System

A desktop Library Management System built with **Java**, **JavaFX** and **MySQL**. It provides a role-based interface where students can register and sign in, while administrators manage students, library staff and their own profile from a dedicated dashboard.

> Developed with NetBeans as a JavaFX desktop application using JDBC for database access.

---

## ✨ Features

**Authentication**
- Login screen with username and password
- Sign-up form for new users (first name, last name, username, password)

**Admin dashboard**
- 👥 **Users**: view all registered students in a table (name, username, password, major, total fines)
- 🧑‍💼 **Staffs**: view all library staff (name, username, password, department)
- ➕ **Add Staff**: register a new staff member with input validation and a confirmation dialog
- ⚙️ **Profile Setting**: update the admin's first name, last name, username and password
- 🚪 **Log out**

**Role-based accounts**

Accounts are separated by a `modeControl` column in the `users` table:

| `modeControl` | Role    |
|---------------|---------|
| `1`           | Admin   |
| `2`           | Staff   |
| anything else | Student |

---

## 🧱 Project Structure

```
├── main/
│   ├── Main.java                    # Entry point (opens the Login form)
│   ├── Admin_Form.java              # Entry point for the Admin dashboard
│   ├── Login_Form.java              # Login screen
│   ├── SignUp_Form.java             # Registration screen
│   ├── AdminFormMain.java           # Admin dashboard layout + navigation
│   ├── AdminFormUsers.java          # Students table
│   ├── AdminFormStaff.java          # Staff table
│   ├── AdminFormAddStaff.java       # Add staff form
│   ├── AdminFormProfileSetting.java # Admin profile editor
│   ├── Common.java                  # Database connection helper
│   └── DBInf.java                   # Database connection constants
├── ClassesLibrary/
│   ├── User.java                    # Base class
│   ├── Student.java                 # extends User (major, fines)
│   └── Staff.java                   # extends User (department)
└── Images/                          # Background images used by the UI
```

The model layer uses simple inheritance: `Student` and `Staff` both extend `User`.

---

## 🛠️ Tech Stack

- **Java 8+** (lambdas are used)
- **JavaFX** for the GUI
- **MySQL** as the database
- **JDBC** with MySQL Connector/J (`5.1.40` or `8.0.25`)
- **NetBeans** IDE

---

## 🚀 Getting Started

### 1. Prerequisites
- JDK 8 (bundles JavaFX), or JDK 11+ together with the OpenJFX SDK
- MySQL Server running locally
- MySQL Connector/J jar (both `mysql-connector-java-5.1.40-bin.jar` and `mysql-connector-java-8.0.25.jar` are included in this project's libraries)

### 2. Set up the database

Create a database named `librarymanagementsystem`:

```sql
CREATE DATABASE librarymanagementsystem;
```

Then create the tables the application uses: `users`, `students` and `staffs`. The app reads from them like this:

- `users`: id, first name, last name, username, password, registration date, `modeControl` (role)
- `students`: student id, major, total fines
- `staffs`: staff id, department, and related fields

> 📄 Put your full schema in a file such as `database/schema.sql` and import it with:
> `mysql -u root -p librarymanagementsystem < database/schema.sql`

You also need at least one admin account (`modeControl = 1`) in `users` to use the admin dashboard.

### 3. Configure the connection

Connection settings live in `main/DBInf.java`:

```java
DB_NAME  = "jdbc:mysql://localhost/librarymanagementsystem"
USER     = "root"
PASSWORD = "your_password"
```

Change `USER` and `PASSWORD` to match your own MySQL setup.

### 4. Add the connector to the classpath

In NetBeans: **Project → Properties → Libraries → Add JAR/Folder** and select one of the MySQL Connector/J jars.

### 5. Run

- Run `main.Main` to start from the login screen.
- Run `main.Admin_Form` to open the admin dashboard directly.

Make sure the `Images/` folder is on the classpath, since the UI loads its background images from there.

---

## 🔐 Security Notes

This is a learning/academic project, and there are known areas to improve before any real-world use:

- Passwords are stored and displayed as **plain text**. Use a hashing algorithm such as bcrypt.
- Database credentials are **hard-coded**. Move them to a config file or environment variables.
- Some queries are built with string concatenation. Use `PreparedStatement` everywhere to prevent SQL injection.

---

## 🗺️ Roadmap

- [ ] Book catalog (add, edit, delete, search)
- [ ] Borrow and return workflow with due dates
- [ ] Automatic fine calculation
- [ ] Staff and student dashboards
- [ ] Password hashing
- [ ] Reports and statistics

---

## 🤝 Contributing

Contributions, issues and feature requests are welcome. Feel free to fork the repo and open a pull request.


## 👤 Author

Hala Afana
