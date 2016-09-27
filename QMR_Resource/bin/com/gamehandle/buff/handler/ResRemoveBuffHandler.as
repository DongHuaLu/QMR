package com.game.buff.handler{

	import com.game.buff.message.ResRemoveBuffMessage;
	import net.Handler;

	public class ResRemoveBuffHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRemoveBuffMessage = ResRemoveBuffMessage(this.message);
			//TODO 添加消息处理
		}
	}
}