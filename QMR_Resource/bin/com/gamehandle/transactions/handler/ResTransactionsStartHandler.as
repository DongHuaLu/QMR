package com.game.transactions.handler{

	import com.game.transactions.message.ResTransactionsStartMessage;
	import net.Handler;

	public class ResTransactionsStartHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTransactionsStartMessage = ResTransactionsStartMessage(this.message);
			//TODO 添加消息处理
		}
	}
}