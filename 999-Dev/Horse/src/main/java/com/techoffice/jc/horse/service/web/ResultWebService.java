package com.techoffice.jc.horse.service.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.techoffice.util.XmlUtil;

@Component
public class ResultWebService {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());

	public static final String URL = "http://racing.hkjc.com/racing/Info/meeting/Results/chinese/Local/";	
	
	@Autowired
	private WebClient webClient;
	
	public String retrieveXml() throws FailingHttpStatusCodeException, MalformedURLException, IOException, ParserConfigurationException, SAXException, XPathExpressionException, InterruptedException, TransformerException{
        final HtmlPage page = webClient.getPage(URL);
        String xml = page.asXml();
        webClient.close();
        return xml;
	}
	
	public void raceDateSelect() throws FailingHttpStatusCodeException, MalformedURLException, XPathExpressionException, IOException, ParserConfigurationException, SAXException, InterruptedException, TransformerException{
		String xml = retrieveXml();
		NodeList dateSelectList = XmlUtil.evaluateXpath(xml, "/html/body/div[2]/div[2]/div[2]/div[3]/table/tbody/tr/td[2]/select");
		Node dateSelect = dateSelectList.item(0);
		NodeList raceDatesNodeList = dateSelect.getChildNodes();
		for (int i=0; i<raceDatesNodeList.getLength(); i++){
			Node raceDate = raceDatesNodeList.item(i);
			if("option".equals(raceDate.getNodeName())){
				System.out.println(raceDate.getAttributes().getNamedItem("value").getNodeValue());
			}
		}
	}
}