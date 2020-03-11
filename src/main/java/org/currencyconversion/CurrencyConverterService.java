package org.currencyconversion;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class CurrencyConverterService {
  private String apiKey;
  private CurrencyConverter converter;

  public CurrencyConverterService() {
    Properties prop = new Properties();
    InputStream inputStream = getClass().getClassLoader().getResourceAsStream("config.properties");
    if (inputStream != null) {
      try {
        prop.load(inputStream);
      } catch (IOException e) {
        e.printStackTrace();
        System.out.println(e.getMessage());
      }
    }
    this.converter = new CurrencyConverter(prop.getProperty("API_KEY"));
  }

  public void convertValues(String from, String to, double amount) {
    try {
      double result = converter.convertValue(from, to, amount);
      String resultPresentation = String.format("Conversion:\n -From: %s\n -To:%s\n -Amount: %.2f\n -Result: %.2f",
              from,to,amount,result);
      System.out.println(resultPresentation);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public void listCountries() {
    try {
      Map<String, String> countries = converter.getCountries();
      System.out.println(countries);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }

  public void listCurrencies(){
    try {
      Map<String, String> currencies = converter.getCurrencies();
      System.out.println(currencies);
    } catch (IOException e) {
      System.out.println(e.getMessage());
    }
  }
}
