package com.game.player.handler{

	import com.game.player.message.ResPlayerMpChangeMessage;
	import net.Handler;

	public class ResPlayerMpChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerMpChangeMessage = ResPlayerMpChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}