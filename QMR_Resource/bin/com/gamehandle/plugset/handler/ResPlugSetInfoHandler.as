package com.game.plugset.handler{

	import com.game.plugset.message.ResPlugSetInfoMessage;
	import net.Handler;

	public class ResPlugSetInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlugSetInfoMessage = ResPlugSetInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}