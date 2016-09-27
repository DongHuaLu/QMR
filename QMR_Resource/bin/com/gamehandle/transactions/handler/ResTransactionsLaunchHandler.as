package com.game.transactions.handler{

	import com.game.transactions.message.ResTransactionsLaunchMessage;
	import net.Handler;

	public class ResTransactionsLaunchHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTransactionsLaunchMessage = ResTransactionsLaunchMessage(this.message);
			//TODO 添加消息处理
		}
	}
}