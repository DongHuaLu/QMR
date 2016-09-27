package com.game.setupmenu.handler{

	import com.game.setupmenu.message.ResMenuStatusMessage;
	import net.Handler;

	public class ResMenuStatusHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMenuStatusMessage = ResMenuStatusMessage(this.message);
			//TODO 添加消息处理
		}
	}
}