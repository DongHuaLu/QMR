package com.game.map.handler{

	import com.game.map.message.ResEnterMapMessage;
	import net.Handler;

	public class ResEnterMapHandler extends Handler {
	
		public override function action(): void{
			var msg: ResEnterMapMessage = ResEnterMapMessage(this.message);
			//TODO 添加消息处理
		}
	}
}