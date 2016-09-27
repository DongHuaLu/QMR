package com.game.map.handler{

	import com.game.map.message.ResPetJumpPositionsMessage;
	import net.Handler;

	public class ResPetJumpPositionsHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPetJumpPositionsMessage = ResPetJumpPositionsMessage(this.message);
			//TODO 添加消息处理
		}
	}
}