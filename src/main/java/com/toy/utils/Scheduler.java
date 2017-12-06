package com.toy.utils;

import java.util.Date;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 * 
 * @author 枫茗丿love
 *
 */
@Component
public class Scheduler {

	@Scheduled(cron = "0 0 0 * * ?")
	public void addDays() {
		System.out.println("---------------------------------->>>>>>>" + new Date());
	}
	
	@Scheduled(cron = "0 * * * * ?")
	public void statusCheck() {
		System.out.println("---------------------------------->>>>>" + new Date());
	}

}
