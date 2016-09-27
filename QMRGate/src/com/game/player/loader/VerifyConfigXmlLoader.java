package com.game.player.loader;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.game.player.structs.VerifyConfig;
import com.game.server.loader.GameConfigXmlLoader;

public class VerifyConfigXmlLoader {

	private Logger log = Logger.getLogger(GameConfigXmlLoader.class);
	
	public VerifyConfig load(String file){
		VerifyConfig config = new VerifyConfig();
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
            		if("md5key".equals(n.getNodeName())){
            			config.setMd5key(n.getTextContent());
            		}else if("losttime".equals(n.getNodeName())){
            			if(StringUtils.isNumeric(n.getTextContent())){
        	            	config.setLosttime(Long.valueOf(n.getTextContent()));
        	            }else{
        	            	config.setLosttime(0);
        	            }
            		}else if("token".equals(n.getNodeName())){
            			config.setToken(StringUtils.isBlank(n.getTextContent())?"":n.getTextContent());
            		}
            	}
            }
            in.close();
            return config;
		}catch(Exception e){
			log.error("读取验证配置异常"+e,e);
		}
		return config;
	}
	
	public static void main(String[] args){
		
		VerifyConfigXmlLoader l = new VerifyConfigXmlLoader();
		VerifyConfig config = l.load("gate-config/verify-config.xml");
		System.out.println(config.getMd5key());
		System.out.println(config.getLosttime());
	}
	
	
	
}
