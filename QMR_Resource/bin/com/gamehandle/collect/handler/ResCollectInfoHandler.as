package com.game.collect.handler{

	import com.game.collect.message.ResCollectInfoMessage;
	import net.Handler;

	public class ResCollectInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCollectInfoMessage = ResCollectInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}