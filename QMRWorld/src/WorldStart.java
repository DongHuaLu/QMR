import com.game.server.WorldServer;


public class WorldStart {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Thread((Runnable)WorldServer.getInstance()).start();
		//记录启动标识  以stid开始的参数才是启动标识
		if(args.length>0){
			for(String s: args){
				if(s.startsWith("stid")){
					WorldServer.startidentity = s;
				}
			}
		}
	}

}
