### Name: Sukyoung Cho

### Email: scho83@wisc.edu

# Reading grocery prices
groceries <- read.table("groceries.csv", stringsAsFactors = FALSE, sep = ",")

# 4.1 how.many() function
how.many <- function(item, n.max){
  q <- cat(sep = "",  "How many ", item, "?")
  n <- as.numeric(readline(q))
  while(n > n.max) {
    cat(sep = "", "ERROR: too many for the budget", "\n")
    q <- cat(sep = "",  "How many ", item, "?")
    n <- as.numeric(readline(q))
  }
  return(n)
}

# 4.2 grocery.list() function
grocery.list <- function(file, budget) {
  groceries <- read.table(file, stringsAsFactors = FALSE, sep = ",") # read the table
  colnames(groceries) = c("item", "price")
  print(groceries)
  total = 0
  shopping.list = data.frame(item = character(), price = character(), quantity = character()) # shopping list
  for(i in seq_len(length(groceries$item))) { # loop through every item
    if(groceries$price[i] < (budget-total)) { # if item is more expensive
      n = how.many(groceries$item[i], n.max = floor((budget-total)/groceries$price[i]))
      total = total + (n * (groceries$price[i]))
      to.buy <- data.frame(a = groceries$item[i], b = groceries$price[i], c = n)
      shopping.list = rbind(shopping.list, to.buy)
    }
  }
  colnames(shopping.list) = c("item", "price", "quantity") # name the cols
  return(shopping.list[!apply(shopping.list, 1, function(y) any(y == 0)), ]) # only non-zero items
}

# Calling grocery.list
n <- grocery.list("groceries.csv", 10)
print(n)
cat(sep = "", "Your bill is $", sum(n$price*n$quantity))
