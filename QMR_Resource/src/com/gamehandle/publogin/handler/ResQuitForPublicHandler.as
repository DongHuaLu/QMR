package com.game.publogin.handler{

	import com.game.publogin.message.ResQuitForPublicMessage;
	import net.Handler;

	public class ResQuitForPublicHandler extends Handler {
	
		public override function action(): void{
			var msg: ResQuitForPublicMessage = ResQuitForPublicMessage(this.message);
			//TODO 添加消息处理
		}
	}
}