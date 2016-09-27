package com.game.map.handler{

	import com.game.map.message.ResRunPositionsMessage;
	import net.Handler;

	public class ResRunPositionsHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRunPositionsMessage = ResRunPositionsMessage(this.message);
			//TODO 添加消息处理
		}
	}
}