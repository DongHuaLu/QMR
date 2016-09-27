package com.game.cloak.handler{

	import com.game.cloak.message.ResCloakInfoMessage;
	import net.Handler;

	public class ResCloakInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCloakInfoMessage = ResCloakInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}