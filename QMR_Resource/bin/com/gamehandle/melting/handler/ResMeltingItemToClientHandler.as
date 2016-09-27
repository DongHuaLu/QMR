package com.game.melting.handler{

	import com.game.melting.message.ResMeltingItemToClientMessage;
	import net.Handler;

	public class ResMeltingItemToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMeltingItemToClientMessage = ResMeltingItemToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}