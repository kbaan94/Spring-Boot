package com.letslearn.restwebservices.restfulwebservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VersioningPersonController {
	// URI and request parameter versioning can cause URI pollution as you can see
	// we are defining multiple mappings below
	
	// URI versioning
	@GetMapping("/v1/person")
	public PersonV1 getFirstVersionOfPerson() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping("/v2/person")
	public PersonV2 getSecondVersionOfPerson() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	// request parameter versioning
	@GetMapping(path = "/person", params = "version=1")
	public PersonV1 getFirstVersionOfPersonRequestParameter() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(path = "/person", params = "version=2")
	public PersonV2 getSecondVersionOfPersonRequestParameter() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	// GET methods for fetching a particular version using headers
	@GetMapping(path = "/person/header", params = "X-API-VERSION=1")
	public PersonV1 getFirstVersionOfPersonRequestHeader() {
		return new PersonV1("Bob Charlie");
	}

	@GetMapping(path = "/person/header", params = "X-API-VERSION=2")
	public PersonV2 getSecondVersionOfPersonRequestHeader() {
		return new PersonV2(new Name("Bob", "Charlie"));
	}

	// whatever is sent as part of the accept header we will receive it as part of
	// Spring MVC and process it
	@GetMapping(path = "/person/accept", produces = "application/vnd.company.app-v1+json")
	public PersonV1 getFirstVersionOfPersonAcceptHeader() {
		return new PersonV1("Bob Charlie");
	}
}
