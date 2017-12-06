package com.toy.utils;

public class DingShi {

	public static void main(String[] args) {

		System.out.println("Start...");
		MyThread mt = new MyThread(500);
		mt.start();
	}
}

class MyThread extends Thread {

	private long time;
	private int num;

	public MyThread(long time) {
		this.time = time;
		num = 0;
	}

	@Override
	public void run() {
		while (true) {
			num++;
			if (num > 10) {
				break;
			}
			System.out.println("--->" + this.getName());
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}