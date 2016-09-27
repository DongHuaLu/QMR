package com.game.amulet.handler{

	import com.game.amulet.message.ResAmuletSetSkillMessage;
	import net.Handler;

	public class ResAmuletSetSkillHandler extends Handler {
	
		public override function action(): void{
			var msg: ResAmuletSetSkillMessage = ResAmuletSetSkillMessage(this.message);
			//TODO 添加消息处理
		}
	}
}