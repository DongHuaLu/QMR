package com.game.map.handler{

	import com.game.map.message.ResChangeMapMessage;
	import net.Handler;

	public class ResChangeMapHandler extends Handler {
	
		public override function action(): void{
			var msg: ResChangeMapMessage = ResChangeMapMessage(this.message);
			//TODO 添加消息处理
		}
	}
}