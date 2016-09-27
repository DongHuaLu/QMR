package com.game.transactions.handler{

	import com.game.transactions.message.ResTmpYuanbaoLogMessage;
	import net.Handler;

	public class ResTmpYuanbaoLogHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTmpYuanbaoLogMessage = ResTmpYuanbaoLogMessage(this.message);
			//TODO 添加消息处理
		}
	}
}