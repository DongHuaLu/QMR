package com.game.server.loader;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.game.server.config.MinaServerConfig;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-1
 * 
 * 服务器启动配置加载
 */
public class MinaServerConfigXmlLoader {
	
	private Logger log = Logger.getLogger(MinaServerConfigXmlLoader.class);
	
	//初始化服务器配置信息
	public MinaServerConfig load(String file){
        try
        {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputStream in = new FileInputStream(file);
            Document doc = builder.parse(in);
            NodeList list = doc.getElementsByTagName("server");
            
            MinaServerConfig config = new MinaServerConfig();
            if(list.getLength() > 0)
            {
                Node node = list.item(0);
                NodeList childs = node.getChildNodes();
                
                for (int j = 0; j < childs.getLength(); j++) {
					if(("server-name").equals(childs.item(j).getNodeName())){
						config.setName(childs.item(j).getTextContent());
					}else if(("server-id").equals(childs.item(j).getNodeName())){
						config.setId(Integer.parseInt(childs.item(j).getTextContent()));
					}else if(("server-web").equals(childs.item(j).getNodeName())){
						config.setWeb(childs.item(j).getTextContent());
					}else if(("server-mina-port").equals(childs.item(j).getNodeName())){
						config.setMina_port(Integer.parseInt(childs.item(j).getTextContent()));
					}else if(("server-mina-ssl-port").equals(childs.item(j).getNodeName())){
						config.setMina_ssl_port(Integer.parseInt(childs.item(j).getTextContent()));
					}
				}
            }
            
            in.close();
            
            return config;
        }catch(Exception e){
        	log.error(e);
        }
        return null;
	}

}
