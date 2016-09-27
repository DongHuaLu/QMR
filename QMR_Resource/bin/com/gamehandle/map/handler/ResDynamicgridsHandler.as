package com.game.map.handler{

	import com.game.map.message.ResDynamicgridsMessage;
	import net.Handler;

	public class ResDynamicgridsHandler extends Handler {
	
		public override function action(): void{
			var msg: ResDynamicgridsMessage = ResDynamicgridsMessage(this.message);
			//TODO 添加消息处理
		}
	}
}