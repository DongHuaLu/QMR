package com.game.bag.handler{

	import com.game.bag.message.ResBagSynMessage;
	import net.Handler;

	public class ResBagSynHandler extends Handler {
	
		public override function action(): void{
			var msg: ResBagSynMessage = ResBagSynMessage(this.message);
			//TODO 添加消息处理
		}
	}
}