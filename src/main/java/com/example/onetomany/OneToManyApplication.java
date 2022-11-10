package com.example.onetomany;

import com.example.onetomany.model.Employee;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootApplication
public class OneToManyApplication {

	static List<Employee> employees = new ArrayList<>();
	static {
		employees.add(
				new Employee("Shabill", "Dawooodi", 5000.0, List.of("Project 1", "Project 2")));
		employees.add(
				new Employee("Nikhiil", "GUpta", 6000.0, List.of("Project 1", "Project 3")));
		employees.add(
				new Employee("Shiaam Kumma", "Nakimti", 5500.0, List.of("Project 3", "Project 4")));
	}

	public static void main(String[] args) {

		SpringApplication.run(OneToManyApplication.class, args);
//
		employees.stream().forEachOrdered(employee -> System.out.println(employee));

		//map
		//collect

		List<Employee> increasedSal = employees.stream()
				.map(employee -> new Employee(
				employee.getFirstName(),
				employee.getLastName(),
				employee.getSalary() * 1.10,
				employee.getProjects()
		))
				.collect(Collectors.toList());
		System.out.println(increasedSal);

		//filter
		List<Employee> filterEmplooyee = employees.stream()
				.filter(employee -> employee.getSalary() > 5000.0)
				.map(employee -> new Employee(
						employee.getFirstName(),
						employee.getLastName(),
						employee.getSalary(),
						employee.getProjects()
				)).collect(Collectors.toList());
		System.out.println(filterEmplooyee);

		//getFirst
		Employee firstEmployee = employees.stream()
				.filter(employee -> employee.getSalary() > 5000.0)
				.map(employee -> new Employee(
						employee.getFirstName(),
						employee.getLastName(),
						employee.getSalary(),
						employee.getProjects()
				)).findFirst().orElse(null);
		System.out.println(firstEmployee);

		//flatMap
		String projects = employees.stream()
				.map(employee -> employee.getProjects())
				.flatMap(strings -> strings.stream())
				.collect(Collectors.joining(","));

		System.out.println(projects);

		//short Circuit opentions
		List<Employee>shortCircuit = employees.stream()
				.skip(1)
				.limit(1)
				.collect(Collectors.toList());
		System.out.println(shortCircuit);

		//
	}
}
