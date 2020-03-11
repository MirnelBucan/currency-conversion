package org.currencyconversion;

import org.apache.commons.cli.*;
import org.graalvm.compiler.nodes.calc.IntegerDivRemNode;

import java.util.Map;

public class App {
  public static void main(String[] args) {
    CurrencyConverterService converterService = new CurrencyConverterService();
    CommandLine cmd = extractArgs(args);

    if (cmd.hasOption("ctry") || cmd.hasOption("countries")) {
      converterService.listCountries();
    }
    if (cmd.hasOption("ccy") || cmd.hasOption("currencies")) {
      converterService.listCountries();
    }
    converterService.convertValues(cmd.getOptionValue("f"), cmd.getOptionValue("t"), Double.parseDouble(cmd.getOptionValue("a")));
  }


  public static CommandLine extractArgs(String[] args) {
    Options options = new Options();
    Option countries = new Option("ctry", "countries", false, "Option for listing available" +
            " countries and their currency");
    countries.setRequired(false);
    options.addOption(countries);

    Option currencies = new Option("ccy", "currencies", false, "Option for listing available" +
            " currencies");
    countries.setRequired(false);
    options.addOption(currencies);

    Option from = new Option("f", "from", true, "Option to set currency to convert from");
    from.setRequired(true);
    options.addOption(from);

    Option to = new Option("t", "to", true, "Option to set currency to convert into");
    to.setRequired(true);
    options.addOption(to);

    Option amount = new Option("a", "amount", true, "Option to set amount to convert");
    amount.setRequired(true);
    amount.setType(Double.class);;
    options.addOption(amount);

    CommandLineParser parser = new DefaultParser();
    HelpFormatter formatter = new HelpFormatter();
    CommandLine cmd = null;

    try {
      cmd = parser.parse(options, args);
    } catch (ParseException e) {
      System.out.println(e.getMessage());
      formatter.printHelp("Currency converter", options);

      System.exit(1);
    }
    return cmd;
  }

  public static boolean checkForConversionArgs(Map<String, Object> argsMap) {
    if (!argsMap.containsKey("from")) {
      System.err.println("Missing from argument");
      System.exit(1);
    }

    if (!argsMap.containsKey("to")) {
      System.err.println("Missing from argument");
      System.exit(1);
    }

    if (!argsMap.containsKey("amount")) {
      System.err.println("Missing from argument");
      System.exit(1);
    }
    return true;
  }
}
