package com.game.realm.handler{

	import com.game.realm.message.ResIntensifyToClientMessage;
	import net.Handler;

	public class ResIntensifyToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResIntensifyToClientMessage = ResIntensifyToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}