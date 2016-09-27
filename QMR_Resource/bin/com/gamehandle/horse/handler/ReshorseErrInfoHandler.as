package com.game.horse.handler{

	import com.game.horse.message.ReshorseErrInfoMessage;
	import net.Handler;

	public class ReshorseErrInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ReshorseErrInfoMessage = ReshorseErrInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}