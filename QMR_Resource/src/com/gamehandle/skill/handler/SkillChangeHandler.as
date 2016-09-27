package com.game.skill.handler{

	import com.game.skill.message.SkillChangeMessage;
	import net.Handler;

	public class SkillChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: SkillChangeMessage = SkillChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}