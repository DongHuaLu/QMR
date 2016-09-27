package com.game.backpack.handler{

	import com.game.backpack.message.ResItemChangeMessage;
	import net.Handler;

	public class ResItemChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResItemChangeMessage = ResItemChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}