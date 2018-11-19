import math

def euclidean_distance(data_point1, data_point2):
    """return the Euclidean distance between two dictionary data points from the data set.
    we're taking the distance between points in three dimensional space, where those dimensions
    are the precipitation amount, maximum temperature, and minimum temperature for the day.
    (You can ignore the date when doing this calculation.)
    """
    d1 = data_point1 #to shorten the formula
    d2 = data_point2
    edist = math.sqrt((d1["PRCP"] - d2["PRCP"])**2 + (d1["TMAX"] - d2["TMAX"])**2 + (d1["TMIN"] - d2["TMIN"])**2) #calculate euclidean_distance
    return edist

def read_dataset(filename):
    """return a list of data point dictionaries read from the specified file.
    the first entry is the DATE, the second entry is the PRCP, the third entry is the TMAX
    the fourth entry is the TMIN and the last entry is a boolean representing RAIN
    In this function, you must read the file in line by line, split each line out with its spaces,
    and create a dictionary with the keys listed above and the values on the line, where the numeric values have been converted to floats.
    The function should return a list with one dictionary for each line in the file.
    """
    with open(filename, 'r') as f:
        lines = [] #empty list to create the list containing all dictionaries
        data = f.readlines() #read every line in the file and return the list!
        for line in data: #go over every line
            d = {} #empty dictionary to add the data
            l = line.split(" ") #distinguish each entry
            date = l[0] #the order of entries as mentioned above
            prcp = l[1]
            tmax = l[2]
            tmin = l[3]
            rain = l[4][:-1] #Don't want to include '/r/n'! #[:-1] for rainn.txt!!
            d["DATE"] = date
            d["PRCP"] = float(prcp) #we need float for PRCP, TMAX and TMIN to calculate euclidean_distance
            d["TMAX"] = float(tmax)
            d["TMIN"] = float(tmin)
            d["RAIN"] = rain
            lines.append(d) #add into the list
        return lines

def majority_vote(nearest_neighbors):
    """ return a prediction of whether it is raining or not based on a majority vote of the list of neighbors.
    In order to classify a point, you look at the classification of the k points closest to it
    and say that it has the same label they do (in this case, whether it was raining or not).
    This function takes in a list of those neighbors, and should return the string representing whether it's raining or not.
    (Note: this function does not return a boolean!)** If a tie occurs, default to 'TRUE' as your answer**
    """
    n = nearest_neighbors #to shorten the code
    t = 0 #the frequencies of rainy days
    f = 0 #the frequencies of not
    for day in n:
        if day["RAIN"] == "TRUE":
            t += 1 #if it rained, increment by 1
        if day["RAIN"] == "FALSE":
            f += 1 #if it didn't increment by 1
    if f > t: #if there are more days did not rain
        return "FALSE" #we need to return string not a "Boolean"
    if t >= f: #if a tie occurs, True!
        return "TRUE"

def k_nearest_neighbors(filename, test_point, k):
    """ Find the closest k neighbors using Euclidean distance, and return their majority vote on
     whether it's raining or not in the test point.
    """
    data = read_dataset(filename) #read the data
    neighbors = [] #empty list for close neighbors
    predict = [] #empty list to predict the rain
    for x in data:
        dist = euclidean_distance(x, test_point) #calculate Euclidean distance
        x["EDIST"] = dist #append to the dictionary for later use
        neighbors.append(dist) #set of neighbors
    neighbors.sort() #sort in order to find the closest neighbors
    neighbors = neighbors[:k] #slice to take the k closest points
    for y in data:
        if y["EDIST"] in neighbors: #find out if the point is one of the closest neighbors
            while len(predict) < k:
            #up to 'k' number of closest neighbors. If there are points with the same Euclidean distance
            #it is okay to take any of them (Not specified).
                predict.append(y) #collect neighbors to predict the rain
    weather = majority_vote(predict) #vote for the forecast
    return weather
