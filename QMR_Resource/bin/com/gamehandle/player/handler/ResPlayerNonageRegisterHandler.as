package com.game.player.handler{

	import com.game.player.message.ResPlayerNonageRegisterMessage;
	import net.Handler;

	public class ResPlayerNonageRegisterHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerNonageRegisterMessage = ResPlayerNonageRegisterMessage(this.message);
			//TODO 添加消息处理
		}
	}
}