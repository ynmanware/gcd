package com.ynm.repository;

import java.util.List;

import com.ynm.model.Parameters;
import com.ynm.repository.domain.GCD;

public interface GCDRepository {

	void persistParameters(Parameters params, String key) throws Exception;

	List<Parameters> getAllParameters(String key);

	void updateGCDResult(GCD gcd);

	List<GCD> getGCDResults(String key);
}
