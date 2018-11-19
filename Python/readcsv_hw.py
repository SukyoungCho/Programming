import csv

def read_csv(path):
    """ return a list of the rows from the CSV file at the specified path
    in the list-of-dictionaries with their column headers as keys, and numbers as floats.
    read any CSV. If the file does not exist, return None.
    Try, except should catch both ValueError raised by type conversion function
    and file IO error raised by file open function or csv library function
    """
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

def print_averages(rows):
    """
    print each column header from the CSV and the average value
    in that column (if numeric) or a message (if non-numeric).
    To make sure you're converting those numeric values to floats,
    write a function to use your list from read_csv() and print out each column
    average or a message if the column is non-numeric. Recall dictionary order is
    (effectively) random, so the order on these lines does not matter
    """
    d= {} #empty dictionary to calculate and print for data in each column
    for row in rows: #for each sets of data
        for i in row: #for each cell
            if i in d: #if it's already in dictionary
                d[i] += row[i] #sum up the values
            if type(row[i]) == float and i not in d: #if it's numeric and not in a dictionary
                d[i] = row[i] #append to the dictionary
    for i in rows[0]: #print each column once
        if type(row[i]) == str: #if it is not numerical data
            print i, "is non-numeric"
        elif type(row[i]) == float: #if it is a numerical data
            print i, "average =", d[i]/len(rows)


def question_22(filename,contents):
        with open(filename, 'r') as f:
            items = f.readlines()
            for i in range(len(items)):
                if items[i] in contents:
                    contents[items[i][:-1]] += 1
                if items[i] not in contents:
                    contents[items[i][:-1]] = 1
            return contents