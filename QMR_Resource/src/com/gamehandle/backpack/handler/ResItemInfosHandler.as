package com.game.backpack.handler{

	import com.game.backpack.message.ResItemInfosMessage;
	import net.Handler;

	public class ResItemInfosHandler extends Handler {
	
		public override function action(): void{
			var msg: ResItemInfosMessage = ResItemInfosMessage(this.message);
			//TODO 添加消息处理
		}
	}
}