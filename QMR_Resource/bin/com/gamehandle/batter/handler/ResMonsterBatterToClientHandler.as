package com.game.batter.handler{

	import com.game.batter.message.ResMonsterBatterToClientMessage;
	import net.Handler;

	public class ResMonsterBatterToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMonsterBatterToClientMessage = ResMonsterBatterToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}