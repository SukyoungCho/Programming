#My name: Sukyoung Cho

import random

def factorial(num):
  """recursive factorial function"""
  if num == 1:
    return 1
  return num * factorial(num-1)

def plant_power(heroes):
  """calculate total strength of plants"""
  random.seed(0) #set the seed so that it always gives constant values
  p = random.randint(1,10) #Fire Cactus. always 9
  q = random.randint(1,10) #Fume Shroom. always 8
  r = random.randint(1,10) #Mystic Petal always 5
  ppower = 0
  if len(heroes) > 6:
    #At most 6 characters
    print "Error! No more than 6 Plants"
  else:
    for element in heroes:
      if element == 'p':
        ppower += factorial(p)
      if element == 'q':
        ppower += factorial(q)
      if element == 'r':
        ppower += factorial(r)
    return ppower
      
def zombie_power(villains):
  """calculates strength of zombies, working exactly same with plant_power"""
  random.seed(0)
  x = random.randint(1,10) #Crime Slime. always 9
  y = random.randint(1,10) #Cone Head. 8
  z = random.randint(1,10) #Imp Monk. 5
  zpower = 0
  if len(villains) > 6:
    #At most 6 characters
    print "Error! No more than 6 Zombies"
  else:
    for element in villains:
      if element == 'x':
        zpower += factorial(x)
      if element == 'y':
        zpower += factorial(y)
      if element == 'z':
        zpower += factorial(z)
    return zpower

def plant_power_war(hero):
  """it is for war_begins. In case the seed is not '0' """
  p = random.randint(1,10) 
  q = random.randint(1,10)
  r = random.randint(1,10)
  ppower = 0
  if len(hero) > 6:
    print "Error! No more than 6 Plants"
  else:
    for element in hero:
      if element == 'p':
        ppower += factorial(p)
      if element == 'q':
        ppower += factorial(q)
      if element == 'r':
        ppower += factorial(r)
    return ppower

def zombie_power_war(villain):
  """for war_begins. In case the seed is not '0' """
  x = random.randint(1,10) 
  y = random.randint(1,10) 
  z = random.randint(1,10)
  zpower = 0
  if len(villain) > 6:
    print "Error! No more than 6 Zombies"
  else:
    for element in villain:
      if element == 'x':
        zpower += factorial(x)
      if element == 'y':
        zpower += factorial(y)
      if element == 'z':
        zpower += factorial(z)
    return zpower

def war_begins(hero,villain, seed):
  """ shows the result of the battle """
  random.seed(seed)
  p = plant_power_war(hero)
  z = zombie_power_war(villain)
  if len(hero) < 7 and len(villain) < 7:
    if p > z:
      print "Plants save the day!"
    if p < z:
      print "Zombies are here to stay"
    if p == z:
      print "IT'S A DRAW!"

  
  
  
