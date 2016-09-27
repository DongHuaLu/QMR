package com.game.prompt.handler{

	import com.game.prompt.message.NonagePromptMessage;
	import net.Handler;

	public class NonagePromptHandler extends Handler {
	
		public override function action(): void{
			var msg: NonagePromptMessage = NonagePromptMessage(this.message);
			//TODO 添加消息处理
		}
	}
}