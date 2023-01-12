package com.WeatherReporting.assignment.PIP_PramodTK_Assignment;

import test.scenarious.MatcherException;

public class CompareData {
	public static void comparedata(Double NDTVTemp, Double NDTVHumidity, Double OwTemp, Double OwHumidity)
			throws MatcherException {

		if (NDTVTemp > OwTemp) {
			if ((NDTVTemp - OwTemp) <= 1) {
				System.out.println("Success: Temperature difference is acceptable");
			} else {
				System.out.println("Temperature difference is not acceptable");
				throw new MatcherException("Data Mismatch");
			}

		} else if (OwTemp > NDTVTemp) {

			if ((OwTemp - NDTVTemp) <= 1) {
				System.out.println("Success: Temperature difference is acceptable");
			} else {
				System.out.println("Temperature difference is not acceptable");
				throw new MatcherException("Data Mismatch");
			}
		}

		if (NDTVHumidity == OwHumidity) {
			System.out.println("Success: Humidity difference is acceptable");
		} else if (NDTVHumidity > OwHumidity) {
			if ((NDTVHumidity - OwHumidity) <= 10) {
				System.out.println("Success: Humidity difference is acceptable");
			} else {
				System.out.println("Humidity difference is not acceptable");
				throw new MatcherException("Data Mismatch");
			}

		} else if (OwHumidity > NDTVHumidity) {
			if ((OwHumidity - NDTVHumidity) <= 10) {
				System.out.println("Success: Humidity difference is acceptable");
			} else {
				System.out.println("Humidity difference is not acceptable");
				throw new MatcherException("Data Mismatch");
			}
		}

	}
}
