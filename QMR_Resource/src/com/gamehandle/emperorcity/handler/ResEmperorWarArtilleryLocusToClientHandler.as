package com.game.emperorcity.handler{

	import com.game.emperorcity.message.ResEmperorWarArtilleryLocusToClientMessage;
	import net.Handler;

	public class ResEmperorWarArtilleryLocusToClientHandler extends Handler {
	
		public override function action(): void{
			var msg: ResEmperorWarArtilleryLocusToClientMessage = ResEmperorWarArtilleryLocusToClientMessage(this.message);
			//TODO 添加消息处理
		}
	}
}