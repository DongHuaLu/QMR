package com.game.maze.handler{

	import com.game.maze.message.ResRewardMessage;
	import net.Handler;

	public class ResRewardHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRewardMessage = ResRewardMessage(this.message);
			//TODO 添加消息处理
		}
	}
}