package com.game.cloak.handler{

	import com.game.cloak.message.ResCloakLevelUpMessage;
	import net.Handler;

	public class ResCloakLevelUpHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCloakLevelUpMessage = ResCloakLevelUpMessage(this.message);
			//TODO 添加消息处理
		}
	}
}