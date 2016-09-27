package com.game.cloak.handler{

	import com.game.cloak.message.ResCloakInfoOtherMessage;
	import net.Handler;

	public class ResCloakInfoOtherHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCloakInfoOtherMessage = ResCloakInfoOtherMessage(this.message);
			//TODO 添加消息处理
		}
	}
}