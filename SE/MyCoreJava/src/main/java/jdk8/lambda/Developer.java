package jdk8.lambda;

import java.math.BigDecimal;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class Developer {
	public Developer(String name, BigDecimal salary, int age) {
		super();
		this.name = name;
		this.salary = salary;
		this.age = age;
	}

	private String name;
	private BigDecimal salary;
	private int age;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getSalary() {
		return salary;
	}

	public void setSalary(BigDecimal salary) {
		this.salary = salary;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public static int compareByAge(Developer a, Developer b) {
		return a.age - b.age;
	}

	public static int compareByName(Developer a, Developer b) {
		return a.name.compareTo(b.name);
	}

	public static int compareBySalary(Developer a, Developer b) {
		return a.salary.compareTo(b.salary);
	}

	public int getYearsBeforeRetirement() {
		return 65 - age;
	}

	public boolean moreThan40YearsBeforeRetirement() {
		return 65 - age > 40;
	}

	public int getYearsBeforeRetirement(int retirementAge) {
		return retirementAge - age;
	}

	public boolean moreThan40YearsBeforeRetirement(int retirementAge) {
		return retirementAge - age > 40;
	}
	
	public boolean moreThanNYearsBeforeRetirement(int retirementAge, int years) {
		return retirementAge - age > years;
	}

}


interface TriFunction<T, U, V, R>{ 
	R apply(T t,U u,V v);
}
