package com.game.monster.handler{

	import com.game.monster.message.ResMonsterDoubleNoticeMessage;
	import net.Handler;

	public class ResMonsterDoubleNoticeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMonsterDoubleNoticeMessage = ResMonsterDoubleNoticeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}