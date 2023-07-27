package com.productcontrol.productcontrol.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.productcontrol.productcontrol.domain.Product;
import com.productcontrol.productcontrol.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	private ProductService productService;
	
	@Autowired
	private ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> products = productService.getAllProducts();
		return new ResponseEntity<>(products, HttpStatus.OK);
	}
	
	@PostMapping("/send")
	public ResponseEntity<Product> createProduct(@RequestBody Product product){
		Product createdProduct = productService.createProduct (product);
		return new ResponseEntity<>(createdProduct, HttpStatus.CREATED); 
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Void> deleteProduct(
			@PathVariable Long id){
		Optional<Product> existProduct =
				productService.getProductById(id);
		if(existProduct.isPresent()){
			productService.deleteProduct(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/up/{id}")
	public ResponseEntity<Void> updateProduct(
			@PathVariable Long id, 
			@RequestBody Product product){
		Optional<Product> existProduct =
				productService.getProductById(id);
		if(existProduct.isPresent()){
			productService.updateProduct(id, product);
			return new ResponseEntity<>(HttpStatus.OK);
		}else{
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	

}



