
package com.ynm.schema.gcd;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ynm.schema.gcd package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GcdValue_QNAME = new QName("http://www.ynm.com/schema/gcd", "gcdValue");
    private final static QName _GcdSum_QNAME = new QName("http://www.ynm.com/schema/gcd", "gcdSum");
    private final static QName _GcdKey_QNAME = new QName("http://www.ynm.com/schema/gcd", "gcdKey");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ynm.schema.gcd
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GcdValueList }
     * 
     */
    public GcdValueList createGcdValueList() {
        return new GcdValueList();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ynm.com/schema/gcd", name = "gcdValue")
    public JAXBElement<Integer> createGcdValue(Integer value) {
        return new JAXBElement<Integer>(_GcdValue_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ynm.com/schema/gcd", name = "gcdSum")
    public JAXBElement<Integer> createGcdSum(Integer value) {
        return new JAXBElement<Integer>(_GcdSum_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ynm.com/schema/gcd", name = "gcdKey")
    public JAXBElement<String> createGcdKey(String value) {
        return new JAXBElement<String>(_GcdKey_QNAME, String.class, null, value);
    }

}
