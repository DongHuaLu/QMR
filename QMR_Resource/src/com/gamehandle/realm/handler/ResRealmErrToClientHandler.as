package com.game.realm.handler{

	import com.game.realm.message.ResRealmErrToClientMessage;
	import net.Handler;

	public class ResRealmErrToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRealmErrToClientMessage = ResRealmErrToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}