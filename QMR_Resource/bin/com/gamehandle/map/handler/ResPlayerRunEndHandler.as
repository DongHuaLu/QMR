package com.game.map.handler{

	import com.game.map.message.ResPlayerRunEndMessage;
	import net.Handler;

	public class ResPlayerRunEndHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerRunEndMessage = ResPlayerRunEndMessage(this.message);
			//TODO 添加消息处理
		}
	}
}