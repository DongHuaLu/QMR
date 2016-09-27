package com.game.prompt.handler{

	import com.game.prompt.message.PersonalPromptWorldMessage;
	import net.Handler;

	public class PersonalPromptWorldHandler extends Handler {
	
		public override function action(): void{
			var msg: PersonalPromptWorldMessage = PersonalPromptWorldMessage(this.message);
			//TODO 添加消息处理
		}
	}
}