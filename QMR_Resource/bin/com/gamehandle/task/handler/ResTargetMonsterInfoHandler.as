package com.game.task.handler{

	import com.game.task.message.ResTargetMonsterInfoMessage;
	import net.Handler;

	public class ResTargetMonsterInfoHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTargetMonsterInfoMessage = ResTargetMonsterInfoMessage(this.message);
			//TODO 添加消息处理
		}
	}
}