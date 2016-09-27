package com.game.arrow.handler{

	import com.game.arrow.message.ResFightSpiritInfoMessage;
	import net.Handler;

	public class ResFightSpiritInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResFightSpiritInfoMessage = ResFightSpiritInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}