package com.game.stalls.handler{

	import com.game.stalls.message.ResStallsBuyMessage;
	import net.Handler;

	public class ResStallsBuyHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStallsBuyMessage = ResStallsBuyMessage(this.message);
			//TODO 添加消息处理
		}
	}
}