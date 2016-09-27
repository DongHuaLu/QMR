package com.game.chat.handler{

	import com.game.chat.message.RoleQueryResultMessage;
	import net.Handler;

	public class RoleQueryResultHandler extends Handler {
	
		public override function action(): void{
			var msg: RoleQueryResultMessage = RoleQueryResultMessage(this.message);
			//TODO 添加消息处理
		}
	}
}