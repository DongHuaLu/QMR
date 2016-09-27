package com.game.login.handler{

	import com.game.login.message.ResQuitMessage;
	import net.Handler;

	public class ResQuitHandler extends Handler {
	
		public override function action(): void{
			var msg: ResQuitMessage = ResQuitMessage(this.message);
			//TODO 添加消息处理
		}
	}
}