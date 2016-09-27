package com.game.player.handler{

	import com.game.player.message.ResPlayerSpChangeMessage;
	import net.Handler;

	public class ResPlayerSpChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerSpChangeMessage = ResPlayerSpChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}