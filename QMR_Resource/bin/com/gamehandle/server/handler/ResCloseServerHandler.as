package com.game.server.handler{

	import com.game.server.message.ResCloseServerMessage;
	import net.Handler;

	public class ResCloseServerHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCloseServerMessage = ResCloseServerMessage(this.message);
			//TODO 添加消息处理
		}
	}
}