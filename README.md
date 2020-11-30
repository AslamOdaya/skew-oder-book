# Order Book Websocket API for Bitstamp

## Assumptions
- Only the trade pair needs to be normalized i.e btcusd -> BTC/USD
- The rest of the of the data should be modeled after the sample table in the test instructions.


## Usage
 Clone the project.
### IDE

To run this on an IDE (IntelliJ), simply open as a Gradle project.
Locate the Main.java class, right-click and select Run 'Main.main()'

A file should be created in the root directory of the project folder called 'orderbook.csv' after the program is terminated.

### Using Command Line
In the project directory:
```Gradle
gradle run
```

The consumed order book should be seen in the command line and the csv file should be generated inside the project file.