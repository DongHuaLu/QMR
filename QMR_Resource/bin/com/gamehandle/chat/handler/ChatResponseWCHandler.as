package com.game.chat.handler{

	import com.game.chat.message.ChatResponseWCMessage;
	import net.Handler;

	public class ChatResponseWCHandler extends Handler {
	
		public override function action(): void{
			var msg: ChatResponseWCMessage = ChatResponseWCMessage(this.message);
			//TODO 添加消息处理
		}
	}
}