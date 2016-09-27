package com.game.login.handler{

	import com.game.login.message.ResDeleteSuccessMessage;
	import net.Handler;

	public class ResDeleteSuccessHandler extends Handler {
	
		public override function action(): void{
			var msg: ResDeleteSuccessMessage = ResDeleteSuccessMessage(this.message);
			//TODO 添加消息处理
		}
	}
}