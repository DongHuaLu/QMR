package com.game.pet.handler{

	import com.game.pet.message.ResPetSkillChangeMessage;
	import net.Handler;

	public class ResPetSkillChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPetSkillChangeMessage = ResPetSkillChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}