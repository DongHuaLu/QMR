package com.game.backpack.handler{

	import com.game.backpack.message.ResMoneyChangeMessage;
	import net.Handler;

	public class ResMoneyChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMoneyChangeMessage = ResMoneyChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}