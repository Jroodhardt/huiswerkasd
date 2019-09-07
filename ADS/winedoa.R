
#Model 1 Random Forest original
set.seed(100)
train<-sample(nrow(wine_data),0.7*nrow(wine_data),replace = F)
trainset<-wine_data[train,]
validset<-wine_data[-train,]
summary(trainset)
model1<-randomForest(quality~.,data=trainset,importance=T)
model1
predtrain<-predict(model1,trainset,type="class")
table(round(predtrain),trainset$quality)
validtrain<-predict(model1,validset,type="class")
table(round(validtrain),validset$quality)
mean(round(validtrain)==validset$quality)
mean(round(predtrain)==trainset$quality)
plot(model1,main= "Model 1: Random Forest error graph")
varImpPlot(model1, main= "Random Forest model 1")
importance(model1)

#Model 2 Random Forest optimal parameters
wine_qualfac<-wine_data
wine_qualfac$quality <- as.factor(wine_qualfac$quality)
set.seed(100)
train<-sample(nrow(wine_qualfac),0.7*nrow(wine_qualfac),replace = F)
trainset<-wine_qualfac[train,]
validset<-wine_qualfac[-train,]
summary(trainset)
model2<-randomForest(quality~.,data=trainset,importance=T,ntree=300,mtry=5)
model2
predtrain<-predict(model1,trainset,type="class")
table(predtrain,trainset$quality)
validtrain<-predict(model1,validset,type="class")
table(validtrain,validset$quality)
mean(validtrain==validset$quality)
mean(predtrain==trainset$quality)
plot(model1,main= "Model 2: Random Forest error graph")
varImpPlot(model1, main= "Random Forest model 2")
importance(model1)
#Model 3 Decision tree rpart
set.seed(100)
train<-sample(nrow(wine_qualfac),0.60*nrow(wine_qualfac),replace = F)
trainset<-wine_qualfac[train,]
validset<-wine_qualfac[-train,]
library(partykit)
model3<-rpart(quality~.,wine_data_train,method="class")
plot(partykit::as.party(model3))
print(model3)
predtrain <- predict(model3, trainset, type="class")
table(predtrain,trainset$quality)
mean(predtrain==trainset$quality)
validtrain <- predict(model3, validset, type="class")
table(validtrain,validset$quality)
mean(validtrain==validset$quality)