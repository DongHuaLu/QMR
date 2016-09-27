package com.game.login.handler{

	import com.game.login.message.ResCreateUserMessage;
	import net.Handler;

	public class ResCreateUserHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCreateUserMessage = ResCreateUserMessage(this.message);
			//TODO 添加消息处理
		}
	}
}