package com.game.server.http.loader;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.game.server.http.config.HttpServerConfig;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-1
 * 
 * 服务器启动配置加载
 */
public class HttpConfigXmlLoader {
	
	private Logger log = Logger.getLogger(HttpConfigXmlLoader.class);
	
	//初始化服务器配置信息
	public HttpServerConfig load(String file){
        try
        {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputStream in = new FileInputStream(file);
            Document doc = builder.parse(in);
            NodeList list = doc.getElementsByTagName("servers");
            if(list.getLength() > 0)
            {
            	HttpServerConfig config = new HttpServerConfig();
                Node node = list.item(0);
                NodeList childs = node.getChildNodes();
                
                for (int i = 0; i < childs.getLength(); i++) {
                	if(("server").equals(childs.item(i).getNodeName())){
                		NodeList schilds = childs.item(i).getChildNodes();
	                    for (int j = 0; j < schilds.getLength(); j++) {
	    					if(("server-port").equals(schilds.item(j).getNodeName())){
	    						config.setPort(Integer.parseInt(schilds.item(j).getTextContent()));
	    					}else if(("server-allow").equals(schilds.item(j).getNodeName())){
	                    		NodeList achilds = schilds.item(j).getChildNodes();
	    	                    for (int k = 0; k < achilds.getLength(); k++) {
	    	    					if(("allow-ip").equals(achilds.item(k).getNodeName())){
	    	    						config.getAllows().add(achilds.item(k).getTextContent());
	    	    					}
	    	    				}
	                    	}
	    				}
                	}
				}
                
                in.close();
                return config;
            }
        }catch(Exception e){
        	log.error(e);
        }
        return null;
	}

}
