package com.game.recall.handler{

	import com.game.recall.message.ResRecallPlayerMessage;
	import net.Handler;

	public class ResRecallPlayerHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRecallPlayerMessage = ResRecallPlayerMessage(this.message);
			//TODO 添加消息处理
		}
	}
}