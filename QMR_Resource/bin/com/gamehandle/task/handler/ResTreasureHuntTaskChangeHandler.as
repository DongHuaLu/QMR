package com.game.task.handler{

	import com.game.task.message.ResTreasureHuntTaskChangeMessage;
	import net.Handler;

	public class ResTreasureHuntTaskChangeHandler extends Handler {
	
		public override function action(): void{
			var msg: ResTreasureHuntTaskChangeMessage = ResTreasureHuntTaskChangeMessage(this.message);
			//TODO 添加消息处理
		}
	}
}