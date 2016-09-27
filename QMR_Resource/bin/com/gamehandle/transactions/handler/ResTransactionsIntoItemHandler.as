package com.game.transactions.handler{

	import com.game.transactions.message.ResTransactionsIntoItemMessage;
	import net.Handler;

	public class ResTransactionsIntoItemHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTransactionsIntoItemMessage = ResTransactionsIntoItemMessage(this.message);
			//TODO 添加消息处理
		}
	}
}