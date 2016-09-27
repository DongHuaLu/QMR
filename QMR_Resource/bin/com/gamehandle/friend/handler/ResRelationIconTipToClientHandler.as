package com.game.friend.handler{

	import com.game.friend.message.ResRelationIconTipToClientMessage;
	import net.Handler;

	public class ResRelationIconTipToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRelationIconTipToClientMessage = ResRelationIconTipToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}