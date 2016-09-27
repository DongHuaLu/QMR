package com.game.guild.handler{

	import com.game.guild.message.ResGuildApplyAddDoingToClientMessage;
	import net.Handler;

	public class ResGuildApplyAddDoingToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResGuildApplyAddDoingToClientMessage = ResGuildApplyAddDoingToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}