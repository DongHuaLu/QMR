package com.game.map.handler{

	import com.game.map.message.ResPetTranMoveMessage;
	import net.Handler;

	public class ResPetTranMoveHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPetTranMoveMessage = ResPetTranMoveMessage(this.message);
			//TODO 添加消息处理
		}
	}
}