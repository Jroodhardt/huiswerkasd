##Let's start the lessons in Machine Learning by loading a file containing
##customer credit ratings of customers of a German Bank (bit old, still in ## currency DM)
##for you youngsters, DM = Deutsche Mark, one DM in 2000 was worth about half a Euro nowadays 

#This is PART 1 of a script to accompany presentations 3a and 3b about
##data exploration, chapter 3 in your course textbook Predictive 
##Analytics 

##in my case the file is downloaded from the internet to the root folder 
##of my C drive  

##read the data into an R dataframe 
credit.df<-read.csv("C:/repos/ads/german_credit_dataset.csv", header = TRUE, sep = ",")

##note, reading the file into an R data frame straight from the url location 
##is also possible 

##check the dataframe structure

str(credit.df)

######result set of variables, note, all features are of type integer (hmmm, bit weird)
'data.frame':   1000 obs. of  21 variables:
 $ credit.rating                 : int  1 1 1 1 1 1 1 1 1 1 ...
 $ account.balance               : int  1 1 2 1 1 1 1 1 4 2 ...
 $ credit.duration.months        : int  18 9 12 12 12 10 8 6 18 24 ...
 $ previous.credit.payment.status: int  4 4 2 4 4 4 4 4 4 2 ...
 $ credit.purpose                : int  2 0 9 0 0 0 0 0 3 3 ...
 $ credit.amount                 : int  1049 2799 841 2122 2171 2241 3398 1361 1098 3758 ...
 $ savings                       : int  1 1 2 1 1 1 1 1 1 3 ...
 $ employment.duration           : int  2 3 4 3 3 2 4 2 1 1 ...
 $ installment.rate              : int  4 2 2 3 4 1 1 2 4 1 ...
 $ marital.status                : int  2 3 2 3 3 3 3 3 2 2 ...
 $ guarantor                     : int  1 1 1 1 1 1 1 1 1 1 ...
 $ residence.duration            : int  4 2 4 2 4 3 4 4 4 4 ...
 $ current.assets                : int  2 1 1 1 2 1 1 1 3 4 ...
 $ age                           : int  21 36 23 39 38 48 39 40 65 23 ...
 $ other.credits                 : int  3 3 3 3 1 3 3 3 3 3 ...
 $ apartment.type                : int  1 1 1 1 2 1 2 2 2 1 ...
 $ bank.credits                  : int  1 2 1 2 2 2 2 1 2 1 ...
 $ occupation                    : int  3 3 2 2 2 2 2 2 1 1 ...
 $ dependents                    : int  1 2 1 2 1 2 1 2 1 1 ...
 $ telephone                     : int  1 1 1 1 1 1 1 1 1 1 ...
 $ foreign.worker                : int  1 1 1 2 2 2 2 2 1 1 ...
#######

##check-out some rows 
head(credit.df) #shows by default the first 6 rows and the header

######result set
credit.rating account.balance credit.duration.months previous.credit.payment.status credit.purpose credit.amount savings employment.duration
1             1               1                     18                              4              2          1049       1                   2
2             1               1                      9                              4              0          2799       1                   3
3             1               2                     12                              2              9           841       2                   4
4             1               1                     12                              4              0          2122       1                   3
5             1               1                     12                              4              0          2171       1                   3
6             1               1                     10                              4              0          2241       1                   2
  installment.rate marital.status guarantor residence.duration current.assets age other.credits apartment.type bank.credits occupation dependents
1                4              2         1                  4              2  21             3              1            1          3          1
2                2              3         1                  2              1  36             3              1            2          3          2
3                2              2         1                  4              1  23             3              1            1          2          1
4                3              3         1                  2              1  39             3              1            2          2          2
5                4              3         1                  4              2  38             1              2            2          2          1
6                1              3         1                  3              1  48             3              1            2          2          2
  telephone foreign.worker
1         1              1
2         1              1
3         1              1
4         1              2
5         1              2
6         1              2
#######

##We'll focus now on data exploration and cleaning, transforming and normalizing the data
##This is a very crucial step of/in the CRISP-DM/ML process framework

##check for missing values
##function is.na returns a 0  if one of the feautures in a row of the frame 
##containts a null value, otherwise a 1 is returned. You can sum the values for all rows and 
##test for the sum to be zero: sum == 0
sum(is.na(credit.df))
#[1] 0

##the sum of the number of is.na datacells = 0, meaning no missing data!
##you are extremely lucky because this usually is not the case and requires a lot of cleaning actions!

#kind of opposite like function, result should be 1000
sum(complete.cases(credit.df))
#[1] 1000

##nice dataset! very fortunate situation but highly unrealistic in a real world case

##we need to do quite some transforming because only the 
##credit.duration.months, credit.amount and age 
##are true numerical variables. the other variables are numerically coded categorial variables. 
##they need to be transformed into factors

##an example transformation of a variable (as.factor() used as transformation function)

credit.df$credit.rating<-as.factor(credit.df$credit.rating)
str(credit.df)

##data.frame':   1000 obs. of  21 variables:
######
'data.frame':   1000 obs. of  21 variables:
 $ credit.rating                 : Factor w/ 2 levels "0","1": 2 2 2 2 2 2 2 2 2 2 ...
######

##good luck, only 17 to go :-)

##okay,okay I found some help in/on the Internet. The German credit data is used/referenced 
##a lot in courses and even in books about Data Science and R/Python
##so a lot of R code is already available helping us out

##let's create a nice uitility function to transform numeric features to catagorial features
##a factor is a data type in R representing categorial data like color = red, bule, green,...
##but storing these internally as integer which is far more efficient
##obviously the German Credit dataset cobtains a lot of categorial features.

##Oh, why in English? Perhaps in the future foreign students want to take the course AIS,
##that's why. Maar ik ben niet erg consequent daarin :-)

##refer to the meta datafile on OnderwijsOnline explaining the meaning of all the features  
##the data has been published by a German professor at the Department of Statistics of the University
##of Munich some 14 years ago. Name of professor is unknown me, but all credits go to this man/woman!

##create the function
#df is the parameter used for passing in an R dataframe structure, variables is a vector parameter
#containing the features to transform to factors, categorial datastructures

to.factors <- function(df, variables){
  for (variable in variables){
    df[[variable]] <- as.factor(df[[variable]])
  }
  return(df)
}

# here's the vector of features to transform, study the metadata descriptions on OO!
categorical.vars <- c('credit.rating', 'account.balance', 'previous.credit.payment.status',
                      'credit.purpose', 'savings', 'employment.duration', 'installment.rate',
                      'marital.status', 'guarantor', 'residence.duration', 'current.assets',
                      'other.credits', 'apartment.type', 'bank.credits', 'occupation',
                      'dependents', 'telephone', 'foreign.worker')

# transform data types
credit.df <- to.factors(df = credit.df, variables = categorical.vars) #binding variables to parameters

# verify transformation in data frame details
str(credit.df)

######
'data.frame':   1000 obs. of  21 variables:
 $ credit.rating                 : Factor w/ 2 levels "0","1": 2 2 2 2 2 2 2 2 2 2 ...
 $ account.balance               : Factor w/ 4 levels "1","2","3","4": 1 1 2 1 1 1 1 1 4 2 ...
 $ credit.duration.months        : int  18 9 12 12 12 10 8 6 18 24 ...
 $ previous.credit.payment.status: Factor w/ 5 levels "0","1","2","3",..: 5 5 3 5 5 5 5 5 5 3 ...
 $ credit.purpose                : Factor w/ 10 levels "0","1","2","3",..: 3 1 9 1 1 1 1 1 4 4 ...
 $ credit.amount                 : int  1049 2799 841 2122 2171 2241 3398 1361 1098 3758 ...
 $ savings                       : Factor w/ 5 levels "1","2","3","4",..: 1 1 2 1 1 1 1 1 1 3 ...
 $ employment.duration           : Factor w/ 5 levels "1","2","3","4",..: 2 3 4 3 3 2 4 2 1 1 ...
 $ installment.rate              : Factor w/ 4 levels "1","2","3","4": 4 2 2 3 4 1 1 2 4 1 ...
 $ marital.status                : Factor w/ 4 levels "1","2","3","4": 2 3 2 3 3 3 3 3 2 2 ...
 $ guarantor                     : Factor w/ 3 levels "1","2","3": 1 1 1 1 1 1 1 1 1 1 ...
 $ residence.duration            : Factor w/ 4 levels "1","2","3","4": 4 2 4 2 4 3 4 4 4 4 ...
 $ current.assets                : Factor w/ 4 levels "1","2","3","4": 2 1 1 1 2 1 1 1 3 4 ...
 $ age                           : int  21 36 23 39 38 48 39 40 65 23 ...
 $ other.credits                 : Factor w/ 3 levels "1","2","3": 3 3 3 3 1 3 3 3 3 3 ...
 $ apartment.type                : Factor w/ 3 levels "1","2","3": 1 1 1 1 2 1 2 2 2 1 ...
 $ bank.credits                  : Factor w/ 4 levels "1","2","3","4": 1 2 1 2 2 2 2 1 2 1 ...
 $ occupation                    : Factor w/ 4 levels "1","2","3","4": 3 3 2 2 2 2 2 2 1 1 ...
 $ dependents                    : Factor w/ 2 levels "1","2": 1 2 1 2 1 2 1 2 1 1 ...
 $ telephone                     : Factor w/ 2 levels "1","2": 1 1 1 1 1 1 1 1 1 1 ...
 $ foreign.worker                : Factor w/ 2 levels "1","2": 1 1 1 2 2 2 2 2 1 1 ...
######

##so we now have our features neately transformed to factors

## a bunch of utily functions are provided below helping you to perform basic descriptive analytical tasks

##some extra R packages need to be installed

##Go to a CRAN repository site, download and install the required packages (names listed below)
##than load the libs
library(gridExtra) ##grid layouts
library(pastecs) ## details summary stats
library(ggplot2) ## high quality visualizations, zelfs van publicatie waardige kwaliteit!
library(gmodels) ## build contingency tables -- tables used to calculate measures like the 
##number of false positives, false negatives, accuracy of predictions, etc.   

##utility functions (thanks to the open source R community utilities)  can be found easily 
##its suits our/my needs very well!

# analyze numerical variables
# produce summary statistics

#eigen functie creëren
#begin van de functie specificatie			
get.numeric.variable.stats <- 
function(indep.var, detailed=FALSE){
  options(scipen=100) #als meer dan 100 cijfers dan wordt exponentiële getalnotatie gebruikt
  options(digits=2) #afronden tot max 2 achter de comma
  if (detailed){
    var.stats <- stat.desc(indep.var) 
	#function stat.desc computes a table giving us
	#various descriptive statistics
  }else{
    var.stats <- summary(indep.var) 
	#function summary is a generic function used to produce result summaries
	#summaries van een type dat afhangt van het type van de parameter en dat is in dit geval een dataframe
  }

#nb de parameterwaarde van parameter detailed overruled the default die op false staat. zonder waarde
#geen gedetailleerde descriptieve stats maar slechts summaries, een soort van statistische
#samenvatting dus 

df <- data.frame(round(as.numeric(var.stats),2)) 
#data.frame maakt een data frame bestaand uit zijn argumenten
#dat zijn in dit geval de descriptieve statische waarden behorend bij de set 
#van independent variables die wij feautures zullen noemen in AIS in navolging van
#het course textbook PredAnalytics

colnames(df) <- deparse(substitute(indep.var))
#substitute retouneert de parse tree van de set aan independant variables die via de parameter
#de functie ingesluisd worden, deparse pakt de boom uit en retourneert de componenten uit de 
#parse tree als strings, zeg maar labels te die als kolomnamen in het data frame in varaibel df 
#worden toegevoegd

  rownames(df) <- names(var.stats)
#gepruts om een  grafisch device to openen waarin een grid gemaakt kan worden
  if (names(dev.cur()) != "null device"){
    dev.off()
  }
  grid.table(t(df)) 
#nice, produces a grid like overview of basic statistical information
} 

##eind functie specificatie 

##nu we aangegeven hebben hoe de statistische kentallen geproduceerd 
##kunnen worden met R (en in welke mate van detail)
##volgt de zeer belangrijke stap van bepalen van het visualiseren van data (zie ook
##Jan Pieter zijn lessen statistiek; geen statistische analyse zonder slimme
##handige plaatjes van je dat te hebben gemaakt).

# visualizations

#gplot uit package ggplot2 is een krachtpatser functie die
#ingezet wordt voor het maken van histogrammen en boxplots
#voor meer informatie bekijk de functiespecificatie en zoek op
#internet (de R documentatie helpt je soms wel, maar vaak
#ook van de regen in de drup). de community is echter enorm
#groot en er is verschrikkelijk veel te vinden!

#independent variables (feautures voor ons) are the feautures
#we assume can be used to predict a particular outcome 
#(rating in this German Bank case, the dependent variable, eh feature, or target
#feature)

# histograms\density plot
visualize.distribution <- function(indep.var){ 
  pl1 <- qplot(indep.var, geom="histogram",
               fill=I('gray'), binwidth=5,
               col=I('black'))+ theme_bw()
  pl2 <- qplot(indep.var, geom="density",
               fill=I('gray'), binwidth=5,
               col=I('black'))+ theme_bw()

  grid.arrange(pl1,pl2, ncol=2)?qplo
}

# box plots
visualize.boxplot <- function(indep.var, dep.var){
  pl1 <- qplot(factor(0),indep.var, geom="boxplot",
               xlab = deparse(substitute(indep.var)),
               ylab="values") + theme_bw()
  pl2 <- qplot(dep.var,indep.var,geom="boxplot",
               xlab = deparse(substitute(dep.var)),
               ylab = deparse(substitute(indep.var))) + theme_bw()

  grid.arrange(pl1,pl2, ncol=2)
}

#tussen neus en lippen door switchen we nu naar de 
#analyse van de categoriale feautures (hiervoor betrof alles
#de analyse van numerieke features 

# analyze categorical variables
# summary statistics
get.categorical.variable.stats <- function(indep.var){
#twee sets aan getallen, nml de frequentie en de proportie
#worden bepaald en in een gridachtig tabelletje getoond 
  feature.name = deparse(substitute(indep.var))
  df1 <- data.frame(table(indep.var))
  colnames(df1) <- c(feature.name, "Frequency")
  df2 <- data.frame(prop.table(table(indep.var)))
  colnames(df2) <- c(feature.name, "Proportion")
  df <- merge(
    df1, df2, by = feature.name
  )
  ndf <- df[order(-df$Frequency),]
  if (names(dev.cur()) != "null device"){
    dev.off()
  }
  grid.table(ndf)
}


#een contingency table die hierna wordt opgetuigd
#voor de catagoriale independent variables, nogmaals, die features die
#we zien als mogelijk target verklarende voorspellende features
#is een tabel waarin de relatie tussen de feauature waarden en de target waarden wordt 
#aangegeven in aantallen

#dus die tabel bevat iets als
#er zijn 60 gevallen van mensen die minder dan 200 DM op de rekening
#courant hebben staan met een negatieve credit rating
#er zijn 397 gevallen van mensen die kinder dan 200 DM op de rekening
#courant hebben staan met een positieve credit rating

#dit dan wel zeer compact weergegeven van alle mogelijk posities
#qua rekening courant en credit rating.

#jullie "ruiken" straks de verbanden tussen de data elementen op basis van dit soort 
#overzichten en kunnen die dan soepeltje uitdrukken in harde cijfers zoals bv correlatie 
# coëfficiënten en (eventuele) lineaire of polynomiale regressie grafieken 
#daar gaat ie dan in R
# generate contingency table



#nu weer plaatjes bij de cijfertjes bakken

# visualizations
# barcharts
visualize.barchart <- function(indep.var){
  qplot(indep.var, geom="bar",
        fill=I('gray'), col=I('black'),
        xlab = deparse(substitute(indep.var))) + theme_bw()
}

# mosaic plots
visualize.contingency.table <- function(dep.var, indep.var){
  if (names(dev.cur()) != "null device"){
    dev.off()
  }
#mosaic is een soort van tegeldiagram waarin de tegelgrootte de onderlinge verhoudingen  #representeert, leuk, snel een visueel inzicht verschaffend 
  mosaicplot(dep.var ~ indep.var, color=T,
             main = "Contingency table plot")
}


####HERE's WHERE THE ANALYSIS PART STRATS


##with these very useful utilities we can easily get the necessary insight in the data
##by visualizing distributions and by producing basic stats in number format in crosstab
#overview 

##start to get acquainted with the German data :-)

##okay, maar houdt in je achterhoofd dat 2019 en 1999 niet echt te vergelijken zijn. de 
#economische toestand onderling onvergelijkbaar is...  

##LET OP, ER WORDEN ENKELE FEATURE ENGINEERING STAPPEN UITGEVOERD
##WAAR IK NU NIET DIEP OP IN GA. MAAR, EEN ZEER BELANGRIJK ASPECT VAN DATA EXPLO
##RATIE IS DATA PREPARATIE. EEN STAP IS HET VERWIJDEREN VAN NUTTELOZE DATA OM
##DE PROCESSING BELASTING TE MINIMALISEREN.
##JE SNOEIT BEWUST ZAKEN WEG!   

#load another dependency
install.packages("car")
library(car)

#attach because it makes referencing of features a lot simpler, less verbode
attach(credit.df)


# credit.rating stats
get.categorical.variable.stats(credit.rating)

#so we have 300 rating 0 customers and 700 rating 1 customers meaning....
#get your metadata file from OO!! YOUR GET LOST WITHOUT IT :-)

##1 means customer is credit worthy, 0 means customer is credit unworthy
##het target dus!

# credit.rating visualizations
visualize.barchart(credit.rating)

##histogram with two bars

# account.balance stats and bar chart
get.categorical.variable.stats(account.balance)
visualize.barchart(account.balance)

# recode classes and update data frame
new.account.balance <- recode(account.balance,
                          "1=1;2=2;3=3;4=3") ##snoeien hier, een categorie wordt gemerged
credit.df$account.balance <- new.account.balance

# contingency table and mosaic plot
get.contingency.table(credit.rating, new.account.balance, stat.tests=TRUE)
visualize.contingency.table(credit.rating, new.account.balance)


# credit.duration.months analysis
get.numeric.variable.stats(credit.duration.months)

# histogram\density plot
visualize.distribution(credit.duration.months)

# box plot
visualize.boxplot(credit.duration.months, credit.rating)


# previous.credit.payment.status stats and bar chart
get.categorical.variable.stats(previous.credit.payment.status)
visualize.barchart(previous.credit.payment.status)

# recode classes and update data frame
new.previous.credit.payment.status <- recode(previous.credit.payment.status,
                                             "0=1;1=1;2=2;3=3;4=3")
credit.df$previous.credit.payment.status <- new.previous.credit.payment.status

# contingency table
get.contingency.table(credit.rating, new.previous.credit.payment.status)


# credit.purpose stats and bar chart
get.categorical.variable.stats(credit.purpose)
visualize.barchart(credit.purpose)

# recode classes and update data frame
new.credit.purpose <- recode(credit.purpose,"0=4;1=1;2=2;3=3;
                                             4=3;5=3;6=3;7=4;
                                             8=4;9=4;10=4")
credit.df$credit.purpose <- new.credit.purpose

# contingency table
get.contingency.table(credit.rating, new.credit.purpose)


# credit.amount analysis
get.numeric.variable.stats(credit.amount)

# histogram\density plot
visualize.distribution(credit.amount)

# box plot
visualize.boxplot(credit.amount, credit.rating)


# feature: savings - recode classes and update data frame
new.savings <- recode(savings,"1=1;2=2;3=3;
                               4=3;5=4")
credit.df$savings <- new.savings

# contingency table
get.contingency.table(credit.rating, new.savings)


# feature: employment.duration - recode classes and update data frame
new.employment.duration <- recode(employment.duration,
                                  "1=1;2=1;3=2;4=3;5=4")
credit.df$employment.duration <- new.employment.duration

# contingency table
get.contingency.table(credit.rating, new.employment.duration)


# feature: installment.rate - contingency table and statistical tests
get.contingency.table(credit.rating, installment.rate,
                     stat.tests=TRUE)


# feature: marital.status - recode classes and update data frame
new.marital.status <- recode(marital.status, "1=1;2=1;3=3;4=4")
credit.df$marital.status <- new.marital.status

# contingency table
get.contingency.table(credit.rating, new.marital.status)


# feature: guarantor - recode classes and update data frame
new.guarantor <- recode(guarantor, "1=1;2=2;3=2")
credit.df$guarantor <- new.guarantor

# perform statistical tests
fisher.test(credit.rating, new.guarantor)
chisq.test(credit.rating, new.guarantor)


# perform statistical tests for residence.duration
fisher.test(credit.rating, residence.duration)
chisq.test(credit.rating, residence.duration)


# perform statistical tests for current.assets
fisher.test(credit.rating, current.assets)
chisq.test(credit.rating, current.assets)


# age analysis
get.numeric.variable.stats(age)

# histogram\density plot
visualize.distribution(age)

# box plot
visualize.boxplot(age, credit.rating)


# feature: other.credits - recode classes and update data frame
new.other.credits <- recode(other.credits, "1=1;2=1;3=2")
credit.df$other.credits <- new.other.credits

# perform statistical tests
fisher.test(credit.rating, new.other.credits)
chisq.test(credit.rating, new.other.credits)


# perform statistical tests for apartment.type
fisher.test(credit.rating, apartment.type)
chisq.test(credit.rating, apartment.type)


# feature: bank.credits - recode classes and update data frame
new.bank.credits <- recode(bank.credits, "1=1;2=2;3=2;4=2")
credit.df$bank.credits <- new.bank.credits

# perform statistical tests
fisher.test(credit.rating, new.bank.credits)
chisq.test(credit.rating, new.bank.credits)


# perform statistical tests for occupation
fisher.test(credit.rating, occupation)
chisq.test(credit.rating, occupation)


# perform statistical tests for dependents
fisher.test(credit.rating, dependents)
chisq.test(credit.rating, dependents)


# perform statistical tests for telephone
fisher.test(credit.rating, telephone)
chisq.test(credit.rating, telephone)


# perform statistical tests for foreign.worker
fisher.test(credit.rating, foreign.worker)
chisq.test(credit.rating, foreign.worker)

##

##STOP HIER, DE REST KOMT LATER AAN BOD!

## Save the transformed dataset
write.csv(file='credit_dataset_final.csv', x = credit.df,
          row.names = F)

EXTRA FUNCTION voor normaliseren

## normalizing - scaling
scale.features <- function(df, variables){
  for (variable in variables){
    df[[variable]] <- scale(df[[variable]], center=T, scale=T)
  }
  return(df)
}

# normalize variables
numeric.vars <- c("credit.duration.months", "age", "credit.amount")
credit.df <- scale.features(credit.df, numeric.vars)

credit.df[,credit.amount)

EXTRA FUNCTIES voor creëren van training en test sets

# split data into training and test datasets in 60:40 ratio
indexes <- sample(1:nrow(credit.df), size=0.6*nrow(credit.df))
train.data <- credit.df[indexes,]
test.data <- credit.df[-indexes,]

#
library(caret)  
# feature selection algorithm
library(randomForest) # random forest algorithm

# rfe based feature selection algorithm
run.feature.selection <- function(num.iters=20, feature.vars, class.var){
  set.seed(10)
  variable.sizes <- 1:10
  control <- rfeControl(functions = rfFuncs, method = "cv", 
                        verbose = FALSE, returnResamp = "all", 
                        number = num.iters)
  results.rfe <- rfe(x = feature.vars, y = class.var, 
             sizes = variable.sizes, 
             rfeControl = control)
  return(results.rfe)
}

#installeer eventueel missende packages

# run feature selection

#geduld, dit duurt wel even!

rfe.results <- run.feature.selection(feature.vars=train.data[,-1], 
                                     class.var=train.data[,1])
# view results
rfe.results











