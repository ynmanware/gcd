package com.ynm;

import java.io.IOException;

import javax.jws.WebService;

import org.apache.cxf.phase.PhaseInterceptorChain;
import org.springframework.beans.factory.annotation.Autowired;

import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.ShutdownSignalException;
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

	@Override
	public int gcd() {

		final String apiKey;

		if (PhaseInterceptorChain.getCurrentMessage().getExchange()
				.get("apiKey") != null) {
			apiKey = (String) PhaseInterceptorChain.getCurrentMessage()
					.getExchange().get("apiKey");

		} else {
			throw new RuntimeException("API key not provided");
		}

		try {
			return gcdservice.gcd(apiKey);
		} catch (ShutdownSignalException | ConsumerCancelledException
				| ClassNotFoundException | IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return -1;
	}

	@Override
	public GcdValueList gcdList() {
		// TODO Auto-generated method stub
		ObjectFactory factory = new ObjectFactory();

		GcdValueList gv = factory.createGcdValueList();
		gv.getGcdValue().add(10);
		gv.getGcdValue().add(12);

		return gv;
	}

	@Override
	public int gcdSum() {
		// TODO Auto-generated method stub
		return 100;
	}

}
