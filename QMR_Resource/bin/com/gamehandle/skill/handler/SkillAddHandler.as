package com.game.skill.handler{

	import com.game.skill.message.SkillAddMessage;
	import net.Handler;

	public class SkillAddHandler extends Handler {
	
		public override function action(): void{
			var msg: SkillAddMessage = SkillAddMessage(this.message);
			//TODO 添加消息处理
		}
	}
}