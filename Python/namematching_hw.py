def hamming_distance(name_A, name_B):
    """ Calculate and return the Hamming Distance between name_A and name_B (you may assume these names have the same length!!) """
    counter = 0 #initialize the counter
    mismatch = 0 #initialize mismatches counter
    while counter < len(name_A):
        if name_A[counter] != name_B[counter]:
            #if letters are not same
            mismatch += 1 #increment mismatches counter
        counter += 1 #increment counter
    return mismatch

def name_distance(name_A, name_B):
    """
    If two names have the same length, their distance is their Hamming distance.
    If not, the minimum Hamming distance between the shorter name and any substring of the longer name with that length,
    plus the number of extra characters left over in the longer name.
    For example, "cob" and "Jacob":
    1. Find the minimum Hamming distance between "cob" and any of "Jac", "aco", "cob"
    2. Find the difference in length between "cob" and "Jacob"
    3. Add those two quantities together """
    if len(name_A) == len(name_B): #if the same length, just Hamming difference
        return hamming_distance(name_A, name_B)
    if len(name_A) < len(name_B): # In case, the legnths are different. name_A shorter than B
        lower = 0 # lower boundary for substring -> to slice!
        upper = len(name_A) # upper boundary for sumbstring -> slicing
        subs = [] # Empty list to collect all the differences with substrings
        while upper <= len(name_B): # <= because index is less by 1
            subs.append(hamming_distance(name_A, name_B[lower:upper])) # collect all the differences
            lower += 1 # increment by 1, to check the next substring
            upper += 1 # same as lower
        distance = min(subs) + (len(name_B) - len(name_A)) # distance is the minimum Hamming distance + the difference in length
        return distance
    if len(name_B) < len(name_A): # if name_B is shorter
        lower = 0
        upper = len(name_B)
        subs = []
        while upper <= len(name_A):
            subs.append(hamming_distance(name_B, name_A[lower:upper]))
            lower += 1
            upper += 1
        distance = min(subs) + (len(name_A) - len(name_B))
        return distance

def get_name_lists(file_name):
    """ Open up a file named file_name, read in the contents, and return the contents to you in a list. """
    with open(file_name, 'r') as fr:
        return [name[:-1] for name in fr]

def name_matching(filename, target, k):
    """
    1. Load in a list of names from a file
    2. compare the target name to each name
    3. return a list of all names within a distance of k from the target name (not including k!!)
    """
    names = get_name_lists(filename) # lists of names from the file
    targets = [] # empty list for output
    for name in names: # go each name in the file
        if name_distance(name, target) < k: # we do not want to include names with k distance.
            targets.append(name) # if less, add the name to the list
    return targets
