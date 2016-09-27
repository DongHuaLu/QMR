package com.game.transactions.handler{

	import com.game.transactions.message.ResTransactionsChangeGoldMessage;
	import net.Handler;

	public class ResTransactionsChangeGoldHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTransactionsChangeGoldMessage = ResTransactionsChangeGoldMessage(this.message);
			//TODO 添加消息处理
		}
	}
}