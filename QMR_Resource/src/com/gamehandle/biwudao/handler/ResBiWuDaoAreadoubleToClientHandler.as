package com.game.biwudao.handler{

	import com.game.biwudao.message.ResBiWuDaoAreadoubleToClientMessage;
	import net.Handler;

	public class ResBiWuDaoAreadoubleToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResBiWuDaoAreadoubleToClientMessage = ResBiWuDaoAreadoubleToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}