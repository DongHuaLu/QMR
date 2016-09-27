package com.game.skill.handler{

	import com.game.skill.message.SkillInfosMessage;
	import net.Handler;

	public class SkillInfosHandler extends Handler {
	
		public override function action(): void{
			var msg: SkillInfosMessage = SkillInfosMessage(this.message);
			//TODO 添加消息处理
		}
	}
}