package com.game.store.handler{

	import com.game.store.message.ResStoreItemRemoveMessage;
	import net.Handler;

	public class ResStoreItemRemoveHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStoreItemRemoveMessage = ResStoreItemRemoveMessage(this.message);
			//TODO 添加消息处理
		}
	}
}