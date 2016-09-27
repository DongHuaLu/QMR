package com.game.task.handler{

	import com.game.task.message.ResTargetMonsterMessage;
	import net.Handler;

	public class ResTargetMonsterHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTargetMonsterMessage = ResTargetMonsterMessage(this.message);
			//TODO 添加消息处理
		}
	}
}