def is_valid_sequence(seq):
    """ Verify if the given sequence is a valid DNA sequence"""
    for i in seq:
        if i != 'A' and i != 'C' and i != 'G' and i != 'T' and i != '-':
            raise ValueError(i + " is not a valid DNA character!")
    return True

def add_indel(seq, pos):
    """takes in a sequence as a string and an index in the string to add an indel ("-"), and returns the modified sequence as a string. Raises ValueError if the sequence contains invalid characters. Raises an IndexError if the position is out of bounds"""
    is_valid_sequence(seq)
    seq = list(seq) #make seq mutable
    if pos >= len(seq): #check if pos is out of range
        raise IndexError("Index " + str(pos) + " is out of range!") #raise error for pos
    else:
        seq.insert(pos, '-') #add '-'
        seq = "".join(seq) #change into string
        return seq

def delete_indel(seq, pos):
    """remove an indel ("-"), and returns the modified sequence. Raises a ValueError if there is not an indel at the specified position. IndexError if the position is out of bounds. Raises ValueError if the sequence contains invalid characters"""
    is_valid_sequence #raise a ValueError
    seq = list(seq)
    if pos >= len(seq): #check if pos is out of range
        raise IndexError("Index " + str(pos) + " is out of range!")
    if seq[pos] != '-':
        raise ValueError("No indel at index "+ str(pos) + "!")
    if seq[pos] == '-': #if it meets all the requirements
        seq.pop(pos) #remove indel
        seq = "".join(seq) #transform into string
        return seq


def align_sequences(seq1, seq2):
    """takes in two sequences as strings, adds indels to the end of the shorter one until they are of equal length, and prints the matches and mismatches. (No return value.)
    1. If either string contains invalid characters, it raises the ValueError.
    2. If one string is shorter than the other, add indels to the end of the shorter string until they are the same length.
    3. Any indel is automatically a mismatch.*
    4. After you count matches and mismatches, print both strings:
        - Matching characters are printed in lower case.
        - Mismatched characters are printed in upper case.
        - Indels are printed as dashes ("-").
    ##Note critical that you match the printed output format EXACTLY."""
    is_valid_sequence(seq1)
    is_valid_sequence(seq2)
    seq1 = list(seq1) #make sequences mutable
    seq2 = list(seq2)
    if len(seq1) < len(seq2): #add indels to shorter sequence to equalize the lengths of two sequences
        while len(seq1) < len(seq2):
            seq1.append('-')
    if len(seq2) < len(seq1):
        while len(seq2) < len(seq1):
            seq2.append('-')
    counter = 0 #initialize the counter
    match = 0 #initialize matches counter
    mismatch = 0 #initialize mismatches counter
    Str1 = "" #output for seq1
    Str2 = "" #output for seq2
    while counter < len(seq1):
        if seq1[counter] == seq2[counter] and seq1[counter] != '-':
            #Matches. However, indels are mismatches in any case.
            match += 1 #increment matches counter
            Str1 += seq1[counter].lower() #create a string output for seq1 with lower alphabet
            Str2 += seq2[counter].lower() #create a string output for seq2 with lower alphabet
        if seq1[counter] != seq2[counter] or seq1[counter] == '-' or seq2[counter] =='-':
            #mismatches Indels are mismatches
            mismatch += 1 #increment mismatches counter
            Str1 += seq1[counter].upper() #add a upper letter
            Str2 += seq2[counter].upper() #add a upper letter
        counter += 1
    print "Mathes:", match, "\n", "Mismatches:", mismatch, "\n", "Str1:", Str1, "\n", "Str2:", Str2

print add_indel("AACCCGGGG", -2)
