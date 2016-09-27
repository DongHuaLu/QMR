package com.game.script.manager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;

import com.game.data.bean.Q_scriptBean;
import com.game.manager.ManagerPool;
import com.game.player.manager.PlayerManager;
import com.game.player.structs.Player;
import com.game.prompt.structs.Notifys;
import com.game.script.IScript;
import com.game.script.loader.ScriptClassLoader;
import com.game.utils.HttpUtil;
import com.game.utils.MessageUtil;
import com.game.utils.ScriptsUtils;

public class ScriptManager {

	private Logger log = Logger.getLogger(ScriptManager.class);

	private URLClassLoader parentClassLoader;

	private String classpath;

	private static Object obj = new Object();

	private static HashMap<Integer, IScript> scripts = new HashMap<Integer, IScript>();
	
	// 玩家管理类实例
	private static ScriptManager manager;

	public static ScriptManager getInstance() {
		synchronized (obj) {
			if (manager == null) {
				manager = new ScriptManager();
			}
		}
		return manager;
	}

	private ScriptManager() {
		this.parentClassLoader = (URLClassLoader) this.getClass()
				.getClassLoader();
		this.buildClassPath();
		this.init();
	}

	private void buildClassPath() {
		this.classpath = null;
		StringBuilder sb = new StringBuilder();
		for (URL url : this.parentClassLoader.getURLs()) {
			String p = url.getFile();
			sb.append(p).append(File.pathSeparator);
		}
		this.classpath = sb.toString();
	}
	
	private void init(){
		File file = new File("bin");
		if(!file.exists()){
			file.mkdir();
		}
		List<Q_scriptBean> scriptBeans = ManagerPool.dataManager.q_scriptContainer.getList();
		for (int i = 0; i < scriptBeans.size(); i++) {
			Q_scriptBean script = scriptBeans.get(i);
			if(script.getQ_world()==0) continue;
			try{
				Class<?> clazz = Class.forName(script.getQ_script_name());
				if(clazz!=null){
					IScript o = (IScript)clazz.newInstance();
					scripts.put(o.getId(), o);
					continue;
				}
			}catch (Exception e) {}
			try{
				if(script.getQ_script_name()==null || "".equals(script.getQ_script_name())) continue;
				InputStream in = new FileInputStream(script.getQ_script_name().replace('.', '/') + ".java");
				StringBuffer buff = new StringBuffer();
				
				IoBuffer buf = IoBuffer.allocate(10240);
				buf.setAutoExpand(true);
				buf.setAutoShrink(true);
				
				byte[] bytes = new byte[1024];
				int len = 0;
				while((len=in.read(bytes)) != -1){
					buf.put(bytes, 0, len);
				}
				buf.flip();
				
				byte[] allbytes = new byte[buf.remaining()];
				buf.get(allbytes);
				buff.append(new String(allbytes, "UTF-8"));
				in.close();
				
				Class<?> clazz = javaCodeToObject(script.getQ_script_name(), buff.toString());
				if(clazz!=null){
					IScript o = (IScript)clazz.newInstance();
					scripts.put(o.getId(), o);
				}
				
			}catch(FileNotFoundException e){
				log.error(e, e);
			}catch(IllegalAccessException e){
				log.error(e, e);
			}catch(InstantiationException e){
				log.error(e, e);
			}catch(IOException e){
				log.error(e, e);
			}catch(Exception e){
				log.error(e, e);
			}
		}
	}
	
	public void reload(int id, long roleId){
		File file = new File("bin");
		if(!file.exists()){
			file.mkdir();
		}
		Q_scriptBean script = ManagerPool.dataManager.q_scriptContainer.getMap().get(id);
		if(script!=null){
			try{
				if(script.getQ_script_name()==null || "".equals(script.getQ_script_name())) return;
				InputStream in = new FileInputStream(script.getQ_script_name().replace('.', '/') + ".java");
				StringBuffer buff = new StringBuffer();
				
				IoBuffer buf = IoBuffer.allocate(10240);
				buf.setAutoExpand(true);
				buf.setAutoShrink(true);
				
				byte[] bytes = new byte[1024];
				int len = 0;
				while((len=in.read(bytes)) != -1){
					buf.put(bytes, 0, len);
				}
				buf.flip();
				
				byte[] allbytes = new byte[buf.remaining()];
				buf.get(allbytes);
				buff.append(new String(allbytes, "UTF-8"));
				in.close();
				
				Class<?> clazz = javaCodeToObject(script.getQ_script_name(), buff.toString());
				if(clazz!=null){
					IScript o = (IScript)clazz.newInstance();
					scripts.put(o.getId(), o);
					
					Player player = PlayerManager.getInstance().getPlayer(roleId);
					if(player!=null){
						MessageUtil.notify_player(player, Notifys.SUCCESS,"重加载脚本{1}成功",id+"");
					}
					log.error("脚本加载成功");
				}
				
			}catch(FileNotFoundException e){
				log.error(e, e);
			}catch(IllegalAccessException e){
				log.error(e, e);
			}catch(InstantiationException e){
				log.error(e, e);
			}catch(IOException e){
				log.error(e, e);
			}catch(Exception e){
				log.error(e, e);
			}
		}
	}
	
	public String reloadbybg(int id, String resulthttp){
		File file = new File("bin");
		if(!file.exists()){
			file.mkdir();
		}
		Q_scriptBean script = ManagerPool.dataManager.q_scriptContainer.getMap().get(id);
		if(script!=null){
			try{
				if(script.getQ_script_name()==null || "".equals(script.getQ_script_name())) return "-1";
				InputStream in = new FileInputStream(script.getQ_script_name().replace('.', '/') + ".java");
				StringBuffer buff = new StringBuffer();

				IoBuffer buf = IoBuffer.allocate(10240);
				buf.setAutoExpand(true);
				buf.setAutoShrink(true);
				
				byte[] bytes = new byte[1024];
				int len = 0;
				while((len=in.read(bytes)) != -1){
					buf.put(bytes, 0, len);
				}
				buf.flip();
				
				byte[] allbytes = new byte[buf.remaining()];
				buf.get(allbytes);
				buff.append(new String(allbytes, "UTF-8"));
				in.close();
				
				Class<?> clazz = javaCodeToObject(script.getQ_script_name(), buff.toString());
				if(clazz!=null){
					IScript o = (IScript)clazz.newInstance();
					scripts.put(o.getId(), o);
					
					log.error("脚本加载成功");
					HttpUtil.wget(resulthttp+"&result=1&location=world");
					
					return "1";
				}
			}catch(FileNotFoundException e){
				log.error(e, e);
			}catch(IllegalAccessException e){
				log.error(e, e);
			}catch(InstantiationException e){
				log.error(e, e);
			}catch(IOException e){
				log.error(e, e);
			}catch(Exception e){
				log.error(e, e);
			}
			try {
				HttpUtil.wget(resulthttp+"&result=0&location=world&tip="+id);
			} catch (Exception e) {
				log.error(e, e);
			}
		}
		return "-2";
	}
	
	public void load(String scriptName, long roleId){
		File file = new File("bin");
		if(!file.exists()){
			file.mkdir();
		}
		try{
			if(scriptName==null || "".equals(scriptName)) return;
			InputStream in = new FileInputStream(scriptName.replace('.', '/') + ".java");
			StringBuffer buff = new StringBuffer();

			IoBuffer buf = IoBuffer.allocate(10240);
			buf.setAutoExpand(true);
			buf.setAutoShrink(true);
			
			byte[] bytes = new byte[1024];
			int len = 0;
			while((len=in.read(bytes)) != -1){
				buf.put(bytes, 0, len);
			}
			buf.flip();
			
			byte[] allbytes = new byte[buf.remaining()];
			buf.get(allbytes);
			buff.append(new String(allbytes, "UTF-8"));
			in.close();
			
			Class<?> clazz = javaCodeToObject(scriptName, buff.toString());
			if(clazz!=null){
				IScript o = (IScript)clazz.newInstance();
				scripts.put(o.getId(), o);
				
				Player player = PlayerManager.getInstance().getPlayer(roleId);
				MessageUtil.notify_player(player, Notifys.SUCCESS,"重加载脚本{1}成功", scriptName);
				
				log.error("脚本加载成功");
			}
			
			
		}catch(FileNotFoundException e){
			log.error(e, e);
		}catch(IllegalAccessException e){
			log.error(e, e);
		}catch(InstantiationException e){
			log.error(e, e);
		}catch(IOException e){
			log.error(e, e);
		}catch(Exception e){
			log.error(e, e);
		}
	}
	
	public String loadByBg(String scriptName,String httpresult){
		File file = new File("bin");
		if(!file.exists()){
			file.mkdir();
		}
		try{
			if(scriptName==null || "".equals(scriptName)) return "-1";
			InputStream in = new FileInputStream(scriptName.replace('.', '/') + ".java");
			StringBuffer buff = new StringBuffer();

			IoBuffer buf = IoBuffer.allocate(10240);
			buf.setAutoExpand(true);
			buf.setAutoShrink(true);
			
			byte[] bytes = new byte[1024];
			int len = 0;
			while((len=in.read(bytes)) != -1){
				buf.put(bytes, 0, len);
			}
			buf.flip();
			
			byte[] allbytes = new byte[buf.remaining()];
			buf.get(allbytes);
			buff.append(new String(allbytes, "UTF-8"));
			in.close();
			
			Class<?> clazz = javaCodeToObject(scriptName, buff.toString());
			if(clazz!=null){
				IScript o = (IScript)clazz.newInstance();
				scripts.put(o.getId(), o);
				
				HttpUtil.wget(httpresult+"&result=1&location=world");
				log.error("脚本加载成功");
				return "1";
			}
			
		}catch(FileNotFoundException e){
			log.error(e, e);
		}catch(IllegalAccessException e){
			log.error(e, e);
		}catch(InstantiationException e){
			log.error(e, e);
		}catch(IOException e){
			log.error(e, e);
		}catch(Exception e){
			log.error(e, e);
		}
		return "-2";
	}
	
	public IScript getScript(int id){
		return scripts.get(id);
	}

	private Class<?> javaCodeToObject(String name, String code)
			throws IllegalAccessException, IOException, ClassNotFoundException {
		boolean reload = false;
		try{
			Class<?> c = Class.forName(name);
			if(c!=null){
				reload = true;
			}
		}catch (Exception e) {}
		
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<JavaFileObject>();
		StandardJavaFileManager fileManager = compiler.getStandardFileManager(
				diagnostics, null, null);
		
		ScriptClassLoader loader = new ScriptClassLoader();
		//ScriptFileManager fileManager = new ScriptFileManager(standFileManager, loader);

		JavaFileObject jfile = new JavaSourceFromString(name, code);
		List<JavaFileObject> jfiles = new ArrayList<JavaFileObject>();
		jfiles.add(jfile);
		List<String> options = new ArrayList<String>();

		options.add("-encoding");
		options.add("UTF-8");
		options.add("-classpath");
		options.add(this.classpath);
		options.add("-d");
		options.add("bin");

		JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager,
				diagnostics, options, null, jfiles);

		boolean success = task.call();
		
		fileManager.close();
		
		if (success) {
			if(reload){
				return loader.loadScriptClass(name);
			}else{
				return Class.forName(name);
			}
		} else {
			String error = "";
			for (Diagnostic<?> diagnostic : diagnostics.getDiagnostics()) {
				error = error + compilePrint(diagnostic);
			}
			log.error(error);
		}
		return null;
	}

	private String compilePrint(Diagnostic<?> diagnostic) {
		StringBuffer res = new StringBuffer();
		res.append("Code:[" + diagnostic.getCode() + "]\n");
		res.append("Kind:[" + diagnostic.getKind() + "]\n");
		res.append("Position:[" + diagnostic.getPosition() + "]\n");
		res.append("Start Position:[" + diagnostic.getStartPosition() + "]\n");
		res.append("End Position:[" + diagnostic.getEndPosition() + "]\n");
		res.append("Source:[" + diagnostic.getSource() + "]\n");
		res.append("Message:[" + diagnostic.getMessage(null) + "]\n");
		res.append("LineNumber:[" + diagnostic.getLineNumber() + "]\n");
		res.append("ColumnNumber:[" + diagnostic.getColumnNumber() + "]\n");
		return res.toString();
	}

	public void excute(int scriptId, String method, Object... paras){
		ScriptsUtils.call(scriptId, method, paras);
	}
	
	public void excuteFromGame(int scriptId, String method, List<String> paras){
		ScriptsUtils.callFromGame(scriptId, method, paras);
	}
	
	private class JavaSourceFromString extends SimpleJavaFileObject {

		private String code;
		
		public JavaSourceFromString(String name, String code) {
			super(URI.create("string:///" + name.replace('.', '/')
					+ Kind.SOURCE.extension), Kind.SOURCE);
			this.code = code;
		}
		
		 @Override
         public CharSequence getCharContent(boolean ignoreEncodingErrors) {
             return code;
         }

	}
}
