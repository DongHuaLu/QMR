package com.game.player.loader;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.game.player.config.NonageConfig;

/**
 * 
 * @author 何洋
 * @2012-10-10 下午2:56:32
 */
public class NonageConfigXmlLoader {
	private Logger log = Logger.getLogger(NonageConfigXmlLoader.class);
	// 初始化服务器配置信息
	public NonageConfig load(String file) {
		NonageConfig config = new NonageConfig();
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputStream in = new FileInputStream(file);
			Document doc = builder.parse(in);
			NodeList list = doc.getElementsByTagName("servers");
			if (list.getLength() > 0) {
				Node node = list.item(0);
				NodeList childs = node.getChildNodes();

				for (int i = 0; i < childs.getLength(); i++) {
					if (("server").equals(childs.item(i).getNodeName())) {
						NodeList schilds = childs.item(i).getChildNodes();
						for (int j = 0; j < schilds.getLength(); j++) {
							if (("nonage").equals(schilds.item(j).getNodeName())) {
								config.setNonage(Integer.parseInt(schilds.item(j).getTextContent()));
							}
						}
					}
				}
				in.close();
			}
		} catch (Exception e) {
			log.error(e);
		}
		return config;
	}
}
