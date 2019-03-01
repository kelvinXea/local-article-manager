package com.localarticle.service.localarticlemanager.infrastructure.quartz.jobs;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

import com.localarticle.service.localarticlemanager.domain.service.LocalArticleService;

@Component
public class UpdateLocalArticleJob extends QuartzJobBean {
	
	@Autowired
	private LocalArticleService localArticleService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		localArticleService.updateLocalArticles();
		
	}

}
