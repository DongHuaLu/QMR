package com.game.marriage.handler{

	import com.game.marriage.message.ResDivorceToClientMessage;
	import net.Handler;

	public class ResDivorceToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResDivorceToClientMessage = ResDivorceToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}