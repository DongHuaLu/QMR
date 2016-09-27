package com.game.transactions.handler{

	import com.game.transactions.message.ResTransactionsSetStateMessage;
	import net.Handler;

	public class ResTransactionsSetStateHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTransactionsSetStateMessage = ResTransactionsSetStateMessage(this.message);
			//TODO 添加消息处理
		}
	}
}