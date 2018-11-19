# Name: Sukyoung Cho
# Email: scho83@wisc.edu

rm(list = ls())

# Implement Connect Four in the same manner that we
# implemented Tic-tac-toe in class. Start by implementing
# the helper functions, below, and testing them by running
#   source("hw3test.R")
# Then write code for the game itself.
#
# We'll test your code by running
#   source("hw3.R")
# We might also play Connect Four, read your code, and do other tests.

# Returns TRUE if vector v (of character strings) contains
# (at least) four in a row of player (character string). e.g.
#   four.in.a.row("X", c("O","X","X","X","X","O"))
# is TRUE, while
#   four.in.a.row("O", c("O","X","X","X","X","O"))
# is FALSE.
four.in.a.row = function(player, v, debug=FALSE) {
  if (debug) {
    cat(sep="", "four.in.a.row(player=", player, ", v=", v, ")\n")
  }
  with(rle(v), any(lengths== 4 & values == player))
}

# Returns TRUE if (matrix) board (of character strings)
# contains at least four in a row of (string) player, who
# just played in position (r, c). (Here "r" means "row" and
# "c" means "column").
#
# Hint: this function should call four.in.a.row() four times, once
# each for the current row, column, diagonal, and reverse diagonal.
won = function(player, board, r, c, debug=FALSE) {
  if (debug) {
    cat(sep="", "won(player=", player, ", board=\n")
    print(board)
    cat(sep="", ", r=", r, ", c=", c, ")\n")
  }
  row=board[r,]
  col=board[,c]
  diag=x[row(board) - col(board) == r - c]
  reverse_diag=board[row(board) + col(board) == r + c]
  return(four.in.a.row(player,row,debug=debug)  ||
           four.in.a.row(player,col,debug=debug)  ||
           four.in.a.row(player,diag,debug=debug) ||
           four.in.a.row(player,reverse_diag,debug=debug))
}

# Returns largest index of an empty position in column col
# of (matrix) board. If there is no such empty position in
# board, return value is NULL.
largest.empty.row = function(board, col, debug=FALSE) {
  if (debug) {
    cat(sep="", "largest.empty.row(board=\n")
    print(board)
    cat(sep="", ", col=", col, ")\n")
  }
  col=board[,col]
  empty_col=which(col=="E")
  if (length(empty_col)==0)
    return(NULL)
  return(max(empty_col))
}

source("hw3test.R") # Run tests on the functions above.

# ... your code to implement Connect Four using the
# functions above ...
par(pty="s") # square plot type
x = rep(1:7, each = 6)
y = rep(1:6, times = 7)

symbols(x, y, squares=rep(1, times=42),
        inches=FALSE, # match squares to axes
        xlim=c(0,8),
        ylim=c(7,0)) # flip y axis to match matrix format
board = matrix(rep("E", times=42), nrow=6, ncol=7)
player = "X"
for (i in 1:42) { # loop through 42 turns
  if (player == "X") {
    repeat { # get user input on empty board
      index = identify(x, y, n=1,plot=FALSE) # , plot=FALSE
      col = x[index]
      row=largest.empty.row(board, col)
      if (is.null(row)){
        next
      }else if (is.null(col)){
        next
      } else if (board[row, col] == "E") {
        break
      }else {next}
    }
  }
  else { # computer chooses randomly
    open.pos = which(c(board)=="E")
    index = sample(x=open.pos, size=1)
    #row = y[index]
    col = x[index]
    row=largest.empty.row(board, col)
  }
  board[row, col] = player
  text(x=col, y=row, labels=player)
  print(board)
  if (won(player, board, row, col)) {
    text(x=2, y=.2, labels=paste(player, "won!"), col="red")
    break
  }
  player = ifelse(player == "X", "O", "X")
}


# Hint: this program is modeled on the tic-tac-toe program we did in
# class, so studying the latter program is worthwhile.

# Note that a user click in a column indicates that a checker should
# go to that column's lowest empty row (unless the column is full).

# Note that you should implement a computer player. At the least, it
# should choose randomly from among the non-full columns. (Feel free
# to do much more! If your computer player beats me on its first try,
# you will earn a package of M&Ms. This is a hard task. Feel free to
# ask for tips.)
