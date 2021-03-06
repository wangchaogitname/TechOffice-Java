package com.tehoffice.word;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;

public class WordDocument {
	
	private File wordFile;
	private XWPFDocument document;
	
	public WordDocument(File file) throws IOException{
		this.wordFile = file;
		FileInputStream fileInputStream = new FileInputStream(file);
		document = new XWPFDocument(fileInputStream);
	}
	
	public XWPFDocument getDocument(){
		return document;
	}
	
	
	public void getParagraph(int seq){
		XWPFParagraph paragraph = document.getParagraphs().get(seq);
	}
	
	public WordTable getTable(int seq){
		return new WordTable(document.getTables().get(seq));
	}
	
}
