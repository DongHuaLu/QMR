package com.game.server.loader;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.game.server.config.CheckConfig;

public class CheckConfigXmlLoader {

	private static Logger log = Logger.getLogger(CheckConfigXmlLoader.class);
	
	public CheckConfig load(String file){
		CheckConfig config = new CheckConfig();
		try{
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputStream in = new FileInputStream(file);
            Document doc = builder.parse(in);
            NodeList taglist = doc.getElementsByTagName("config");
            if(taglist.getLength()>0){
            	Node item = taglist.item(0);
            	NodeList childNodes = item.getChildNodes();
            	for(int index=0; index<childNodes.getLength(); index++){
            		Node n = childNodes.item(index);
            		if("checkbetween".equals(n.getNodeName())){
            			config.setCheckbetween(Integer.parseInt(n.getTextContent()));
            		} else if("distance".equals(n.getNodeName())){
            			config.setDistance(Integer.parseInt(n.getTextContent()));
            		} else if("basespeed".equals(n.getNodeName())){
            			config.setBasespeed(Integer.parseInt(n.getTextContent()));
            		}
            	}
            }
            in.close();
            return config;
		}catch(Exception e){
			log.error("读取角色检查配置异常"+e,e);
		}
		//加载异常则使用默认值
		config.setCheckbetween(30000);
		config.setDistance(50);
		config.setBasespeed(100);
		return config;
	}
	
	
	public static void main(String[] args){
		CheckConfig ck = new CheckConfig();
		ck = new CheckConfigXmlLoader().load("server-config/check-config.xml");
		System.out.println(ck.getCheckbetween());
		System.out.println(ck.getDistance());
		System.out.println(ck.getBasespeed());
	}
}
