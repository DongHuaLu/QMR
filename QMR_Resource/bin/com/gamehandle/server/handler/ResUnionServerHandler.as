package com.game.server.handler{

	import com.game.server.message.ResUnionServerMessage;
	import net.Handler;

	public class ResUnionServerHandler extends Handler {
	
		public override function action(): void{
			var msg: ResUnionServerMessage = ResUnionServerMessage(this.message);
			//TODO 添加消息处理
		}
	}
}