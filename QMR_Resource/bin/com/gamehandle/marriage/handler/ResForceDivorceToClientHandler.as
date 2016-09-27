package com.game.marriage.handler{

	import com.game.marriage.message.ResForceDivorceToClientMessage;
	import net.Handler;

	public class ResForceDivorceToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResForceDivorceToClientMessage = ResForceDivorceToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}