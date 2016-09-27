package com.game.player.handler{

	import com.game.player.message.RespopuGuideMessage;
	import net.Handler;

	public class RespopuGuideHandler extends Handler {
	
		public override function action(): void{
			var msg: RespopuGuideMessage = RespopuGuideMessage(this.message);
			//TODO 添加消息处理
		}
	}
}