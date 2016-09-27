package com.game.server.line.loader;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.game.server.line.config.LineConfig;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-1
 * 
 * 服务器线程配置信息加载
 */
public class LineConfigXmlLoader {
	
	private Logger log = Logger.getLogger(LineConfigXmlLoader.class);
	
	//初始化服务器线程配置信息
	public List<LineConfig> load(String file){
        try
        {
        	List<LineConfig> configs = new ArrayList<LineConfig>();
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            InputStream in = ClassLoader.getSystemResourceAsStream(file);
            Document doc = builder.parse(in);
            NodeList list = doc.getElementsByTagName("server");
            for (int i = 0; i < list.getLength(); i++) 
            {
                Node node = list.item(i);
                NodeList childs = node.getChildNodes();
                //区域编号
                for (int j = 0; j < childs.getLength(); j++) {
					if(("lines").equals(childs.item(j).getNodeName())){
						NodeList lines = childs.item(j).getChildNodes();
						for (int k = 0; k < lines.getLength(); k++) {
							if(("line").equals(lines.item(k).getNodeName())){
								Node line = lines.item(k);
								LineConfig config = new LineConfig();
								
								NodeList attrs = line.getChildNodes();
								for (int l = 0; l < attrs.getLength(); l++) {
									if(("id").equals(attrs.item(l).getNodeName())){
										config.setId(Integer.parseInt(attrs.item(l).getTextContent()));
									}else if(("name").equals(attrs.item(l).getNodeName())){
										config.setName(attrs.item(l).getTextContent());
									}
								}
								
								configs.add(config);
							}
						}
						
					}
				}
            }
            in.close();
            
            return configs;
        }catch(Exception e){
        	log.error(e);
        }
        return null;
	}

}
