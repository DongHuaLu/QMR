package com.game.arrow.handler{

	import com.game.arrow.message.ResBowInfoMessage;
	import net.Handler;

	public class ResBowInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResBowInfoMessage = ResBowInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}