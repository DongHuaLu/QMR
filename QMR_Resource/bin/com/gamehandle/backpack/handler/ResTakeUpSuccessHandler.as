package com.game.backpack.handler{

	import com.game.backpack.message.ResTakeUpSuccessMessage;
	import net.Handler;

	public class ResTakeUpSuccessHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTakeUpSuccessMessage = ResTakeUpSuccessMessage(this.message);
			//TODO 添加消息处理
		}
	}
}