package com.ynm.repository.mappers;

import java.util.List;

import com.ynm.model.Parameters;
import com.ynm.repository.domain.GCD;

public interface GCDMapper {

	public void insertParameters(GCD gcd);

	public List<GCD> getGCDByApiKey(String apiKey);
	
	public List<Parameters> getParametersByApiKey(String apiKey);
	
	public void updateGCDResult(GCD gcd);

	public void deleteGCD(String apiKey);

}