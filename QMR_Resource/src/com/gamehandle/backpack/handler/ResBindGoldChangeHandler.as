package com.game.backpack.handler{

	import com.game.backpack.message.ResBindGoldChangeMessage;
	import net.Handler;

	public class ResBindGoldChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResBindGoldChangeMessage = ResBindGoldChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}