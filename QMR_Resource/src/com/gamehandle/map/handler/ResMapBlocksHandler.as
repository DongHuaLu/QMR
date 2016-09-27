package com.game.map.handler{

	import com.game.map.message.ResMapBlocksMessage;
	import net.Handler;

	public class ResMapBlocksHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMapBlocksMessage = ResMapBlocksMessage(this.message);
			//TODO 添加消息处理
		}
	}
}