package com.ynm.service.gcd;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * The address path we provided will be configured as
 * 			part of the jax-ws endpoint CXF definition.
 * 			The service will map to an
 * 			interface in Java.
 * 		
 *
 * This class was generated by Apache CXF 2.7.8
 * 2017-07-01T18:37:31.898+10:00
 * Generated source version: 2.7.8
 * 
 */
@WebServiceClient(name = "GCD", 
                  wsdlLocation = "file:/F:/unico2/gcd/gcdsoap/src/main/resources/wsdl/gcd.wsdl",
                  targetNamespace = "http://www.ynm.com/service/gcd/") 
public class GCD extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://www.ynm.com/service/gcd/", "GCD");
    public final static QName GCDSOAP = new QName("http://www.ynm.com/service/gcd/", "GCDSOAP");
    static {
        URL url = null;
        try {
            url = new URL("file:/F:/unico2/gcd/gcdsoap/src/main/resources/wsdl/gcd.wsdl");
        } catch (MalformedURLException e) {
            java.util.logging.Logger.getLogger(GCD.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "file:/F:/unico2/gcd/gcdsoap/src/main/resources/wsdl/gcd.wsdl");
        }
        WSDL_LOCATION = url;
    }

    public GCD(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public GCD(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public GCD() {
        super(WSDL_LOCATION, SERVICE);
    }
    
    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public GCD(WebServiceFeature ... features) {
        super(WSDL_LOCATION, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public GCD(URL wsdlLocation, WebServiceFeature ... features) {
        super(wsdlLocation, SERVICE, features);
    }

    //This constructor requires JAX-WS API 2.2. You will need to endorse the 2.2
    //API jar or re-run wsdl2java with "-frontend jaxws21" to generate JAX-WS 2.1
    //compliant code instead.
    public GCD(URL wsdlLocation, QName serviceName, WebServiceFeature ... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     *
     * @return
     *     returns GCDInterface
     */
    @WebEndpoint(name = "GCDSOAP")
    public GCDInterface getGCDSOAP() {
        return super.getPort(GCDSOAP, GCDInterface.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns GCDInterface
     */
    @WebEndpoint(name = "GCDSOAP")
    public GCDInterface getGCDSOAP(WebServiceFeature... features) {
        return super.getPort(GCDSOAP, GCDInterface.class, features);
    }

}
