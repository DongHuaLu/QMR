package com.game.friend.handler{

	import com.game.friend.message.ResRelationConfigToClientMessage;
	import net.Handler;

	public class ResRelationConfigToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRelationConfigToClientMessage = ResRelationConfigToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}