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
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "dashboardEntityManagerFactory", basePackages = {"com.dashboard.repositories"},transactionManagerRef = "dashboardTransactionManager")
public class DashboardDBConfig {

	@Bean(name="dashboardDatasource")
	@ConfigurationProperties(prefix = "spring.dashboard.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="dashboardEntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder,@Qualifier("dashboardDatasource") DataSource datasource){
		Map<String,Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto","update");
		properties.put("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
		return builder.dataSource(datasource).properties(properties).packages("com.dashboard.entities").persistenceUnit("Dashboard").build();
	}
	
	@Bean(name="dashboardTransactionManager")
	public PlatformTransactionManager platformTransactionManager(@Qualifier("dashboardEntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}
}
