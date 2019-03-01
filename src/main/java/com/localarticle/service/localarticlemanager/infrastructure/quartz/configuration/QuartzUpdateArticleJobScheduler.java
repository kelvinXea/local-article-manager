package com.localarticle.service.localarticlemanager.infrastructure.quartz.configuration;

import org.quartz.JobDetail;
import org.quartz.SimpleTrigger;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.scheduling.quartz.SpringBeanJobFactory;

import com.localarticle.service.localarticlemanager.infrastructure.quartz.jobs.UpdateLocalArticleJob;

@Configuration
public class QuartzUpdateArticleJobScheduler {

	@Bean
	public SpringBeanJobFactory springBeanJobFactory(ApplicationContext applicationContext) {
		AutoWiringSpringBeanJobFactory jobFactory = new AutoWiringSpringBeanJobFactory();

		jobFactory.setApplicationContext(applicationContext);
		return jobFactory;
	}

	@Bean(name = "JobDetailUpdateLocalArticleJob")
	public JobDetailFactoryBean jobDetail() {
		JobDetailFactoryBean jobDetailFactory = new JobDetailFactoryBean();
		jobDetailFactory.setJobClass(UpdateLocalArticleJob.class);
		jobDetailFactory.setDescription("... Iniciando Local Article Updater");
		jobDetailFactory.setDurability(true);
		return jobDetailFactory;
	}

	// trigger.setRepeatInterval(1800000);
	@Bean(name = "TriggerUpdateLocalArticleJob")
	public SimpleTriggerFactoryBean trigger(@Qualifier("JobDetailUpdateLocalArticleJob") JobDetail job) {
		SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
		trigger.setJobDetail(job);
		trigger.setRepeatInterval(10000);
		trigger.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
		return trigger;
	}

	@Bean
	public SchedulerFactoryBean scheduler(@Qualifier("TriggerUpdateLocalArticleJob") Trigger trigger,
			@Qualifier("JobDetailUpdateLocalArticleJob") JobDetail job, ApplicationContext applicationContext) {
		SchedulerFactoryBean schedulerFactory = new SchedulerFactoryBean();
		schedulerFactory.setJobFactory(springBeanJobFactory(applicationContext));
		schedulerFactory.setJobDetails(job);
		schedulerFactory.setTriggers(trigger);
		return schedulerFactory;
	}

}
