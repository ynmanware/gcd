package com.ynm;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import com.ynm.model.Parameters;
import com.ynm.repository.ParametersRepository;
import com.ynm.repository.ParametersRepositoryStub;

public class ParameterServiceTest {
	static ParametersRepository ps;

	@BeforeClass
	public static void testParameterService() throws Exception {
		ps = new ParametersRepositoryStub();
		Parameters params = new Parameters();
		params.setParam1(10);
		params.setParam1(20);
		ps.persistParameters(params);
	}

	@Test
	public void testFetch() {
		List<Parameters> paramList = ps.getAllParameters("TODO");
		assertNotNull(paramList);
	}
}
