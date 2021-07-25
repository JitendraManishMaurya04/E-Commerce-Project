package com.jmm.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

import com.jmm.ecommerce.entities.Product;
import com.jmm.ecommerce.entities.ProductCategory;

@Configuration
public class MyDataRestConfigAccess implements RepositoryRestConfigurer {

	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {

		HttpMethod[] unsupportedActionsList = { HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE };

		// Disable HTTP methods for Product: PUT, POST, DELETE
		config.getExposureConfiguration().forDomainType(Product.class)
				.withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActionsList))
				.withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActionsList));

		// Disable HTTP methods for ProductCategory: PUT, POST, DELETE
		config.getExposureConfiguration().forDomainType(ProductCategory.class)
				.withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActionsList))
				.withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActionsList));

	}

}
