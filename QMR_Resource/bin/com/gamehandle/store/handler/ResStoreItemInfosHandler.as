package com.game.store.handler{

	import com.game.store.message.ResStoreItemInfosMessage;
	import net.Handler;

	public class ResStoreItemInfosHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStoreItemInfosMessage = ResStoreItemInfosMessage(this.message);
			//TODO 添加消息处理
		}
	}
}