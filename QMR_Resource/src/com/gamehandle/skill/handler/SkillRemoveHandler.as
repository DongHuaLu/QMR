package com.game.skill.handler{

	import com.game.skill.message.SkillRemoveMessage;
	import net.Handler;

	public class SkillRemoveHandler extends Handler {
	
		public override function action(): void{
			var msg: SkillRemoveMessage = SkillRemoveMessage(this.message);
			//TODO 添加消息处理
		}
	}
}