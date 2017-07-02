package com.ynm;

import java.util.List;

import javax.jms.JMSException;

import com.ynm.repository.domain.GCD;

public interface GCDservice {

	int gcd(String apiKey) throws JMSException;

	List<GCD> gcdList(String apiKey);

	int gcdSum(String apiKey);
}
