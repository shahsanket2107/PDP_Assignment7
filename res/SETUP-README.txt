Instructions to run the program correctly
—--------------------------------------------------
The simplest method to run everything correctly is to ensure the ‘files’ folder and the jar file are in the same directory. Furthermore, any portfolios you want to load should also be in the same folder. This will also make loading these files easier. 


For example, make a folder called program on your computer. In that folder drag the files folder(unzipped version) and the hw5.jar file. 

Upon running the program, you will be able to choose between text-based or GUI for your view.


List of Stocks Available:
—----------------------------
A, AAL, AAP, AAPL, ABBV, ABC, ABMD, ABT, ACN, ADI, DVA, DXCM, EA, EBAY, ECL, HBAN, BHI, HCA, HD, HES, NEE, NEOG, NFLX, NI, NLSN, STX, STZ-B, SWK, SWKS, SYF, SYK, T, TAP, TCYSF, TEL, TIME, TJX, TMO, TMUS, TRAUF


List of Dates Available:
—---------------------------
2000-2021 
** Some dates may be missing in between for some stocks because this is a Kaggle dataset **


How to create a program with three different stocks and Query on Date/Cost Basis:
—--------------------------------------------------------------------------------------------
Step 1: Buy Shares for the three Stocks


Choose option ‘1’ and hit enter. Then enter the name of the stock for example ‘AAPL’ and hit enter. Enter the date in the format DD-MM-YYYY. For example, 24-10-2000. Finally, enter the number of shares you want to purchase, for example ‘13’. You can also enter in a commission fee at the end for the cost basis.


You should now be at the home screen and can now choose option ‘1’ again and hit enter to buy shares for another stock. For example, ‘EA’, ‘12-07-2000', number of shares(for example, 20).


You should now be at the home screen and can now choose option ‘1’ again and hit enter to buy shares for another stock. For example, ‘EBAY’, '14-03-2000', number of shares(for example, 10).


Step 2: Create a Portfolio
Now we need to create a portfolio. Choose option 2. Enter a name for the portfolio, for example, ‘games’. Enter ‘y’ to add a stock. First, add AAPL, then EBAY, then EA. When prompted to enter a new stock after adding those three, enter ‘n’.


You will then be prompted to save your file to your computer. Enter a name for example, ‘gamesPortfolio’. The portfolio will then be saved in the same folder(in the format of .csv) as your other files(.jar, files, etc).


Step 3: Query on the Date
Choose option 5. You will be presented with the portfolio you just created. For example, ‘games’. Choose that portfolio. Enter a date in the correct format, for example, ‘12-10-2000’. You will now see the value of the portfolio on that date(if you followed this example, it should be $41.785732).

Step 4: Query for cost basis
Choose option 6. You will be presented with the portfolio you just created. For example, ‘games’. Choose that portfolio. Enter a date in the correct format, for example, ‘12-03-2001’. You will now see the cost basis of the portfolio on that date(if you followed this example, it should be $53.039993).



How to load a file
—--------------------
It should be saved as a .csv(comma-separated-values).


The file must be in this format:
Ticker, date-purchased, price, number of shares


For example, a file could look like this:
—-----------------------------------------
AAPL, 12-03-2001, 149.35, 10
AMZN, 12-03-2001, 115.66, 8


Please note that having the files you want to load in the same directory as the jar file makes it easier to load, as when asked for the path you can simply input ‘nameOfFile.csv’.


To load a portfolio, run the program, and choose option ‘5’. Enter a name for your portfolio(for example, ‘retirement’), and then the path(for example, ‘retirement.csv’).