package com.game.player.handler{

	import com.game.player.message.ResPlayerMaxMpChangeMessage;
	import net.Handler;

	public class ResPlayerMaxMpChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerMaxMpChangeMessage = ResPlayerMaxMpChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}