package com.letslearn.restwebservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

@RestController
public class UserResource {

	private UserDaoService service;

	// Inject object using constructor autowired

	public UserResource(UserDaoService service) {
		this.service = service;
	}

	// return list of users back
	@GetMapping(path = "/users")
	public List<User> retrieveAllUsers() {
		return service.findAll();
	}

	// return a specific user using path vars
	@GetMapping(path = "/users/{id}")
	public User retrieveUser(@PathVariable int id) {
		User user = service.findOne(id);

		if (user == null) {
			throw new UserNotFoundException("id:" + id);
		}

		return user;
	}

	// return a specific user using path vars
	@DeleteMapping(path = "/users/{id}")
	public void deleteUser(@PathVariable int id) {
		service.deleteById(id);
	}

	// POST - for creating a user
	@PostMapping("/users")
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		User savedUser = service.save(user);

		// Fetch current URL, append path with newly created user ID then create the new
		// URI.
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
				.toUri();

		// create location and send back as part of HTTP response
		return ResponseEntity.created(location).build();

	}

}
