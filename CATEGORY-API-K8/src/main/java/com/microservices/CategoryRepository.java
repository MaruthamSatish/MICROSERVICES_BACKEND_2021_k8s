/**
 * 
 */
package com.microservices;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author SatishReddy
 *
 */
@Repository
public interface CategoryRepository  extends JpaRepository<Category,Long>{
	//sList<Category> findByCategoryName(String categoryName);
	Optional<Category> findByCategoryId(Integer categoryId);
	Optional<Category> findByCategoryName(String categoryName);
}
