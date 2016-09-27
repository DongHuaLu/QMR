package com.game.dazuo.handler{

	import com.game.dazuo.message.ResDazuoEruptMessage;
	import net.Handler;

	public class ResDazuoEruptHandler extends Handler {
	
		public override function action(): void{
			var msg: ResDazuoEruptMessage = ResDazuoEruptMessage(this.message);
			//TODO 添加消息处理
		}
	}
}