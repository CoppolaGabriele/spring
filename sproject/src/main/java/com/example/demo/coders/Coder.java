package com.example.demo.coders;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.demo.teams.Team;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "CODERS")
public class Coder {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CodGen")
	@SequenceGenerator(sequenceName = "CODER_SEQ", allocationSize = 1, name = "CodGen")
	@Column(name = "CODER_ID")
	private long id;

//    @Column(name = "FIRST_NAME")
	private String firstName;

//    @Column(name = "LAST_NAME")
	private String lastName;

//    @Column(name = "HIRE_DATE")
	private LocalDate hireDate;

//    @Column(name = "SALARY")
	private double salary;

	@OneToOne(optional = true, mappedBy = "leader")
	@JsonIgnoreProperties({"coders", "leader"})
	private Team leadingTeam;
	

	@ManyToMany(mappedBy = "coders")
 	@JsonIgnoreProperties({"coders", "leader"})
    private Set<Team> teams;

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	public Coder() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public LocalDate getHireDate() {
		return hireDate;
	}

	public void setHireDate(LocalDate hireDate) {
		this.hireDate = hireDate;
	}

	public Team getLeadingTeam() {
		return leadingTeam;
	}

	public void setLeadingTeam(Team leadingTeam) {
		this.leadingTeam = leadingTeam;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Coder [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", hireDate=" + hireDate
				+ ", salary=" + salary + ", leadingTeam=" + leadingTeam + ", teams=" + teams + "]";
	}
}