package com.game.map.handler{

	import com.game.map.message.ResStopBlockMessage;
	import net.Handler;

	public class ResStopBlockHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStopBlockMessage = ResStopBlockMessage(this.message);
			//TODO 添加消息处理
		}
	}
}