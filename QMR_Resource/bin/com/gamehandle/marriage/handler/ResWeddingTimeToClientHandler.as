package com.game.marriage.handler{

	import com.game.marriage.message.ResWeddingTimeToClientMessage;
	import net.Handler;

	public class ResWeddingTimeToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResWeddingTimeToClientMessage = ResWeddingTimeToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}