package com.game.map.handler{

	import com.game.map.message.ResChangeDirectMessage;
	import net.Handler;

	public class ResChangeDirectHandler extends Handler {
	
		public override function action(): void{
			var msg: ResChangeDirectMessage = ResChangeDirectMessage(this.message);
			//TODO 添加消息处理
		}
	}
}