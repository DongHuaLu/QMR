package com.game.stalls.handler{

	import com.game.stalls.message.ResStallsSortMessage;
	import net.Handler;

	public class ResStallsSortHandler extends Handler {
	
		public override function action(): void{
			var msg: ResStallsSortMessage = ResStallsSortMessage(this.message);
			//TODO 添加消息处理
		}
	}
}