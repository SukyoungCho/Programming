#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Thu Apr 26 15:46:41 2018

@author: patron
"""

import pandas as pd
from sys import argv



def readcsv():
    """ read_csv file accrding to given $ prompt. The path to the file will be
    provided as a command line argument. If the path is invalid (the file doesn't exist)
    or isn't provided, your program should end without crashing, provide a reason 
    for ending and directions for proper usage.
    """
    if len(argv) < 3: # in case the $ prompt has not given enough arguments
        print "Not enough arguments provided.\nUsage: starcolor_hw2.py [filename.csv] [color]"
    if len(argv) == 3: # If $ prompt has given appropriate form
        filename = argv[1] # [filename.csv] is the 2nd argument
        try: # To avoid crashing
            df = pd.read_csv(filename) # Create a DataFrame using pd.read_csv()
            return df
        except IOError: # In case the csv file does not exist.
            print "File " + filename + " does not exist.\nUsage: starcolor_hw2.py [filename.csv] [color]"

def display():
    """
    Create a DataFrame that consists of just these five columns out of 64 columns
    – in that order – and display its basic statistics (mean, sd, min/max, quartiles)
    """
    try: #To avoid Crashing
        df = pd.read_csv(argv[1])
        cols =["UFS", "BFS", "VFD", "RFS", "IFD"] # Five columns in the specified order
        new_df = df[cols] # Filter the DataFrame
        print new_df.describe()
    except: # If there is no file
        pass # Avoid crash

def filter_data():
    try:
        df = pd.read_csv(argv[1])
        if argv[2] not in ("blue", "yellow", "red"): #If the given color is not one of RYB
            print "Invalid color specified; please use blue, yellow or red."
        else: # if the color is one of RYB
            cols =["UFS", "BFS", "VFD", "RFS", "IFD"] # 5 columns in the order
            new_df = df[cols] # Filter the DataFrame
            RI = new_df["RFS"] - new_df["IFD"] # RFS-IFD for red
            VR = new_df["VFD"] - new_df["RFS"] # VFD-RFS for yellow
            BV = new_df["BFS"] - new_df["VFD"] # BFS-VFD for blue
            UB = new_df["UFS"] - new_df["BFS"] # UFS-BFS for blue
            if argv[2] == "red": #If the red given
                filter_red = (RI >= VR) & (RI >= BV) & (RI >= UB) # Filter for red
                # RI should be the maximum value
                # We choose red over yellow and blue so >= instead of >
                red_df = new_df[filter_red] # Filter the DataFrame
                print red_df.describe() # Print basic statistics for red
            if argv[2] == "yellow":
                filter_yellow = (VR > RI) & (VR >= BV) & (VR >= UB) # Filter for yellow
                # Choose yellow over blue, hence >= for blue and > for red
                yellow_df = new_df[filter_yellow] # Filter yellow
                print yellow_df.describe() #Stats for yellow
            if argv[2] == "blue":
                filter_blue = ((BV > RI) & (BV > VR)) | ((UB > RI) & (UB > VR)) # Filter for blue
                # Either BV or UB the max
                blue_df = new_df[filter_blue] # Filter blue
                print blue_df.describe() # Stats for blue
    except: # Avoid crash
        pass

readcsv()
display()
filter_data()