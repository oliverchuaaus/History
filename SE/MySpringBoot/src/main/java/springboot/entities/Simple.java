package springboot.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQuery;
import javax.persistence.PreRemove;
import javax.persistence.SequenceGenerator;

@Entity
@SequenceGenerator(name = "ENTITY_SEQ_STORE", sequenceName = "CITY_SEQ", allocationSize = 50)
@NamedQuery(name = "Simple.findByLastName", query = "select u from Simple u where u.lastName = ?1")
@NamedNativeQuery(name = "Simple.findByLastNameNamedNative", query = "select * from Simple where last_name = ?1")
public class Simple {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ENTITY_SEQ_STORE")
	private Long id;

	private String firstName;
	private String lastName;
	private LocalDate birthDate;
	private LocalTime birthTime;
	private Integer age;
	private Boolean registered;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public LocalTime getBirthTime() {
		return birthTime;
	}

	public void setBirthTime(LocalTime birthTime) {
		this.birthTime = birthTime;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Boolean getRegistered() {
		return registered;
	}

	public void setRegistered(Boolean registered) {
		this.registered = registered;
	}
	
	@PreRemove
	public void preRemove() {
		System.out.println("PreRemove called");
	}
}
