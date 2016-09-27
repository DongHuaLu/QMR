package com.game.player.handler{

	import com.game.player.message.ResPlayerMaxSpChangeMessage;
	import net.Handler;

	public class ResPlayerMaxSpChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerMaxSpChangeMessage = ResPlayerMaxSpChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}