package com.ynm;

import java.util.List;

import javax.jms.JMSException;
import javax.jws.WebService;

import org.apache.cxf.phase.PhaseInterceptorChain;
import org.springframework.beans.factory.annotation.Autowired;

import com.ynm.repository.domain.GCD;
import com.ynm.schema.gcd.GcdValueList;
import com.ynm.schema.gcd.ObjectFactory;
import com.ynm.service.gcd.GCDInterface;

/**
 * @author Yogesh.Manware
 *
 */
@WebService(portName = "GCDSOAP", serviceName = "GCD", endpointInterface = "com.ynm.service.gcd.GCDInterface", targetNamespace = "http://www.ynm.com/service/gcd/")
public class GCDEndpoint implements GCDInterface {

	@Autowired
	private GCDservice gcdservice;

	@Autowired
	private UserController userController;

	@Override
	public int gcd() {
		final String apiKey = getApiKey();
		userController.waitAndGo(apiKey);
		try {
			return gcdservice.gcd(apiKey);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public GcdValueList gcdList() {
		final String apiKey = getApiKey();
		userController.waitAndGo(apiKey);

		List<GCD> gcdList = gcdservice.gcdList(apiKey);
		ObjectFactory factory = new ObjectFactory();
		GcdValueList gv = factory.createGcdValueList();

		for (GCD gcd : gcdList) {
			if (gcd.getResult() != 0) {
				gv.getGcdValue().add(gcd.getResult());
			}
		}
		return gv;
	}

	@Override
	public int gcdSum() {
		final String apiKey = getApiKey();
		userController.waitAndGo(apiKey);

		int sum = 0;
		List<GCD> gcdList = gcdservice.gcdList(apiKey);
		for (GCD gcd : gcdList) {
			sum += gcd.getResult();
		}
		return sum;
	}

	private String getApiKey() {
		if (PhaseInterceptorChain.getCurrentMessage().getExchange()
				.get("apiKey") != null) {
			return (String) PhaseInterceptorChain.getCurrentMessage()
					.getExchange().get("apiKey");

		} else {
			throw new RuntimeException("API key not provided");
		}
	}
}
