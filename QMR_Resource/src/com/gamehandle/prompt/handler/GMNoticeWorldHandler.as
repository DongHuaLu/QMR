package com.game.prompt.handler{

	import com.game.prompt.message.GMNoticeWorldMessage;
	import net.Handler;

	public class GMNoticeWorldHandler extends Handler {
	
		public override function action(): void{
			var msg: GMNoticeWorldMessage = GMNoticeWorldMessage(this.message);
			//TODO 添加消息处理
		}
	}
}