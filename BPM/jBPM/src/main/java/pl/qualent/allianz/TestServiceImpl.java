package pl.qualent.allianz;

import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

	
	@Override
	public void printTestMsg() {
		System.out.println("*********************************************************");
		System.out.println("msg from service");
		System.out.println("*********************************************************");

	}
}
