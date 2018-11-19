import math
def area(a,b,c):
      s=(a+b+c)/2
      return math.sqrt(s*(s-a)*(s-b)*(s-c))


def is_heronian(a,b,c):
      if a != int(a):
        return False
      if b != int(b):
        return False
      if c != int(c):
        return False
      if area(a,b,c) != int(area(a,b,c)):
        return False
      else:
        return True
        	


a=float(input("a="))
b=float(input("b="))
c=float(input("c="))

print "=>", area(a,b,c)
print "=>", is_heronian(a,b,c)