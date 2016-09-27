package com.game.marriage.handler{

	import com.game.marriage.message.ResGetSpouseSkillToClientMessage;
	import net.Handler;

	public class ResGetSpouseSkillToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGetSpouseSkillToClientMessage = ResGetSpouseSkillToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}