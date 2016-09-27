package com.game.store.handler{

	import com.game.store.message.ResStoreCellTimeMessage;
	import net.Handler;

	public class ResStoreCellTimeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStoreCellTimeMessage = ResStoreCellTimeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}