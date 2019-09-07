modejp <- function(v)
  # for v: a vector without any NA-values
  # table(v) ??? a table with the # of occurrences of each value from v
  #			with in its first row ("names"): each distinct value in v.
  #			So return a "names"-value itself if v is a string vector,
  #			or return the numeric equivalent if v is a numeric vector.
{			tv <- table(v)
if(mode(v)=="character")
{return(names(tv[tv==max(tv)]))}
{return(as.numeric(names(tv[tv==max(tv)])))}}

#1.3.2
plot(df3)
s1 <- c(4,15,6,18,5,18,4,18,6,12)
s2 <- c(4,15,6,18,5,18,4,18,6,120)
mean(s1)
modejp(s1)
median(s1)
mean(s2)
modejp(s2)
median(s2)
