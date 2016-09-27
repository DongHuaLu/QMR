package com.game.map.handler{

	import com.game.map.message.ResChangeMapFailedMessage;
	import net.Handler;

	public class ResChangeMapFailedHandler extends Handler {
	
		public override function action(): void{
			var msg: ResChangeMapFailedMessage = ResChangeMapFailedMessage(this.message);
			//TODO 添加消息处理
		}
	}
}