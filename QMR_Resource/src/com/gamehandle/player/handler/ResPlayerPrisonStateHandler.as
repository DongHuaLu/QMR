package com.game.player.handler{

	import com.game.player.message.ResPlayerPrisonStateMessage;
	import net.Handler;

	public class ResPlayerPrisonStateHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerPrisonStateMessage = ResPlayerPrisonStateMessage(this.message);
			//TODO 添加消息处理
		}
	}
}