package com.techoffice.jc.horse.service.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.text.ParseException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.xml.sax.SAXException;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.techoffice.jc.horse.dao.RaceResultDao;
import com.techoffice.jc.horse.dao.RaceResultHorseDao;
import com.techoffice.jc.horse.dao.RaceResultQueueDao;
import com.techoffice.jc.horse.model.RaceResult;
import com.techoffice.jc.horse.model.RaceResultQueue;
import com.techoffice.util.exception.XmlUtilXpathNotUniqueException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/beans.xml")
public class ResultWebServiceTest {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private ResultWebService resultWebService;
	
	@Autowired
	private RaceResultQueueDao raceResultQueueDao ;
	
	@Autowired
	private RaceResultDao raceResultDao;
	
	@Autowired
	private RaceResultHorseDao raceResultHorseDao;

//	@Test
	public void retrieveXml() throws FailingHttpStatusCodeException, MalformedURLException, XPathExpressionException, IOException, ParserConfigurationException, SAXException, InterruptedException, TransformerException{
		String xml = resultWebService.retrieveXml();
		System.out.println(xml);	
	}
	
//	@Test
	public void retrieveRaceDateList() throws FailingHttpStatusCodeException, MalformedURLException, XPathExpressionException, IOException, ParserConfigurationException, SAXException, InterruptedException, TransformerException{
		List<String> raceDateList = resultWebService.retrieveRaceDateList();
		for(String raceDate: raceDateList){
			System.out.println(raceDate);
		}
	}
	
//	@Test
	public void getRaceNumList() throws FailingHttpStatusCodeException, MalformedURLException, XPathExpressionException, IOException, ParserConfigurationException, SAXException, InterruptedException, TransformerException{
		raceResultQueueDao.deleteAll();
		int raceResultCount = 0;
		List<String> raceDateList = resultWebService.retrieveRaceDateList();
		for(String raceDate: raceDateList){
			List<String> raceNumList = resultWebService.getRaceNumList(raceDate);
			for(String raceNum: raceNumList){
				RaceResultQueue queue = new RaceResultQueue();
				queue.setLocation(raceNum);
				raceResultQueueDao.addRaceResultQueue(queue);
				raceResultCount++;
				System.out.println(raceNum);
			}
		}
		System.out.println(raceResultCount + " Reace Results is inserted into the Queue.");
	}
	
	@Test
	public void getRaceResult() throws FailingHttpStatusCodeException, MalformedURLException, XPathExpressionException, IOException, ParserConfigurationException, SAXException, InterruptedException, TransformerException, XmlUtilXpathNotUniqueException, ParseException{
		List<String> raceDateList = resultWebService.retrieveRaceDateList();
		List<String> raceNumList = resultWebService.getRaceNumList(raceDateList.get(0));
		RaceResult raceResult = resultWebService.getRaceResult(raceNumList.get(0));
		raceResultDao.add(raceResult);
		raceResultHorseDao.addList(raceResult.getRaceResultHorseList());
		System.out.println("raceResult with id:" + raceResult.getId() + " is created.");
		System.out.println(raceResult.getRaceResultHorseList().get(0).getId());
	}
}
