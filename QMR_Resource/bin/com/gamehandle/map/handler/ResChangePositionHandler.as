package com.game.map.handler{

	import com.game.map.message.ResChangePositionMessage;
	import net.Handler;

	public class ResChangePositionHandler extends Handler {
	
		public override function action(): void{
			var msg: ResChangePositionMessage = ResChangePositionMessage(this.message);
			//TODO 添加消息处理
		}
	}
}