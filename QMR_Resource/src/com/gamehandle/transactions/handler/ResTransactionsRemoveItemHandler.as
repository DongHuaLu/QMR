package com.game.transactions.handler{

	import com.game.transactions.message.ResTransactionsRemoveItemMessage;
	import net.Handler;

	public class ResTransactionsRemoveItemHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTransactionsRemoveItemMessage = ResTransactionsRemoveItemMessage(this.message);
			//TODO 添加消息处理
		}
	}
}