package com.example.demo.teams;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class TeamCtrl {
	private static final Logger LOG = LoggerFactory.getLogger(TeamCtrl.class);

	@Autowired
	private TeamRepository repository;

	@GetMapping("/teams")
	public List<Team> getAll() {
		LOG.trace("getAll");
		return repository.findAll();
	}
	
	@GetMapping("/teams/{id}")
	public Team get(@PathVariable Long id) {
		LOG.trace("get " + id);
		return repository.findById(id).orElseThrow(() -> new TeamNotFoundException(id));
	}

	@PostMapping("/teams")
	public Team create(@RequestBody Team team) {
		LOG.trace("create " + team);
		return repository.save(team);
	}

	@PutMapping("/teams/{id}")
	public Team update(@RequestBody Team newer, @PathVariable Long id) {
		LOG.trace(String.format("update team %d by %s", id, newer));
		return repository.findById(id).map(team -> {
			team.setName(newer.getName());
			return repository.save(team);
		}).orElseGet(() -> {
			newer.setId(id);
			return repository.save(newer);
		});
	}

	@PatchMapping("/teams/{id}")
	public Team partialUpdate(@RequestBody Team newer, @PathVariable Long id) {
		LOG.trace(String.format("patch team %d by %s", id, newer));
		return repository.findById(id).map(team -> {
			if (newer.getName() != null) {
				team.setName(newer.getName());
			}
			return repository.save(team);
		}).orElseThrow(() -> new TeamNotFoundException(id));
	}

	@DeleteMapping("/teams/{id}")
	public void delete(@PathVariable Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException ex) {
			LOG.warn("Can't delete team", ex);
			throw new TeamNotFoundException(id);
		} catch (IllegalArgumentException ex) {
			LOG.warn("Invalid teamId", ex);
			System.out.println(ex.getMessage());
		}
	}
}
