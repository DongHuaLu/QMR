package com.game.backpack.handler{

	import com.game.backpack.message.ResGoldChangeMessage;
	import net.Handler;

	public class ResGoldChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGoldChangeMessage = ResGoldChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}