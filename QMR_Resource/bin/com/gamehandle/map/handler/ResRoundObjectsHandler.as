package com.game.map.handler{

	import com.game.map.message.ResRoundObjectsMessage;
	import net.Handler;

	public class ResRoundObjectsHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRoundObjectsMessage = ResRoundObjectsMessage(this.message);
			//TODO 添加消息处理
		}
	}
}