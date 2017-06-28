package com.ynm;

import javax.jws.WebService;

import com.ynm.schema.gcd.GcdValueList;
import com.ynm.schema.gcd.ObjectFactory;
import com.ynm.service.gcd.GCDInterface;

/**
 * @author Yogesh.Manware
 *
 */
@WebService(portName = "GCDSOAP", serviceName = "GCD", endpointInterface = "com.ynm.service.gcd.GCDInterface", targetNamespace = "http://www.ynm.com/service/gcd/")
public class GCDEndpoint implements GCDInterface {

	@Override
	public int getGCD(String gcdKey) {
		return 10;
	}

	@Override
	public GcdValueList getGCDList(String gcdKey1) {
		// TODO Auto-generated method stub
		ObjectFactory factory = new ObjectFactory();

		GcdValueList gv = factory.createGcdValueList();
		gv.getGcdValue().add(10);
		gv.getGcdValue().add(12);

		return gv;
	}

	@Override
	public int getGCDSum(String gcdKey2) {
		// TODO Auto-generated method stub
		return 100;
	}

}
