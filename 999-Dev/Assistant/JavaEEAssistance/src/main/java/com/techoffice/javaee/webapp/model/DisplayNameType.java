//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.11.18 at 10:19:16 AM CST 
//


package com.techoffice.javaee.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * 
 * 	
 * 
 * 	  The display-name type contains a short name that is intended
 * 	  to be displayed by tools. It is used by display-name
 * 	  elements.  The display name need not be unique.
 * 
 * 	  Example:
 * 
 * 	  ...
 * 	     <display-name xml:lang="en">
 * 	       Employee Self Service
 * 	     </display-name>
 * 
 * 	  The value of the xml:lang attribute is "en" (English) by default.
 * 
 * 	  
 *       
 * 
 * <p>Java class for display-nameType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="display-nameType"&gt;
 *   &lt;simpleContent&gt;
 *     &lt;extension base="&lt;http://java.sun.com/xml/ns/javaee&gt;string"&gt;
 *       &lt;attribute ref="{http://www.w3.org/XML/1998/namespace}lang"/&gt;
 *     &lt;/extension&gt;
 *   &lt;/simpleContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "display-nameType")
public class DisplayNameType
    extends com.techoffice.javaee.webapp.model.String
{

    @XmlAttribute(name = "lang", namespace = "http://www.w3.org/XML/1998/namespace")
    protected java.lang.String lang;

    /**
     * Gets the value of the lang property.
     * 
     * @return
     *     possible object is
     *     {@link java.lang.String }
     *     
     */
    public java.lang.String getLang() {
        return lang;
    }

    /**
     * Sets the value of the lang property.
     * 
     * @param value
     *     allowed object is
     *     {@link java.lang.String }
     *     
     */
    public void setLang(java.lang.String value) {
        this.lang = value;
    }

}
