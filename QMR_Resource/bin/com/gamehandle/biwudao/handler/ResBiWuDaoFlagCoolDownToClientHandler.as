package com.game.biwudao.handler{

	import com.game.biwudao.message.ResBiWuDaoFlagCoolDownToClientMessage;
	import net.Handler;

	public class ResBiWuDaoFlagCoolDownToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResBiWuDaoFlagCoolDownToClientMessage = ResBiWuDaoFlagCoolDownToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}