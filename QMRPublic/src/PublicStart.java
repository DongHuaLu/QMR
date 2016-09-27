import com.game.server.impl.PublicServer;



public class PublicStart {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if(args==null || args.length==0){
			new Thread((Runnable)PublicServer.getInstance()).start();
			
		}else if(args.length==1){
			new Thread((Runnable)PublicServer.getInstance()).start();
			if(args[0].startsWith("stid")) PublicServer.startidentity = args[0]; 
			
		}
//		else if(args.length==4){
//			new Thread((Runnable)PublicServer.getInstance(args[0], args[1], args[2], args[3])).start();
//			
//		}else if(args.length>4){
//			new Thread((Runnable)PublicServer.getInstance(args[0], args[1], args[2], args[3])).start();
//			if(args[4].startsWith("stid")) PublicServer.startidentity = args[4];
//		}
	}

}
