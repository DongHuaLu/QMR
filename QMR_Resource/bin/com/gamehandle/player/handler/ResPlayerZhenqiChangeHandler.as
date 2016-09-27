package com.game.player.handler{

	import com.game.player.message.ResPlayerZhenqiChangeMessage;
	import net.Handler;

	public class ResPlayerZhenqiChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerZhenqiChangeMessage = ResPlayerZhenqiChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}