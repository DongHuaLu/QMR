package com.game.friend.handler{

	import com.game.friend.message.ResRelationDeleteToClientMessage;
	import net.Handler;

	public class ResRelationDeleteToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRelationDeleteToClientMessage = ResRelationDeleteToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}