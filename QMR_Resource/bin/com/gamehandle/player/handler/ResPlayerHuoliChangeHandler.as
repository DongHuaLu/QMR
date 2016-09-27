package com.game.player.handler{

	import com.game.player.message.ResPlayerHuoliChangeMessage;
	import net.Handler;

	public class ResPlayerHuoliChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerHuoliChangeMessage = ResPlayerHuoliChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}