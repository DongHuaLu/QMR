package com.game.horse.handler{

	import com.game.horse.message.ResSkillInfoMessage;
	import net.Handler;

	public class ResSkillInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResSkillInfoMessage = ResSkillInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}