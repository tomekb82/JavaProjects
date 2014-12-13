
package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

import country.wsdl.GetCountryResponse;

public class Application {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(CountryConfiguration.class, args);

		CountryClient countryClient = ctx.getBean(CountryClient.class);

		GetCountryResponse response = countryClient.getCountryByName("Poland");
		countryClient.printResponse(response);
		
		response = countryClient.getCountryByName("Spain");
		countryClient.printResponse(response);
	}

}
