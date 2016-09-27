package com.game.batter.handler{

	import com.game.batter.message.ResMomentKillToClientMessage;
	import net.Handler;

	public class ResMomentKillToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMomentKillToClientMessage = ResMomentKillToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}