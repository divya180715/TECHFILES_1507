# 🔬 Complete Data Preprocessing Pipeline in Data Mining
### A Production-Ready Machine Learning Project

---

## 📌 Abstract / Problem Statement

Data preprocessing is one of the most critical phases in the data mining and machine learning pipeline. Raw, real-world data is typically incomplete, inconsistent, noisy, and not ready for direct use by machine learning algorithms. This project demonstrates a **complete, end-to-end preprocessing pipeline** applied to a synthetic HR Employee Attrition dataset.

The goal is to transform raw, messy data into a clean, structured, and model-ready format — covering every major preprocessing technique used in industry and academia.

---

## 🎯 Objectives

1. Understand and implement ALL major data preprocessing steps
2. Handle real-world data issues: missing values, duplicates, outliers, inconsistencies
3. Apply both normalization and standardization techniques
4. Perform categorical encoding using Label Encoding and One-Hot Encoding
5. Engineer meaningful new features from existing ones
6. Select the most relevant features using statistical methods
7. Reduce dimensionality using Principal Component Analysis (PCA)
8. Split data and train a baseline Logistic Regression model
9. Produce professional visualizations for academic/industry presentation

---

## 📁 Project Structure

```
ml_preprocessing_project/
│
├── src/
│   └── preprocessing_pipeline.py    ← Main modular Python script
│
├── notebooks/
│   └── preprocessing_notebook.ipynb ← Jupyter Notebook version
│
├── outputs/                         ← All generated plots + cleaned CSV
│   ├── step2_histograms.png
│   ├── step2_class_balance.png
│   ├── step3_missing_values.png
│   ├── step6_boxplot_before.png
│   ├── step6_boxplot_after.png
│   ├── step7_transformation.png
│   ├── step10_correlation_heatmap.png
│   ├── step12_pca.png
│   ├── step12_pca_scatter.png
│   ├── extra_pairplot.png
│   ├── bonus_confusion_matrix.png
│   └── final_cleaned_dataset.csv
│
├── data/                            ← Place external datasets here
├── docs/                            ← Additional documentation
├── requirements.txt
└── README.md
```

---

## 📊 Dataset Description

| Property | Value |
|----------|-------|
| **Name** | Synthetic HR Employee Attrition Dataset |
| **Rows** | 515 (500 base + 15 injected duplicates) |
| **Columns** | 12 raw → 16 after engineering |
| **Target** | `Attrition` (0 = No, 1 = Yes) |
| **Type** | Binary Classification |

### Features:
| Column | Type | Description |
|--------|------|-------------|
| Age | Numeric | Employee age (22–60) |
| Gender | Categorical | Male / Female |
| Department | Categorical | Sales / IT / HR / Finance / Operations |
| Education | Ordinal | HighSchool / Bachelor / Master / PhD |
| JobRole | Categorical | Analyst / Manager / Director / Engineer / Clerk |
| YearsExperience | Numeric | Years of work experience |
| MonthlySalary | Numeric | Monthly salary in USD |
| SatisfactionScore | Numeric | Job satisfaction (1–5) |
| HoursPerWeek | Numeric | Average working hours/week |
| Attrition | Binary | Target: 0=Stays, 1=Leaves |

**Intentionally injected data quality issues:**
- ~8% missing values in 4 columns
- 15 duplicate rows
- 3 extreme outliers (salary=$500K, hours=120, age=-5)
- Inconsistent casing (e.g. "sales", "male")
- Irrelevant columns (EmployeeID, RandomNoise)

---

## ⚙️ Preprocessing Steps Covered

| Step | Technique | Status |
|------|-----------|--------|
| 1 | Data Collection / Generation | ✅ |
| 2 | EDA (head, info, describe, dtypes) | ✅ |
| 3 | Handling Missing Values (median/mode imputation) | ✅ |
| 4 | Removing Duplicates | ✅ |
| 5 | Data Cleaning (drop irrelevant cols, fix casing) | ✅ |
| 6 | Outlier Handling (IQR + Z-score) | ✅ |
| 7 | Normalization + Standardization | ✅ |
| 8 | Label Encoding + One-Hot Encoding | ✅ |
| 9 | Feature Engineering (3 new features) | ✅ |
| 10 | Feature Selection (Correlation matrix) | ✅ |
| 11 | Data Integration (merge secondary table) | ✅ |
| 12 | Data Reduction (PCA – 95% variance) | ✅ |
| 13 | Train/Test Split (80/20, stratified) | ✅ |
| ★ | Bonus: Logistic Regression Model | ✅ |

---

## 🚀 How to Run

### Option A – Python Script (Recommended)

```bash
# 1. Clone or download the project
cd ml_preprocessing_project

# 2. Create a virtual environment (optional but recommended)
python -m venv venv
source venv/bin/activate        # Linux/Mac
venv\Scripts\activate           # Windows

# 3. Install dependencies
pip install -r requirements.txt

# 4. Run the pipeline
python src/preprocessing_pipeline.py
```

All outputs (plots + CSV) are saved to the `outputs/` folder.

### Option B – Jupyter Notebook

```bash
pip install -r requirements.txt
jupyter notebook notebooks/preprocessing_notebook.ipynb
```

Run all cells top-to-bottom (Cell → Run All).

---

## 📈 Visualizations Generated

| File | Description |
|------|-------------|
| `step2_histograms.png` | Feature distributions before preprocessing |
| `step2_class_balance.png` | Target class balance bar chart |
| `step3_missing_values.png` | Missing value % per column |
| `step6_boxplot_before.png` | Boxplots showing outliers |
| `step6_boxplot_after.png` | Boxplots after Winsorization |
| `step7_transformation.png` | MinMax vs StandardScaler comparison |
| `step10_correlation_heatmap.png` | Full correlation heatmap |
| `step12_pca.png` | Scree plot + cumulative variance |
| `step12_pca_scatter.png` | 2D PCA projection colored by target |
| `extra_pairplot.png` | Pairplot of key numeric features |
| `bonus_confusion_matrix.png` | Model evaluation confusion matrix |

---

## 🏁 Conclusion

This project successfully demonstrates all major data preprocessing techniques in data mining:

- **Data quality** issues (missing values, duplicates, outliers) were identified and resolved
- **Categorical data** was encoded for ML compatibility
- **New features** were engineered to capture domain knowledge
- **Dimensionality** was reduced from 15 → 11 features (retaining 95% variance via PCA)
- A baseline **Logistic Regression** model achieved ~58% accuracy on the preprocessed data

The preprocessing pipeline is modular, reproducible, and production-ready.

---

## 🔮 Future Scope

1. **Advanced Imputation** – KNN imputer, MICE (Multiple Imputation by Chained Equations)
2. **Feature Selection** – SHAP values, Recursive Feature Elimination (RFE)
3. **Ensemble Models** – Random Forest, XGBoost on preprocessed data
4. **AutoML Integration** – Use TPOT or Auto-sklearn for automated preprocessing
5. **Real Dataset** – Apply pipeline to IBM HR Analytics dataset from Kaggle
6. **MLOps** – Wrap pipeline into a scikit-learn Pipeline object for deployment
7. **Hyperparameter Tuning** – GridSearchCV / RandomizedSearchCV for model optimization

---

## 📚 References

- Scikit-learn Documentation: https://scikit-learn.org
- Pandas Documentation: https://pandas.pydata.org
- Seaborn Documentation: https://seaborn.pydata.org
- Han, J., Kamber, M., & Pei, J. (2011). *Data Mining: Concepts and Techniques*. Morgan Kaufmann.

---

*Project Version: 1.0 | Python 3.10+ | Last Updated: 2025*
