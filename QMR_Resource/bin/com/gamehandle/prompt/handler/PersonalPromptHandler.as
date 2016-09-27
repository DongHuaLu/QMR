package com.game.prompt.handler{

	import com.game.prompt.message.PersonalPromptMessage;
	import net.Handler;

	public class PersonalPromptHandler extends Handler {
	
		public override function action(): void{
			var msg: PersonalPromptMessage = PersonalPromptMessage(this.message);
			//TODO 添加消息处理
		}
	}
}