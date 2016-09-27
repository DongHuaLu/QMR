package com.game.vip.handler{

	import com.game.vip.message.ResPlayerVIPInfoMessage;
	import net.Handler;

	public class ResPlayerVIPInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerVIPInfoMessage = ResPlayerVIPInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}