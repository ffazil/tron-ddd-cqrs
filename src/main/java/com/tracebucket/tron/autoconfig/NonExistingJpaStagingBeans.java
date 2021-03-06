package com.tracebucket.tron.autoconfig;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Created by ffl on 28-05-2015.
 */
public class NonExistingJpaStagingBeans implements Condition {
	private static final String JPA_STAGING_CONFIGURATION_BEAN_NAME = "jpaStagingConfiguration";

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		return !context.getBeanFactory().containsBean(JPA_STAGING_CONFIGURATION_BEAN_NAME);
	}
}