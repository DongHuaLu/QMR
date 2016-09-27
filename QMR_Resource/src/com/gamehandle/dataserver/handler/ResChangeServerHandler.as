package com.game.dataserver.handler{

	import com.game.dataserver.message.ResChangeServerMessage;
	import net.Handler;

	public class ResChangeServerHandler extends Handler {
	
		public override function action(): void{
			var msg: ResChangeServerMessage = ResChangeServerMessage(this.message);
			//TODO 添加消息处理
		}
	}
}