package com.game.team.handler{

	import com.game.team.message.ResMapSearchPlayerInfoClientMessage;
	import net.Handler;

	public class ResMapSearchPlayerInfoClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResMapSearchPlayerInfoClientMessage = ResMapSearchPlayerInfoClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}