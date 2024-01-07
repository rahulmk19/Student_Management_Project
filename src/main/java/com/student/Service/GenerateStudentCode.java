package com.student.Service;

import java.util.Random;

public class GenerateStudentCode {

	public static String generateRandomString(String name) {

		String initials = extractInitials(name);

		String randomDigits = generateRandomDigits();

		return initials + randomDigits;
	}

	private static String extractInitials(String name) {
		StringBuilder initials = new StringBuilder();
		String[] nameParts = name.split("\\s+");

		for (String part : nameParts) {
			if (!part.isEmpty()) {
				initials.append(part.charAt(0));
			}
		}

		return initials.toString().toUpperCase();
	}

	private static String generateRandomDigits() {
		Random random = new Random();
		int randomNum = 10000 + random.nextInt(90000);
		return String.valueOf(randomNum);
	}
}
