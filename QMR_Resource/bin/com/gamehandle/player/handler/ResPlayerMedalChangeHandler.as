package com.game.player.handler{

	import com.game.player.message.ResPlayerMedalChangeMessage;
	import net.Handler;

	public class ResPlayerMedalChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerMedalChangeMessage = ResPlayerMedalChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}