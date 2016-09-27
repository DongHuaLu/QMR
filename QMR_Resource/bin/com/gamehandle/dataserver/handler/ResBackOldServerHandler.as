package com.game.dataserver.handler{

	import com.game.dataserver.message.ResBackOldServerMessage;
	import net.Handler;

	public class ResBackOldServerHandler extends Handler {
	
		public override function action(): void{
			var msg: ResBackOldServerMessage = ResBackOldServerMessage(this.message);
			//TODO 添加消息处理
		}
	}
}