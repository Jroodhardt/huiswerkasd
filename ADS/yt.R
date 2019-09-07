install.packages("readxl")
install.packages("corrplot")
library("readxl")
wine <- read_excel("D:/Downloads/Wine_data.xlsx")
library(corrplot)
corrplot(wine, method="circle")
setwd("c:/repos/ADS")
wine<- read.table("wine.txt", header=T, dec= ",",sep="\t", fill=T, check.names=F)
par(mfrow=c(1,2))


hist(wine$pH, breaks = 10,col="lightblue")
boxplot(wine$pH, col="lightblue" , horizontal = F, main="Boxplot")
text(x=1.30,y=fivenum(as.numeric(wine$pH)[0:4], labels=fivenum(wine$pH)[0:4])

hist(log(wine$sulphates), breaks = 30,col="lightblue", main = "Sulfaten rode wijn",xlab = "Sulfaten", ylab = "Frequentie")
boxplot(wine$sulphates, col="lightblue" , horizontal = F, main="Boxplot")
text(x=1.30,y=fivenum(wine$sulphates)[0:4], labels=fivenum(wine$sulphates)[0:4])

hist(my_data$sulphates, breaks = 30,col="lightblue",main = "Sulfaten witte wijn",xlab = "Sulfaten", ylab = "Frequentie")
boxplot(my_data$sulphates, col="lightblue" , horizontal = F, main="Boxplot")
text(x=1.30,y=fivenum(my_data$sulphates)[0:4], labels=fivenum(my_data$sulphates)[0:4])

hist(wine$quality, breaks = 6,col="lightblue", main="Wijnkwaliteit", xlab= "Wijnkwaliteit", ylab="Frequentie")
boxplot(wine$quality, col="lightblue" , horizontal = F, main="Boxplot")
text(x=1.30,y=fivenum(wine$quality)[0:4], labels=fivenum(wine$quality)[0:4])

hist(wine$alcohol, breaks = 9,col="lightblue")
boxplot(wine$alcohol, col="lightblue" , horizontal = F, main="Boxplot")
text(x=1.30,y=fivenum(wine$alcohol)[0:4], labels=fivenum(wine$alcohol)[0:4])
library(ggplot2)
ggplot(wine, aes(`citric acid`, `volatile acidity`)) +
  geom_point() +
  geom_smooth(method=lm) + labs(title="citric vs volatile")       
ggplot(wine, aes( pH,  `fixed acidity`)) +
  geom_point() +
  geom_smooth(method=lm) + labs(title="pH vs fixed acidity")    
ggplot(wine, aes( sulphates,  quality)) +
  geom_point() +
  geom_smooth(method=lm) + labs(title="sulphates vs quality")  
ggplot(wine, aes( pH,  `citric acid`)) +
  geom_point() +
  geom_smooth(method=lm) + labs(title="pH vs citric acids")  
ggplot(wine, aes( chlorides,  sulphates)) +
  geom_point() +
  geom_smooth(method=lm) + labs(title="chlorides vs sulphates")  

factor <- factor(quality, levels=c(0,1,2,3,4,5,6,7,8,9,10))


alcohol_mean <- mean(alcohol)
alcohol_median <- median(alcohol)
quality_mean <- mean(quality)
quality_median <- median(quality)
quality_min <- min(quality)
quality_max <- max(quality)
tapply(alcohol, quality, mean)

ggplot(data=wine, aes(x=factor, y=alcohol)) +
  geom_boxplot() +
  xlab("Quality") +
  ylab("Alcohol")
ggplot(data=wine, aes(x=factor, y=sulphates)) +
  geom_boxplot() +
  xlab("Quality") +
  ylab("Sulphates")

scale.features <- function(df, variables){
  for (variable in variables){
    df[[variable]] <- scale(df[[variable]], center=T, scale=T)
  }
  return(df)
}

attach(wine)
numeric.vars <- c(`fixed acidity`, `volatile acidity`, `citric acid`,`residual sugar`,chlorides,`free sulfur dioxide`,`total sulfur dioxide`, density, pH, sulphates,alcohol)
credit.df <- scale.features(wine,numeric.vars)
nn.model <- train(formula.init, data = transformed.train, method="nnet") 
