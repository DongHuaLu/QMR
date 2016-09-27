package com.game.recall.handler{

	import com.game.recall.message.ResRecallPrizeMessage;
	import net.Handler;

	public class ResRecallPrizeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRecallPrizeMessage = ResRecallPrizeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}