package com.ynm;

import javax.xml.namespace.QName;

import org.apache.cxf.binding.soap.SoapHeader;
import org.apache.cxf.binding.soap.SoapMessage;
import org.apache.cxf.binding.soap.interceptor.AbstractSoapInterceptor;
import org.apache.cxf.interceptor.Fault;
import org.apache.cxf.phase.Phase;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.w3c.dom.Element;

public class GCDServiceSoapHeaderInInterceptor extends AbstractSoapInterceptor {

	/**
	 * Set the phase for this interceptor to USER_PROTOCOL
	 */
	public GCDServiceSoapHeaderInInterceptor() {
		super(Phase.USER_PROTOCOL);
	}

	@Override
	public void handleMessage(SoapMessage message) throws Fault {
		// Set the qualified name using the orders web service namespace and the
		// local part name of apikey.
		QName qname = new QName("http://www.ynm.com/service/gcd/", "apikey");

		// Get the SOAP header from the message using the qualified name.
		SoapHeader header = (SoapHeader) message.getHeader(qname);

		// Get the element from the soap header and set the api key to its text
		// content.
		String apikey = ((Element) header.getObject()).getTextContent();

		PhaseInterceptorChain.getCurrentMessage().getExchange()
				.put("apiKey", apikey);
	}

}
