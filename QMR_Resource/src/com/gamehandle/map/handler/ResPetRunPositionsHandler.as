package com.game.map.handler{

	import com.game.map.message.ResPetRunPositionsMessage;
	import net.Handler;

	public class ResPetRunPositionsHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPetRunPositionsMessage = ResPetRunPositionsMessage(this.message);
			//TODO 添加消息处理
		}
	}
}