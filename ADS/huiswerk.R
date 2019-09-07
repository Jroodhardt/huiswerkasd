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
 
#a
mean(s1)
modejp(s1)
median(s1)
mean(s2)
modejp(s2)
median(s2)
#b
#De mean verschilt behoorlijk maar de mode blijft hetzelfde.

#c
#De mean is een realistisch gemiddelde maar de median zorgt er voor dat de uitliggers genegeerd worden, dit is goed als dit foute data kan zijn.

#d
#De median want 120 kan foute data zijn.


#1.3.3
library(RODBC) 
bron2 <- odbcConnect("GelreAirport")
query1 <- "Select flightnumber, max_number_psgrs from Flight"
df4 <- sqlQuery(bron2,query1)
plot(df4)
median<-median(df4$max_number_psgrs)
mode<-modejp(df4$max_number_psgrs)
mean<-mean(df4$max_number_psgrs)
abline(h=median, col="red")
abline(h=mode, col="blue")
abline(h=mean, col="yellow")

#1.3.4
#a
#Dit verschilt alsnog per persoon en de eerste interpretatie is moeilijk


#1.3.5
#a
#quantitative
#b
#pro: minder datagebruik con: geen mogelijkheid om 999999 te overschreiden


#1.3.6
sd(df2$df1.TotalDue)
sd(df4$max_number_psgrs)
sd(s1)
sd(s2)


#1.4
# 380415

collier <- data.frame(year=c("1952 1.3","1952 4","1953 1"),payroll=c(3104729,3140709,3205000))

plot(collier, ylim=c(0,400000000))
barplot(collier$payroll, names.arg=collier$year)

cor(hills)

#2.3.1
x <- 1:100
t.test(x)$conf.int
round(t.test(x, conf.level=0.9)$conf.int, digits=2)

x <- c(1,2,3,4,5,6,7,8,9,10)
y <- c(3,4,5,6,7,8,9,10,11,12,13) 

t.test(x, y, paired=F, var.equal=T)
t.test(x, y, paired=F, var.equal=F)


#2.3.3
mean(df1$SalesOrderNumber)


#2.3.4
library(RODBC) 
bron <- odbcConnect("AdventureWorks2014")
df1 <- sqlFetch(bron, "Sales.SalesOrderDetail")
query1 <- "SELECT TOP 100 Sales.SalesOrderDetail.UnitPrice
FROM Sales.SalesOrderDetail"
query2 <- "SELECT TOP 100 Sales.SalesOrderDetail.UnitPrice FROM Sales.SalesOrderDetail ORDER BY SalesOrderDetailID desc"


df3 <- sqlQuery(bron, query1)
df4 <- sqlQuery(bron, query2)
mean(df3$UnitPrice)
mean(df4$UnitPrice)

sd.pop <- function(v) {l=length(v); sd(v)*sqrt((l-1)/l)}
sd.pop(df3)
sapply(df3,sd)
binom.test(64, 100, p=0.5, conf.level=0.95).



#2.3.5
setwd("c:/repos/DW")
df.raw <- read.table("Demo.txt", header=T, sep="\t", fill=T, check.names=F)
# Header=T warns R that the first row contains headers instead of values
# fill=T puts "NA" (Not Applicable, the NULL-value in R) where values are missing
# check.names=F prevents R from changing the names (adding an 'x') in the top row
m <- unname(sapply(df.raw, mean, na.rm=T))
# sapply(df.raw, <function>) applies the function to all the parts (columns) of df.raw,
# and simplifies the result to a vector or matrix if possible.
# In this case, the result is a named vector. The names are the column names from df.raw.
# na.rm=T (rm means remove) ignores the NA values, otherwise the result would also be NA.
# unname(V1) removes the names from V1, so the final result is a vector with just the means.
s <- unname(sapply(df.raw, sd, na.rm=T))
n <- as.numeric(names(df.raw))
# names (df.raw) returns the names (top row) of df.raw: ("2012", "2013", .).
# These names are string constants.
# as.numeric(V1) converts the strings in V1 to numbers (integers in this case).
df.plot <- data.frame(year=n, mean=m, sd=s)
# To make the desired plot, we need a data frame with three columns for year, mean and sd
xv.mat<-barplot(df.plot$mean,names.arg = df.plot$year,ylim=c(0,12),xlim=c(0,6), width=0.5,
xlab="year",ylab="complaints", legend.text="mean + SD")
# barplot() not only draws a barplot,
# but is also a function that yields a matrix containing # the x-values of the bar centers:
# We need those to position the error bars, and store them in 'xv.mat.
# ylim=c(0,12) makes enough vertical room to add the vertical error bars on top of the bars.
# xlim=c(0,6) and width=0.5: narrows the plotted bars.
# Note: 'width' works only if 'xlim' is specified as well.
xv.vec <- xv.mat[,1]
# xv.mat is a matrix, and this extracts all the values in the first column,
# returning a vector with just the x-values without the row names and column name.
# An alternative way is to use: xv.vec <- as.numeric(xv.mat).
segments(xv.vec, df.plot$mean, xv.vec, df.plot$mean+df.plot$sd)
# segments(x_from, y_from, x_to, y_to) adds a line segment to the current plot.
# This adds the vertical error bar lines to all the bars.
# With vectors as arguments no for-loop is needed (vectors must have the same length).
segments(xv.vec-0.1, df.plot$mean+df.plot$sd, xv.vec+0.1, df.plot$mean+d)
install.packages("ggplot2") 
library(ggplot2) 
ggplot(df.plot,aes(x=year,y=mean))+ 
  geom_bar(stat="identity",color="black",fill="grey")+
  geom_errorbar(aes(ymin=mean,ymax=mean+sd),width=0.3) +
  labs(title="mean+SD",x="year",y="Complaints")

t.test(1:10,mu=0.0,conf.level = 0.95)


#3.5.1
#a. b
#b. if AGE=34 THEN you have a driving license
#   NONE WHERE AGE<18 have driving license
#   if !drivingslicence THEN AGE<18


#3.5.2
binom.test(48,48+95,p=0.4,conf.level = 0.95)
#The company therefore expects that
#of the total number of items of P1 and P2 sold, P1 will sell at a proportion of
#40%, with 60% for P2. So far, 48 items of P1 were sold, and 95 of P2. \

#3.5.3
binom.test(4,10,p=0.1,conf.level = 0.95)

#3.5.4
x1 <- c(1,2,3,4,5,6,7,8,9,10)
y1 <- c(3,4,5,6,7,8,9,10,11,12,13) 
t.test(x1,mu=4,con=0.95)
#If the CI does not contain the null-hypothesis, then the P-value is less than the treshold specified
t.test(x1,mu=8,con=0.95)

t.test(x1,mu=4,con=0.99)


x2 <- c(4.7, 3.6, 5.8, 4.5, 6.1, 8.1, 6.0)
y2 <- c(2.3, 3.7, 3.6, 1.8, 3.9, 4.9, 6.9)
t.test(1,2,paired=T)
#If the CI does not contain the null-hypothesis, then the P-value is less than the treshold specified


#4.2.1
setwd("c:/repos/ADS")
df.raw <- read.table("356.txt", header=T, sep="\t", fill=T, check.names=F)
correl<-cor.test(df.raw$Before,df.raw$After, method = "pearson")

#4.2.2
cor.test(df1$TotalDue,df1$TaxAmt,method = "pearson")

cor.test(df1$ShipMethodID,df1$TotalDue)

#4.2.3
library(ggplot2)
ggplot(df.raw, aes(x=df.raw$Subject, y=df.raw$Rate))+
  geom_point() +
  geom_smooth(method=lm)
#4.2.4
cor.test(df.raw$Rate[df.raw$BorA=="B"],df.raw$Rate[df.raw$BorA=="A"],method = "pearson")
