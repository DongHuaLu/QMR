package com.game.login.handler{

	import com.game.login.message.ResLoginSuccessMessage;
	import net.Handler;

	public class ResLoginSuccessHandler extends Handler {
	
		public override function action(): void{
			var msg: ResLoginSuccessMessage = ResLoginSuccessMessage(this.message);
			//TODO 添加消息处理
		}
	}
}