package com.calypso.yahooFinance.service;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.calypso.yahooFinance.model.Results;
import com.calypso.yahooFinance.model.YahooResponse;

/**
 * service class
 * 
 * @author jayati
 *
 */
@Service
public class YahooFinanceService {
	/**
	 * This method call the yahoo finance webservice and get the data
	 * corresponding to the given company symbol
	 * 
	 * @param companySymbol
	 * @return
	 */
	public Results findStock(String companySymbol) {
		RestTemplate restTemplate = new RestTemplate();

		String URLString = "";
		try {
			URLString = "http://query.yahooapis.com/v1/public/yql?q="
					+ URLEncoder
							.encode("select * from csv where url='http://download.finance.yahoo.com/d/quotes.csv?s="
									+ companySymbol
									+ "&f=sl1d1t1c1ohgv&e=.csv'and columns='symbol,price,date,time,change,col1,high,low,col2'",
									"UTF-8") + "&format=json";

			System.out.println("temp url:" + URLString);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		URI financeURL = null;
		try {
			financeURL = new URI(URLString);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		System.out.println("Calling url:" + financeURL);

		YahooResponse yahooResponse = restTemplate.getForObject(financeURL,
				YahooResponse.class);

		return yahooResponse.getQuery().getResults();

	}


}
