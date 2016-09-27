package com.game.login.loader;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.game.login.config.GateHeartConfig;

public class GateHeartConfigXmlLoader {

	private static Logger log = Logger.getLogger(GateHeartConfigXmlLoader.class);
	
	public GateHeartConfig load(String file){
		GateHeartConfig config = new GateHeartConfig();
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
            		if("heartpara".equals(n.getNodeName())){
            			config.setHeart_para(n.getTextContent());
            		} else if("heartweb".equals(n.getNodeName())){
            			config.setHeart_web(n.getTextContent());
            		} 
            	}
            }
            in.close();
            return config;
		}catch(Exception e){
			log.error("读取角色检查配置异常"+e,e);
		}
		//加载异常则使用默认值
		config.setHeart_para("pt=%s&sid=%d&state=%d&tip=alive&locate=server"); //默认值
		config.setHeart_web("http://122.226.64.45:9080/QMRBackend/r/callback.do?c=tablehearbeat");  //默认值
		return config;
	}
	
	
	public static void main(String[] args){
		GateHeartConfig ck = new GateHeartConfig();
		ck = new GateHeartConfigXmlLoader().load("gate-config/gate-heart-config.xml");
		System.out.println(ck.getHeart_para());
		System.out.println(ck.getHeart_web());
	}
}
