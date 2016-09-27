package com.game.map.handler{

	import com.game.map.message.ResSendLinesMessage;
	import net.Handler;

	public class ResSendLinesHandler extends Handler {
	
		public override function action(): void{
			var msg: ResSendLinesMessage = ResSendLinesMessage(this.message);
			//TODO 添加消息处理
		}
	}
}