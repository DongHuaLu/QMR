package com.game.player.handler{

	import com.game.player.message.ResAutoStartStateMessage;
	import net.Handler;

	public class ResAutoStartStateHandler extends Handler {
	
		public override function action(): void{
			var msg: ResAutoStartStateMessage = ResAutoStartStateMessage(this.message);
			//TODO 添加消息处理
		}
	}
}