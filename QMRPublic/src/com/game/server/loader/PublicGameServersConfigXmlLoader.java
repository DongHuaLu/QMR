package com.game.server.loader;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.game.server.config.PublicGameServerInfo;

public class PublicGameServersConfigXmlLoader {

	private static Logger log = Logger.getLogger(PublicGameServersConfigXmlLoader.class);
	
	public ConcurrentHashMap<Integer, PublicGameServerInfo> load(String file){
		ConcurrentHashMap<Integer, PublicGameServerInfo> configs = new ConcurrentHashMap<Integer, PublicGameServerInfo>();
		try{
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputStream in = new FileInputStream(file);
            Document doc = builder.parse(in);
            NodeList list = doc.getElementsByTagName("servers");
            if(list.getLength() > 0)
            {
                Node node = list.item(0);
                NodeList childs = node.getChildNodes();
                
                for (int i = 0; i < childs.getLength(); i++) {
                	if(("server").equals(childs.item(i).getNodeName())){
                		NodeList schilds = childs.item(i).getChildNodes();
                		PublicGameServerInfo config = new PublicGameServerInfo();
	                    for (int j = 0; j < schilds.getLength(); j++) {
	    					if(("server-id").equals(schilds.item(j).getNodeName())){
	    						config.setServerId(Integer.parseInt(schilds.item(j).getTextContent().trim()));
	    					}else if(("server-web").equals(schilds.item(j).getNodeName())){
	    						config.setWeb(schilds.item(j).getTextContent().trim());
	    					}else if(("server-ip").equals(schilds.item(j).getNodeName())){
	    						config.setIp(schilds.item(j).getTextContent().trim());
	    					}else if(("server-port").equals(schilds.item(j).getNodeName())){
	    						config.setPort(Integer.parseInt(schilds.item(j).getTextContent().trim()));
	    					}else if(("server-sslport").equals(schilds.item(j).getNodeName())){
	    						config.setSslport(Integer.parseInt(schilds.item(j).getTextContent().trim()));
	    					}
	    				}
	                    configs.put(config.getServerId(), config);
                	}
				}
            }
            in.close();
		}catch(Exception e){
			log.error("读取角色检查配置异常"+e,e);
		}
		return configs;
	}

}
