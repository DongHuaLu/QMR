package com.game.team.handler{

	import com.game.team.message.ResApplyClientMessage;
	import net.Handler;

	public class ResApplyClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResApplyClientMessage = ResApplyClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}