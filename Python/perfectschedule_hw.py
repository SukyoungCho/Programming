#My name = Sukyoung Cho

from operator import itemgetter

def sort_schedule(requests):
    """sort schedules"""
    schedule = sorted(requests, key = itemgetter(2)) #sort by the ending time
    return schedule

def is_available(request, curr_sched):
  """ it will take in a meeting request and the current schedule as lists and return True if the meeting fits in the schedule"""
  index = 0 #initialize the count
  while index < len(curr_sched):
    sched = curr_sched[index] #compare each schedule
    if request[1] < sched[2]:
      #if there is an event ends earlier than the request
      if request[2] > sched[1]:
        #if it starts earlier before the request ends
        return False
    index += 1
  return True

def get_perfect_schedule(sorted):
  """  takes in a sorted list of meeting requests and returns a list of accepted meeting requests, compiled using the Greedy Algorithm for Interval """
  index = 1 # initialize the count
  perfect = sorted
  while index < len(perfect):
    if perfect[index-1][1] == perfect[index][1]:
      # it is already sorted with its starting time and ending time
      if perfect[index-1][2] <= perfect[index][2]:
      # if it starts at the same time delete the one which ends later since we wants the maximum number of meetings.
        perfect.pop(index)
    elif perfect[index-1][2] > perfect[index][1]:
        # works same as is_available! if there is a schedule starts before the previous one ends.
        perfect.pop(index)
    else:
      # we only want one meeting with the shortest duration for the max no of meetings. We evaluate all the meetings beginning at the same time and then move onto next meeting schedule.
      index += 1
  return perfect


def main(requests):
  """ sort and get perfect schedule for the requests"""
  sorted = sort_schedule(requests) #sort
  perfect_schedule = get_perfect_schedule(sorted) #get the perfect schedule
  return perfect_schedule
