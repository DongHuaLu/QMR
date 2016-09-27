package com.game.player.handler{

	import com.game.player.message.ResReviveSuccessMessage;
	import net.Handler;

	public class ResReviveSuccessHandler extends Handler {
	
		public override function action(): void{
			var msg: ResReviveSuccessMessage = ResReviveSuccessMessage(this.message);
			//TODO 添加消息处理
		}
	}
}