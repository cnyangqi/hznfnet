package com.dps.tools;

import org.nutz.lang.Lang;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class Test {

	public static void main(String[] args) {
		// Integer tmp = Integer.valueOf("20120210113812828");
		// System.out.println(tmp);

		// System.out.println(Integer.MAX_VALUE);
		// System.out.println("20120210113812828".length());

		SchedulerFactory sf = new StdSchedulerFactory();
		try {
			Scheduler sched = sf.getScheduler();
		}
		catch (SchedulerException e) {
			throw Lang.wrapThrow(e);
		}

		JobDetail job = JobBuilder.newJob(HelloJob.class).withIdentity("job1", "group1").build();

	}
}
