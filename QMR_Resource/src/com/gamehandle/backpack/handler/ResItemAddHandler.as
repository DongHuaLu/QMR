package com.game.backpack.handler{

	import com.game.backpack.message.ResItemAddMessage;
	import net.Handler;

	public class ResItemAddHandler extends Handler {
	
		public override function action(): void{
			var msg: ResItemAddMessage = ResItemAddMessage(this.message);
			//TODO 添加消息处理
		}
	}
}