package com.game.recall.handler{

	import com.game.recall.message.ResBackPrizeMessage;
	import net.Handler;

	public class ResBackPrizeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResBackPrizeMessage = ResBackPrizeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}