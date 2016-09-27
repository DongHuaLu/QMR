package com.game.map.handler{

	import com.game.map.message.ResSetPositionMessage;
	import net.Handler;

	public class ResSetPositionHandler extends Handler {
	
		public override function action(): void{
			var msg: ResSetPositionMessage = ResSetPositionMessage(this.message);
			//TODO 添加消息处理
		}
	}
}