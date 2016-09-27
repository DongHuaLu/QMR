package com.game.login.loader;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.game.login.config.HeartConfig;

public class HeartConfigXmlLoader {

	private static Logger log = Logger.getLogger(HeartConfigXmlLoader.class);
	
	public HeartConfig load(String file){
		HeartConfig config = new HeartConfig();
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
            		if("hearttime".equals(n.getNodeName())){
            			config.setHearttime(Long.valueOf(n.getTextContent()));
            		} else if("allowtime".equals(n.getNodeName())){
            			config.setAllowtime(Long.valueOf(n.getTextContent()));
            		} else if("success".equals(n.getNodeName())){
            			config.setSuccess(Integer.parseInt(n.getTextContent()));
            		} else if("error".equals(n.getNodeName())){
            			config.setError(Integer.parseInt(n.getTextContent()));
            		} else if("closetime".equals(n.getNodeName())){
            			config.setClosetime(Long.valueOf(n.getTextContent()));
            		}
            	}
            }
            in.close();
            return config;
		}catch(Exception e){
			log.error("读取心跳配置异常"+e,e);
		}
		//加载异常则使用默认值
		config.setHearttime(10000L);
		config.setAllowtime(8000L);
		config.setSuccess(2);
		config.setError(1);
		config.setClosetime(120000L);
		return config;
	}

}
