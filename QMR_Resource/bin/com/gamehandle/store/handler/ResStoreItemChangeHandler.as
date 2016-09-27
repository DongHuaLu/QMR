package com.game.store.handler{

	import com.game.store.message.ResStoreItemChangeMessage;
	import net.Handler;

	public class ResStoreItemChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStoreItemChangeMessage = ResStoreItemChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}