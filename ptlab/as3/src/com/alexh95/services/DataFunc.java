package com.alexh95.services;

public class DataFunc {

	private DataFunc() {
	}

	public static double getStandardDeviation(double[] dataSet) {
		// for (int i = 0; i < dataSet.length; i++) {
		// System.out.print(dataSet[i] + " ");
		// }
		// System.out.println();

		double sum = 0;
		for (double data : dataSet) {
			sum += data;
		}
		double mean = sum / dataSet.length;

		double sumDeviations = 0;
		for (double data : dataSet) {
			double deviationRoot = data - mean;
			sumDeviations += deviationRoot * deviationRoot;
		}
		double variance = sumDeviations / (dataSet.length - 1);

		double standardDeviation = Math.sqrt(variance);
		// System.out.println("StdDev: " + standardDeviation);
		return standardDeviation;
	}
}
