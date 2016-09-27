package com.game.biwudao.handler{

	import com.game.biwudao.message.ResBiWuDaoSurplusTimeToClientMessage;
	import net.Handler;

	public class ResBiWuDaoSurplusTimeToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResBiWuDaoSurplusTimeToClientMessage = ResBiWuDaoSurplusTimeToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}