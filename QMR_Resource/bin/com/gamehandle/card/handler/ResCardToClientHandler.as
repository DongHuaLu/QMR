package com.game.card.handler{

	import com.game.card.message.ResCardToClientMessage;
	import net.Handler;

	public class ResCardToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCardToClientMessage = ResCardToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}