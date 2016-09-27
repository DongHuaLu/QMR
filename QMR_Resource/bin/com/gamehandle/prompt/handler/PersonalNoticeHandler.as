package com.game.prompt.handler{

	import com.game.prompt.message.PersonalNoticeMessage;
	import net.Handler;

	public class PersonalNoticeHandler extends Handler {
	
		public override function action(): void{
			var msg: PersonalNoticeMessage = PersonalNoticeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}