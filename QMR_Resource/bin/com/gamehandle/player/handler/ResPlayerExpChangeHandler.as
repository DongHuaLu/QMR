package com.game.player.handler{

	import com.game.player.message.ResPlayerExpChangeMessage;
	import net.Handler;

	public class ResPlayerExpChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerExpChangeMessage = ResPlayerExpChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}