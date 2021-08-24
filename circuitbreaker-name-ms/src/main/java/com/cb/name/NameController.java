package com.cb.name;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NameController {
	@GetMapping("/api/v1/names")
	public String getName() {
		return "Name api is called";
	}
}
