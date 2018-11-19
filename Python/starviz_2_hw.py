#!/usr/bin/env python2
# -*- coding: utf-8 -*-
"""
Created on Tue May  1 16:57:00 2018

@author: sycho
"""

import matplotlib.pyplot as plt
import pandas as pd
import numpy as np

# SNOW
# PRECIPITATION
# TMAX, TMIN, TAVG
# UPTO 2018.03 FROM 1869
#Box plot for temperatures max, min, average
#Bar for precipitation

#df.boxplot(column="TMAX", by="TMIN")
# data.boxplot(column='hourly_wage', by=['area1', 'name'], vert=False) #Vert 가로
#data.hourly_wage.hist(bins=10)
"""
Demonstrate a dynamic characteristic of the data over time.
Create at least three different representations of this time series data,
 and explain your choice of charts in your comments.
 """

df = pd.read_csv('./NewYork.csv')


"""
 One of the biggest concerns nowadays is a global warming.
 This graph is to visualize whether or not the temperature has risen over the past near 150 years.
 To see how the temperature has risen due to the global warming.
 To figure out whether only the maximum temperature has increased or the temperature in overall.
 Lines and box plot will be used to examine.
 The conclusion is that the temperature in overall has risen over the time.
"""

fig, axes = plt.subplots() # To plot each temperature on the same plane
df.plot(x="DATE", y= "TAVG",ax=axes).set_ylabel('Temperature') # plot for average temperature
# x-axis = "Date", y-axis = "Temperature"
df.plot(x="DATE", y= "TMIN",ax=axes, title = "Temperature Change Over The Years") #plot Min_temp with title
df.plot(x="DATE", y= "TMAX",ax=axes) # Max_temperature
plt.show() #display the plot

df.boxplot(column=["TMAX","TMIN","TAVG"]) # Box plot to show the summarized information - variance.
plt.show() #display the plot

"""
 As the temperature has increased, I wanted to know if there has been a change in the precipitation, too.
 Histogram will be used to compare and contrast how the precipitation has changed over the time.
 It will compare first half of the duration (from 1869 to 1942), and the second half (1943 to 2017)
 The conlusion is that the precipitation has increased. The second half shows far more precipitation
 than the first half in overall.
"""

df2 = df[:75] # Filter the first half
df3 = df[-74:] # Second half
df2.PRCP.hist(color = 'r', bins=50, label = "1869-1942") # histogram with red color
# 50 bins to show more detailed distribution.
df3.PRCP.hist(color = 'b', bins=50, label = "1943-2017") # with blue color to compare
plt.legend(loc='upper right', title = "Precipitation\n(Time period)") # legend with titles
plt.show() # display the plot



"""
 As the temperature has risen, I believe it must snow less.
 If it snows less, should precipitation not decrease?
 Scatter plot and the best-fitted line will be used to examine the relationship
 among the snow, precipitation, and the temperature.
 Scatter plot would be effective in case two variables do not have a linear relationship.
 Here, the date was regarded as the temperature as the temperature has gone up as the time goes by.
 The conclusion is that the temperature MATTERS how much it snows. As the minimum temperature
 has gone up, it snowed less. However, the snow and the precipitation shows no tangible
 relationship between each other and the date. It might be interpreted that it rains more. 
"""
df.plot.scatter("DATE","PRCP", title = "Precipitation over Time")
# First see, the relationship between the precipitation and the Date (temperature)
x1 = df['DATE']
y1 = df['PRCP']
A1 = np.vstack([x1,np.ones(len(x1))]).T # Create vstack to find m & b
m1, b1 = np.linalg.lstsq(A1, y1)[0] # Find m&b
plt.plot(x1, m1*x1+b1) # Draw the best-fitted line

df.plot.scatter("TMIN","SNOW", title = "Temp.Min & Snow") # The relationship between the snow and the temperature
# Minimum temperature is used because it alligns with other temp changes and should be cold to snow.

df.plot.scatter("DATE","SNOW", title = "Snow Over Time") # Snow over time. The temperature has increased.

df.plot.scatter("SNOW","PRCP", title = "The Relationship between Snow and Precipitation")
# Does more snow means more precipitation?
plt.show()
