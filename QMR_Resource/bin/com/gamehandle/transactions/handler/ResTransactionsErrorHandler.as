package com.game.transactions.handler{

	import com.game.transactions.message.ResTransactionsErrorMessage;
	import net.Handler;

	public class ResTransactionsErrorHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTransactionsErrorMessage = ResTransactionsErrorMessage(this.message);
			//TODO 添加消息处理
		}
	}
}