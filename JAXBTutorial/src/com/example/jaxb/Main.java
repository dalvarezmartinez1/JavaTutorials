package com.example.jaxb;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

public class Main {

	public static void main(String[] args) throws JAXBException {
		List<Person> persons = new ArrayList<Person>();
		persons.add(new Person("John Doe", 21));
		persons.add(new Person("Mike Shimoda", 24));
		Team team = new Team(persons);
		JAXBContext jaxbContext = JAXBContext.newInstance(Team.class);
		File file = new File("Team.xml");
		jaxbContext.createMarshaller().marshal(team, file);
		
		Team teamFromFile = (Team) jaxbContext.createUnmarshaller().unmarshal(file);
		System.out.println(teamFromFile);
	}

}

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class Person {
	@XmlElement
	String name;
	@XmlElement
	int age;

	// Necessary for JAXB, unless you want to use an XMLAdapter and make classes
	// immutable
	public Person() {
	}
	
	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return String.format("%s, %s%n", name,age);
	}
}

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
class Team {
	@XmlElementWrapper(name = "persons")
	@XmlElement(name = "person")
	List<Person> persons;

	// Necessary for JAXB, unless you want to use an XMLAdapter
	public Team() {
		persons = new ArrayList<Person>();
	}
	
	public Team(List<Person> persons) {
		this.persons = persons;
	}

	public List<Person> getPersons() {
		return persons;
	}

	public void setPersons(List<Person> persons) {
		this.persons = persons;
	}
	
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for (Person p : persons) {
			sb.append(p);
		}
		return sb.toString();
	}
	
}
