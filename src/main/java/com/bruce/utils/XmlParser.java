package com.bruce.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.input.SAXBuilder;


public class XmlParser {
private static XmlParser instance;
public static XmlParser newInstance(){
	if(instance == null)
		instance = new XmlParser();
	return instance;
}

/**
 * 解析器把InputStream字符串解析成document
 * @param xml
 * @return
 * @throws Exception
 */
public Document tranceStringToDoc(InputStream xml,SAXBuilder sax)throws Exception{
	Document doc = sax.build(xml);
	return doc;
}
/**
 * 获取根element
 * @param doc
 * @return
 */
public Element getRootElement(Document doc){
	Element root = null;
	if(doc != null){
		root = doc.getRootElement();
	}
	return root;
}
public Element parseInputStringToelement(InputStream in)throws Exception{
	Element e = null;
	SAXBuilder sax = new SAXBuilder();
	sax.setValidation(false);
	Document doc = tranceStringToDoc(in, sax);
	e = getRootElement(doc);
	return e;
}
/**
 * string 转换为element
 * @param xml
 * @return
 * @throws Exception
 */
public Element parseXmlToElement(String xml) throws Exception{
	Element e = null;
	if(null == xml || "".equals(xml))
		return e;
	InputStream str = new  ByteArrayInputStream(xml.getBytes("utf-8"));
	
	SAXBuilder sax = new SAXBuilder();
	sax.setValidation(false);
	Document doc = tranceStringToDoc(str, sax);
	e = getRootElement(doc);
	return e;
}
public Element getElementByName(Document doc,String name){
	Element el = null;
	return el;
}
}
