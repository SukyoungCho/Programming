import math
def area(a,b,c):
  s = (a+b+c)/2
  return math.sqrt(s*(s-a)*(s-b)*(s-c))

def is_heronian(a,b,c):
  if a == int(a) and b == int(b) and c == int(c) and area(a,b,c) == int(area(a,b,c)):
    return True
  else:
    return False
