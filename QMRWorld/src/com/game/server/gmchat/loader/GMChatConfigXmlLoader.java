package com.game.server.gmchat.loader;

import java.io.FileInputStream;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.game.server.gmchat.config.GMChatConfig;
import com.game.server.http.config.HttpServerConfig;
import com.game.server.http.loader.HttpConfigXmlLoader;

/**
 * 
 * @author 赵聪慧
 * @2012-10-10 下午2:56:32
 */
public class GMChatConfigXmlLoader {
	private Logger log = Logger.getLogger(HttpConfigXmlLoader.class);
	// 初始化服务器配置信息
	public GMChatConfig load(String file) {
		try {
			DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			InputStream in = new FileInputStream(file);
			Document doc = builder.parse(in);
			NodeList list = doc.getElementsByTagName("servers");
			if (list.getLength() > 0) {
				GMChatConfig config = new GMChatConfig();
				Node node = list.item(0);
				NodeList childs = node.getChildNodes();

				for (int i = 0; i < childs.getLength(); i++) {
					if (("server").equals(childs.item(i).getNodeName())) {
						NodeList schilds = childs.item(i).getChildNodes();
						for (int j = 0; j < schilds.getLength(); j++) {
							if (("server-port").equals(schilds.item(j).getNodeName())) {
								config.setPort(Integer.parseInt(schilds.item(j).getTextContent()));
							} else if (("server-allow").equals(schilds.item(j).getNodeName())) {
								config.setIp(schilds.item(j).getTextContent());
							}
						}
					}
				}
				in.close();
				return config;
			}
		} catch (Exception e) {
			log.error(e);
		}
		return null;
	}
}
