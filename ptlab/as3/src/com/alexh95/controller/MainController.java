package com.alexh95.controller;

public class MainController {

	public static void main(String[] args) {
		try {
			new SimulatorController();
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}
}
