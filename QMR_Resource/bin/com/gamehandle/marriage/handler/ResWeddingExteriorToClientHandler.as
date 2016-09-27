package com.game.marriage.handler{

	import com.game.marriage.message.ResWeddingExteriorToClientMessage;
	import net.Handler;

	public class ResWeddingExteriorToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResWeddingExteriorToClientMessage = ResWeddingExteriorToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}