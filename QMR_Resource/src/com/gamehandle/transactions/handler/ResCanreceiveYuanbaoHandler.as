package com.game.transactions.handler{

	import com.game.transactions.message.ResCanreceiveYuanbaoMessage;
	import net.Handler;

	public class ResCanreceiveYuanbaoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCanreceiveYuanbaoMessage = ResCanreceiveYuanbaoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}