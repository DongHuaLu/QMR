package com.game.toplist.handler{

	import com.game.toplist.message.ResWorShipToClientMessage;
	import net.Handler;

	public class ResWorShipToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResWorShipToClientMessage = ResWorShipToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}