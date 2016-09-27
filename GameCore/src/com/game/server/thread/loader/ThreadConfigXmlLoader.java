package com.game.server.thread.loader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.game.server.thread.config.ThreadConfig;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-1
 * 
 * 服务器线程配置信息加载
 */
public class ThreadConfigXmlLoader {
	
	private Logger log = Logger.getLogger(ThreadConfigXmlLoader.class);
	
	//初始化服务器线程配置信息
	public List<ThreadConfig> load(String file){
        try
        {
        	List<ThreadConfig> configs = new ArrayList<ThreadConfig>();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputStream in = ClassLoader.getSystemResourceAsStream(file);
            Document doc = builder.parse(in);
            NodeList list = doc.getElementsByTagName("thread");
            for (int i = 0; i < list.getLength(); i++) 
            {
                Node node = list.item(i);
                NodeList childs = node.getChildNodes();
                
                ThreadConfig config = new ThreadConfig();
                for (int j = 0; j < childs.getLength(); j++) {
					if(("thread-name").equals(childs.item(j).getNodeName())){
						config.setThreadName(childs.item(j).getTextContent());
					}else if(("thread-heart").equals(childs.item(j).getNodeName())){
						config.setHeart(Integer.parseInt(childs.item(j).getTextContent()));
					}
				}
                configs.add(config);
            }
            in.close();
            
            return configs;
        }catch(Exception e){
            log.error(e);
        }
        return null;
	}

}
