package com.game.marriage.handler{

	import com.game.marriage.message.ResWeddingListToClientMessage;
	import net.Handler;

	public class ResWeddingListToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResWeddingListToClientMessage = ResWeddingListToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}