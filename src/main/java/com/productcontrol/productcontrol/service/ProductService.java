package com.productcontrol.productcontrol.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.productcontrol.productcontrol.domain.Product;
import com.productcontrol.productcontrol.domain.ProductRepository;

@Service
public class ProductService {
	
	private ProductRepository productRepository;
	
	@Autowired
	public ProductService(ProductRepository prodRepo) {
		this.productRepository = prodRepo;
	}
	
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}
	
	public void updateProduct(Long id, Product product) {
		product.setId(id);
		productRepository.save(product);
	}
	
	public void deleteProduct(Long id) {
		productRepository.deleteById(id);
	}
	
	public List<Product> getAllProducts(){
		return productRepository.findAll();
	}
	
	public Optional<Product> getProductById (Long id) {
		return productRepository.findById(id);
	}

}
