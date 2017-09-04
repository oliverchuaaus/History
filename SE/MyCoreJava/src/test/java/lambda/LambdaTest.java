package lambda;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class LambdaTest {

	@Test
	public void testLambda() {
		Lambda lambda = new Lambda();
		List<Person> roster = new ArrayList<>();
		Person person = new Person();
		person.setEmailAddress("1@1.com");
		person.setAge(19);
		roster.add(person);
		
		person = new Person();
		person.setEmailAddress("2@2.com");
		person.setAge(26);
		roster.add(person);

		lambda.printPersons(roster, 
				(Person p) -> ((p.getAge() >= 18) && (p.getAge() <= 25)), 
				p -> p.getEmailAddress(),
				email -> System.out.println(email));
	}
}
