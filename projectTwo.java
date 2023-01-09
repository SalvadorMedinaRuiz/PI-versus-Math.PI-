/*
 * Credit to Mitchell Chau for partial code present here
 * 
 */

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class projectTwo {

	public static void main(String[] args) {
		// Variable Random Definements
		Random rand = new Random();
		int rand_int;
		int rand_int2;
		int gcd = 1;
		double sumOfGcd = 0;
		ArrayList<Double> individualPiRandoms = new ArrayList<>();
		double stepOneRandom = 0;
		double standardDeviation = 0;

		// Variable SecureRandom Definements
		SecureRandom rand2 = new SecureRandom();
		int secureRand_int;
		int secureRand_int2;
		int gcd2 = 1;
		double sumOfGcd2 = 0;
		ArrayList<Double> individualPiSecureRandoms = new ArrayList<>();
		double stepOneSecureRandom = 0;
		double standardDeviationSR = 0;

		// Variable True Random Definements
		int truerand_int = 0;
		int truerand_int2 = 0;
		int gcd3 = 1;
		double sumOfGcd3 = 0;
		ArrayList<Double> individualPiTrueRandoms = new ArrayList<>();
		double stepOneTrueRandom = 0;
		double standardDeviationTR = 0;

		// PRNG Random
		double pi = 0;
		double sumOfPi = 0;

		System.out.println("Frequency distribution table PRNG:");

		for (int i4 = 0; i4 <= 29; i4++) {// doing 30 estimates of pi
			sumOfGcd = 0;
			for (int i = 0; i <= 99; i++) { // 100 pairs of intergers
				rand_int = Math.abs(rand.nextInt(100) + 1);
				rand_int2 = Math.abs(rand.nextInt(100) + 1);
				while (rand_int != rand_int2) {// calculating gcd
					if (rand_int > rand_int2) {
						rand_int = rand_int - rand_int2;
					} else {
						rand_int2 = rand_int2 - rand_int;
					}
					// System.out.println("Calculating GCD of pair of numbers (random). Might be a
					// bit!");
				}
				if ((rand_int == gcd) || (rand_int2 == gcd)) {
					sumOfGcd++;
				}
			}
			pi = Math.sqrt(6 / (sumOfGcd / 100)); // calculating pi for the thing
			individualPiRandoms.add(pi); // adding each pi to the array list
			System.out.println("\n" + pi);
			sumOfPi += pi;
		}
		double averagePi = sumOfPi / 30; // the average of all pi's
		System.out.println("\nThe average (mean) PI for PRNG was: " + averagePi);

		// Standard Deviation Random Calculations
		for (int i = 0; i < individualPiRandoms.size(); i++) {
			stepOneRandom = individualPiRandoms.get(i) - averagePi;
			stepOneRandom = Math.pow(stepOneRandom, 2);
			standardDeviation += stepOneRandom;
		}
		standardDeviation = standardDeviation / 29;
		standardDeviation = Math.sqrt(standardDeviation);
		System.out.println("The Standard Deviation for PRNG was: " + standardDeviation);

		double mathPiDifferences = Math.abs(averagePi - Math.PI);
		System.out.println(
				"PRNG: The difference between (PI) " + Math.PI + " and " + averagePi + " is: " + mathPiDifferences);

		System.out.print("\n---------------------------------------------------------------------------------------");// just
		// something
		// to
		// break
		// everything
		// up
		// CSPRNG SecureRandom
		double pi2 = 0;
		double sumOfPi2 = 0;

		System.out.println("\nFrequency distribution table CSPRNG:");

		for (int i3 = 0; i3 <= 29; i3++) {
			sumOfGcd2 = 0;
			for (int i2 = 0; i2 <= 99; i2++) {
				secureRand_int = Math.abs(rand2.nextInt(100) + 1);
				secureRand_int2 = Math.abs(rand2.nextInt(100) + 1);
				while (secureRand_int != secureRand_int2) {
					if (secureRand_int > secureRand_int2) {
						secureRand_int = secureRand_int - secureRand_int2;
					} else {
						secureRand_int2 = secureRand_int2 - secureRand_int;
					}
					// System.out.println("Calculating GCD of pair of numbers (secureRandom).Might
					// be a bit!");
				}
				if ((secureRand_int == gcd2) || (secureRand_int2 == gcd2)) {
					sumOfGcd2++;
				}
			}
			pi2 = Math.sqrt(6 / (sumOfGcd2 / 100));
			individualPiSecureRandoms.add(pi2);
			System.out.println("\n" + pi2);
			sumOfPi2 += pi2;
		}
		double averagePi2 = sumOfPi2 / 30;
		System.out.println("\nThe average (mean) PI for CSPRNG was: " + averagePi2);

		// Standard Deviation Secure Pseudo Random Calculations
		for (int i = 0; i < individualPiSecureRandoms.size(); i++) {
			stepOneSecureRandom = individualPiSecureRandoms.get(i) - averagePi2;
			stepOneSecureRandom = Math.pow(stepOneSecureRandom, 2);
			standardDeviationSR += stepOneSecureRandom;
		}
		standardDeviationSR = standardDeviationSR / 29;
		standardDeviationSR = Math.sqrt(standardDeviationSR);
		System.out.println("The Standard Deviation for CSPRNG was: " + standardDeviationSR);

		double mathPiDifferences2 = Math.abs(averagePi2 - Math.PI);
		System.out.println(
				"CSPRNG: The difference between (PI) " + Math.PI + " and " + averagePi2 + " is: " + mathPiDifferences2);

		System.out.println("\n---------------------------------------------------------------------------------------");

		// TRNG Random.org
		System.out.println("\nFrequency distribution table TRNG:");

		String address = "https://www.random.org/integers/?num=200&min=1&max=100&col=2&base=10&format=plain&rnd=new";
		double pi3 = 0;
		double sumOfPi3 = 0;

		try {
			URL u;
			HttpURLConnection connection;

			InputStream stream=null;

			Scanner in=null;
			for(int i9 = 0; i9 <=29; i9 ++) {
				sumOfGcd3=0;
				
				u = new URL(address);
				connection = (HttpURLConnection) u.openConnection();
				stream = connection.getInputStream();
				in = new Scanner(stream);
				
				while (in.hasNext()) {
					truerand_int = in.nextInt();
					truerand_int2 = in.nextInt();
					if (truerand_int > truerand_int2) {
						truerand_int = truerand_int - truerand_int2;
						
					} else {
						truerand_int2 = truerand_int2 - truerand_int;						
					}

					if ((truerand_int == gcd3) || (truerand_int2 == gcd3)) {
						sumOfGcd3++;
					}
				}
				pi3 = Math.sqrt(6 / (sumOfGcd3 / 100)); // calculating pi for the thing
				individualPiTrueRandoms.add(pi3); // adding each pi to the array list
				System.out.println("\n" + pi3);
				sumOfPi3 += pi3;
				
				stream.close();
				in.close();
			}
		}

		catch (java.net.MalformedURLException ex) {
			System.out.println("Invalid URL");
		} catch (java.io.IOException ex) {
			System.out.println("IO Errors");
		}
		double averagePi3 = sumOfPi3 / 30;
		System.out.println("\nThe average (mean) PI for TRNG was: " + averagePi3);

		// Standard Deviation Secure Pseudo Random Calculations
		for (int i = 0; i < individualPiTrueRandoms.size(); i++) {
			stepOneTrueRandom = individualPiTrueRandoms.get(i) - averagePi3;
			stepOneTrueRandom = Math.pow(stepOneTrueRandom, 2);
			standardDeviationTR += stepOneTrueRandom;
		}
		standardDeviationTR = standardDeviationTR / 29;
		standardDeviationTR = Math.sqrt(standardDeviationTR);
		System.out.println("The Standard Deviation for TRNG was: " + standardDeviationTR);

		double mathPiDifferences3 = Math.abs(averagePi3 - Math.PI);
		System.out.println(
				"TRNG: The difference between (PI) " + Math.PI + " and " + averagePi3 + " is: " + mathPiDifferences3);
		System.out.println("\n---------------------------------------------------------------------------------------");

		// Comparing the 3 RNGs Standard Deviations
		System.out.println("\nThe Standard Deviation for each RNG goes as follows: ");
		System.out.println("\nPRNG: " + standardDeviation + "\t" + "CSPRNG: " + standardDeviationSR + "\t" + "TRNG: "
				+ standardDeviationTR);
	}

}
