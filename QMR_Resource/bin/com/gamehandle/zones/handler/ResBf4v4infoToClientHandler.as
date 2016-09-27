package com.game.zones.handler{

	import com.game.zones.message.ResBf4v4infoToClientMessage;
	import net.Handler;

	public class ResBf4v4infoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResBf4v4infoToClientMessage = ResBf4v4infoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}