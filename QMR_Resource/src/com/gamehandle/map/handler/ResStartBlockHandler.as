package com.game.map.handler{

	import com.game.map.message.ResStartBlockMessage;
	import net.Handler;

	public class ResStartBlockHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStartBlockMessage = ResStartBlockMessage(this.message);
			//TODO 添加消息处理
		}
	}
}