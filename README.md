# 📊 Natural Disaster Data Analysis System

## 📌 Overview

This project was developed as part of the **Software Development course** at the University of Ioannina.

It is a **data analysis system** that processes and analyzes **natural disaster data** from TSV files, providing filtering, statistical analysis, and reporting functionalities.

📄 Based on the full report: 

---

## 🎯 Features

### 📂 Data Handling

* Load data from `.tsv` files
* Store each row as structured objects
* Validate input data

### 🔍 Data Filtering

* Filter by:

  * Country
  * Disaster type
  * Year range

### 📊 Analysis

* Descriptive statistics:

  * Mean, median, min, max
  * Number of events
* Regression analysis:

  * Trend calculation
  * Slope & error estimation

### 💾 Export

* Save reports in multiple formats:

  * `.txt`
  * `.md`
  * `.html`

### 🚪 System Control

* Interactive menu system
* Exit confirmation

---

## 🧠 System Architecture

The system is designed using a **layered architecture**:

### 🔹 Domain Layer

* `MeasurementVector`
* `SingleMeasureRequest`

Handles:

* Data representation
* Statistical calculations

---

### 🔹 Business Logic Layer

* `MainController`

Responsible for:

* Implementing all use cases
* Connecting domain logic with UI

---

### 🔹 Presentation Layer (GUI)

* `AppStarter`
* `JFrame100RootFrame`
* `JTableView`
* `LineChartViewer`

Handles:

* User interaction
* Visualization of results

---

## 🔄 Core Use Cases

The system supports the following operations:

1. 📥 Load data from TSV file
2. 🔍 Filter by country & disaster type
3. 📅 Filter by country, disaster type & year range
4. 📊 Compute descriptive statistics
5. 📈 Perform regression analysis
6. 💾 Export reports
7. 🚪 Exit application

👉 These are described in detail in the report (pages 4–10)

---

## 🧪 Testing

System testing is implemented for each use case.

Examples:

* ✔ Correct parsing of TSV files
* ✔ Accurate filtering results
* ✔ Valid statistical calculations
* ✔ Correct report generation

📌 According to the test tables (pages 11–17):

* Each use case is mapped to specific test cases
* Traceability matrix ensures full coverage

---

## 🧱 Project Structure

```bash
.
├── app/              # Main application logic
├── dom/              # Domain classes
├── engine/           # Business logic
├── gui/              # User interface
├── examples/         # Sample outputs
└── README.md
```

---

## ⚙️ How It Works

1. User loads a `.tsv` file
2. System parses and stores data
3. User selects filters or analysis
4. System processes request
5. Results are displayed or exported

---

## 🛠️ Technologies

* Java
* Object-Oriented Programming (OOP)
* MVC Architecture
* Data Analysis Techniques

---

## 🚀 Future Improvements

* Add more unit tests (not fully implemented yet)
* Improve UI/UX
* Support more file formats
* Optimize performance for large datasets

---

## 👥 Authors

* Charalampos Kounnapis

---

## 📚 Notes

This project demonstrates:

* Use of **software engineering principles**
* Mapping **use cases → methods → tests**
* Clean separation of layers (Domain, Logic, UI)
* Real-world data processing workflow

---
