package com.game.map.handler{

	import com.game.map.message.ResPlayerJumpPositionsMessage;
	import net.Handler;

	public class ResPlayerJumpPositionsHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerJumpPositionsMessage = ResPlayerJumpPositionsMessage(this.message);
			//TODO 添加消息处理
		}
	}
}