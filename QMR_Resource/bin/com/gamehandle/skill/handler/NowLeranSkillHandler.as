package com.game.skill.handler{

	import com.game.skill.message.NowLeranSkillMessage;
	import net.Handler;

	public class NowLeranSkillHandler extends Handler {
	
		public override function action(): void{
			var msg: NowLeranSkillMessage = NowLeranSkillMessage(this.message);
			//TODO 添加消息处理
		}
	}
}