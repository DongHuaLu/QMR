package com.game.prompt.handler{

	import com.game.prompt.message.PersonalNoticeWorldMessage;
	import net.Handler;

	public class PersonalNoticeWorldHandler extends Handler {
	
		public override function action(): void{
			var msg: PersonalNoticeWorldMessage = PersonalNoticeWorldMessage(this.message);
			//TODO 添加消息处理
		}
	}
}