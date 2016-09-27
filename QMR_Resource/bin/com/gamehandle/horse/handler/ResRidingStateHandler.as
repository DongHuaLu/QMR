package com.game.horse.handler{

	import com.game.horse.message.ResRidingStateMessage;
	import net.Handler;

	public class ResRidingStateHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRidingStateMessage = ResRidingStateMessage(this.message);
			//TODO 添加消息处理
		}
	}
}