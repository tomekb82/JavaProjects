
package hello;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import country.wsdl.Country;
import country.wsdl.GetCountryRequest;
import country.wsdl.GetCountryResponse;

public class CountryClient extends WebServiceGatewaySupport {

	public GetCountryResponse getCountryByName(String name) {
		GetCountryRequest request = new GetCountryRequest();
		request.setName(name);

		System.out.println();
		System.out.println("Requesting country for " + name);

		GetCountryResponse response = (GetCountryResponse) getWebServiceTemplate().marshalSendAndReceive(
				request,
				new SoapActionCallback(
						"http://localhost:8080/ws"));

		return response;
	}

	public void printResponse(GetCountryResponse response) {
		Country country = response.getCountry();
		if(country != null){
			System.out.println("Country: ");
			System.out.println("   - name: "+ country.getName());
			System.out.println("   - capital: "+ country.getCapital());
			System.out.println("   - population: "+ country.getPopulation());
			System.out.println("   - currency: "+ country.getCurrency());
		} else {
			System.out.println("No country received");
		}
	}

}
