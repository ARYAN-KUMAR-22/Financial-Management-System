
# Personal Finance Management System

This JavaFX-based Personal Finance Management System allows users to upload and analyze financial transactions from an Excel file (`.xlsx`), view spending by category, predict transaction categories, sort transactions by amount, and search transactions by date using a DatePicker.

---

## 🚀 Features

- 📂 Upload transactions from an Excel file
- 📊 Analyze transactions to view spending by category
- 🤖 Predict category for a transaction using machine learning
- 🔎 Search transactions by date (binary search)
- 📈 Sort transactions by amount (QuickSort)

---

## 🛠 Technologies Used

- Java 17+
- JavaFX
- Apache POI (for Excel file reading)
- FXML (UI layout)
- MVC Architecture
- Custom utility classes for Sorting and Searching
- Machine Learning prediction (Random Forest-based logic)
- IntelliJ / Eclipse as IDE

---

## 📁 Project Structure

```

FinanceApp/
├── controller/
│   └── MainViewController.java
├── engine/
│   └── FinanceEngine.java
├── model/
│   └── Transaction.java
├── utils/
│   ├── SortUtils.java
│   └── SearchUtils.java
├── view/
│   └── MainView\.fxml
├── resources/
│   └── style.css
├── Main.java
├── README.md

````

---

## 🧩 How It Works

### 1. Upload Excel File
- Format required: `.xlsx`
- Columns expected: **Date**, **Description**, **Amount**, **Category**

### 2. Analyze
- Displays total spending grouped by category.

### 3. Predict
- Uses built-in ML logic to guess the category of a transaction based on description and amount.

### 4. Sort Transactions
- Uses **QuickSort** to sort transactions by amount.

### 5. Search by Date
- Select a date using the **DatePicker**
- Uses **Binary Search** to locate a transaction on that date.

---

## ✅ How to Run

1. **Clone the repository:**
   ```bash
   git clone https://github.com/ARYAN-KUMAR-22/Financial-Management-System
   cd DAA2
````

2. **Open the project in your IDE (Eclipse/IntelliJ)**

3. **Add Required Libraries:**

   * JavaFX SDK
   * Apache POI (poi, poi-ooxml)

4. **Run `Main.java`**

---

## 📝 License

This project is licensed under the MIT License. See `LICENSE` for more information.

```

Let me know if you'd like me to help generate the `LICENSE` file or add build instructions (e.g., using Maven or Gradle).
```
