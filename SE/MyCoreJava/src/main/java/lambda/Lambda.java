package lambda;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Lambda {

	public void printPersons(final List<Person> roster, final Predicate<Person> predicate,
			final Function<Person, String> function, final Consumer<String> consumer) {
		for (final Person p : roster) {
			if (predicate.test(p)) {
				final String data = function.apply(p);
				consumer.accept(data);
			}
		}
	}
}

class Person {
	private int age;
	private String emailAddress;


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}
}
