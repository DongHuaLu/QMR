package com.game.task.handler{

	import com.game.task.message.ResTargetMonsterChangeMessage;
	import net.Handler;

	public class ResTargetMonsterChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTargetMonsterChangeMessage = ResTargetMonsterChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}