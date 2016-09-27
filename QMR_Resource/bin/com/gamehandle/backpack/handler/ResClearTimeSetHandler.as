package com.game.backpack.handler{

	import com.game.backpack.message.ResClearTimeSetMessage;
	import net.Handler;

	public class ResClearTimeSetHandler extends Handler {
	
		public override function action(): void{
			var msg: ResClearTimeSetMessage = ResClearTimeSetMessage(this.message);
			//TODO 添加消息处理
		}
	}
}