package com.game.dataserver.handler{

	import com.game.dataserver.message.ResStartMatchMessage;
	import net.Handler;

	public class ResStartMatchHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStartMatchMessage = ResStartMatchMessage(this.message);
			//TODO 添加消息处理
		}
	}
}