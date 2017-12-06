package com.toy.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskUtils {

	public static void main(String[] args) {

		System.out.println("Start -----<");
		Timer timer = new Timer();
		MyTimerTask task = new MyTimerTask(0, timer);
		long period = 2000;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.set(2017, 10, 6, 11, 5, 0);
		Date date = calendar.getTime();
		if (date.before(new Date())) {
			System.out.println("时间已过...");
			calendar.set(2017, 10, 6, 11, 19, 0);
			date = calendar.getTime();
		}
		System.out.println(sdf.format(date));
		timer.schedule(task, date, period);
	}

}

class MyTimerTask extends TimerTask {

	private int re;
	private Timer timer;
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

	public MyTimerTask(int re, Timer timer) {
		this.re = re;
		this.timer = timer;
	}

	@Override
	public void run() {
		re++;
		if (re > 10)
			timer.cancel();
		System.out.println("--->" + sdf.format(new Date()) + "--" + re);
	}

}
