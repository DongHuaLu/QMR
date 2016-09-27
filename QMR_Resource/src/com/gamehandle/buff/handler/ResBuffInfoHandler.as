package com.game.buff.handler{

	import com.game.buff.message.ResBuffInfoMessage;
	import net.Handler;

	public class ResBuffInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResBuffInfoMessage = ResBuffInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}