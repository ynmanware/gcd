package com.ynm.service;

import java.util.List;

import com.ynm.model.Parameters;

public interface GCDRestService {

	String processParameters(Parameters params, String key);

	List<Parameters> getAllParameters(String key);

}
