#my name: Sukyoung Cho 
#Partner: Jiayi Liu


def encode_data(num, key):
  alphabet = "abcdefghijklmnopqrstuvwxyz"
  numbers = str(num)
  count = 0
  word = ""
  while count < len(numbers):
    word += alphabet[int(numbers[count])+key-1]
    count+=1
  return word

def compress_data(data):
      letter = data[0] #each letter
      nletter = 0 # the number of the letter = 0
      word = letter  # word starts with the first letter
      count = 0 # loop counter
      while count < len(data):
        if data[count] != letter:  # check if the next letter is different from the previous one -> current word
          if nletter>1:
            word += str(nletter)  # add the number of letter
          nletter = 1 # reset the number of letter
          letter = data[count] # starts with the new letter
          word += letter # add the letter to the output
        else:
          nletter += 1  # if so, increase nletter by 1
        count += 1  # increase loop counter
      return word

def decompress_data(data):
      word = data[0]  # start with the first letter
      count = 1
      while count < len(data):
        if data[count].isalpha(): # check if it is an alphabet
          word += data[count] # if so, write the letter
        else: # if it is a digit
          i = 1 # a loop counter for if-else statement
          while i < int(data[count]): # loop through the integer value of that element
            word += data[count-1] # alphabet i times
            i += 1  
        count += 1 
      return word

def decode_data(string, key):
      count = 0
      num = ""
      while count < len(string):
        num += str(ord(string[count]) - key - 96) #a = chr(97)
        count += 1
      return int(num)

def verify_system(input, key):
      data = encode_data(input, key)
      data = compress_data(data)
      data = decompress_data(data)
      data = decode_data(data, key) # process the data thru all 4 steps
      return input == data  # check if input is the same with the final output


  
