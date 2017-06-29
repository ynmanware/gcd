package com.ynm.repository;

import java.util.ArrayList;
import java.util.List;

import com.ynm.model.Parameters;

/**
 * @author Yogesh.Manware
 *
 */
public class GCDRepositoryStub implements GCDRepository {

	private static List<Parameters> params = new ArrayList<>();

	@Override
	public void persistParameters(Parameters params) {
		this.params.add(params);
	}

	@Override
	public List<Parameters> getAllParameters(String key) {
		return params;
	}
}
