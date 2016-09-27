package com.game.transactions.handler{

	import com.game.transactions.message.ResTransactionsCanceledMessage;
	import net.Handler;

	public class ResTransactionsCanceledHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTransactionsCanceledMessage = ResTransactionsCanceledMessage(this.message);
			//TODO 添加消息处理
		}
	}
}