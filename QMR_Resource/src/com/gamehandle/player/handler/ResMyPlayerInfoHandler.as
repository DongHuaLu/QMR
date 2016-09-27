package com.game.player.handler{

	import com.game.player.message.ResMyPlayerInfoMessage;
	import net.Handler;

	public class ResMyPlayerInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMyPlayerInfoMessage = ResMyPlayerInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}