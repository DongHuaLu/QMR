package com.game.realm.handler{

	import com.game.realm.message.ResRealmInfoToClientMessage;
	import net.Handler;

	public class ResRealmInfoToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResRealmInfoToClientMessage = ResRealmInfoToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}