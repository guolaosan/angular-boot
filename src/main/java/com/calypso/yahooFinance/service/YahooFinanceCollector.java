package com.calypso.yahooFinance.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.json.simple.*;
import org.json.simple.parser.*;
import org.springframework.web.client.RestTemplate;

import com.calypso.yahooFinance.model.YahooResponse;

public class YahooFinanceCollector {
    class Quote implements Comparable<Quote> {
	public String symbol, date;
	public double open, high, low, close, adjustClose;

	public Quote(String symbol, String date, double open, double high,
		     double low, double close, double adjustClose) {
	    this.symbol = symbol;
	    this.date = date;
	    this.open = open;
	    this.high = high;
	    this.low = low;
	    this.close = close;
	    this.adjustClose = adjustClose;
	}

	@Override
	public int compareTo(Quote q) {
	    if (!this.symbol.equals(q.symbol)) {
		return this.symbol.compareTo(q.symbol);
	    } else {
		return this.date.compareTo(q.date);
	    }
	}
    }
    
    private String getURL(String[] symbols, String start, String end) {
	if (symbols == null || symbols.length == 0) return null;

	String baseURL = "https://query.yahooapis.com/v1/public/yql?";

	StringBuilder sb = new StringBuilder();
	for (String s : symbols) {
	    sb.append("'" + s + "',");
	}
	sb.deleteCharAt(sb.length() - 1);
	
	String q = "select * from yahoo.finance.historicaldata where symbol in (" + sb.toString() + ") ";
	q += "and startDate = '" + start + "' and endDate = '" + end + "'";
	String env = "store://datatables.org/alltableswithkeys";
	    
	String url = null;
	try {
	    url = baseURL + "q=" + URLEncoder.encode(q, "UTF-8") + "&format=json&env=" + URLEncoder.encode(env, "UTF-8");
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return url;
    }
    
    private String getSingleSymbolURL(String symbol, String start, String end) {
	if (symbol == null ) return null;

	String baseURL = "https://query.yahooapis.com/v1/public/yql?";
	
	String q = "select * from yahoo.finance.historicaldata where symbol = (" + symbol + ") ";
	q += "and startDate = '" + start + "' and endDate = '" + end + "'";
	String env = "store://datatables.org/alltableswithkeys";
	    
	String url = null;
	try {
	    url = baseURL + "q=" + URLEncoder.encode(q, "UTF-8") + "&format=json&env=" + URLEncoder.encode(env, "UTF-8");
	} catch (Exception e) {
	    e.printStackTrace();
	}

	return url;
    }
    
	
	public String getHisByStock(String symbol,String start, String end){

		RestTemplate restTemplate = new RestTemplate();

		String baseURL = "https://query.yahooapis.com/v1/public/yql?";
		
		String q = "select * from yahoo.finance.historicaldata where symbol = '" + symbol + "' ";
		q += "and startDate = '" + start + "' and endDate = '" + end + "'";
		String env = "store://datatables.org/alltableswithkeys";
		    
		String url = null;
		try {
		    url = baseURL + "q=" + URLEncoder.encode(q, "UTF-8") + "&format=json&env=" + URLEncoder.encode(env, "UTF-8");
		} catch (Exception e) {
		    e.printStackTrace();
		}

		URI financeURL = null;
		try {
			financeURL = new URI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		System.out.println("Calling url:" + financeURL);

		YahooResponse yahooResponse = restTemplate.getForObject(financeURL,
				YahooResponse.class);

		return yahooResponse.toString();

	
	}

    public List<Quote> getQuotes(String[] symbols, String start, String end) {
	List<Quote> quotes = new ArrayList<>();
	if (symbols == null || symbols.length == 0) return quotes;

	String url = getURL(symbols, start, end);
	if (url != null) {
	    try (BufferedReader br = new BufferedReader(new InputStreamReader(new URL(url).openStream()))) {
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject)parser.parse(br);
		JSONObject query = (JSONObject)obj.get("query");
		JSONObject results = (JSONObject)query.get("results");
		JSONArray quoteArray = (JSONArray)results.get("quote");
		for (Object quote : quoteArray) {
		    JSONObject q = (JSONObject)quote;
		    String symbol = (String)q.get("Symbol");
		    String date = (String)q.get("Date");
		    double open = Double.parseDouble((String)q.get("Open"));
		    double high = Double.parseDouble((String)q.get("High"));
		    double low = Double.parseDouble((String)q.get("Low"));
		    double close = Double.parseDouble((String)q.get("Close"));
		    double adjustClose = Double.parseDouble((String)q.get("Adj_Close"));
		    quotes.add(new Quote(symbol, date, open, high, low, close, adjustClose));
		}
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}

	return quotes;
    }

    public static void main(String[] args) {
		YahooFinanceCollector yfc = new YahooFinanceCollector();
		
		yfc.getHisByStock("MSFT", "2015-04-01", "2016-08-10");
		
//		String[] symbols = new String[] {"GOOG", "MSFT"};
//		List<Quote> quotes = yfc.getQuotes(symbols, "2015-04-01", "2016-08-10");
//		for (Quote q : quotes) {
//		    System.out.println("Symbol: " + q.symbol + " Date: " + q.date + " Close: " + q.close);
//		}
    }
}
