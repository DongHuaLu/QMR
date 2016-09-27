package com.game.chat.handler{

	import com.game.chat.message.ChatResponseMessage;
	import net.Handler;

	public class ChatResponseHandler extends Handler {
	
		public override function action(): void{
			var msg: ChatResponseMessage = ChatResponseMessage(this.message);
			//TODO 添加消息处理
		}
	}
}