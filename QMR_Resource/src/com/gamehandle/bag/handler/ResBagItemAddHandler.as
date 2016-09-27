package com.game.bag.handler{

	import com.game.bag.message.ResBagItemAddMessage;
	import net.Handler;

	public class ResBagItemAddHandler extends Handler {
	
		public override function action(): void{
			var msg: ResBagItemAddMessage = ResBagItemAddMessage(this.message);
			//TODO 添加消息处理
		}
	}
}