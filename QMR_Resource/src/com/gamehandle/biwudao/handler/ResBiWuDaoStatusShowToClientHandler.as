package com.game.biwudao.handler{

	import com.game.biwudao.message.ResBiWuDaoStatusShowToClientMessage;
	import net.Handler;

	public class ResBiWuDaoStatusShowToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResBiWuDaoStatusShowToClientMessage = ResBiWuDaoStatusShowToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}