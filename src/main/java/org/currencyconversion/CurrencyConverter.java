package org.currencyconversion;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class CurrencyConverter {
  private final String baseUrl = "https://free.currconv.com/api/v7";

  private String apiKey;
  private ObjectMapper objectMapper;
  CurrencyConverter(String apiKey) {
    this.apiKey = apiKey;
    this.objectMapper = new ObjectMapper();
    this.objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
  }

  public double convertValue(String from, String to, Double amount) throws IOException {
    URL url = new URL(String.format("%s/convert?q=%s_%s&compact=ultra&apiKey=%s",
            baseUrl, from, to, apiKey));

    HttpURLConnection con = (HttpURLConnection) url.openConnection();
    int statusCode = con.getResponseCode();
    BufferedReader inputResponse;
    if (statusCode == HttpsURLConnection.HTTP_OK) {
      inputResponse = new BufferedReader(
              new InputStreamReader(con.getInputStream())
      );
      @SuppressWarnings("unchecked")
      Map<String, Object> response = objectMapper.readValue(readResponse(inputResponse), Map.class);
      if (response.isEmpty()) {
        throw new IOException(
                String.format("Unable to convert %s to %s, please check if entered currencies are valid", from, to)
        );
      }
      double currency = (double) response.get(from+"_"+to);
      return amount * currency;
    } else {
      inputResponse = new BufferedReader(
              new InputStreamReader(con.getErrorStream())
      );
      throw new IOException(readError(inputResponse));
    }
  }

  public Map<String, String> getCurrencies() throws IOException {
    URL url = new URL(String.format("%s/currencies?apiKey=%s", baseUrl, apiKey));
    HttpURLConnection con = (HttpsURLConnection) url.openConnection();
    BufferedReader br = new BufferedReader(
            new InputStreamReader(con.getInputStream())
    );
    return objectMapper.readValue(readResponse(br), Map.class);
  }

  public Map<String, String> getCountries() throws IOException {
    URL url = new URL(String.format("%s/countries?apiKey=%s", baseUrl, apiKey));
    HttpURLConnection con = (HttpsURLConnection) url.openConnection();
    BufferedReader br = new BufferedReader(
            new InputStreamReader(con.getInputStream())
    );
    return objectMapper.readValue(readResponse(br), Map.class);
  }

  private String readResponse(BufferedReader br) throws IOException {
    String line = "";
    StringBuilder sb = new StringBuilder();
    while ((line = br.readLine()) != null) {
      sb.append(line);
    }
    return sb.toString();
  }

  private String readError(BufferedReader br) throws IOException {
    String line = "";
    StringBuilder sb = new StringBuilder();
    while ((line = br.readLine()) != null) {
      sb.append(line);
    }
    return sb.toString();
  }

}
