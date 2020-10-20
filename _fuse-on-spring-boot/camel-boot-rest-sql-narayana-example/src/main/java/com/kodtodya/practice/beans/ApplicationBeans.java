package com.kodtodya.practice.beans;

import org.apache.camel.spring.spi.SpringTransactionPolicy;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.PlatformTransactionManager;

@Component
public class ApplicationBeans {
	
	@Bean(name = "PROPAGATION_REQUIRES_NEW")
	public SpringTransactionPolicy propogationRequiredNew(PlatformTransactionManager jtaTransactionManager){
		SpringTransactionPolicy propagationRequired = new SpringTransactionPolicy();
		propagationRequired.setTransactionManager(jtaTransactionManager);
		propagationRequired.setPropagationBehaviorName("PROPAGATION_REQUIRES_NEW");
		return propagationRequired;
	}
}
