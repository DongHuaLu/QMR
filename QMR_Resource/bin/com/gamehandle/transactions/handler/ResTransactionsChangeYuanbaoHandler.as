package com.game.transactions.handler{

	import com.game.transactions.message.ResTransactionsChangeYuanbaoMessage;
	import net.Handler;

	public class ResTransactionsChangeYuanbaoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTransactionsChangeYuanbaoMessage = ResTransactionsChangeYuanbaoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}