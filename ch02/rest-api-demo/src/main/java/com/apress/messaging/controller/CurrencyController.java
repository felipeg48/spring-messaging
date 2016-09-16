package com.apress.messaging.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.apress.messaging.domain.CurrencyExchange;
import com.apress.messaging.domain.Rate;
import com.apress.messaging.repository.RateRepository;

@RestController
@RequestMapping("/currency")
public class CurrencyController {
	
	private static final String BASE_CODE = "USD";
	private static final Logger log = LoggerFactory.getLogger(CurrencyController.class);
	
	@Autowired
	RateRepository repository;
	
	@RequestMapping("/latest")
	public ResponseEntity<CurrencyExchange> getLatest(@RequestParam(name="base",defaultValue=BASE_CODE)String base) throws Exception{
		return new ResponseEntity<CurrencyExchange>(new CurrencyExchange(base,new SimpleDateFormat("yyyy-MM-dd").format(new Date()),calculateByCode(new Date(),base)),HttpStatus.OK);
	}
	
	@RequestMapping("/{date}")
	public ResponseEntity<CurrencyExchange> getByDate(@PathVariable("date") @DateTimeFormat(pattern="yyyy-MM-dd") Date date,@RequestParam(name="base",defaultValue="USD")String base) throws Exception{
		return new ResponseEntity<CurrencyExchange>(new CurrencyExchange(base,new SimpleDateFormat("yyyy-MM-dd").format(date),calculateByCode(date,base)),HttpStatus.OK);
	}
	
	@RequestMapping(path="/new",method = {RequestMethod.POST})
	public ResponseEntity<CurrencyExchange> addNewRates(@RequestBody CurrencyExchange currencyExchange) throws Exception{
		try{
			final Date date = new SimpleDateFormat("yyyy-MM-dd").parse(currencyExchange.getDate());
			final Rate[] rates = currencyExchange.getRates();
			Arrays.stream(rates).forEach(rate -> repository.save(new Rate(rate.getCode(),rate.getRate(),date)));
		}catch(Exception ex){
			throw ex;
		}
		return new ResponseEntity<CurrencyExchange>(HttpStatus.CREATED);
	}
	
	private Rate[] calculateByCode(Date date, String code) throws Exception{
		List<Rate> rates = repository.findByDate(date);
		if(code.equals(BASE_CODE))
			return rates.toArray(new Rate[0]);
		
		Rate baseRate = rates.stream()
			 .filter(rate -> rate.getCode().equals(code)).findFirst().orElse(null);
		if(null == baseRate)
			throw new Exception("Bad Base Code");
		
		return Stream.concat(rates.stream()
			 .filter(n -> !n.getCode().equals(code))
			 .map(n -> new Rate(n.getCode(),n.getRate()/baseRate.getRate(),date)),Stream.of(new Rate(BASE_CODE,1/baseRate.getRate(),date)))
			 .toArray(size -> new Rate[size]);
	}
}
