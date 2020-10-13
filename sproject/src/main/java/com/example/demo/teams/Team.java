package com.example.demo.teams;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.example.demo.coders.Coder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "TEAMS")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TeamGen")
	@SequenceGenerator(sequenceName = "TEAM_SEQ", allocationSize = 1, name = "TeamGen")
	@Column(name = "TEAM_ID")
	private long id;

	private String name;

	@OneToOne(optional = false)
	@JsonIgnoreProperties({"teams", "leadingTeam"})
	@JoinColumn(name = "leader_id")
	private Coder leader;
	
    @ManyToMany
    @JoinTable(name = "team_coder", joinColumns = @JoinColumn(name = "team_id"), 
    								inverseJoinColumns = @JoinColumn(name = "coder_id"))
    
    @JsonIgnoreProperties({"teams", "leadingTeam"})
    private Set<Coder> coders;


	public Set<Coder> getCoders() {
		return coders;
	}

	public void setCoders(Set<Coder> coders) {
		this.coders = coders;
	}

	public Team() {
	}

	public Coder getLeader() {
		return leader;
	}

	public void setLeader(Coder leader) {
		this.leader = leader;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Team [id=" + id + ", name=" + name + ", leader=" + leader + ", coders=" + coders + "]";
	}

}