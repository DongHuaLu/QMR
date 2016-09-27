package com.game.friend.handler{

	import com.game.friend.message.ResRelationGetListToClientMessage;
	import net.Handler;

	public class ResRelationGetListToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRelationGetListToClientMessage = ResRelationGetListToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}