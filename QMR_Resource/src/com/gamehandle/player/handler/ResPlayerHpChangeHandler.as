package com.game.player.handler{

	import com.game.player.message.ResPlayerHpChangeMessage;
	import net.Handler;

	public class ResPlayerHpChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerHpChangeMessage = ResPlayerHpChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}