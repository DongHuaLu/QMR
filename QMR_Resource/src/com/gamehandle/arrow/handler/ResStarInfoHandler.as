package com.game.arrow.handler{

	import com.game.arrow.message.ResStarInfoMessage;
	import net.Handler;

	public class ResStarInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStarInfoMessage = ResStarInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}