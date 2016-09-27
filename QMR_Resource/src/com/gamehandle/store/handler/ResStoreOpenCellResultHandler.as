package com.game.store.handler{

	import com.game.store.message.ResStoreOpenCellResultMessage;
	import net.Handler;

	public class ResStoreOpenCellResultHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStoreOpenCellResultMessage = ResStoreOpenCellResultMessage(this.message);
			//TODO 添加消息处理
		}
	}
}