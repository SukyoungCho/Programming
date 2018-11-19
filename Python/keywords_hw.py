def get_text(filename):
    """ read text files, and passed through get_alphanum function."""
    with open(filename, 'r') as f:
        return get_alphanum(f.read()).split()

def get_stopwords(filename):
    """ stop words from the txt files """
    with open(filename, 'r') as f:
        return [word[:-1].replace("'","") for word in f]

def get_alphanum(text):
    """ transforms the argument text string to lowercase, removes all non-alphanumeric characters
    (letters, numbers, and spaces) and returns the resulting string. """
    alphanum = [] # empty list for the output
    for letter in text:
        if letter.isalpha() == True:
            # if it is an alphabet
            alphanum.append(letter.lower()) #lowercase for every alphabet
        if letter.isdigit() == True:
            # if it is an number
            alphanum.append(letter)
        if letter == ' ':
            # if it is a space
            alphanum.append(letter)
    alphanum = "".join(alphanum) # convert a list of letters into a string (word)
    return alphanum

def remove_stopwords(word_list, stopwords):
    """removes all words in the stopwords list from the word list in place. This function does not *return* a value."""
    for word1 in stopwords: # go over all the words in stopwords
        for word2 in word_list: # check each word in the word_list
            if word1 == word2: # if it is in the stopwords
                word_list.remove(word1) # delete it but no returning


def get_word_freq(word_list):
    """ creates and returns a dictionary of frequencies of words in the word list
        , with the words as keys and the frequencies as values."""
    word_dict = {} # Empty dictionary to create
    for word in word_list:
        if word not in word_dict:
            # if the word has not been yet registerd as a key
            word_dict[word] = 1 # add to a dictonary as a key and initialize the count
        else:
            word_dict[word] += 1 # if it's in the list, increase the count
    return word_dict


def get_keywords_threshold(word_freq, threshold):
    """ creates and returns a dictionary of words and their frequencies from the provided word_freq dictionary
        , where their frequency *exceeds* the provided threshold. """
    word_threshold = {} # Empty dictonary to create
    for word in word_freq.keys(): # Check each word
        if word_freq[word] > threshold: # For only words with greater* frequencies than the threshold
            word_threshold[word] = word_freq[word] # add to the dictionary
    return word_threshold

def get_top_keywords(word_freq, n):
    """creates and returns a dictionary of the top n keywords in the provided word_freq dictionary."""
    top_freq = {} # Empty dictionary to create
    sorted_freq = sorted(word_freq.values()) # Sort frequencies
    counter = -1 # Counter for top frequencies
    for i in range(n): # Only 'n' times of numbers
        for key in word_freq.keys():
            if word_freq[key] == sorted_freq[counter]: # If it is the most frequent word
                top_freq[key] = sorted_freq[counter] # Add to the dictionary
        counter -= 1 # The next most frequent word
    return top_freq

print  get_top_keywords({'hello': 20, 'there': 300, 'world': 300, 'fruit': 500, 'flower': 3, 'pen': 50, 'apple': 100, 'hi': 100, 'i': 500}, 2)
#=> {'apple': 100, 'fruit': 500, 'pen': 50, 'world': 300}
