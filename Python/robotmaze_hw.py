def can_move_up(up, left, right):
  """returns True if and only if up is an int (and not a bool, str, or float); False otherwise."""
  if isinstance(up, bool):
    return False
  if isinstance(up, int):
    return True
  else:
    return False

def can_move_left(up, left, right):
  """returns True if and only if left and right are strings and left is the same as right; False otherwise."""
  if isinstance(left, str) and isinstance(right, str) and left == right:
    return True
  else:
    return False

def can_move_right(up, left, right):
  """returns True if and only if left and right are floats and left + right is a whole number (or close enough); False otherwise."""
  if isinstance(left, float) and isinstance(right, float) and left+right == int(left+right):
    return True
  else:
    return False

def can_move_down(up, left, right):
  """if left and right are bools, returns the result of left and right; if one is a bool, returns that value; False otherwise."""
  if isinstance(left, bool) and isinstance(right, bool):
    return left and right
  if isinstance(left, bool) and type(right) != bool:
    return left
  if isinstance(right, bool) and type(left) != bool:
    return right
  else:
    return False

def should_stop(up, left, right):
  """returns True if and only if all three parameters are strings, and concatenating left and right produces up; False otherwise."""
  if isinstance(up, str) and isinstance(left, str) and isinstance(right, str) and up == left + right:
    return True
  else:
    return False
  
