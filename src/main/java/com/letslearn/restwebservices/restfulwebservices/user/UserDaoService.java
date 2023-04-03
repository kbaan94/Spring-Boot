package com.letslearn.restwebservices.restfulwebservices.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component
public class UserDaoService {
	// JPA/Hibernate will interact with our database
	// create static Lists and have userDaoService talk with it

	private static List<User> users = new ArrayList<>();

	private static Integer usersCount = 0;

	// Initialize with a set of users
	static {
		users.add(new User(++usersCount, "Adam", LocalDate.now().minusYears(30)));
		users.add(new User(++usersCount, "Kyle", LocalDate.now().minusYears(25)));
		users.add(new User(++usersCount, "Jenny", LocalDate.now().minusYears(21)));
	}

	public List<User> findAll() {
		return users;
	}

	public User save(User user) {
		user.setId(++usersCount);
		users.add(user);
		return user;
	}

	// use functional programming to return the requested user.
	public User findOne(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		return users.stream().filter(predicate).findFirst().orElse(null);
	}

	// use functional programming to delete the requested user.
	public void deleteById(int id) {
		Predicate<? super User> predicate = user -> user.getId().equals(id);
		// remove the matching object if the ID matches
		users.removeIf(predicate);
	}
}
