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

To install program on your machine to able to run in to follow next steps.

First clone the repository:
```
git clone https://github.com/MirnelBucan/currency-conversion.git
```

In directory where u cloned program run:

```
mvn clean install assembly:single
```

This will create single jar with program and dependencies.

### Usage

To run the program, from directory where it's cloned run:
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
