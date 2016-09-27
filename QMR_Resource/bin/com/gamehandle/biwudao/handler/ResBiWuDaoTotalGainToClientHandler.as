package com.game.biwudao.handler{

	import com.game.biwudao.message.ResBiWuDaoTotalGainToClientMessage;
	import net.Handler;

	public class ResBiWuDaoTotalGainToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResBiWuDaoTotalGainToClientMessage = ResBiWuDaoTotalGainToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}