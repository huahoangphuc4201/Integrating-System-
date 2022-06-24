package com.dashboard.config;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "payrollEntityManagerFactory", basePackages = {"com.dashboard.payroll.repositories"}, transactionManagerRef = "payrollTransactionManager")
public class PayrollDBConfig {
	
	@Primary
	@Bean(name="payrollDatasource")
	@ConfigurationProperties(prefix = "spring.payroll.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Primary
	@Bean(name="payrollEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,@Qualifier("payrollDatasource") DataSource datasource){
		Map<String,Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto","update");
		properties.put("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
		return builder.dataSource(datasource).properties(properties).packages("com.dashboard.payroll.entities").persistenceUnit("Payroll").build();
	}
	
	@Bean(name="payrollTransactionManager")
	public PlatformTransactionManager platformTransactionManager(@Qualifier("payrollEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
