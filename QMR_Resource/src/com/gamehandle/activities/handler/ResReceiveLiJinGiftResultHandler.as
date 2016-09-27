package com.game.activities.handler{

	import com.game.activities.message.ResReceiveLiJinGiftResultMessage;
	import net.Handler;

	public class ResReceiveLiJinGiftResultHandler extends Handler {
	
		public override function action(): void{
			var msg: ResReceiveLiJinGiftResultMessage = ResReceiveLiJinGiftResultMessage(this.message);
			//TODO 添加消息处理
		}
	}
}