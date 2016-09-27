package com.game.bag.handler{

	import com.game.bag.message.ResBagItemRemoveMessage;
	import net.Handler;

	public class ResBagItemRemoveHandler extends Handler {
	
		public override function action(): void{
			var msg: ResBagItemRemoveMessage = ResBagItemRemoveMessage(this.message);
			//TODO 添加消息处理
		}
	}
}