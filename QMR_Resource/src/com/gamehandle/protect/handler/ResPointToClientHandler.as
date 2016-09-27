package com.game.protect.handler{

	import com.game.protect.message.ResPointToClientMessage;
	import net.Handler;

	public class ResPointToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPointToClientMessage = ResPointToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}