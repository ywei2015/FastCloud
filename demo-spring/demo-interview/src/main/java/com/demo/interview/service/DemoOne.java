package com.demo.interview.service;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class DemoOne {

	private static ReentrantLock lock = new ReentrantLock();
	public static boolean flag = true;
	public static int i = 0;

	static class AThread extends Thread{
		@Override
		public void run(){
			while (true){
				if (flag){
					lock.lock();
					try {
						i++;
						//flag = false;
					}finally {
						lock.unlock();
					}
				}
			}
		}
	}

	 static class BThread extends Thread{
		@Override
		public void run(){
			while (true){
				if (flag){
					lock.lock();
					try {
						i++;
						//flag = true;
					}finally {
						lock.unlock();
					}
				}
			}
		}
	}

	public static void main(String[] args) {
		AThread aThread = new AThread();
		BThread bThread = new BThread();
		aThread.start();
		bThread.start();
		try {
			Thread.sleep(1000);
			flag = false;
			System.out.println(i);
			Thread.sleep(1000);
			System.out.println(i);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
