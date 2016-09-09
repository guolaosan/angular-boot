package com.calypso.yahooFinance.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.calypso.yahooFinance.model.Results;
import com.calypso.yahooFinance.service.YahooFinanceService;

/**
 * Controller class 
 * @author jayati
 *
 */
@Controller
@RequestMapping("/v1/finance")
public class YahooFinanceController {
	
	private YahooFinanceService yahooFinanceService;

	@Autowired
	public YahooFinanceController(YahooFinanceService yahooFinanceService) {
		this.yahooFinanceService = yahooFinanceService;
	}

	/**
	 * This method is to display welcome.html, using url /firsthtml
	 * URL: http://localhost:9080/v1/finance/firsthtml
	 * @return
	 */
	@RequestMapping(value = "/firsthtml")
	public String test() {

		return "welcome";
	}

	/**
	 * This method is to call yahoo finance service
	 * @param name
	 * @return
	 */
	@RequestMapping(value = "/company")
	@ResponseBody
	public Results getFinanceResponse(@RequestParam String name) {
		Results result = yahooFinanceService.findStock(name);
		System.out.println("getting price in controller:"
				+ result.getRow().getPrice());
		return result;
	}

}
