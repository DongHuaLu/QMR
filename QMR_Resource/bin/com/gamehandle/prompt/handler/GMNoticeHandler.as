package com.game.prompt.handler{

	import com.game.prompt.message.GMNoticeMessage;
	import net.Handler;

	public class GMNoticeHandler extends Handler {
	
		public override function action(): void{
			var msg: GMNoticeMessage = GMNoticeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}