package eu.tbelina.spring.jax_ws;

public class MyBObjectImpl implements MyBObject {

	@Override
	public String printMessage(String s) {
		return "Hello " + s + " from JAXWS with Spring";
	}
}
