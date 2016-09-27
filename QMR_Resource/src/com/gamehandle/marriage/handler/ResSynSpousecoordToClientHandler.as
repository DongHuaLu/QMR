package com.game.marriage.handler{

	import com.game.marriage.message.ResSynSpousecoordToClientMessage;
	import net.Handler;

	public class ResSynSpousecoordToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResSynSpousecoordToClientMessage = ResSynSpousecoordToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}