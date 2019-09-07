library(RODBC) 
bron <- odbcConnect("AdventureWorks2014")
df1 <- sqlFetch(bron, "Sales.SalesOrderHeader")
head(df1)

df2 <- data.frame(df1$SalesOrderID, df1$TotalDue)
plot(df2)

plot(df1$TotalDue ~ df1$SalesOrderID)

query1 <- "select SalesOrderID, TotalDue from Sales.SalesOrderHeader order by
SalesOrderID"
df3 <- sqlQuery(bron, query1)
plot(df3)
###################################################################################

library(RODBC) 
bron <- odbcConnect("Cars")

################################################################################
library(RODBC) 
bron <- odbcConnect("AdventureWorks2014")
df1 <- sqlFetch(bron, "Sales.SalesOrderHeader")
head(df1)

df2 <- data.frame(df1$SalesOrderID, df1$TotalDue)
plot(df2)

plot(df1$TotalDue ~ df1$SalesOrderID)

query1 <- "select top 2 SalesPersonID,sum(s.Totaldue)/count(s.SalesOrderID) as q from Sales.SalesOrderHeader s group by
s.SalesPersonID
order by (q) desc"

query2 <- "select SalesPersonID,sum(s.Totaldue)/count(s.SalesOrderID) as q from Sales.SalesOrderHeader s group by
s.SalesPersonID
order by (q) desc"


df3 <- sqlQuery(bron, query1)
df4<- sqlQuery(bron, query1)
plot(df4)

wilcox.test( round(df4$q),round(df3$q) ) 
#################################################################################

setwd("c:/repos/ADS")
dfcor <- read.table("CorrelationExampleBook22_1.txt", header=T, sep="\t")

plot(Perc.FA ~ Ins.Sens)
line1 <- lm(Perc.FA ~ Ins.Sens)
intercept1 <- unname(line1$coefficients[1])
slope1 <- unname(line1$coefficients[2]) 

from_x <- min(Ins.Sens)
from_y <- intercept1 + (slope1 * min(Ins.Sens))
to_x <- max(Ins.Sens)
to_y <- intercept1 + (slope1 * max(Ins.Sens))
segments(from_x, from_y, to_x, to_y) 
confint(line1, level=0.95)
abline(intercept1, slope1)

xval <- data.frame(Ins.Sens=200)
predict(line1, xval, level=0.95)
 ###ez
library(ggplot2)

ggplot(dfcor, aes(x=Ins.Sens, y=Perc.FA)) +
  geom_point() +
  geom_smooth(method = "lm", formula = y ~ splines::bs(x, 3), se = FALSE)
