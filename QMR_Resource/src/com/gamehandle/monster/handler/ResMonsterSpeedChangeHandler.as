package com.game.monster.handler{

	import com.game.monster.message.ResMonsterSpeedChangeMessage;
	import net.Handler;

	public class ResMonsterSpeedChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMonsterSpeedChangeMessage = ResMonsterSpeedChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}