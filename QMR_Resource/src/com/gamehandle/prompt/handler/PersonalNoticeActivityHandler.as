package com.game.prompt.handler{

	import com.game.prompt.message.PersonalNoticeActivityMessage;
	import net.Handler;

	public class PersonalNoticeActivityHandler extends Handler {
	
		public override function action(): void{
			var msg: PersonalNoticeActivityMessage = PersonalNoticeActivityMessage(this.message);
			//TODO 添加消息处理
		}
	}
}