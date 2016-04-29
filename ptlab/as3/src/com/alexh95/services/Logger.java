package com.alexh95.services;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Logger {

	private static Logger instance = null;

	public static Logger instance() {
		if (instance == null) {
			instance = new Logger();
		}
		return instance;
	}

	private PrintWriter pw;
	
	private Logger() {
	}
	
	public void startLogging() {
		try {
			pw = new PrintWriter(new File("log.txt"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		pw.println("Started Logging...");
	}
	
	public void log(String log) {
		pw.println(log);
	}
	
	public void endLogging() {
		pw.println("Ended Logging.");
		pw.close();
	}
}
