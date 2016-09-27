package com.game.shop.handler{

	import com.game.shop.message.ResRebuySuccessMessage;
	import net.Handler;

	public class ResRebuySuccessHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRebuySuccessMessage = ResRebuySuccessMessage(this.message);
			//TODO 添加消息处理
		}
	}
}