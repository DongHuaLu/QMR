package com.game.biwudao.handler{

	import com.game.biwudao.message.ResBiWuDaoGuildnameToClientMessage;
	import net.Handler;

	public class ResBiWuDaoGuildnameToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResBiWuDaoGuildnameToClientMessage = ResBiWuDaoGuildnameToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}