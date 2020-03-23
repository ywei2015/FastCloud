package com.demo.interview.service;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Test04 {
	public static int i = 1;
	public volatile static int j = 1;
	public volatile static boolean flag = false;
	public static int count = 0;

	public static Lock lock = new ReentrantLock();
	public static Condition conditionA = lock.newCondition();
	public static Condition conditionB = lock.newCondition();

	private static CountDownLatch latch = new CountDownLatch(2);
	private static AtomicInteger numA = new AtomicInteger();
	private static AtomicInteger numB = new AtomicInteger();

	public static void test4() {

		new Thread(() -> {
			lock.lock();
			try {
				while (i < 10) {
					if (flag) {
						conditionA.await();
					}
					flag = true;
					System.out.println(Thread.currentThread().getName() + "----"
							+ (i++));
					conditionB.signal();
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}	).start();

		new Thread(() -> {

			lock.lock();
			try {
				while (j < 10) {
					if (!flag) {
						conditionB.await();
					}
					flag = false;
					System.out.println(Thread.currentThread().getName() + "----"
							+ (j++));
					conditionA.signal();
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}	).start();
	}

	public static void main(String[] args) {
		Test04.test4();
	}
}
