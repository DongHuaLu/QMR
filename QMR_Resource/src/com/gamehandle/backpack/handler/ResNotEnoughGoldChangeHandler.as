package com.game.backpack.handler{

	import com.game.backpack.message.ResNotEnoughGoldChangeMessage;
	import net.Handler;

	public class ResNotEnoughGoldChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResNotEnoughGoldChangeMessage = ResNotEnoughGoldChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}