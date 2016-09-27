package com.game.buff.handler{

	import com.game.buff.message.ResBuffsMessage;
	import net.Handler;

	public class ResBuffsHandler extends Handler {
	
		public override function action(): void{
			var msg: ResBuffsMessage = ResBuffsMessage(this.message);
			//TODO 添加消息处理
		}
	}
}