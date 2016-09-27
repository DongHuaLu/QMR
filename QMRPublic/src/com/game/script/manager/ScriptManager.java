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
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

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
import com.game.script.IScript;
import com.game.script.loader.ScriptClassLoader;

public class ScriptManager {

	private Logger log = Logger.getLogger(ScriptManager.class);

	private URLClassLoader parentClassLoader;

	private String classpath;

	private static Object obj = new Object();

	private static ConcurrentHashMap<Integer, IScript> scripts = new ConcurrentHashMap<Integer, IScript>();
	
	// 玩家管理类实例
	private static ScriptManager manager;
	
	private static ConcurrentHashMap<Integer, Q_scriptBean> scriptBeanMap = new ConcurrentHashMap<Integer, Q_scriptBean>();

	private static Vector<Q_scriptBean> scriptBeanList = new Vector<Q_scriptBean>();
	
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
		Q_scriptBean bean = new Q_scriptBean();
		bean.setQ_script_id(13);
		bean.setQ_script_name("scripts.gm.GmCommandScript");
		scriptBeanList.add(bean);
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
		log.error("脚本加载需要类路径：" + this.classpath);
	}
	
	private void init(){
		File file = new File("bin");
		if(!file.exists()){
			file.mkdir();
		}
		List<Q_scriptBean> scriptBeans = scriptBeanList;
		for (int i = 0; i < scriptBeans.size(); i++) {
			Q_scriptBean script = scriptBeans.get(i);
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
	
	public void reload(int id){
		File file = new File("bin");
		if(!file.exists()){
			file.mkdir();
		}
		Q_scriptBean script = scriptBeanMap.get(id);
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
	
	public void reload(int id, String httpresult){
		File file = new File("bin");
		if(!file.exists()){
			file.mkdir();
		}
		Q_scriptBean script = scriptBeanMap.get(id);
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
					
					log.error("脚本加载成功");
				}
				return ;
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
			log.error("脚本加载失败");
		}
	}
	
	
	public void load(String scriptName,long roleId){
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
	
	public void load(String scriptName,String httpresult){
		File file = new File("bin");
		if(!file.exists()){
			file.mkdir();
		}
		try{
			if(scriptName==null || "".equals(scriptName)) return;
			InputStream in = new FileInputStream(scriptName.replace('.', '/') + ".java");
			StringBuffer buff = new StringBuffer();
			byte[] bytes = new byte[1024];
			int len = 0;
			while((len=in.read(bytes)) != -1){
				buff.append(new String(bytes, 0, len, "UTF-8"));
			}
			in.close();
			
			Class<?> clazz = javaCodeToObject(scriptName, buff.toString());
			if(clazz!=null){
				IScript o = (IScript)clazz.newInstance();
				scripts.put(o.getId(), o);
				
				log.error("脚本加载成功");
				
				return;
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

	public void loadByBg(String string) {
	}

}
