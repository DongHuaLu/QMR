package com.game.offline.handler{

	import com.game.offline.message.ResRetreatInfoMessage;
	import net.Handler;

	public class ResRetreatInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRetreatInfoMessage = ResRetreatInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}