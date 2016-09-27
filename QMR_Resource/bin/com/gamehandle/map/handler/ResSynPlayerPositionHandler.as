package com.game.map.handler{

	import com.game.map.message.ResSynPlayerPositionMessage;
	import net.Handler;

	public class ResSynPlayerPositionHandler extends Handler {
	
		public override function action(): void{
			var msg: ResSynPlayerPositionMessage = ResSynPlayerPositionMessage(this.message);
			//TODO 添加消息处理
		}
	}
}