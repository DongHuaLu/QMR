package com.game.transactions.handler{

	import com.game.transactions.message.ResTransactionsSuccessMessage;
	import net.Handler;

	public class ResTransactionsSuccessHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTransactionsSuccessMessage = ResTransactionsSuccessMessage(this.message);
			//TODO 添加消息处理
		}
	}
}