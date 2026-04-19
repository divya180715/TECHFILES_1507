"""
run_pipeline.py — Quick-start launcher for the Preprocessing Pipeline
----------------------------------------------------------------------
Usage:
    python run_pipeline.py

This script installs dependencies (if needed) and runs the full pipeline.
All outputs are written to the outputs/visualizations/ folder.
"""

import subprocess
import sys
import os

def install_requirements():
    req_path = os.path.join(os.path.dirname(__file__), "requirements.txt")
    print("📦 Installing dependencies from requirements.txt ...")
    subprocess.check_call([sys.executable, "-m", "pip", "install", "-r", req_path, "-q"])
    print("✅ Dependencies ready.\n")

def run_pipeline():
    pipeline = os.path.join(os.path.dirname(__file__), "src", "preprocessing_pipeline.py")
    print("🚀 Running preprocessing pipeline ...\n")
    subprocess.check_call([sys.executable, pipeline])

if __name__ == "__main__":
    install_requirements()
    run_pipeline()
