package com.game.store.handler{

	import com.game.store.message.ResStoreItemAddMessage;
	import net.Handler;

	public class ResStoreItemAddHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStoreItemAddMessage = ResStoreItemAddMessage(this.message);
			//TODO 添加消息处理
		}
	}
}