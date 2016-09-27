package com.game.backpack.handler{

	import com.game.backpack.message.ResItemRemoveMessage;
	import net.Handler;

	public class ResItemRemoveHandler extends Handler {
	
		public override function action(): void{
			var msg: ResItemRemoveMessage = ResItemRemoveMessage(this.message);
			//TODO 添加消息处理
		}
	}
}