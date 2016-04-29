package com.alexh95.services;

import java.util.concurrent.atomic.AtomicInteger;

public class Timer {

	private static Timer instance;

	private AtomicInteger currentTime;

	private Timer() {
	}

	public static Timer instance() {
		if (instance == null) {
			instance = new Timer();
		}
		return instance;
	}

	public void startTimer() {
		currentTime = new AtomicInteger(0);
	}

	public final int addAndGet(int delta) {
		return currentTime.addAndGet(delta);
	}

	public final int get() {
		return currentTime.get();
	}
}
