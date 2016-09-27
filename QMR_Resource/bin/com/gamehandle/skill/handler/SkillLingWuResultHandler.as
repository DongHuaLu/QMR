package com.game.skill.handler{

	import com.game.skill.message.SkillLingWuResultMessage;
	import net.Handler;

	public class SkillLingWuResultHandler extends Handler {
	
		public override function action(): void{
			var msg: SkillLingWuResultMessage = SkillLingWuResultMessage(this.message);
			//TODO 添加消息处理
		}
	}
}