package com.game.map.handler{

	import com.game.map.message.ResJumpSetPositionMessage;
	import net.Handler;

	public class ResJumpSetPositionHandler extends Handler {
	
		public override function action(): void{
			var msg: ResJumpSetPositionMessage = ResJumpSetPositionMessage(this.message);
			//TODO 添加消息处理
		}
	}
}