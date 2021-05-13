/**
 * 
 */
package com.microservices;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * @author SatishReddy
 *
 */
@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryRestController {
	@Autowired
	private CategoryRepository categoryRepository;

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping
	public ResponseEntity<List<Category>> getCategories() {
		List<Category> getCategories = categoryRepository.findAll().stream().collect(Collectors.toList());
		return new ResponseEntity<List<Category>>(getCategories, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<Category> saveCoupon(@RequestBody Category category) {
		try {
			categoryRepository.save(category);
			return new ResponseEntity<Category>(category, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/categoryName/{categoryName}")
	public ResponseEntity<Optional<Category>> getByCouponCode(@PathVariable String categoryName) {
		Optional<Category> categoryDetails = categoryRepository.findByCategoryName(categoryName);
		if (categoryDetails.isPresent()) {
			return new ResponseEntity<Optional<Category>>(categoryDetails, HttpStatus.OK);
		}
		return new ResponseEntity<Optional<Category>>(categoryDetails, HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{categoryId}")
	public HttpStatus deleteCoupon(@PathVariable("categoryId") Long categoryId) {

		Optional<Category> category = categoryRepository.findById(categoryId);
		if (category.isPresent()) {
			categoryRepository.deleteById(categoryId);
			return HttpStatus.NO_CONTENT;
		} else {
			return HttpStatus.NOT_FOUND;
		}
	}
}
