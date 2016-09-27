package com.game.horse.handler{

	import com.game.horse.message.ResOpenSkillUpPanelMessage;
	import net.Handler;

	public class ResOpenSkillUpPanelHandler extends Handler {
	
		public override function action(): void{
			var msg: ResOpenSkillUpPanelMessage = ResOpenSkillUpPanelMessage(this.message);
			//TODO 添加消息处理
		}
	}
}