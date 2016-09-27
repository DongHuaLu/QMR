import com.game.server.impl.WServer;

/** 
 * @author heyang E-mail: szy_heyang@163.com
 * 
 * @version 1.0.0
 * 
 * @since 2011-5-3
 * 
 * 类说明 
 */
public class ServerStart {

	public static void main(String[] args) {
		if(args==null || args.length==0){
			new Thread(WServer.getInstance()).start();
			
		}else if(args.length==1){
			new Thread(WServer.getInstance()).start();
			if(args[0].startsWith("stid")) WServer.startidentity = args[0]; //记录启动标示
		}else if(args.length==2){
			new Thread(WServer.getInstance(args[0], args[1])).start();
			
		}else if(args.length>2){
			new Thread(WServer.getInstance(args[0], args[1])).start();
			if(args[3].startsWith("stid")) WServer.startidentity = args[0]; //记录启动标示
		}
		
	}
}
