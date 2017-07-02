package com.ynm;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.ynm.model.Parameters;
import com.ynm.repository.GCDRepository;
import com.ynm.repository.GCDRepositoryImpl;
import com.ynm.repository.domain.GCD;

public class GCDRepositoryTest {
	private static GCDRepository gcdRepository;

	private static String TEST_QUEUE = "TEST_QUEUE";

	@BeforeClass
	public static void setup() {
		gcdRepository = new GCDRepositoryImpl();
	}

	@Before
	public void clearTable() {
		gcdRepository.deleteGCD(TEST_QUEUE);
	}

	@AfterClass
	public static void clearTable2() {
		gcdRepository.deleteGCD(TEST_QUEUE);
	}
	
	
	@Test
	public void testPersistParameters() throws Exception {
		List<Parameters> before = gcdRepository.getAllParameters(TEST_QUEUE);
		Parameters params = new Parameters();
		params.setParam1(10);
		params.setParam1(20);
		gcdRepository.persistParameters(params, TEST_QUEUE);
		List<Parameters> after = gcdRepository.getAllParameters(TEST_QUEUE);
		Assert.assertTrue((after.size() != before.size()));
	}

	@Test
	public void testUpdateGCDResult() throws Exception {
		Parameters params = new Parameters();
		params.setParam1(10);
		params.setParam2(20);
		gcdRepository.persistParameters(params, TEST_QUEUE);

		GCD gcd = new GCD();
		gcd.setParam1(10);
		gcd.setParam2(20);
		gcd.setResult(10);
		gcd.setApiKey(TEST_QUEUE);
		gcdRepository.updateGCDResult(gcd);

		List<GCD> gcdResultList = gcdRepository.getGCDResults(TEST_QUEUE);

		for (GCD gcd2 : gcdResultList) {
			Assert.assertTrue(gcd2.getResult() != 0);
		}

	}

	@Test
	public void testFetch() {
		List<Parameters> paramList = gcdRepository.getAllParameters(TEST_QUEUE);
		assertNotNull(paramList);
	}
}
