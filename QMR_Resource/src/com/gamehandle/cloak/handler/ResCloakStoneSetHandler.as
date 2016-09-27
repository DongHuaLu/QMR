package com.game.cloak.handler{

	import com.game.cloak.message.ResCloakStoneSetMessage;
	import net.Handler;

	public class ResCloakStoneSetHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCloakStoneSetMessage = ResCloakStoneSetMessage(this.message);
			//TODO 添加消息处理
		}
	}
}