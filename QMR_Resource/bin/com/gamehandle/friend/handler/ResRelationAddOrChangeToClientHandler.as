package com.game.friend.handler{

	import com.game.friend.message.ResRelationAddOrChangeToClientMessage;
	import net.Handler;

	public class ResRelationAddOrChangeToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRelationAddOrChangeToClientMessage = ResRelationAddOrChangeToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}