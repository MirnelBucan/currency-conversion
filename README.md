# Currency conversion

Simple java program for currency conversion, using https://www.currencyconverterapi.com.
<br>With additional listing of countries and their currencies and 
list of available currencies for conversion.

## Getting Started

### Prerequisites

Before running this program make sure u have JDK and maven installed on your computer.

To check if java and maven are setup on your computer, run following commands.
<br>For java run:
```
java --version
```
For maven run:
```
mvn --version
```

### Installing

To install program on your machine, follow steps.

First clone the repository:
```
git clone https://github.com/MirnelBucan/currency-conversion.git
```

Go to https://free.currencyconverterapi.com/ and get your free api key.
Next in directory where you cloned the program go to
`src/main/resources` and change `config.properties.example` to `config.properties` .
<br>
Inside `config.properties` place you api key you got from https://free.currencyconverterapi.com/ .
<br>
Next from the same directory run :

```
mvn clean install assembly:single
```

This will create new folder `target`, inside it you'll see jar file `currencyConverter.jar` .


### Usage

To run the program:
<br>
Example:
```
java -cp target/currencyConverter.jar org.currencyconversion.App -f EUR -t USD -a 100
```
Result:
```
Conversion:
 -From: EUR
 -To: USD
 -Amount: 100.00
 -Result: 112.80
```

Note:
Flags -f, -t, -a are required for program to run.
Optional flags -ccy, ctry.
<br>
Detailed flags:
```
usage: Currency converter
 -a,--amount <arg>   Option to set amount to convert [number]
 -ccy,--currencies   Option for listing available currencies
 -ctry,--countries   Option for listing available countries and their
                     currency
 -f,--from <arg>     Option to set currency to convert from
 -t,--to <arg>       Option to set currency to convert into
```
