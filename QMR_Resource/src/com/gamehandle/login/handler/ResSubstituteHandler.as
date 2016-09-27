package com.game.login.handler{

	import com.game.login.message.ResSubstituteMessage;
	import net.Handler;

	public class ResSubstituteHandler extends Handler {
	
		public override function action(): void{
			var msg: ResSubstituteMessage = ResSubstituteMessage(this.message);
			//TODO 添加消息处理
		}
	}
}