package com.game.horse.handler{

	import com.game.horse.message.ReshorseReceiveMessage;
	import net.Handler;

	public class ReshorseReceiveHandler extends Handler {
	
		public override function action(): void{
			var msg: ReshorseReceiveMessage = ReshorseReceiveMessage(this.message);
			//TODO 添加消息处理
		}
	}
}