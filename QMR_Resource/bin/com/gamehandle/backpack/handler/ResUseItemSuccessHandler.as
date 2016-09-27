package com.game.backpack.handler{

	import com.game.backpack.message.ResUseItemSuccessMessage;
	import net.Handler;

	public class ResUseItemSuccessHandler extends Handler {
	
		public override function action(): void{
			var msg: ResUseItemSuccessMessage = ResUseItemSuccessMessage(this.message);
			//TODO 添加消息处理
		}
	}
}