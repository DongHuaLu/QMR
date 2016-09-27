package com.game.card.handler{

	import com.game.card.message.ResCardPhoneToClientMessage;
	import net.Handler;

	public class ResCardPhoneToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCardPhoneToClientMessage = ResCardPhoneToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}