import csv

def read_csv(path):
    """ read the data from csv files. From the last week's assignment """
    try: #use try to avoid an error
        with open(path, "r") as f: #open a file
            rows = [] #empty list for the list of dictionaries
            for row in csv.DictReader(f): #DictReader to automatically create a dictionary with column headers as keys
                for i in row: #for every element in each row
                    try: #try to avoid an error in type conversion for non-numeric values
                        row[i] = float(row[i]) #conver to float
                    except: #if non-numeric
                        pass #just pass
                rows.append(row) #add to the list
            return rows
    except: #if there is no such file
        return None

def group_magnitudes(stars, mag):
    """ return a dictionary where the keys are the colors of your stars
     and the the values from the corresponding magnitude column in a list as the values.
     If the argument provided for the magnitude parameter is not one of 'u', 'g', 'r', 'i', or 'z',
      raise a ValueError with the message "[mag] is not a valid magnitude"
      This should NOT produce a KeyError!
    """
    d = {} #empty dictionary
    for i in stars: #for each star
        if mag not in i.keys(): #if mag is not a valid key
            raise ValueError(mag + " is not a valid magnitude") #raise a ValueError
        else: #if mag is one of valid parameters
            color = i["color"] #for each color
            if color in d: #if the color is already in dictionary
                d[color].append(i[mag]) #add the value to the list
            if color not in d: #if the color is not yet added
                l = [] #an empty list to collect data. To have a list as a value of dictionary
                l.append(i[mag]) #append the data into the list
                d[color] = l #create a new element in dictionary
    return d

def combine_magnitudes(stars, mag1, mag2):
    """ return a dictionary where the keys are the colors of your stars and
     the values are a list of the data corresponding to the value from column
     mag1 minus the value in column mag2.
     If either magnitude is invalid, raise a ValueError
    """
    d = {} #empty dictionary for mag1
    for i in stars: #for each star
        if mag1 not in i.keys(): #raise a ValueError if either of them is invalid
            raise ValueError(mag1 + " is not a valid magnitude")
        if mag2 not in i.keys():
            raise ValueError(mag2 + " is not a valid magnitude")
        else:
            color = i["color"]
            gap = i[mag1] - i[mag2] #the value is mag1 minus mag2 as described
            if color in d: #same as previous function below
                d[color].append(gap)
            if color not in d:
                l = []
                l.append(gap)
                d[color] = l
    return d

def print_averages(grouping):
    """ Expects the resulting dictionary from one of the first two functions,
     and prints the average value associated with each star color present in the dictionary
    """
    d = {} #empty dictionary for the result
    for i in grouping.keys(): #for each color
        average = sum(grouping[i])/float(len(grouping[i])) #calculate average. convert to float in case the values are not floats.
        d[i] = average #add average values into the dictionary
    return d  
