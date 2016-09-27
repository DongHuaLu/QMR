package com.game.shop.handler{

	import com.game.shop.message.ResSaleSuccessMessage;
	import net.Handler;

	public class ResSaleSuccessHandler extends Handler {
	
		public override function action(): void{
			var msg: ResSaleSuccessMessage = ResSaleSuccessMessage(this.message);
			//TODO 添加消息处理
		}
	}
}