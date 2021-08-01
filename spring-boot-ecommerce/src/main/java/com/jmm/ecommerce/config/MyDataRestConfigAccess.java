package com.jmm.ecommerce.config;

import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.jmm.ecommerce.entities.Product;
import com.jmm.ecommerce.entities.ProductCategory;

@Configuration
public class MyDataRestConfigAccess implements RepositoryRestConfigurer {

	@Autowired
	private EntityManager entityManager;

	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {

		HttpMethod[] unsupportedActionsList = { HttpMethod.POST, HttpMethod.PUT, HttpMethod.DELETE };

		// Disable HTTP methods for Product: PUT, POST, DELETE
		config.getExposureConfiguration().forDomainType(Product.class)
				.withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActionsList))
				.withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActionsList));

		// Disable HTTP methods for ProductCategory: PUT, POST, DELETE
		config.getExposureConfiguration().forDomainType(ProductCategory.class)
				.withItemExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActionsList))
				.withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(unsupportedActionsList));

		// Internal Helper Method to Expose Entity Id's
		config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(e -> e.getJavaType())
				.collect(Collectors.toList()).toArray(new Class[0]));
	}

}
