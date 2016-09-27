package com.game.gm.handler{

	import com.game.gm.message.GmLevelMessage;
	import net.Handler;

	public class GmLevelHandler extends Handler {
	
		public override function action(): void{
			var msg: GmLevelMessage = GmLevelMessage(this.message);
			//TODO 添加消息处理
		}
	}
}