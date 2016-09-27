package com.game.backpack.handler{

	import com.game.backpack.message.ResCellTimeMessage;
	import net.Handler;

	public class ResCellTimeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResCellTimeMessage = ResCellTimeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}