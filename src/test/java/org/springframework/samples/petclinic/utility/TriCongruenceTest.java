package org.springframework.samples.petclinic.utility;

import com.github.mryf323.tractatus.*;
import com.github.mryf323.tractatus.experimental.extensions.ReportingExtension;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ExtendWith(ReportingExtension.class)
@ClauseDefinition(clause = 'a', def = "t1arr[0] != t2arr[0]")
@ClauseDefinition(clause = 'b', def = "t1arr[1] != t2arr[1]")
@ClauseDefinition(clause = 'c', def = "t1arr[2] != t2arr[2]")
@ClauseDefinition(clause = 'd', def = "t1arr[0] < 0")
@ClauseDefinition(clause = 'e', def = "t1arr[0] + t1arr[1] < t1arr[2]")
class TriCongruenceTest {

	private static final Logger log = LoggerFactory.getLogger(TriCongruenceTest.class);
	// CUTPNFP line 14

	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "a",
		valuations = {
			@Valuation(clause = 'a', valuation = true),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@Test
	public void areCongruent_line14_CUTPNFPTest1() {
		Triangle t1 = new Triangle(2, 5, 6);
		Triangle t2 = new Triangle(3, 5, 6);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent);
	}


	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "b",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = true),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@Test
	public void areCongruent_line14_CUTPNFPTest2() {
		Triangle t1 = new Triangle(3, 4, 6);
		Triangle t2 = new Triangle(3, 5, 6);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent);
	}


	@UniqueTruePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "c",
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = true)
		}
	)
	@Test
	public void areCongruent_line14_CUTPNFPTest3() {
		Triangle t1 = new Triangle(3, 4, 5);
		Triangle t2 = new Triangle(3, 4, 6);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent);
	}

	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "a",
		clause = 'a',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@Test
	public void areCongruent_line14_CUTPNFPTest4() {
		Triangle t1 = new Triangle(3, 4, 6);
		Triangle t2 = new Triangle(3, 4, 6);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertTrue(areCongruent);
	}

	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "b",
		clause = 'b',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@Test
	public void areCongruent_line14_CUTPNFPTest7() {
		Triangle t1 = new Triangle(3, 4, 6);
		Triangle t2 = new Triangle(3, 4, 6);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertTrue(areCongruent);
	}

	@NearFalsePoint(
		predicate = "a + b + c",
		dnf = "a + b + c",
		implicant = "c",
		clause = 'c',
		valuations = {
			@Valuation(clause = 'a', valuation = false),
			@Valuation(clause = 'b', valuation = false),
			@Valuation(clause = 'c', valuation = false)
		}
	)
	@Test
	public void areCongruent_line14_CUTPNFPTest6() {
		Triangle t1 = new Triangle(3, 4, 6);
		Triangle t2 = new Triangle(3, 4, 6);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertTrue(areCongruent);
	}


	// CC & CACC line 15
	@ClauseCoverage(
		predicate = "d || e",

		valuations = {
			@Valuation(clause = 'd', valuation = true),
			@Valuation(clause = 'e', valuation = true),
		}
	)
	@Test
	public void areCongruent_line15_CCTest_trueCase() {
		Triangle t1 = new Triangle(-1, 4, 5);
		Triangle t2 = new Triangle(1, 2, 3);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent);
	}
	@ClauseCoverage(
		predicate = "d || e",

		valuations = {
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = false),
		}
	)
	@CACC(
		predicate = "d || e",
		majorClause = 'd',
		predicateValue = false,
		valuations = {
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = false)
		}
	)

	@Test
	public void areCongruent_line15_CCTest_falseCase() {
		Triangle t1 = new Triangle(3, 4, 5);
		Triangle t2 = new Triangle(1, 2, 3);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent);
	}

	@CACC(
		predicate = "d || e",
		majorClause = 'd',
		predicateValue = true,
		valuations = {
			@Valuation(clause = 'd', valuation = true),
			@Valuation(clause = 'e', valuation = false)
		}
	)
	@Test
	public void areCongruent_line15_CACCTest_majorD_valueTrue() {
	//	Assertions.fail();
	}


	@CACC(
		predicate = "d || e",
		majorClause = 'e',
		predicateValue = true,
		valuations = {
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = true)
		}
	)
	@Test
	public void areCongruent_line15_CACCTest_majorE_valueTrue() {
		Triangle t1 = new Triangle(1, 2, 4);
		Triangle t2 = new Triangle(1, 2, 4);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertFalse(areCongruent);
	}



	@CACC(
		predicate = "d || e",
		majorClause = 'e',
		predicateValue = false,
		valuations = {
			@Valuation(clause = 'd', valuation = false),
			@Valuation(clause = 'e', valuation = false)
		}
	)
	@Test
	public void areCongruent_line15_CACCTest_majorE_valueFalse() {
		Triangle t1 = new Triangle(2, 3, 4);
		Triangle t2 = new Triangle(2, 3, 4);
		boolean areCongruent = TriCongruence.areCongruent(t1, t2);
		log.debug("Triangles identified as '{}'.", areCongruent ? "Congruent" : "Not Congruent");
		Assertions.assertTrue(areCongruent);
	}

	/*
	از مثال نقض برای نشان دادن خواسته سوال استفاده کردیم. عبارت ab + cd را در نظر گرفتیم و
	مجموعه حالت‌ها را به ازای CUTPNFP بدست آوردیم و نشان دادیم این حالت ها نمی‌توانند خواسته UTPC را
	پوشش دهند. پس CUTPNFP نمی‌تواند UTPC را پوشش دهد.
	f = ab + cd ---> implicants = {ab, cd, ~a~d, ~a~c, ~b~c, ~b~d}
	CUTPNFP set = {TTFF, FFTT, TFFF, FTFF, FFTF, FFFT}
	TTFF --> ab = T , cd = F , ~a~d = F , ~a~c = F , ~b~c = F , ~b~d = F --> can use for ab
	FFTT --> ab = F , cd = T , ~a~d = F , ~a~c = F , ~b~c = F , ~b~d = F --> can use for cd
	TFFF --> ab = F , cd = F , ~a~d = F , ~a~c = F , ~b~c = T , ~b~d = T --> can't use
	FTFF --> ab = F , cd = F , ~a~d = T , ~a~c = T , ~b~c = F , ~b~d = F --> can't use
	FFTF --> ab = F , cd = F , ~a~d = T , ~a~c = F , ~b~c = F , ~b~d = T --> can't use
	FFFT --> ab = F , cd = F , ~a~d = F , ~a~c = T , ~b~c = T , ~b~d = F --> can't use

	---->> CUTPNFP doesn't subsume UTPC
*/

	public void questionTwo(boolean a, boolean b, boolean c, boolean d) {
		boolean predicate = a&b | c&d;
	}
}
