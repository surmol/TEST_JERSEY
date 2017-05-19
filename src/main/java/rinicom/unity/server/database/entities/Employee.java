package rinicom.unity.server.database.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Employee {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="employee_id_seq")
	@SequenceGenerator(name="employee_id_seq", sequenceName="employee_id_seq", allocationSize=1)

	private Long id;
	private Long age;
	private String name;
	public Employee () {
	    super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}