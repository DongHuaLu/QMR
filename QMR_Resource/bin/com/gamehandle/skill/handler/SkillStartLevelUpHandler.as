package com.game.skill.handler{

	import com.game.skill.message.SkillStartLevelUpMessage;
	import net.Handler;

	public class SkillStartLevelUpHandler extends Handler {
	
		public override function action(): void{
			var msg: SkillStartLevelUpMessage = SkillStartLevelUpMessage(this.message);
			//TODO 添加消息处理
		}
	}
}