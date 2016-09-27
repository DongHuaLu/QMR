package com.game.guild.handler{

	import com.game.guild.message.ResGuildChangeBulletinToClientMessage;
	import net.Handler;

	public class ResGuildChangeBulletinToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildChangeBulletinToClientMessage = ResGuildChangeBulletinToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}