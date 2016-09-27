package com.game.maze.handler{

	import com.game.maze.message.ResChangeFloorMessage;
	import net.Handler;

	public class ResChangeFloorHandler extends Handler {
	
		public override function action(): void{
			var msg: ResChangeFloorMessage = ResChangeFloorMessage(this.message);
			//TODO 添加消息处理
		}
	}
}