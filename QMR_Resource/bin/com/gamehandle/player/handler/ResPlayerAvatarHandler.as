package com.game.player.handler{

	import com.game.player.message.ResPlayerAvatarMessage;
	import net.Handler;

	public class ResPlayerAvatarHandler extends Handler {
	
		public override function action(): void{
			var msg: ResPlayerAvatarMessage = ResPlayerAvatarMessage(this.message);
			//TODO 添加消息处理
		}
	}
}