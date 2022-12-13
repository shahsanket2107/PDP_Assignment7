Section - 1 -> How to run the submission :-

The Assignment7.jar file is present in the res folder.
When you download the zip folder uploaded on handins, unzip it and navigate to res folder.
open the res folder in command prompt and type : java -jar Assignment7.jar.
Once this is done the jar will run.
If you want to run from IntelliJ then simple open the project in IntelliJ and run the main class from MainControllerImpl and the program
will run.
Note that the files folder must be present while running the program as it contains the csv data from the kaggle dataset to fetch
values of different stocks.


Section - 2 -> Implementation completion :-

We have implemeted rebalancing feature for a portfolio and exposed it through both text as well as GUI interface successfully.
But here there is a catch. In the code provided to us they had a major design flaw. Once a portfolio is created or loaded we are unable
to buy/sell stocks for that portfolio. In their design it is like first you have to perform the buy/sell transactions, then you have to 
add that stock to the portfolio. 

Since their design is flawed, we talked with the professor and he told us that either create/load a portfolio first, then rebalance it 
and after rebalancing create a new portfolio and save it to file thus showing that rebalancing is working fine.

So we have done the exact same thing. So to run the rebalancing feature load any portfolio say for eg. sam.csv. Once the portfolio is 
loaded view it's composition and valuation. Then apply the rebalancing feature on that portfolio and upon completion it will ask to save 
it to a file. Provide a path and save it to a file. Then load that file into a different portfolio name and then view it's composisiton 
and valuation to test if our rebalancing feature is working correctly.

Also one more important thing they have supported only a few stocks on only a few dates as they are using the kaggle dataset and not
the AlphaVantage API. So dates till 2021 are supported and that too valid dates. So in their design if at a date data is not found they 
are returning -1 as the stock price on that date and their portfolio valuation comes out to be negative. That affects our rebalancing.
So enter a date where value exists.

Sample inputs for text-based rebalancing feature :-

Create a portfolio using -->
- Buy 50 shares of aapl on 11-11-2020
- Buy 50 shares of a on 12-11-2020
- Buy 47 shares of nflx on 12-11-2020
and name it sam
Or you can load sam.csv from res and rebalance
For loading -->
Please enter in the name of the portfolio you want to load: 
p1
Please enter in a valid path to load in your portfolio file: 
sam.csv

- Once the portfolio is created.loaded, choose option 11 to rebalance 
- Once you press 11, you will have p1 portfolio which you loaded earlier. In Choose a portfolio enter p1
- For date enter 17-11-2020
- Then you will be asked to mention weights -> you can enter 60,20,20 in comma-separated format.
- Then you will have to write a name which you want to save the portfolio as- you can mention rebalanced.

You will see a csv file of rebalanced would be created with rebalanced quantities. Then you will have to load rebalanced.csv using the steps mentioned earlier and you can check the composition and value.
Please enter in the name of the portfolio you want to load: 
rebalanced
Please enter in a valid path to load in your portfolio file: 
rebalanced

After the rebalanced file is loaded, you can check it's value and composisiton. It will show the updated quantities in composition after rebalancing and the value will be same.


Sample inputs for GUI-based rebalancing feature :-

You will have to load sam.csv file created in text-based here and then rebalance because apparently in GUI adding multiple stocks to a portfolio does not work in their code.
You can choose load portfolio from menu.
You can name portfolio name as p1 and path as sam.csv.
Once the file is loaded, you can rebalance it by choosing option from menu.
For rebalancing -->
- choose p1 and press enter.
- for date enter 17-11-2020
- For weights mention 60 of aapl, 20 of a and 20 of nflx.
- For path write gui_output and press enter.
- The portfolio will be saved and you will be redirected to main menu.
- You can now load your portfolio by pressing load button, then naming file name as rebalanced and in path mention gui_output.csv.
- Once it is loaded, you can check the composisiton and value of rebalanced portfolio. It will show the updated quantities in composition after rebalancing and the value will be same.


Section - 3 -> Changes made to implement rebalancing :-
