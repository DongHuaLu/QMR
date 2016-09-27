package com.game.bag.handler{

	import com.game.bag.message.ResBagItemUpdateMessage;
	import net.Handler;

	public class ResBagItemUpdateHandler extends Handler {
	
		public override function action(): void{
			var msg: ResBagItemUpdateMessage = ResBagItemUpdateMessage(this.message);
			//TODO 添加消息处理
		}
	}
}