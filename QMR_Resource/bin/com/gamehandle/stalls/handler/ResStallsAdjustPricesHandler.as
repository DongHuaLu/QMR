package com.game.stalls.handler{

	import com.game.stalls.message.ResStallsAdjustPricesMessage;
	import net.Handler;

	public class ResStallsAdjustPricesHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStallsAdjustPricesMessage = ResStallsAdjustPricesMessage(this.message);
			//TODO 添加消息处理
		}
	}
}