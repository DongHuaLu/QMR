package com.game.ybcard.handler{

	import com.game.ybcard.message.ResYBCardToClientMessage;
	import net.Handler;

	public class ResYBCardToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResYBCardToClientMessage = ResYBCardToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}