package com.game.player.handler{

	import com.game.player.message.ResPlayerMaxHpChangeMessage;
	import net.Handler;

	public class ResPlayerMaxHpChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerMaxHpChangeMessage = ResPlayerMaxHpChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}