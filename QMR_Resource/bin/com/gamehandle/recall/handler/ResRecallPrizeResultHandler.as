package com.game.recall.handler{

	import com.game.recall.message.ResRecallPrizeResultMessage;
	import net.Handler;

	public class ResRecallPrizeResultHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRecallPrizeResultMessage = ResRecallPrizeResultMessage(this.message);
			//TODO 添加消息处理
		}
	}
}