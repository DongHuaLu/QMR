package com.game.arrow.handler{

	import com.game.arrow.message.ResArrowInfoMessage;
	import net.Handler;

	public class ResArrowInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResArrowInfoMessage = ResArrowInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}